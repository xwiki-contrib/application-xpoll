package org.xwiki.xpoll.test.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.InlinePage;
import org.openqa.selenium.support.ui.Select;

public class XPollEditPage extends InlinePage
{

    public static final String statusInPreparation = "inpreparation";

    public static final String statusActive = "active";

    public static final String statusFinished = "finished";

    @FindBy(id = "XPoll.XPollClass_0_name")
    public WebElement entryName;

    @FindBy(id = "XPoll.XPollClass_0_description")
    public WebElement entryDescription;

    @FindBy(id = "XPoll.XPollClass_0_status")
    public WebElement entryStatus;

    @FindBy(id = "XPoll.XPollClass_0_proposals")
    public WebElement entryProposals;

    /**
     * @return the poll name
     */

    public String getName()
    {
        return entryName.getAttribute("Value");
    }

    /**
     * @param name to entryName to be set
     */

    public void setName(String name)
    {
        entryName.clear();
        this.entryName.sendKeys(name);
    }

    /**
     * @param description to entryDescription to be set
     */

    public void setDescription(String description)
    {
        entryDescription.clear();
        this.entryDescription.sendKeys(description);
    }

    /**
     * @return the in preparation status
     */

    public String getStatusInPreparation()
    {
        return "In preparation";
    }

    /**
     * status in preparation to be selected
     */

    public void setStatusInPreparation()
    {
        Select select = new Select(this.entryStatus);
        select.selectByValue(statusInPreparation);
    }

    /**
     * status active to be set
     */

    public void setStatusActive()
    {
        Select select = new Select(this.entryStatus);
        select.selectByValue(statusActive);
    }

    /**
     * status finished to be set
     */

    public void setStatusFinished()
    {
        Select select = new Select(this.entryStatus);
        select.selectByValue(statusFinished);
    }

    /**
     * @param proposals to be set
     */

    public void setProposals(String proposals)
    {
        entryProposals.clear();
        this.entryProposals.sendKeys(proposals);
    }

}
