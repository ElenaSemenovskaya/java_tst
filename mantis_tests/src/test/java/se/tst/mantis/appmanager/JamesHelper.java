package se.tst.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;

import javax.mail.Session;
import javax.mail.Store;
import java.io.InputStream;
import java.io.PrintStream;

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
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public void createUser(String name, String psw) {
        initTelnetSession(); //уст-ся соед
        write("adduser" + name + psw); //пишем команду
        String result = readUntil("User"+ name + "added"); //ждем текст
        closetelnetSession();
    }

    private void initTelnetSession() {
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

        readUntil("Login id:");
        write(login);
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

    private String readUntil(String s) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.println(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)); {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closetelnetSession() {
        write("quit");
    }

    public void deleteUser(String name) {
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + "deleted");
        closetelnetSession();
    }

    public boolean doesUserExist(String name) {
        initTelnetSession();
        write("verify " + name);
        String result = readUntil("exist");
        closetelnetSession();
        return result.trim().equals("User " + name + "exist");
    }






}
