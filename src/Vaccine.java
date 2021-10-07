
public class Vaccine {

    public String ID;
    public String name;

    public Vaccine() {
    }

    public Vaccine(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vaccine{" + "ID=" + ID + ", name=" + name + '}';
    }

}
