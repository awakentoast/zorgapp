public class PhysicalTherapist extends User {

    public PhysicalTherapist(int id, String name) {
        super(id, name);
    }

    @Override
    public String getOccupation() {
        return "Physical Therapist";
    }

    @Override
    public boolean getMedicationEditingAuthorization() {
        return false;
    }

    @Override
    public boolean getMedicationInsightAuthorization() {
        return false;
    }
}
