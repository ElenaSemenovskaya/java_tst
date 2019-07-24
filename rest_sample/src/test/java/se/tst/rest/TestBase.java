package se.tst.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {

  public boolean isIssueOpen(int issueId){
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=10000").asString();
    JsonElement parsed = new JsonParser().parse(json);//анализируем строчку, получаем json элемент
    JsonElement issues = parsed.getAsJsonObject().get("issues");//по ключу issues извлекаем нужную часть (получаем нужный список)
    //преобразование полученного issues в МНОЖЕСТВО объектов типа Issue
    Set<Issue> elementIssue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    Issue issue = elementIssue.iterator().next();
    String status = issue.withId(issueId).getState_name();
    if (status.equals("Closed")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}

