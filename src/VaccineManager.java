
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class VaccineManager {

    ArrayList<Vaccine> vaccineList = new ArrayList<Vaccine>();
    FileReader in = null;
    FileWriter out = null;
    BufferedReader br = null;
    String decoyString;
    String[] listString;

    public void loadFromFile() {
        try {
            in = new FileReader("vaccine.dat");
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                listString = line.split(";");
                Vaccine newVaccine = new Vaccine(listString[0], listString[1]);
                addVaccine(newVaccine);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addVaccine(Vaccine vaccine) {
        vaccineList.add(vaccine);
    }

    public boolean isIdDuplicate(String ID) {
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.ID.equals(ID)) {
                return true;
            }
        }
        return false;
    }
}
