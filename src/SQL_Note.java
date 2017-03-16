/**
 * Created by Torleif on 14.03.17.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL_Note{
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

    public void insert(String purpose, String tips) {
        StringBuilder sb = new StringBuilder(0);
        sb.append("insert into note ");
        sb.append("values (null, ");
        sb.append("'"); sb.append(purpose); sb.append("', ");
        sb.append("'"); sb.append(tips); sb.append("');");
        String sql = sb.toString();
        System.out.println(sql);
        try {
            state = this.db.conn.createStatement();
            if (state.executeUpdate(sql)==1)
                System.out.println("Successfully added query into note");
        } catch (SQLException ex) {
            System.out.println("Failed adding data into note");
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }
}
