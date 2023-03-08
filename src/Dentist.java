public class Dentist extends User{
    public Dentist(int id, String name) {
        super(id, name);
    }

    @Override
    public String getOccupation() { return "Dentist"; }

    @Override
    public boolean getMedicationEditingAuthorization() {
        return false;
    }

    @Override
    public boolean getMedicationInsightAuthorization() {
        return true;
    }
}
