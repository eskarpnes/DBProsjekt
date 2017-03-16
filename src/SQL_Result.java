import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by mariasoleim on 15.03.2017.
 */

public class SQL_Result{

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
                int _workout_no = result.getInt("workout_no");
                this.results.add(new Result(_description, _goal, _workload, _reps, _sets, workout_id));
            }
        } catch (SQLException ex) {
            System.out.println("Failed to fetch data from results");
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }

    //insert into result values (null, 'ultrices enim lorem ',
    // 'embrace ubiquitous e-markets', 18, 11, 8, 25, 17);

    public void insert(String descr, String goal, int workload, int reps,
                       int sets, int connected_exercise, int current_workout) {
        StringBuilder sb = new StringBuilder(0);
        sb.append("insert into result ");
        sb.append("values (null, ");
        sb.append("'"); sb.append(descr); sb.append("', ");
        sb.append("'"); sb.append(goal); sb.append("', ");
        sb.append(workload); sb.append(", ");
        sb.append(reps); sb.append(", ");
        sb.append(sets); sb.append(", ");
        sb.append(connected_exercise); sb.append(", ");
        sb.append(current_workout); sb.append(");");
        String sql = sb.toString();
        System.out.println(sql);
        try {
            state = this.db.conn.createStatement();
            if (state.executeUpdate(sql)==1)
                System.out.println("Successfully added query into result");
        } catch (SQLException ex) {
            System.out.println("Failed adding data into result");
            this.db.SQLEx(ex);
        } finally {
            this.db.close();
        }
    }


    public ArrayList<Result> getResults() {
        return this.results;
    }
}
