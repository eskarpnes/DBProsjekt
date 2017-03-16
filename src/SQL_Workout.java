/**
 * Created by tollef on 14.03.17.
 */

import java.sql.*;
import java.util.ArrayList;

public class SQL_Workout {
    LoadDatabase db;
    ResultSet result = null;
    Statement state = null;
    ArrayList<Workout> workouts;

    SQL_Workout(LoadDatabase db){
        this.workouts = new ArrayList<>();
        this.db = db;
        this.result = db.result;
        this.state = db.state;
    }

    public ArrayList<Workout> getWorkouts(){
        return this.workouts;
    }
    

    public void fetch(String date) {
        try {
            state = this.db.conn.createStatement();
            String sql = "select workout_no, " +
                    "workout_date, workout_time, " +
                    "duration, shape, performance from workout"+ date;
            result = state.executeQuery(sql);
            while (result.next()) {
                int _num = result.getInt("workout_no");
                String _date = result.getString("workout_date");
                String _time = result.getString("workout_time");
                int _dur = result.getInt("duration");
                int _shape = result.getInt("shape");
                int _perf = result.getInt("performance");
                this.workouts.add(new Workout(_num, _date, _time, _dur, _shape, _perf));
            }
        } catch (SQLException ex) {
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }

    public void insert(String date, String time, int duration, int shape, int perf) {
        StringBuilder sb = new StringBuilder(0);
        sb.append("insert into workout ");
        sb.append("values (null, ");
        sb.append("'"); sb.append(date); sb.append("', ");
        sb.append("'"); sb.append(time); sb.append("', ");
        sb.append(duration); sb.append(",");
        sb.append(shape); sb.append(",");
        sb.append(perf); sb.append(");");
        String sql = sb.toString();
        System.out.println(sql);
        try {
            state = this.db.conn.createStatement();
            if (state.executeUpdate(sql)==1)
                System.out.println("Successfully added query into workout");
        } catch (SQLException ex) {
            System.out.println("Failed adding data into workout");
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }

    public ArrayList<Workout> getResults() {
        return this.workouts;
    }

}
