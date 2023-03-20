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

    @Override
    public BillingData getListOfBillings() {
        BillingData bills = new BillingData();
        bills.addBill(Bill.dentistBill("Routine check", 20.00));
        bills.addBill(Bill.dentistBill("Extraction", 30.00));
        bills.addBill(Bill.dentistBill("Fluoride treatment", 30.00));
        bills.addBill(Bill.dentistBill("Root canal treatment", 55.00));
        bills.addBill(Bill.dentistBill("Implant", 55.00));
        return bills;
    }

}
