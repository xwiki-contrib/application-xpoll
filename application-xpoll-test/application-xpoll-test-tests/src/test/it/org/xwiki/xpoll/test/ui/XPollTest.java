/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
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
import org.xwiki.xpoll.test.po.FinishedStatusViewPage;
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
        Assert.assertEquals(this.proposals, activeStatusViewPage.pollProposals);

    }

    @Test
    public void createNewEntryWithFinishedStatus()
    {
        XPollHomePage xpollHomePage = XPollHomePage.gotoPage();
        xpollHomePage.clickAddNewEntryButton();
        xpollHomePage.setAddPollEntryInput(pollName);

        XPollEditPage xpollEditPage = xpollHomePage.clickAddPollEntryButton();
        Assert.assertEquals(pollName, xpollEditPage.getName());
        xpollEditPage.setDescription(pollDescription);
        xpollEditPage.setStatusFinished();
        xpollEditPage.setProposals(pollProposals);
        xpollEditPage.clickSaveAndView();

        FinishedStatusViewPage finishedStatusViewPage = new FinishedStatusViewPage();
        Assert.assertEquals(pollName, finishedStatusViewPage.getDocumentTitle());
        Assert.assertEquals(pollDescription, finishedStatusViewPage.getDescription());

        finishedStatusViewPage.getProposals();
        Assert.assertEquals(this.proposals, finishedStatusViewPage.pollProposals);
    }
}
