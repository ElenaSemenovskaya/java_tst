package se.tst.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PointTstZero {
  public void testDistanceZero (){
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);
    Assert.assertEquals(p1.distance(p2),0.0);
  }
  public void testDistanceEquals () {
    Point p1 = new Point(1,3);
    Point p2 = new Point(1,3);
    Assert.assertEquals(p1.distance(p2),0.0);
  }

  public void testNotZero (){
    Point p1 = new Point(1,3);
    Point p2 = new Point(5,6);
    Assert.assertNotEquals(p1.distance(p2),0.0);
  }
  public void tst1(){
    Point p1 = new Point(-1,-3);
    Point p2 = new Point(-5,-6);
    Assert.assertNotEquals((p2.x - p1.x),0.0);
  }
  public void tst2(){
    Point p1 = new Point(-1,-3);
    Point p2 = new Point(-5,-6);
    assert p2.distance(p1) >= 0.00;
  }
}
