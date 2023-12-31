import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillingData {

    private final List<Bill> billings = new ArrayList<>();
    private int amountOfBills = 0;
    private double totalCostOfBillings = 0;

    public BillingData() {
        //TODO empty
    }

    public int getAmountOfBills() {
        return amountOfBills;
    }

    public void printBillingHistory(String occupation) {
        for (Bill bill : billings) {
            if (Objects.equals(occupation, bill.occupation())) {
            System.out.printf("%s  € %.2f [%s]\n", bill.procedure(), bill.price(), bill.date().toString());
            }
        }
    }

    public void printAllBillsWithIndexWithoutDate() {
        int count = 1;
        for (Bill bill : billings) {
            count += 1;
            System.out.printf("%d: %s  € %.2f\n", count, bill.procedure(), bill.price());
        }
    }

    public void addBill(Bill bill) {
        billings.add(bill);
        amountOfBills += 1;
        totalCostOfBillings += bill.price();
    }

    public Bill getBill(int index) {
        return billings.get(index - 1);
    }

    public double getTotalCostOfBillings() {
        return totalCostOfBillings;
    }

    public List<Bill> getBillings() {
        return billings;
    }
}
