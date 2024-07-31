package ee.lhv.smoke.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ee.lhv.smoke.configuration.Constant.APPLICATION_URL;
import static ee.lhv.smoke.selectors.mainPage.ACCEPT_COOKIES;
import static ee.lhv.smoke.selectors.maxMonthlyPayment.*;
import static ee.lhv.smoke.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateMaximumPaymentTest extends BaseTest {

    @BeforeEach
    public void setup() {
        open(APPLICATION_URL + "#kalkulaator");
        clickButton(ACCEPT_COOKIES);
    }

    @AfterEach
    public void teardown() {
        getWebDriver().quit();
    }

    @Test
    public void checkPrefilledValues() {
        clickTab(MAX_MONTHLY_PAYMENT_TAB);

        assertThat(getTabStatus(MAX_MONTHLY_PAYMENT_TAB)).isEqualTo("active");
        assertThat(getInputStatus(SINGLE_OWNERSHIP)).isEqualTo(true);
        assertThat(getInputStatus(GUARANTOR_OWNERSHIP)).isEqualTo(false);
        assertThat(getInputStatus(MARITAL_STATUS)).isEqualTo(true);
        assertThat(getSelectedOption(DEPENDENT_PERSONS)).isEqualTo("1");
        assertThat(getFieldValue(MONTHLY_INCOME)).isEqualTo("1000");
        assertThat(getCalculatorResult(MAX_PAYMENT_FORM)).isEqualTo("271.25");
    }

    @Test
    public void monthlyPaymentAmountIsUpdatedWhenValuesAreChanged() {
        clickTab(MAX_MONTHLY_PAYMENT_TAB);
        clickLabel("marital-status-married");

        assertThat(getInputStatus(MARITAL_STATUS)).isEqualTo(false);
        assertThat(getCalculatorResult(MAX_PAYMENT_FORM)).isEqualTo("184.84");

        selectOption(DEPENDENT_PERSONS, "0");

        assertThat(getCalculatorResult(MAX_PAYMENT_FORM)).isEqualTo("357.67");

        insertInputValue(MONTHLY_INCOME, "1491");

        assertThat(getCalculatorResult(MAX_PAYMENT_FORM)).isEqualTo("668.14");

        clickButton(APPLY_BUTTON);

        assertThat(getWebDriver().getCurrentUrl()).isEqualTo(APPLICATION_URL + "/taotlus");
    }

    @Test
    public void errorMessageIsDisplayedWhenIncomeTooLow() {
        clickTab(MAX_MONTHLY_PAYMENT_TAB);

        insertInputValue(MONTHLY_INCOME, "999");

        assertThat(getCalculatorErrorMessage(ERROR_MESSAGE)).isEqualTo(
                "Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike.");
    }

    private String getCalculatorErrorMessage(String identifier) {
        return $(By.cssSelector(identifier)).getText();
    }

}