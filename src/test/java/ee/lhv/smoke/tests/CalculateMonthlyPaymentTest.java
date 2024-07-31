package ee.lhv.smoke.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ee.lhv.smoke.configuration.Constant.APPLICATION_URL;
import static ee.lhv.smoke.selectors.mainPage.ACCEPT_COOKIES;
import static ee.lhv.smoke.selectors.monthlyPayment.*;
import static ee.lhv.smoke.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateMonthlyPaymentTest extends BaseTest {

    @BeforeEach
    public void setup() {
        open(APPLICATION_URL + "#kalkulaator");
        getWebDriver().manage().window().setSize(new Dimension(1600, 900));
        clickButton(ACCEPT_COOKIES);
    }

    @AfterEach
    public void teardown() {
        getWebDriver().quit();
    }

    @Test
    public void checkPrefilledValues() {
        assertThat(getSectionTitle()).isEqualTo("Arvuta kuumakse");
        assertThat(getTabStatus(MONTHLY_PAYMENT_TAB)).isEqualTo("active");

        assertThat(getInputStatus(PRIVATE_PERSON_ACCOUNT)).isEqualTo(true);
        assertThat(getInputStatus(BUSINESS_ACCOUNT)).isEqualTo(false);
        assertThat(getInputStatus(CAPITAL_LEASING)).isEqualTo(true);
        assertThat(getInputStatus(OPERATIONAL_LEASING)).isEqualTo(false);
        assertThat(getFieldValue(CAR_PRICE)).isEqualTo("15 000");
        assertThat(getFieldValue(INITIAL_PERCENTAGE)).isEqualTo("10");
        assertThat(getFieldValue(INITIAL_AMOUNT)).isEqualTo("1500");
        assertThat(getSelectedOption(LOAN_PERIOD_YEARS)).isEqualTo("5");
        assertThat(getSelectedOption(LOAN_PERIOD_MONTHS)).isEqualTo("0");
        assertThat(getFieldValue(INTEREST_RATE)).isEqualTo("7");
        assertThat(getFieldValue(REMINDER_PERCENTAGE)).isEqualTo("25");
        assertThat(getFieldValue(REMINDER_AMOUNT)).isEqualTo("3750");
        assertThat(getCalculatorResult()).isEqualTo("214.93");
    }

    @Test
    public void monthlyPaymentAmountIsUpdatedWhenValuesAreChanged() {
        clickLabel("account_type-1");

        assertThat(getInputStatus(BUSINESS_ACCOUNT)).isEqualTo(true);
        assertThat(getCalculatorResult()).isEqualTo("166.73");

        clickLabel("kas_rent");

        assertThat(getInputStatus(OPERATIONAL_LEASING)).isEqualTo(true);
        assertThat(getInputStatus(VAT)).isEqualTo(true);
        assertThat(getCalculatorResult()).isEqualTo("205.23");

        insertInputValue(CAR_PRICE, "135000");

        assertThat(getCalculatorResult()).isEqualTo("1847.14");

        insertInputValue(INITIAL_PERCENTAGE, "25");

        assertThat(getFieldValue(INITIAL_AMOUNT)).isEqualTo("33750");
        assertThat(getCalculatorResult()).isEqualTo("1458.12");

        selectOption(LOAN_PERIOD_YEARS, "24");

        assertThat(getCalculatorResult()).isEqualTo("3145.04");

        selectOption(LOAN_PERIOD_MONTHS, "6");

        assertThat(getCalculatorResult()).isEqualTo("2581.96");

        insertInputValue(INTEREST_RATE, "1");

        assertThat(getCalculatorResult()).isEqualTo("2296.95");

        insertInputValue(REMINDER_PERCENTAGE, "10");

        assertThat(getFieldValue(REMINDER_AMOUNT)).isEqualTo("13500");
        assertThat(getCalculatorResult()).isEqualTo("2965.29");

        clickButton(APPLY_BUTTON);

        assertThat(getWebDriver().getCurrentUrl()).isEqualTo(APPLICATION_URL + "/taotlus");

        getWebDriver().navigate().back();

        clickButton(SCHEDULE_BUTTON);
        assertThat(getWebDriver().getWindowHandles().size()).isEqualTo(2);
        switchTo().window(1);
        assertThat(getWebDriver().getCurrentUrl()).contains(APPLICATION_URL + "/graafik");
    }

    private String getSectionTitle() {
        return $(By.cssSelector("#kalkulaator h2")).getText();
    }

}