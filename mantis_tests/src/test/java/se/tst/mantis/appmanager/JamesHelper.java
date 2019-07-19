package se.tst.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import se.tst.mantis.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {

    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailserver;

    public JamesHelper (ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());  //в самом начале создается почтовая сессия
    }

    public void createUser(String name, String psw) {
        initTelnetSession(); //уст-ся соед
        write("adduser" + name + psw); //пишем команду
        String result = readUntil("User"+ name + "added"); //ждем текст
        closeTelnetSession();
    }

    private void initTelnetSession() { //установка телнет соединения
        mailserver = app.getProperty("mailserver.host"); //получаем инфу из конф. файла
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("maiserver.adminlogin");
        String password = app.getProperty("maiserver.adminpassword");

        try {
            telnet.connect(mailserver, port); //уст. соед с почтовым сервером
            in = telnet.getInputStream(); //берем входной поток (читать то, что к нам пришло)
            out = new PrintStream( telnet.getOutputStream()); // выходной поток (отправлять команды)
        } catch (Exception e) {
            e.printStackTrace();
        }

        readUntil("Login id:"); //для чтения - тот текст, который пишет нам сервер
        write(login);  // для записи - тот текст, который мы отправляем серверу
        readUntil("Password:");
        write(password);

        //подтв. пароля
        readUntil("Login id:");
        write(login);
        readUntil("Password:");
        write(password);

        //после успешного входа
        readUntil("Welcome "+login+". HELP for list of commands");
    }

    private void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeTelnetSession() {
        write("quit");
    }

    //метод для удаления пользователя
    public void deleteUser(String name) {
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + "deleted");
        closeTelnetSession();
    }

    //метод для проверки существования пользователя
    public boolean doesUserExist(String name) {
        initTelnetSession();
        write("verify " + name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + name + "exist");
    }


    public List<MailMessage> waitForMail(String username, String password, long timeout) throws MessagingException {
        long now = System.currentTimeMillis(); // запоминаем момент начала ожидания
        while (System.currentTimeMillis() < now + timeout) {
            List<MailMessage> allMail = getAllMail(username, password);
            if (allMail.size() > 0) {
                return allMail;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }

    //извлекает сообщения из почтового ящика и превращает их в модельные объекты
    public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
        Folder inbox = openInbox(username, password); //открыть почтовый ящик
        List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
        closeFolder(inbox); //закрыть почтовый ящик
        return messages;
    }

    private void closeFolder(Folder folder) throws MessagingException {
        folder.close(true);
        store.close();
    }

    private Folder openInbox(String username, String password) throws MessagingException {
        store = mailSession.getStore("pop3"); //берем почтовую сессию и сообщаем, что будем использовать pop3
        store.connect(mailserver, username, password); //устанавливаем соед-е
        Folder folder = store.getDefaultFolder().getFolder("INBOX"); //получаем доступ к папке inbox
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    //преобразование реальных писем в модельные
    public static MailMessage toModelMail(Message m) {
        try {
            return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //позволяет удалить все письма, которые получены каким-то пользователем
    public void drainEmail(String username, String password) throws MessagingException {
        Folder inbox = openInbox(username, password);
        for (Message message : inbox.getMessages()) {
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }
}
