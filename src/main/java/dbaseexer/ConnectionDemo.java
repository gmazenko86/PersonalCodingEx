package dbaseexer;

import myioutils.MyIOUtils;
import mypostgre.MyPostGreSqlClass;

public class ConnectionDemo extends MyPostGreSqlClass {

    ConnectionDemo(String configFilePath) {
        super(configFilePath);
    }

    public void runDemo() {
        MyIOUtils.printlnBlueText(this.toString());

        this.conn = connectUsingDriverManager();
        printConnectionStatus(this.conn);
        closeConnection(this.conn);

        this.conn = connectUsingDataSource();
        printConnectionStatus(this.conn);
        closeConnection(this.conn);

        this.conn = getConnection();
        printConnectionStatus(this.conn);
        closeConnection(this.conn);
    }
}
