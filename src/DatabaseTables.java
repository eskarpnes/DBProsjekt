import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * used in the creation of a new database
 * contains functions to add all missing tables
 * with proper fields and keys
 */
public class DatabaseTables {
    private int rs;
    private String sql;
    private Connection conn;
    private Statement state;
    public DatabaseTables(Connection c,String db_to_select){
        this.conn = c;
        this.sql = null;
        this.rs = 0;
        System.out.println("Loading database tables creator");
        try {
            this.state = this.conn.createStatement();
//            System.out.println("Attempting to execute: use database "+db_to_select);
//            this.rs = this.state.executeUpdate("use database "+db_to_select);
//            System.out.println(this.rs);
//            this.clear();
            this.init();
        } catch (SQLException e) {
            System.out.println("Failed to add all tables");
        }
    }
    private void clear(){
        if (sql != null){
            sql = "";
        }
        if (state != null){
            try{
                state.close();
            }catch(SQLException ex){
                System.out.println("Error closing statement handler");
            }
        }
    }

    private void apply(String query) throws SQLException {
        System.out.println("Applying query");
        this.state = this.conn.createStatement();
//        System.out.println(this.state);
        this.state.executeUpdate(query);
        this.clear();
    }
    public void init() throws SQLException {
        System.out.println("Adding tables to database");
        this.addWorkout();
        this.addNote();
        this.addResult();
        this.addExercise();
        this.addReplacement();
        this.addExerciseData();
    }
    private void addWorkout(){
        System.out.println("Creating workout table");
        sql = "CREATE TABLE workout (" +
                "workout_no INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "workout_date DATE NOT NULL," +
                "workout_time TIME NOT NULL,"+
                "duration MEDIUMINT NOT NULL,"+
                "shape TINYINT DEFAULT NULL,"+
                "performance TINYINT DEFAULT NULL)";
        try {
            this.apply(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to create workout table");
        }
    }
    private void addNote(){
        System.out.println("Creating note table");
        sql = "CREATE TABLE note(" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "purpose TEXT NOT NULL," +
                "tips TEXT NULL," +
                "FOREIGN KEY (id) REFERENCES workout(workout_no))";
        try {
            this.apply(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to create note table");
        }
    }
    private void addResult(){
        System.out.println("Creating result table");
        sql = "CREATE TABLE result(" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "description TEXT NOT NULL," +
                "goal TEXT NULL," +
                "workload INT DEFAULT NULL," +
                "reps TINYINT NOT NULL," +
                "sets TINYINT NOT NULL," +
                "connected_exercise_id INT NOT NULL,"+
                "FOREIGN KEY (id) REFERENCES workout(workout_no))";
        try {
            this.apply(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create result table");
        }

    }
    private void addExercise(){
        System.out.println("Create exercise table");
        sql = "CREATE TABLE exercise(" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(20) NOT NULL," +
                "outside TINYINT(1) NOT NULL DEFAULT '0'," +
                "temperature FLOAT DEFAULT NULL," +
                "weather VARCHAR(20) DEFAULT NULL," +
                "air_quality TINYINT(2) DEFAULT '0'," +
                "spectators INT DEFAULT '0'," +
                "cardio TINYINT(1) DEFAULT '0'," +
                "bodybuilding TINYINT(1) DEFAULT '0')";
        try {
            this.apply(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table exercise");
        }
    }
    private void addReplacement() {
        System.out.println("Create replacement table");
        sql = "CREATE TABLE exercise_replacement(" +
                "original_id INT NOT NULL," +
                "replace_id INT NOT NULL)";
        try {
            this.apply(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table exercise_replacement");
        }
    }
    private void addExerciseData() {
        System.out.println("Create exercise data table");
        sql = "CREATE TABLE exercise_data(" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "time_stamp TIME DEFAULT NULL," +
                "heart_rate INT UNSIGNED DEFAULT NULL," +
                "lat_coord FLOAT DEFAULT NULL," +
                "long_coord FLOAT DEFAULT NULL," +
                "msl FLOAT DEFAULT NULL," +
                "FOREIGN KEY (id) REFERENCES result(id))";
        try {
            this.apply(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table exercise_data");
        }
    }


}
