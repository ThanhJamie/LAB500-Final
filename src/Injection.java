
import java.util.Date;

public class Injection {

    public String injectionID;
    public String firstPlace;
    public String secondPlace;
    public Date firstDate;
    public Date secondDate;
    public String studentID;
    public String vaccineID;

    public Injection() {
    }

    public Injection(String injectionID, String firstPlace, String secondPlace, Date firstDate, Date secondDate, String studentID, String vaccineID) {
        this.injectionID = injectionID;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public void setInjectionID(String injectionID) {
        this.injectionID = injectionID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    //check current injection is enough for adding or not
    public String checkValidInjection() {
        if (injectionID == null) {
            return "Injection ID is require";
        }
        if (firstPlace == null) {
            return "First place is require";
        }
        if (firstDate == null) {
            return "First date is require";
        }
        if (studentID == null) {
            return "Student ID is require";
        }
        if (vaccineID == null) {
            return "Vaccine ID is require";
        }
        return "checked";
    }

}
