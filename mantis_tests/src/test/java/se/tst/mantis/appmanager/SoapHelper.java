package se.tst.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import se.tst.mantis.model.Issue;
import se.tst.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws ServiceException, MalformedURLException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        //список проектов, к которым пользователь имеет доступ
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        //преобразуем полученные данные в модельные объекты
        return Arrays.asList(projects).stream().map((p) -> new Project()
                .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL("http://localhost:8080/mantisbt-2.21.1/api/soap/mantisconnect.php"));
    }

    //public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    //    MantisConnectPortType mc = getMantisConnect(); //открываем соединение
        IssueData issueData = new IssueData();
       // IssueData.setSummary(issue.getSummary());
       // IssueData.setDescription(issue.getDescription());

    //    mc.mc_issue_add("administrator", "root", issueData);

  //  }
}
