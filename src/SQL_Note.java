/**
 * Created by Torleif on 14.03.17.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL_Note implements SQL {
    LoadDatabase db;
    ResultSet result = null;
    Statement state = null;
    ArrayList<Note> notes;

    SQL_Note(LoadDatabase db){
        this.notes = new ArrayList<>();
        this.db = db;
        this.result = db.result;
        this.state = db.state;
    }

    @Override
    public void fetch() {
        try {
            state = this.db.conn.createStatement();
            String sql = "select workout_no, " +
                    "workout_date, workout_time, " +
                    "duration, shape, performance from workout";
            result = state.executeQuery(sql);
            while (result.next()) {
                String _purpose = result.getString("purpose");
                String _tips = result.getString("tips");
                this.notes.add(new Note(_purpose, _tips));
            }
        } catch (SQLException ex) {
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }

    @Override
    public void insert(String s) {
        try {
            state = this.db.conn.createStatement();
            String sql = "INSERT INTO note VALUES " + s; // s = "(purpose, tips)"
            result = state.executeQuery(sql);
        } catch (SQLException ex) {
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }
}
