/**
 * Created by Thomas on 13.03.2017.
 */
public class Note {

    Tool in = new Tool();

    private String purpose, tips;

    public Note() {
        this.purpose = in.getStringInput("What was the purpose of this workout?");
        this.tips = in.getStringInput("What is the tips from this workout?");
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
