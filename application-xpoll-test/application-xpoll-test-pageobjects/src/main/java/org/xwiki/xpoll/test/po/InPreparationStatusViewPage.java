package org.xwiki.xpoll.test.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.ViewPage;

public class InPreparationStatusViewPage extends ViewPage
{

    @FindBy(xpath = "//div[@class = 'xform']//dd[1]")
    public WebElement pollName;

    @FindBy(xpath = "//div[@class = 'xform']//dd[2]")
    public WebElement pollDescription;

    @FindBy(xpath = "//div[@class = 'xform']//dd[3]")
    public WebElement pollStatus;

    @FindBy(xpath = "//div[@class = 'xform']//dd[4]")
    public WebElement pollProposals;

    public String getPollName()
    {
        return pollName.getText();
    }

    public String getPollDescription()
    {
        return pollDescription.getText();
    }

    public String getPollStatus()
    {
        return pollStatus.getText();
    }

    public String getPollProposals()
    {
        return pollProposals.getText();
    }

}
