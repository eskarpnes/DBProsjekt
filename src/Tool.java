import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Thomas on 13.03.2017.
 */

public class Tool {

    Scanner input;

    public String getStringInput(String text ){

        this.input = new Scanner(System.in);
        System.out.println(text);
        System.out.print(">>> ");
        String value = "";
        if (this.input.hasNextLine()) {
            value = this.input.nextLine();
        }

        return value;
    }

    public Integer getIntInput(String text ){

        this.input = new Scanner(System.in);
        System.out.println(text);
        System.out.print(">>> ");
        int value = -1;
        if (this.input.hasNextLine()) {
            value = this.input.nextInt();
        }

        return value;
    }

    public void closeScanner(){

        this.input.close();
    }

    //Returns a String on the format YYYY-MM-DD
    public static String getDate(int days_back_in_time) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days_back_in_time*-1);
        int year = calendar.get(Calendar.YEAR);
        String month = "" + (calendar.get(Calendar.MONTH) + 1);
        String day = "" + calendar.get(Calendar.DAY_OF_MONTH);
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    }
}
