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

    @Override
    public BillingData getListOfBillingsForOccupation() {
        BillingData bills = new BillingData();
        bills.addBill(Bill.physicalTherapistBill("Standard procedure", 17.50, null));
        bills.addBill(Bill.physicalTherapistBill("Taping", 25.00, null));
        bills.addBill(Bill.physicalTherapistBill("Mobilization", 25.00, null));
        bills.addBill(Bill.physicalTherapistBill("Massage", 25.00, null));
        bills.addBill(Bill.physicalTherapistBill("manual therapy", 50.00, null));
        bills.addBill(Bill.physicalTherapistBill("Dry needling", 50.00, null));
        bills.addBill(Bill.physicalTherapistBill("Usage of exercise band", 5.00, null));
        return bills;
    }
}
