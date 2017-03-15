import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by mariasoleim on 15.03.2017.
 */

public class SQL_Result {

    LoadDatabase db;
    ResultSet result = null;
    Statement state = null;
    ArrayList<Result> results;

    SQL_Result(LoadDatabase db){
        this.results = new ArrayList<>();
        this.db = db;
        this.result = db.result;
        this.state = db.state;
    }

    public void fetch(int workout_id) {
        try {
            state = this.db.conn.createStatement();
            String sql = "select id, description, goal, workload, reps, sets, workout_no " +
                    "from result where workout_no = " + workout_id;
            result = state.executeQuery(sql);
            while (result.next()) {
                String _description = result.getString("description");
                String _goal = result.getString("goal");
                int _workload = result.getInt("workload");
                int _reps = result.getInt("reps");
                int _sets = result.getInt("sets");
                this.results.add(new Result(_description, _goal, _workload, _reps, _sets));
            }
        } catch (SQLException ex) {
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }

    public void insert(String s) {
        try {
            state = this.db.conn.createStatement();
            String sql = "INSERT INTO result VALUES " + s; // s = "(1, 15.03.2017, 15.15, 45, null, null)"
            result = state.executeQuery(sql);
        } catch (SQLException ex) {
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }

    public ArrayList<Result> getResults() {
        return this.results;
    }

}
