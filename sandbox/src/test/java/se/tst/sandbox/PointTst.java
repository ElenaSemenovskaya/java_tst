package se.tst.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
  public class PointTst {
    public void testDistance () {
      Point p1 = new Point(5,5);
      Point p2 = new Point(3,10);
      Assert.assertEquals(p1.distance(p2),5.385164807134504);
    }

}
