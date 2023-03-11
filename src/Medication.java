
public class Medication {

    private final String substance;
    private final String type;
    private String dose;


    public void setDose(String dose) {
        this.dose = dose;
    }

    public Medication(String substance, String type, String dose) {
        this.substance = substance;
        this.type = type;
        this.dose = dose;
    }

    public String getDose() {
        return dose;
    }

    public String getType() {
        return type;
    }

    public String getSubstance() {
        return substance;
    }
}
