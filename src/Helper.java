
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//helper for input data
public class Helper {

    Scanner sc = new Scanner(System.in);
    boolean isLoop = true;

    public boolean dateValidation(String date) {
        boolean valid = false;
        String pattern = "(0?[0-9]|[12][0-9]|3[01])\\/(0?[0-9]|1[0-2])\\/([0-9]{4})";

        if (date.matches(pattern)) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Date not to be lenient
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                valid = true;
            } catch (ParseException e) {
                valid = false;
            }
        }

        return valid;
    }

    int getInt(String msg) {
        sc = new Scanner(System.in);
        int result;
        do {
            try {
                System.out.print(msg);
                result = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Invalid int");
            }
        } while (true);

        return result;
    }

    String getString(String msg, String pattern) {
        sc = new Scanner(System.in);
        String result = "";
        do {
            System.out.print(msg);
            result = sc.nextLine().trim();
        } while (result.matches(pattern) || result.equals("") || result == null);

        return result;
    }

    Date getDate(String msg) {
        Date date = new Date();
        do {
            try {
                String strDate;
                do {
                    strDate = getString(msg, "/^\\d{2}/\\d{2}/\\d{4}$/");
                    boolean isValidDate = dateValidation(strDate);
                    if (isValidDate) {
                        break;
                    }
                } while (true);

                SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
                date = ft.parse(strDate);
                break;
            } catch (Exception e) {
                System.out.println("invalid date");
            }
        } while (true);
        return date;
    }

    public Date getDateAfter(Date date, int week) {
        Date result;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, week);
        result = calendar.getTime();

        return result;
    }

    public String dayToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        return ft.format(date);
    }
}
