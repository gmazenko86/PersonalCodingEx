package reactive;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import myioutils.MyIOUtils;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SchedulerDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        System.out.println("Available processors = " + Runtime.getRuntime().availableProcessors());

//        ConnectableObservable<Double> observable = Observable.fromArray(1.,2.,3.,4.,5.,6.,
//                7.,8.,9.,10.,11.,12.,13.,14.,15.,16.).publish();
//        observable.connect();

        // first process 2 Observables (streams) of 8 values in the main thread
        // they will be processed sequentially
        Observable<Double> obsOdd = getDblObsOdds();
        Observable<Double> obsEven = getDblObsEvens();
        processObsMain(obsOdd);
        processObsMain(obsEven);

    //TODO: add use of benchmark harness to track time?

        // now process similar Observables (streams) of 8 values
        // by using a multi threading scheduler. they will be processed concurrently
        obsOdd = getDblObsOdds();
        obsEven = getDblObsEvens();
        Disposable dispOdd = processObsThreads(obsOdd);
        Disposable dispEven = processObsThreads(obsEven);

        pauseMs(1000);
        printDispStatus(dispOdd);
        printDispStatus(dispEven);

    }

    void processObsMain(Observable<Double> observable){
        observable
            .doOnNext(this::printDouble)
            .map(this::getSqrtWithDelay)
            .forEach(this::printlnWithThreadName);
    }

    Disposable processObsThreads(Observable<Double> observable){
        // create a pool for demo purposes: to show another method
        // for creating a thread to do some of the processing
        ExecutorService pool = Executors.newFixedThreadPool(1);
        // Disposable == true if the resource has been disposed (completed processing)
        Disposable disposable = observable
            // use the thread from the pool to emit the values
            .subscribeOn(Schedulers.from(pool))
            // print the value out
            .doOnNext(this::printDouble)
            // map it to its square root and add a delay for demo purposes
            .map(this::getSqrtWithDelay)
            // use a Schedulers factory method to create a
            // thread to observe and process the values
            .observeOn(Schedulers.computation())
            // from javadocs: "Subscribes to an ObservableSource and
            // provides a callback to handle the items it emits"
            // subscribe returns a Disposable
            .subscribe(this::printlnWithThreadName);
        pool.shutdown();
        return disposable;
    }

    Double getSqrtWithDelay(Double param){
        pauseMs(100);
        return Math.sqrt(param);
    }

    void pauseMs (long ms){
        try{
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void printDouble(Double param){
        System.out.format("%4.1f", param);
        System.out.print(": ");
    }

    void printlnWithThreadName(Double param){
        LocalTime localTime = LocalTime.now();
        System.out.print(Thread.currentThread().getName() + ": ");
        System.out.format("%3.3f" , param);
        System.out.println(": " + localTime);
    }

    Observable<Double> getDblObsOdds(){
        return  Observable.fromArray(1.,3.,5.,7.,9.,11.,13.,15.);
    }

    Observable<Double> getDblObsEvens(){
        return  Observable.fromArray(2.,4.,6.,8.,10.,12.,14.,16.);
    }

    Observable<Double> getDblObsOddEven(){
        return Observable.fromArray(1.,2.,3.,4.,5.,6.,
                7.,8.,9.,10.,11.,12.,13.,14.,15.,16.);
    }

    void printDispStatus(Disposable disposable){
        System.out.println(disposable.toString() + " is disposed = " + disposable.isDisposed());
    }
}
