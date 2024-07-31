package ee.lhv.smoke.selectors;

public class maxMonthlyPayment {

    public static final String MAX_PAYMENT_FORM = "#max-payment";
    public static final String MAX_MONTHLY_PAYMENT_TAB = "max-payment";
    public static final String SINGLE_OWNERSHIP = "#ownership-0";
    public static final String GUARANTOR_OWNERSHIP = "#ownership-1";
    public static final String MARITAL_STATUS = "#marital-status-married";
    public static final String DEPENDENT_PERSONS = "#dependent-persons";
    public static final String MONTHLY_INCOME = "#monthly-income";
    public static final String ERROR_MESSAGE = MAX_PAYMENT_FORM + " div[class*='calculator-error']";
    public static final String APPLY_BUTTON = MAX_PAYMENT_FORM + " .payment + a";
}
