import java.util.ArrayList;

/**
 * Created by Erlend on 13.03.2017.
 */

public class Workout {

    ArrayList<Result> results = new ArrayList<Result>();
    Tool tool = new Tool();
    Note note;
    String _date, _time;
    int wo_num, duration, shape, performance;


    public Workout () {
        this._date = tool.getDate(0);
        getUserInput();
    }

    public Workout(int num, String date, String time, int duration, int shape, int performance) {
        this.wo_num = num;
        this._date = date;
        this._time = time;
        this.duration = duration;
        this.shape = shape;
        this.performance = performance;
    }
    public void insert_sql(LoadDatabase db){
        SQL_Workout insertion = new SQL_Workout(db);
        insertion.insert(this._date,this._time,this.duration,this.shape,this.performance);
    }

    public int getWorkoutID(){
        System.out.println("Retrieving workout #"+Integer.toString(this.wo_num));
        return this.wo_num;
    }

    public void addNote(Note note) {
        this.note = note;
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public void addResults(ArrayList<Result> results) {
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

    public String getReport(){
        StringBuilder sb = new StringBuilder(0);
        sb.append(this);
        sb.append("\nIn this workout, you did the following:\n");
        for (Result r:this.results){
            sb.append(r); sb.append("\n");
        }
        return sb.toString();
    }
}
