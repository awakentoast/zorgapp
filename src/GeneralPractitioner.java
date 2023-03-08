public class GeneralPractitioner extends User {
    public GeneralPractitioner(int id, String name) {
        super(id, name);
    }

    @Override
    public String getOccupation() { return "General Practitioner"; }

    @Override
    public boolean getMedicationEditingAuthorization() {
        return true;
    }

    @Override
    public boolean getMedicationInsightAuthorization() {
        return true;
    }
}
