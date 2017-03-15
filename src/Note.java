/**
 * Created by Thomas on 13.03.2017.
 */
public class Note {

    Tool tool = new Tool();

    private String purpose, tips;

    public Note() {
        this.purpose = tool.getStringInput("What was the purpose of this workout?");
        this.tips = tool.getStringInput("What is the tips from this workout?");
    }

    public Note(String purpose, String tips){
        this.purpose = purpose;
        this.tips = tips;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getTips() {
        return tips;
    }

    @Override
    public String toString() {
        return ("Purpose: " + this.purpose
                + '\n' + "Tips: " + this.tips);
    }
}
