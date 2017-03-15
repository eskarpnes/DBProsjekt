import com.sun.corba.se.impl.orb.DataCollectorBase;

import java.sql.*;

public class LoadDatabase {
    public static final String JDBC = "com.mysql.jdbc.Driver";
    public static final String DB = "jdbc:mysql://localhost/";
    public static final String DBNAME = "TRENINGSDB";
    public static final String SSL = "&autoReconnect=true&useSSL=false";
    public static final String USER = "?user=root";
    public static final String PW = "&password=qqq";
    public static final String HIDDENPW = "&password=*************";
    Connection conn = null;
    Statement state = null;
    ResultSet result = null;
    private boolean connected = true;
    private DatabaseTables tables = null;

    public LoadDatabase() throws SQLException {
        try {
            Class.forName(JDBC).newInstance();
            System.out.println("Attempting MySQL Connection to "+DBNAME);
            this.connect();
        } catch (Exception ex) {
            this.connected = false;
            ex.printStackTrace();
            System.out.println("Failed connection.");
        }
//        System.out.println("Connected? "+this.connected);
        if (!this.connected){
            System.out.println("..Not connected");
            this.createDatabase();
            tables = new DatabaseTables(this.conn,DBNAME);
        }

    }
//    private boolean hasMissingTables(){
//        SQL_Workout w = new SQL_Workout(this);
//        try{
//            w.fetch();
//        } catch (Exception e){
//            System.out.println("Missing tables... Creating!");
//            return true;
//        }
//        return false;
//    }
    private void connect(){
        try {
            System.out.println("......");
            String connectionQuery = DB+DBNAME+USER+PW+SSL;
            System.out.println("Using url: "+DB+DBNAME+USER+HIDDENPW+SSL);
            this.conn = DriverManager.getConnection(connectionQuery);
            System.out.println("Connected to Database "+DBNAME);
        } catch (Exception ex){
            this.connected = false;
            System.out.println("Failed connection. SQLException: " + ex.getMessage());
        }
    }

    private void createDatabase(){
        System.out.println("No database found - Beginning creation process");
        try {
            Class.forName(JDBC);
            System.out.println("Connecting to new database...");
            String connectionQuery = DB+USER+PW+SSL;
            System.out.println("Using url: "+DB+USER+HIDDENPW+SSL);
            this.conn = DriverManager.getConnection(connectionQuery);
            System.out.println("Connection succeeded");
            System.out.println("Creating database, named "+DBNAME);
            this.state = this.conn.createStatement();
            PreparedStatement ps = this.conn.prepareStatement("CREATE DATABASE "+DBNAME);
            int result = ps.executeUpdate();
            System.out.println("Attempt result: "+Integer.toString(result));
            System.out.println("Successfully created the database "+this.DBNAME);
        }
        catch (ClassNotFoundException e){
            e.getMessage();
            e.printStackTrace();
            this.connect();
        }
        catch (SQLException e) {
            e.getMessage();
            this.SQLEx(e);
            this.connect();
        }
        finally{
            this.close();
            this.connect();
        }
    }

    public void SQLEx(SQLException ex){
        System.out.println("SQL Exception: " + ex.getMessage());
    }
    public void close(){
        if (result!=null){
            try{
                result.close();
            }catch(SQLException ex) {
                System.out.println("Error closing result handler...");
            }
            result = null;
        }
        if (state != null){
            try{
                state.close();
            }catch(SQLException ex){
                System.out.println("Error closing statement handler");
            }
        }
    }

}
