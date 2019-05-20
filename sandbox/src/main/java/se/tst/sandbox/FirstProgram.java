package se.tst.sandbox;

public class FirstProgram {

  public static void main(String[] args) {
    hello ("Elena");
    hello ("User");
    area1 (10, 20);
    area1 (30, 40);

    }
  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");
    }

  public static double area (double a, double b) {
    return a * b;
  }
  public static void area1(double c,double d) {
    System.out.println("Площадь прямоугольника со сторонами " + c + " и " + d + "=" + area(c, d));

  }


}