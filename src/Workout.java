import java.util.ArrayList;

/**
 * Created by Erlend on 13.03.2017.
 */

public class Workout {

    ArrayList<Result> results = new ArrayList<Result>();
    Tool tool = new Tool();
    Note note;
    String _date="2017-03-19", _time="12:03:00";
    int workout_id, duration=69, shape=6, performance=9;

    public Workout () {
        this._date = tool.getDate(0);
        getUserInput();
    }

    public Workout(int num, String date, String time, int duration, int shape, int performance) {
        this.workout_id = num;
        this._date = date;
        this._time = time;
        this.duration = duration;
        this.shape = shape;
        this.performance = performance;
    }
    public void setID(int id){
        this.workout_id = id;
    }
    public void insert_sql(LoadDatabase db){
        SQL_Workout insertion = new SQL_Workout(db);
        insertion.insert(this._date,this._time,this.duration,this.shape,this.performance);
    }

    public int getWorkoutID(LoadDatabase db){
        SQL_Workout getID = new SQL_Workout(db);
        return getID.getWorkoutID();
    }

    public void addNote(Note note) {
        System.out.println("Adding note to workout #"+this.workout_id);
        this.note = note;
    }

    public void addResult(Result result) {
        System.out.println("Adding "+result+"to workout #"
                +this.workout_id);
        results.add(result);
    }

    public void addResults(ArrayList<Result> results) {
        System.out.println("Adding list of results to workout #"
                +this.workout_id);
        this.results.addAll(results);
    }

    public void getUserInput() {
        this._date = tool.getStringInput("When did you do this workout? yyyy-mm-dd");
        this._time = tool.getStringInput("At what time? hh:mm:ss");
        this.duration = tool.getIntInput("Input duration in minutes");
        this.shape = tool.getIntInput("Input shape from 1-10");
        this.performance = tool.getIntInput("Input performance from 1-10");
    }

    public int getTotal() {
        int total = 0;
        for (Result result : results) {
            int load = result.getWorkload();
            int sets = result.getSets();
            int reps = result.getReps();
            total += load*sets*reps;
        }
        return total;
    }
    @Override
    public String toString() {
        return "Workout{\n" +
                "Date: "+_date + '\n' +
                "Time: "+_time + '\n' +
                "Duration (in minutes): "+duration + '\n'+
                "Shape (1-10): "+shape + '\n'+
                "Performance (1-10): "+performance +'\n'+
                "}";
    }

    public String getReport(LoadDatabase db){
        StringBuilder sb = new StringBuilder(0);
        sb.append(this);
        sb.append("\nIn this workout, you did the following:\n");
        for (Result r:this.results){
            if (r.getWorkoutID()==this.getWorkoutID(db)){
                sb.append(r); sb.append("\n");
            }
        }
        return sb.toString();
    }
}
