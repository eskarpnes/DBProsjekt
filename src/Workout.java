import java.util.ArrayList;

/**
 * Created by Erlend on 13.03.2017.
 */

public class Workout {

    ArrayList<Result> results = new ArrayList<Result>();
    Tool tool = new Tool();
    Note note;
    String wo_date, wo_time;
    int wo_num, duration, shape, performance, id;


    public Workout () {
        this.wo_date = tool.getDate(0);
        getUserInput();
    }

    public Workout(int wo_num, String date, String time, int duration, int shape, int performance) {
        this.wo_num = wo_num;
        this.wo_date = date;
        this.wo_time = time;
        this.duration = duration;
        this.shape = shape;
        this.performance = performance;
    }

    public void addNote() {
        this.note = new Note();
    }

    public void addResult() {
        results.add(new Result());
    }

    public void addResults(ArrayList<Result> results) {
        this.results.addAll(results);
    }

    public void getUserInput() {
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
        return "Workout{" +
                "wo_date='" + wo_date + '\'' +
                ", wo_time='" + wo_time + '\'' +
                ", wo_num=" + wo_num +
                ", duration=" + duration +
                '}';
    }
}
