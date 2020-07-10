package dbaseexer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import myioutils.MyIOUtils;
import mypostgre.MyPostGreSqlClass;

public class NorthwindBasicDemo extends MyPostGreSqlClass {

    NorthwindBasicDemo (String configFilePath) {
        super(configFilePath);
    }

    void runDemo(){
        MyIOUtils.printlnBlueText(this.toString());

        // execute a query where command is encoded in the java file
        try (Statement statement = getStatementScrollable()){
            String sql = "SELECT * FROM orders WHERE ship_city = 'Oulu' limit 5";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("executeQuery produces the following result:");
            printScrollableResultSet(resultSet);
            resultSet.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        // execute a series of queries from a .sql file
        String sqlFilePath = "src/main/resources/simple.sql";
        executeQueriesFromFile(sqlFilePath);

        System.out.println("End of demo " + this.toString());
    }



}
