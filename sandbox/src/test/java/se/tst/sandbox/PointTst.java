package se.tst.sandbox;

import org.testng.annotations.Test;

@Test
public class PointTst {
  public void testDistance () {
    Point p1 = new Point(5,5);
    Point p2 = new Point(3,10);
    assert p1.distance(p2) == 5.39;
  }
}
