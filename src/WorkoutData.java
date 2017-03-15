/**
 * Created by Thomas on 13.03.2017.
 */
public class WorkoutData {

    private Tool input = new Tool();

    private String time;
    private int heartRate;
    private String lat_coord;
    private String long_coord;
    private int masl;

    public WorkoutData(){

        setTime();
        setHeartRate();
        setCoordLocation();
        setMASL();

    }

    private void setTime(){

        this.time = input.getStringInput("At what time did you do the workout? [dd-mm-yy]");

    }

    private void setHeartRate(){

        this.heartRate = input.getIntInput("What was your average heart rate?");

    }

    private void setCoordLocation(){

        this.lat_coord = input.getStringInput("Your latitude coordinates? [-90 to 90]");
        this.long_coord = input.getStringInput("Your longitude coordinates? [-180 to 180]");
    }

    private void setMASL(){

        this.masl = input.getIntInput("What was the average meeter above sea level?");

    }

    public String getTime() {
        return time;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public String getLat_coord() {
        return lat_coord;
    }

    public String getLong_coord() {
        return long_coord;
    }

    public int getMasl() {
        return masl;
    }

    @Override
    public String toString() {
        return ("Time: " + this.time
                + '\n' + "Heart rate: " + Integer.toString(this.heartRate)
                + '\n' + "Location: longitude " + this.long_coord + ", latitude " + this.lat_coord
                + '\n' + "MASL: " + Integer.toString(this.masl));
    }
}
