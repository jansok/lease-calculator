package ee.lhv.smoke.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TestUtils {

    public static void clickButton(String identifier) {
        SelenideElement button = $(By.cssSelector(identifier));
        button.click();
    }

    public static void clickLabel(String identifier) {
        SelenideElement label = $(By.cssSelector("label[for='" + identifier + "']"));
        label.click();
    }

    public static void clickTab(String identifier) {
        SelenideElement tab = $(By.cssSelector("a[href*='" + identifier + "']"));
        tab.click();
    }

    public static String getCalculatorResult() {
        return $(By.cssSelector(".payment")).getText();
    }

    public static String getCalculatorResult(String tabId) {
        return $(By.cssSelector(tabId + " .payment")).getText();
    }

    public static String getFieldValue(String fieldId) {
        return $(By.cssSelector(fieldId)).getValue();
    }

    public static boolean getInputStatus(String identifier) {
        SelenideElement element = $(By.cssSelector(identifier));
        return element.isSelected();
    }

    public static String getSelectedOption(String identifier) {
        return $(By.cssSelector(identifier)).getSelectedOption().getText();
    }

    public static String getTabStatus(String identifier) {
        return $(By.cssSelector("li:has(a[href*='" + identifier + "'])")).getAttribute("class");
    }

    public static void insertInputValue(String fieldId, String data) {
        $(By.cssSelector(fieldId)).setValue(data);
    }

    public static void selectOption(String identifier, String value) {
        SelenideElement option = $(By.cssSelector(identifier + " > option[value= '" + value + "']"));
        option.click();
    }

}