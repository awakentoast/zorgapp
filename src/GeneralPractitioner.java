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

    @Override
    public BillingData getListOfBillings() {
        BillingData bills = new BillingData();
        bills.addBill(Bill.generalPractitionerBill("Consultancy", 21.50));
        bills.addBill(Bill.generalPractitionerBill("Home visit", 43.00));
        bills.addBill(Bill.generalPractitionerBill("Health checkup", 43.00));
        return bills;
    }
}
