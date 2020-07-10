package dbaseexer;

import java.sql.*;
import myioutils.MyIOUtils;
import mypostgre.MyPostGreSqlClass;

public class StoredProcDemo extends MyPostGreSqlClass {
    StoredProcDemo (String configFilePath) {
        super(configFilePath);
    }

    void runDemo(){
       MyIOUtils.printlnBlueText(this.toString());

        // execute a stored procedure from the database
        execStoredProc();

        // now effectively send it parameters by building a string
        paramsStoredProc();

        // now use a java PreparedStatement
        execPreparedStatement();
        try(Statement st = getStatementScrollable()){
            st.execute("truncate table procdemo");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        // now use a Callable Statement to execute a stored procedure
        int param1 = 32;
        int param2 = 54;
        MyIOUtils.printlnYellowText("Called stored sql function :");
        MyIOUtils.printlnYellowText("...function addints(integer, integer) returns integer...");
        MyIOUtils.printlnYellowText("with input parameters " + param1 + " and " + param2);
        MyIOUtils.printlnYellowText("returned " + callSqlAddints(param1, param2));

    }

    void execStoredProc(){
        try(Statement statement = getStatementScrollable()){
            // execute a stored procedure from the database
            String proccall = "call addrow_procdemo('Robert', 'Mazenko')";
            statement.execute(proccall);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    void paramsStoredProc(){
        try (Statement statement = getStatementScrollable()){
            String fname = "Barbara";
            String lname = "Mazenko";
            String sql2 = get_addrow_procedureString(fname, lname);
            statement.execute(sql2);

            String sql = "SELECT * FROM procdemo";
            ResultSet resultSet = statement.executeQuery(sql);

            MyIOUtils.printlnYellowText("SQL statement: " + sql + " produces the following result:");
            printScrollableResultSet(resultSet);
            resultSet.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    String get_addrow_procedureString(String firstname, String lastname){
        return "call addrow_procdemo('" +
                firstname +
                "', '" +
                lastname +
                "')";
    }

    void execPreparedStatement(){
        // note that a java PreparedStatement does not create either a sql procedure or a sql a function
        // it just caches it in the database so that it does not need to be compiled every time
        String statestr = "insert into procdemo (firstname, lastname) values (?, ?)";
        try(Statement st = getStatementScrollable();
            PreparedStatement ps = getPreparedScrollable(statestr)){
            int updateCount = 0;
            int executedCount = 0;
            String[][] names = {
                    {"Greg", "Mazenko"},
                    {"Carolyn", "Mazenko"},
                    {"Catherine", "Mazenko"},
                    {"Justin", "Mazenko"},
            };

            // names is an array of String arrays (an array of String[]). Use enhanced for loop
            for (String[] name : names) {
                for (int j = 0; j < name.length; j++) {
                    // replaces ps.setString (1, "Greg"); & ps.setString(2, "Mazenko");
                    ps.setString(j + 1, name[j]);
                }
                ps.execute();
                executedCount += 1;
                updateCount += ps.getUpdateCount();
            }

            MyIOUtils.printlnYellowText("The sql statement \"" + statestr + "\" ");
            MyIOUtils.printlnYellowText("Number times executed = " + executedCount);
            MyIOUtils.printlnYellowText("Number rows updated = " + updateCount);

            ResultSet resultSet = st.executeQuery("select * from procdemo");
            printScrollableResultSet(resultSet);
            resultSet.close();
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }

    int callSqlAddints (int param1, int param2){
        // Callable Statement executes a sql procedure (function in this case) stored in the database
        // only works if I surround the query with {} as in the below declaration
        // that seems to create a 'select * ...' statement to be executed
        String sql = "{? = call addints(?, ?)}";
        try(CallableStatement statement = this.conn.prepareCall(sql)) {
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setInt(2, param1);
            statement.setInt(3, param2);
            statement.execute();
            return statement.getInt(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }
}
