package se.tst.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import se.tst.mantis.model.Issue;
import se.tst.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
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
        //получить список проектов, к которым пользователь имеет доступ
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        //преобразуем полученные данные в модельные объекты
        return Arrays.asList(projects).stream().map((p) -> new Project()
                .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    public String getStatus(int issueId) throws ServiceException, MalformedURLException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        //получаем список запросов пользователя администратор
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        //получаем статус запроса
        return issueData.getStatus().getName();
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("web.soapUrl")));
    }


    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect(); //открываем соединение
        String[] categories = mc.mc_project_get_categories
                (app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));//получаем категорию
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
        IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);
        return new Issue()
                .withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project()
                        .withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));


    }


}
