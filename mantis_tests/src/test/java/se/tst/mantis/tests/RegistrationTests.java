package se.tst.mantis.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import se.tst.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

    //@BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test(enabled = true)
    public void testRegistration () throws IOException, MessagingException {
        long now = System.currentTimeMillis(); //возвращает текущее время
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);

        app.james().createUser(user, password);

        app.registartion().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); //встроенный почтовый сервер
        //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000); //внешний почтовый сервер

        //String confirmationLink = findConfirmationLink(mailMessages, email); //среди писем нужно найти то, которое отпр. на этот адрес и извлечь ссылку
        //app.registartion().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();//фильтрация сообщений по нужному адресу
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); //регулярное выражение
        return regex.getText(mailMessage.text); //возвращает кусок текста, который соот-ет построенному регулярному выражению
    }

    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}

