package se.tst.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }


    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1666);
        Set<Issue> oldIssues = getIssues(); //получаем множество запросов до добавления нового
        Issue newIssue = new Issue().withSubject("Tst_issue").withDescription("Tst_description"); //создаем новый объект
        int issueId = createIssue(newIssue); //вызываем метод create
        Set<Issue> newIssues = getIssues();// получаем новый набор
        oldIssues.add(newIssue.withId(issueId)); //в старый набор добавляем созданный объект
        assertEquals(newIssues, oldIssues);
    }

    private Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=10000").asString();//авторизация + получить список /issues. багрепортов в формате json
        JsonElement parsed = new JsonParser().parse(json); //анализируем строчку, получаем json элемент
        //по ключу извлекаем нужную часть
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        //преобразование полученного issues в множество объектов типа Issue
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://bugify.stqa.ru/api/issues.json?limit=10000").asString();

        JsonElement parsed = new JsonParser().parse(json);
        //ид созданного багрепорта
        return parsed.getAsJsonObject().get("issue_id").getAsInt(); //извелкаем элемент по ключу issue_id и представляем как целое число.

    }

}
