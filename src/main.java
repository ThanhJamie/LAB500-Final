
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class main {

    public static void main(String[] args) throws IOException {

        MenuManager menu = new MenuManager();
        StudentManager studentManager = new StudentManager();
        InjectionManager injectionManager = new InjectionManager();
        VaccineManager vaccineManager = new VaccineManager();
        Helper helper = new Helper();
        //config
        studentManager.loadFromFile();
        vaccineManager.loadFromFile();
        injectionManager.loadFromFile();
        File file = new File("injection.dat");
        //optional variable

        int userChoice;
        int isContinue;
        Injection newInjection;
        do {
            injectionManager.writeFile(file);
            System.out.println("AUTO UPDATE NEWEST INFORMATION");
            System.out.println("-------------------------------------------------------------");
            menu.printMenu();
            do {
                userChoice = menu.getUserChoice();
            } while (userChoice <= 0 || userChoice > 7);
            switch (userChoice) {
                case 1:
                    injectionManager.printAll();
                    break;
                case 2:
                    do {
                        newInjection = injectionManager.getInjection(studentManager, vaccineManager);
                        injectionManager.addInjection(newInjection);

                        do {
                            menu.printConfirmMenu("Do you want to add another student's vaccine injection?");
                            isContinue = menu.getUserChoice();
                        } while (isContinue < 1 || isContinue > 2);

                        if (isContinue == 2) {
                            break;
                        }
                    } while (true);

                    break;
                case 3:
                    if (injectionManager.isEmpty()) {
                        System.err.println("There's no injection in list");
                        break;
                    }
                    newInjection = null;
                    do {
                        String ID = helper.getString("Input injection ID you want to update : ", "");
                        newInjection = injectionManager.findInjectionByID(ID);
                        if (newInjection != null) {
                            break;
                        } else {
                            System.err.println("Injection ID dose not existed");
                        }
                    } while (true);

                    if (newInjection.firstDate != null && newInjection.secondDate != null) {
                        injectionManager.printAnInjection(newInjection);
                        System.err.println("This student has completed 2 injection");
                        break;
                    }

                    String secondPlace = helper.getString("Input second place : ", "");
                    newInjection.setSecondPlace(secondPlace);

                    Date secondDate;
                    do {
                        secondDate = helper.getDate("Input second date : ");
                        Date fourWeekAfter = helper.getDateAfter(newInjection.firstDate, 4);
                        Date twelveWeekAfter = helper.getDateAfter(newInjection.firstDate, 12);

                        if (secondDate.after(fourWeekAfter) && secondDate.before(twelveWeekAfter)) {
                            break;
                        } else {
                            System.err.println("The second date must from after 4 weeks and 12 weeks");
                        }
                    } while (true);

                    newInjection.setSecondDate(secondDate);
                    System.out.println("UPDATE SUCCESS!");

                    injectionManager.printAnInjection(newInjection);
                    break;
                case 4:
                    boolean a = true;
                    if (injectionManager.isEmpty()) {
                        System.err.println("There's no injection in list");
                        break;
                    }

                    newInjection = null;
                    do {
                        String ID = helper.getString("Input injection ID you want to detele : ", "");
                        newInjection = injectionManager.findInjectionByID(ID);
                        if (newInjection != null) {
                            break;
                        } else {
                            System.err.println("Injection ID dose not existed");
                            a = false;
                        }
                    } while (a);
                    //get user confirmation
                    if (a == true) {
                        do {
                            menu.printConfirmMenu("Do you really want to delete this student's vaccine injection?");
                            isContinue = menu.getUserChoice();
                        } while (isContinue < 1 || isContinue > 2);

                        if (isContinue == 1) {
                            injectionManager.deleteInjection(newInjection);
                            System.err.println("DELETE SUCCCESS!");
                            break;
                        }
                        System.err.println("DELETE FAILED!");
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (injectionManager.isEmpty()) {
                        System.out.println("There's no injection in list");
                        break;
                    }
                    newInjection = null;
                    //get user confirmation
                    do {
                        menu.printSearchMethodMenu();
                        isContinue = menu.getUserChoice();
                    } while (isContinue < 1 || isContinue > 2);

                    if (isContinue == 1) {
                        if (injectionManager.isEmpty()) {
                            System.err.println("There's no injection in list");
                            break;
                        }
                        newInjection = null;
                        //get user confirmation
                        boolean b = false;
                        do {
                            String ID = helper.getString("Input student ID you want to search : ", "");
                            newInjection = injectionManager.findInjectionByStudentID(ID);
                            if (newInjection != null) {
                                break;
                            } else {
                                System.err.println("Student ID dose not existed");
                                b = false;
                            }
                        } while (b);
                        try {
                            injectionManager.printAnInjection(newInjection);
                        } catch (Exception e) {
                            break;
                        }
                    }
                    if (isContinue == 2) {
                        //get input data from user
                        String key = helper.getString("Input student name you want to search : ", "");
                        //get student list
                        ArrayList<Injection> injectionList = injectionManager.findInjectionByStudentName(studentManager, key);
                        if (injectionList.isEmpty()) {
                            System.err.println("Can't find the student with that name");
                            break;
                        } else {
                            injectionManager.printWithInjectionList(injectionList);
                        }

                    }
                    break;
                case 6:
                    injectionManager.writeFile(file);
                    System.err.println("UPDATED INTO FILE");
                    break;
                case 7:
                    break;
                default:

                    break;
            }
        } while (userChoice != 7);
    }

}
