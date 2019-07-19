package se.tst.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient(); //при вызове конструктора выполняется инициализация, создается фтп клиент
    }

    public void upload (File file, String target, String backup) throws IOException { //загружает новый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode(); //пассивный режим передачи данных (техническая штука)
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException { //восстанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(backup, target);
        ftp.disconnect();
    }

}
