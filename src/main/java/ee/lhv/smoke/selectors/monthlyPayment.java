package ee.lhv.smoke.selectors;

public class monthlyPayment {

    public static final String MONTHLY_PAYMENT_FORM = "#monthly-payment";
    public static final String MONTHLY_PAYMENT_TAB = "#monthly-payment";
    public static final String PRIVATE_PERSON_ACCOUNT = "#account_type-0";
    public static final String BUSINESS_ACCOUNT = "#account_type-1";
    public static final String CAPITAL_LEASING = "#kap_rent";
    public static final String OPERATIONAL_LEASING = "#kas_rent";
    public static final String CAR_PRICE = "#price";
    public static final String VAT = "#vat_included";
    public static final String INITIAL_PERCENTAGE = "#initial_percentage";
    public static final String INITIAL_AMOUNT = "#initial";
    public static final String INTEREST_RATE = "#interest_rate";
    public static final String REMINDER_PERCENTAGE = "#reminder_percentage";
    public static final String REMINDER_AMOUNT = "#reminder";
    public static final String LOAN_PERIOD_YEARS = "[name='years']";
    public static final String LOAN_PERIOD_MONTHS = "[name='months']";
    public static final String APPLY_BUTTON = MONTHLY_PAYMENT_FORM + " .payment + a";
    public static final String SCHEDULE_BUTTON = ".payment ~ a > span";
}