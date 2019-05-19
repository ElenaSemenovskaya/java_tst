package se.tst.sandbox;

public class FirstProgram {

  public static void main(String[] args) {

    hello ("Elena");
    hello ("User");

    double c= 10;
    double d = 20;
    System.out.println("Площадь прямоугольника со сторонами " + c + " и " + d + "=" + area(c, d));
    }
  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");
    }

  public static double area (double a, double b) {
    return a * b;
  }
}