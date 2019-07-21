package se.tst.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.mantis.appmanager.HttpSession;
import se.tst.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        String adminlogin = "administrator";
        String adminPsw = "root";
        app.admin().loginAdmin(adminlogin, adminPsw);
        UserData user = app.user().resetPassword();
        String new_password = app.user().settingNewPassword(user);
        HttpSession session = app.newSession();
        assertTrue(session.login(user.getUserName(), new_password));
        assertTrue(session.isLoggedInAs(user.getUserName()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
