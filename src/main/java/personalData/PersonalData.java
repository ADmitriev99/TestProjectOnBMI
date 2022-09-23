package personalData;

public class PersonalData {

    protected String name;
    protected String surname;
    protected int height;
    protected int weight;

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        if (this == null) return "Personal Data is NULL";
        else {
            String toReturn = "";
            toReturn = "Name: " + this.name + "\t";
            toReturn = toReturn + "Surname: " + this.surname + "\t";
            toReturn = toReturn + "Weight: " + this.weight + "\t";
            toReturn = toReturn + "Height: " + this.height + "\t";
            return toReturn;
        }
    }

    public PersonalData() {
        this.height = 0;
        this.weight = 0;
        this.name = "";
        this.surname = "";
    }

    public PersonalData(String name, String surname, int height, int weight) {
        this.surname = surname;
        this.weight = weight;
        this.name = name;
        this.height = height;
    }
}
