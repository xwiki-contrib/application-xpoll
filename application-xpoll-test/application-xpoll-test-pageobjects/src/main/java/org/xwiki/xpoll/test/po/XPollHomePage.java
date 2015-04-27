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
