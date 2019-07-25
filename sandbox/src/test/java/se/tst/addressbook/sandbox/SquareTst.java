package se.tst.addressbook.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTst {

  @Test
  public void testArea(){
    Square q = new Square (50);
    Assert.assertEquals(q.area(),3500.0);
  }
}
