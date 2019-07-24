package se.tst.rest;

import org.testng.SkipException;

public class TestBase {

  public boolean isIssueOpen(int issueId){
    return true; //заглушка
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}

