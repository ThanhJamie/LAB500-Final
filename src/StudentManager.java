
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class StudentManager {

    ArrayList<Student> studentList = new ArrayList<Student>();
    FileReader in = null;
    FileWriter out = null;
    BufferedReader br = null;
    String decoyString;
    String[] listString;

    public void loadFromFile() {
        try {

            in = new FileReader("student.dat");
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                listString = line.split(";");
                Student newStudent = new Student(listString[0], listString[1]);
                addStudent(newStudent);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public boolean isIdDuplicate(String ID) {
        for (Student student : studentList) {
            if (student.ID.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public Student searchStudentByID(String ID) {
        for (Student student : studentList) {
            if (student.ID.equals(ID)) {
                return student;
            }
        }
        return null;
    }
    
     public boolean isNameDuplicate(String findName) {
        for (Student student : studentList) {
            if (student.name.equals(findName)) {
                return true;
            }
        }
        return false;
    }
    
    public Student searchStudentByName(String findname){
        for (Student student : studentList) {
            if (student.name.equals(findname)) {
                return student;
            }
        }
        return null;
    }
}
