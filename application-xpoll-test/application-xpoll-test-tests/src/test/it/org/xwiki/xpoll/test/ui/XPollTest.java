package org.xwiki.xpoll.test.ui;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.xwiki.panels.test.po.ApplicationsPanel;
import org.xwiki.test.ui.AbstractTest;
import org.xwiki.test.ui.SuperAdminAuthenticationRule;
import org.xwiki.test.ui.po.ViewPage;
import org.xwiki.xpoll.test.po.ActiveStatusViewPage;
import org.xwiki.xpoll.test.po.InPreparationStatusViewPage;
import org.xwiki.xpoll.test.po.XPollEditPage;
import org.xwiki.xpoll.test.po.XPollHomePage;

/**
 * @version $Id$
 * @since 1.6.4
 */

public class XPollTest extends AbstractTest
{
    @Rule
    public SuperAdminAuthenticationRule authenticationRule = new SuperAdminAuthenticationRule(getUtil(), getDriver());

    public static final String pollName = "Poll 1";

    public static final String pollDescription = "Poll 1 Description";

    public static final String pollProposals = "1,2,3,4,5";

    public ArrayList<String> proposals = new ArrayList<String>(Arrays.asList(pollProposals.split(",")));

    @Test
    public void appEntryRedirectsToHomePage()
    {
        ApplicationsPanel applicationPanel = ApplicationsPanel.gotoPage();
        ViewPage vp = applicationPanel.clickApplication("Polls");
        Assert.assertEquals(XPollHomePage.getSpace(), vp.getMetaDataValue("space"));
        Assert.assertEquals(XPollHomePage.getPage(), vp.getMetaDataValue("page"));

    }

    @Test
    public void createNewEntryWithInPreparationStatus()
    {
        XPollHomePage xpollHomePage = XPollHomePage.gotoPage();
        xpollHomePage.clickAddNewEntryButton();
        xpollHomePage.setAddPollEntryInput(pollName);

        XPollEditPage xpollEditPage = xpollHomePage.clickAddPollEntryButton();
        Assert.assertEquals(pollName, xpollEditPage.getName());
        xpollEditPage.setDescription(pollDescription);
        xpollEditPage.setStatusInPreparation();
        xpollEditPage.setProposals(pollProposals);
        xpollEditPage.clickSaveAndView();

        InPreparationStatusViewPage inPreparationStatusViewPage = new InPreparationStatusViewPage();
        Assert.assertEquals(pollDescription, inPreparationStatusViewPage.getPollDescription());
        Assert.assertEquals(xpollEditPage.getStatusInPreparation(), inPreparationStatusViewPage.getPollStatus());

    }

    @Test
    public void createNewEntryWithActiveStatus()
    {
        XPollHomePage xpollHomePage = XPollHomePage.gotoPage();
        xpollHomePage.clickAddNewEntryButton();
        xpollHomePage.setAddPollEntryInput(pollName);

        XPollEditPage xpollEditPage = xpollHomePage.clickAddPollEntryButton();
        Assert.assertEquals(pollName, xpollEditPage.getName());
        xpollEditPage.setDescription(pollDescription);
        xpollEditPage.setStatusActive();
        xpollEditPage.setProposals(pollProposals);
        xpollEditPage.clickSaveAndView();

        ActiveStatusViewPage activeStatusViewPage = new ActiveStatusViewPage();
        Assert.assertEquals(pollName, activeStatusViewPage.getDocumentTitle());
        Assert.assertEquals(pollDescription, activeStatusViewPage.getDescription());

        activeStatusViewPage.getProposals();
        Assert.assertEquals(proposals, activeStatusViewPage.pollProposals);

    }
}
