package dbaseexer;

import java.sql.*;
import myioutils.MyIOUtils;
import mypostgre.MyPostGreSqlClass;


public class BasicSqlDemo extends MyPostGreSqlClass {

    BasicSqlDemo(String configFilePath) {
        super(configFilePath);
    }

    public void runDemo(){
        MyIOUtils.printlnBlueText(this.toString());

        try {
            System.out.println(this.conn.toString() + " is closed = " + this.conn.isClosed());
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }

        try (Statement statement = getStatementScrollable()){
            String sql = "UPDATE family SET first_name = 'Gregory' WHERE dob = '12/17/1964'";
            System.out.println(statement.executeUpdate(sql) + " record updated with executeUpdate() ");

            String sql2 = "SELECT * FROM family WHERE last_name <> 'Mazenko'";
            ResultSet resultSet = statement.executeQuery(sql2);

            System.out.println("executeQuery produces the following result:");
            printScrollableResultSet(resultSet);
            resultSet.close();

        } catch (SQLException sqlException) {
            System.out.println("from the catch block");
            sqlException.printStackTrace();
        }
        System.out.println("End of demo");
    }
}
