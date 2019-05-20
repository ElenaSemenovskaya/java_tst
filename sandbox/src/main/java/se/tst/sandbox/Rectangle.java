package se.tst.sandbox;

public class Rectangle {

  public double c;
  public double d;

  public Rectangle (double c, double d) {
    this.c = c;
    this.d = d;
  }
  public double area () {
    return this.c * this.d;
  }
}
