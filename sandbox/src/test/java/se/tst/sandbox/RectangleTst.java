package se.tst.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTst {

  @Test
  public void testArea() {
    Rectangle r = new Rectangle(3, 4);
    Assert.assertEquals(r.area(), 12.0);
  }
}
