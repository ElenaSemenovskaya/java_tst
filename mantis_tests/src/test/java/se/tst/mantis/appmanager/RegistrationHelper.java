package se.tst.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver(); //попросить ссылку на драйвер у менеджера
    }

    public void start(String user1, String email) {
        wd.get(app.getProperty("web.baseUrl")+ "/signup_page.php");
    }
}
