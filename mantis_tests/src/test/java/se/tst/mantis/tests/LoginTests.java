package se.tst.mantis.tests;

import org.testng.annotations.Test;
import se.tst.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root")); //проверяется, что пользователь действительно залогинился
        assertTrue(session.isLoggedInAs("administrator"));//проверка, что залогинился пользователь администратор
    }
}
