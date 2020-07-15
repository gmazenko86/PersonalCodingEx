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

        Observable<Double> obsOdd = getDblObsOdds();
        Observable<Double> obsEven = getDblObsEvens();

        processObsMain(obsOdd);
        processObsMain(obsEven);

    //TODO: add check of disposable to shutdown threads
    //TODO: add use of benchmark harness to track time?
// ##############################################################################

        obsOdd = getDblObsOdds();
        obsEven = getDblObsEvens();

//        processObsThreads(obsOdd);
//        processObsThreads(obsEven);

        ExecutorService pool = Executors.newFixedThreadPool(1);
        ExecutorService pool1 = Executors.newFixedThreadPool(1);
        Disposable dispOdd = obsOdd
            .subscribeOn(Schedulers.computation())
            .doOnNext(this::printDouble)
            .map(this::getSqrtWithDelay)
            .observeOn(Schedulers.from(pool))
            .subscribe(this::printlnWithThreadName);
        Disposable dispEven = obsEven
            .subscribeOn(Schedulers.computation())
            .doOnNext(this::printDouble)
            .map(this::getSqrtWithDelay)
            .observeOn(Schedulers.from(pool))
            .subscribe(this::printlnWithThreadName);

        pauseMs(1000);
        pool.shutdown();
        pool1.shutdown();
    }

    void processObsMain(Observable<Double> observable){
        observable
            .doOnNext(this::printDouble)
            .map(this::getSqrtWithDelay)
            .forEach(this::printlnWithThreadName);
    }

    void processObsThreads(Observable<Double> observable){
        ExecutorService pool = Executors.newFixedThreadPool(1);
        observable
//            .subscribeOn(Schedulers.computation())
            .doOnNext(this::printDouble)
            .map(this::getSqrtWithDelay)
            .observeOn(Schedulers.from(pool))
//            .observeOn(Schedulers.computation())
            .subscribe(this::printlnWithThreadName);
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
}
