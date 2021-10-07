
import java.util.Scanner;

public class MenuManager {

    Helper helper = new Helper();

    public void printMenu() {
        System.out.println("Welcome to Vaccine Management - @ 2021 by SE150380 - Dang Chi Thanh");
        System.out.println("Select the options below :");
        System.out.println("1. Show information all students have been injected");
        System.out.println("2. Add student's vaccine injection information");
        System.out.println("3. Updating information of students' vaccine injection");
        System.out.println("4. Delete student vaccine injection information");
        System.out.println("5. Search for injection information");
        System.out.println("6. Save to file");
        System.out.println("7. Quit");
    }

    public void printSearchMethodMenu() {
        System.out.println("Select the method you want to search :");
        System.out.println("1. StudentID");
        System.out.println("2. StudentName");
    }

    public void printConfirmMenu(String msg) {
        System.out.println(msg);
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    public int getUserChoice() {
        int choice = helper.getInt("Your command : ");;
        return choice;
    }

    public void printInjectionMenu(Injection injection) {
        System.out.println("Choose one to add the properties of new Injection :");
        System.out.println("Select the options below :");
        System.out.println("1. Injection ID : " + injection.injectionID);
        System.out.println("2. First place : " + injection.firstPlace);
        System.out.println("3. Second place : " + injection.secondPlace);
        System.out.println("4. First date : " + injection.firstDate);
        System.out.println("5. Second date : " + injection.secondDate);
        System.out.println("6. Student ID : " + injection.studentID);
        System.out.println("7. Vaccine ID : " + injection.vaccineID);
        System.out.println("8. Confirm");
    }
}
