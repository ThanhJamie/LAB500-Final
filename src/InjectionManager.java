
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InjectionManager {

    ArrayList<Injection> injectionList = new ArrayList<Injection>();
    MenuManager menu = new MenuManager();
    Helper helper = new Helper();
    FileReader in = null;
    FileWriter out = null;
    BufferedReader br = null;
    String decoyString;
    String[] listString;

    public boolean isEmpty() {
        return injectionList.isEmpty();
    }

    public void loadFromFile() {
        try {
            in = new FileReader("injection.dat");
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                listString = line.split(";");
                //ID;firstPlace;secondPlace;firstDate;secondDate;studentID;vaccineID
                //injection ID
                String ID = listString[0];
                //first place
                String firstPlace = listString[1].equals("null") ? null : listString[1];
                //second place
                String secondPlace = listString[2].equals("null") ? null : listString[2];
                //first date
                Date firstDate = null;
                String firstDateString = listString[3].equals("null") ? null : listString[3];
                if (firstDateString != null) {
                    firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(firstDateString);
                }
                //second date
                Date secondDate = null;
                String secondDateString = listString[4].equals("null") ? null : listString[4];
                if (secondDateString != null) {
                    secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(secondDateString);
                }
                //student ID
                String studentID = listString[5].equals("null") ? null : listString[5];
                //vaccint ID
                String vaccineID = listString[6].equals("null") ? null : listString[6];
                //create new injection
                Injection newInjection = new Injection(ID, firstPlace, secondPlace, firstDate, secondDate, studentID, vaccineID);

                injectionList.add(newInjection);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAll() {
        if (injectionList.isEmpty()) {
            System.err.println("There's no injection!");
            return;
        }

        System.out.printf("%-5s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", "", "ID", "first place", "second place", "first date", "second date", "student ID", "vaccine ID");
        for (Injection injection : injectionList) {
            System.out.printf("%-5s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", "", injection.injectionID, injection.firstPlace, injection.secondPlace, helper.dayToString(injection.firstDate), helper.dayToString(injection.secondDate), injection.studentID, injection.vaccineID);
        }
    }

    public void printWithInjectionList(ArrayList<Injection> injectionList) {
        if (injectionList.isEmpty()) {
            System.out.println("There's no injection!");
            return;
        }

        System.out.printf("%-5s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", "", "ID", "first place", "second place", "first date", "second date", "student ID", "vaccine ID");
        for (Injection injection : injectionList) {
            System.out.printf("%-5s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", "", injection.injectionID, injection.firstPlace, injection.secondPlace, helper.dayToString(injection.firstDate), helper.dayToString(injection.secondDate), injection.studentID, injection.vaccineID);
        }
    }

    public void printAnInjection(Injection injection) {
        System.out.printf("%-5s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", "", "ID", "first place", "second place", "first date", "second date", "student ID", "vaccine ID");
        System.out.printf("%-5s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n", "", injection.injectionID, injection.firstPlace, injection.secondPlace, helper.dayToString(injection.firstDate), helper.dayToString(injection.secondDate), injection.studentID, injection.vaccineID);

    }

    public Injection getInjection(StudentManager studentManager, VaccineManager vaccineManager) {
        int userChoice;
        boolean isLoop = true;
        boolean isTrue = false;
        Injection newInjection = new Injection();

        do {
            System.out.println("------Add new injection-------------");
            menu.printInjectionMenu(newInjection);
            do {
                userChoice = menu.getUserChoice();
            } while (userChoice < 1 || userChoice > 8);

            switch (userChoice) {
                case 1:
                    String ID;
                    do {
                        ID = helper.getString("Input ID: ", "");
                        isTrue = isInjectionIdDuplicate(ID);
                        if (!isTrue) {
                            break;
                        }
                        System.err.println("This ID already existed");
                    } while (true);
                    newInjection.setInjectionID(ID);
                    break;
                case 2:
                    String firstPlace = helper.getString("Input first place: ", "");
                    newInjection.setFirstPlace(firstPlace);
                    break;
                case 3:
                    if (newInjection.firstPlace == null) {
                        System.err.println("You have to input first place to fill in this field");
                        break;
                    }

                    String secondPlace = helper.getString("Input second place: ", "");
                    newInjection.setSecondPlace(secondPlace);
                    break;
                case 4:
                    Date firstDate = helper.getDate("Input first Date: ");
                    newInjection.setFirstDate(firstDate);
                    break;
                case 5:
                    if (newInjection.getFirstDate() == null) {
                        System.err.println("You have to input first date to fill in this field");
                        break;
                    }
                    Date secondDate;
                    do {
                        secondDate = helper.getDate("Input second Date : ");
                        Date fourWeekAfter = helper.getDateAfter(newInjection.firstDate, 4);
                        Date twelveWeekAfter = helper.getDateAfter(newInjection.firstDate, 12);
                        if (secondDate.after(fourWeekAfter) && secondDate.before(twelveWeekAfter)) {
                            break;
                        } else {
                            System.err.println("Second date must be from after 4 weeks to 12 weeks");
                        }
                    } while (true);

                    newInjection.setSecondDate(secondDate);
                    break;
                case 6:
                    String studentID;
                    do {
                        System.out.println("===LIST STUDENT===");
                        System.out.println("AI01. Nguyen Van A");
                        System.out.println("AI02. Nguyen Van B");
                        System.out.println("AI03. Nguyen Van C");
                        System.out.println("AI04. Nguyen Van D");
                        System.out.println("AI05. Nguyen Van E");
                        studentID = helper.getString("Input student ID: ", "").trim().toUpperCase();
                        isTrue = studentManager.isIdDuplicate(studentID);
                        if (!isTrue) {
                            System.err.println("The student ID must be existed");
                            continue;
                        }
                        isTrue = isStudentIDDuplicate(studentID);
                        if (isTrue) {
                            System.err.println("The student id already have in injection list");
                        } else {
                            break;
                        }
                    } while (true);
                    newInjection.setStudentID(studentID);
                    break;
                case 7:
                    String vaccineID;
                    do {
                        System.out.println("===List Vaccine===");
                        System.out.println("1. Sinopharm");
                        System.out.println("2. AstraZeneca");
                        System.out.println("3. Gam-COVID-Vac");
                        System.out.println("4. Janssen");
                        System.out.println("5. Moderna");
                        System.out.println("6. Pfizer");
                        vaccineID = helper.getString("Input vaccine id : ", "");
                        isTrue = vaccineManager.isIdDuplicate(vaccineID);
                        if (isTrue) {
                            break;
                        } else {
                            System.out.println("ID of vaccine must be existed");
                        }
                    } while (true);
                    newInjection.setVaccineID(vaccineID);
                    break;
                case 8:
                    String checkingMessage = newInjection.checkValidInjection();
                    if (!checkingMessage.equals("checked")) {
                        System.out.println(checkingMessage);
                        break;
                    }
                    isLoop = false;
                    break;
                default:
                    break;
            }
        } while (isLoop);

        return newInjection;
    }

    public void addInjection(Injection injection) {
        injectionList.add(injection);
    }

    public Injection findInjectionByID(String ID) {
        for (Injection injection : injectionList) {
            if (injection.injectionID.equals(ID)) {
                return injection;
            }
        }
        return null;
    }

    public Injection findInjectionByStudentID(String ID) {
        ID = ID.trim().toUpperCase();
        for (Injection injection : injectionList) {
            if (injection.studentID.equals(ID)) {
                return injection;
            }
        }
        return null;
    }

    public ArrayList<Injection> findInjectionByStudentName(StudentManager studentManager, String name) {
        ArrayList<Injection> nameIncluded = new ArrayList<Injection>();
        name = name.trim();
        for (Injection injection : nameIncluded) {
            Student newStudent = studentManager.searchStudentByID(injection.studentID);
            if (newStudent == null) {
                continue;
            }
            if (newStudent.name.toUpperCase().contains(name)) {
                nameIncluded.add(injection);
            }
        }
        return nameIncluded;
    }

    public void updateInjection(String ID) {
    }

    public boolean isInjectionIdDuplicate(String ID) {
        for (Injection injection : injectionList) {
            if (injection.injectionID.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentIDDuplicate(String studentID) {
        for (Injection injection : injectionList) {
            if (injection.studentID.equals(studentID)) {
                return true;
            }
        }
        return false;
    }

    public void deleteInjection(Injection injection) {
        injectionList.remove(injection);
    }

    public void writeFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        String ortherString;
        String result = new String("");

        for (Injection injection : injectionList) {
            ortherString = injection.injectionID + ";" + injection.firstPlace + ";" + injection.secondPlace + ";" + helper.dayToString(injection.firstDate) + ";" + helper.dayToString(injection.secondDate) + ";" + injection.studentID + ";" + injection.vaccineID + "\n";
            result = result + ortherString;
        }
        writer.write(result);
        writer.close();
    }
}
