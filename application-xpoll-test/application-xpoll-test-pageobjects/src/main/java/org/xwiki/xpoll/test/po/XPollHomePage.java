package org.xwiki.xpoll.test.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.ViewPage;

/**
 * @version $Id$
 * @since 1.6.4
 */

public class XPollHomePage extends ViewPage
{

    @FindBy(xpath = "//div[@id = 'actionBox']//a[@class= 'action add']")
    private WebElement addNewEntryButton;

    @FindBy(xpath = "//div[@id = 'entryNamePopup']//input[@type = 'text']")
    private WebElement addPollEntryInput;

    @FindBy(xpath = "//div[@id = 'entryNamePopup']//input[@type = 'image']")
    private WebElement addPollEntryButton;

    public static XPollHomePage gotoPage()
    {
        getUtil().gotoPage(getSpace(), getPage());
        return new XPollHomePage();
    }

    public static String getPage()
    {
        return "WebHome";
    }

    public static String getSpace()
    {
        return "XPoll";
    }

    public void clickAddNewEntryButton()
    {
        addNewEntryButton.click();
    }

    public void setAddPollEntryInput(String pollName)
    {
        this.addPollEntryInput.clear();
        this.addPollEntryInput.sendKeys(pollName);
    }

    public XPollEditPage clickAddPollEntryButton()
    {
        this.addPollEntryButton.click();
        return new XPollEditPage();
    }
}
