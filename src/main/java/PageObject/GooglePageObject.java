package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePageObject {

    public GooglePageObject(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@name='q']")
    private WebElement searchItem;

    public WebElement getSearchItem()
    {
        return searchItem;
    }

    @FindBy(xpath="//input[@type='submit']")
    private WebElement search;

    public WebElement getSearch()
    {
        return search;
    }

}
