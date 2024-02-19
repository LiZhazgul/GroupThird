package com.fall23.ui.homeworks.talentLms;

import org.testng.annotations.Test;


public class ImportTest  extends BaseTest{
    ImportPage importPage=new ImportPage();


    @Test
    void enterImportPageByIcon() throws InterruptedException {
        Thread.sleep(3000);
        importPage.importExportIcon.click();

        ImportPage importPage = new ImportPage();
//        browserManager.openByGet("https://www.talentlms.com/dashboard");
//       driver.get();
        importPage.enterImportPageByIcon();
    }



}

