package se.tst.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAdmin(String adminLogin, String adminPsw) {
        app.getDriver().findElement(By.name("username")).sendKeys(adminLogin);
        click(By.cssSelector("input[value = 'Login']"));
        app.getDriver().findElement(By.name("password")).sendKeys(adminPsw);
        click(By.cssSelector("input[value = 'Login']"));
    }
}
