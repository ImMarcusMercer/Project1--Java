package Banks;

import java.util.Comparator;

public class BankComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        return Integer.compare(b1.getId(), b2.getId());
    }
}
