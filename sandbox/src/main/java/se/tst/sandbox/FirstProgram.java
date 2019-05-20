package se.tst.sandbox;

public class FirstProgram {

  public static void main(String[] args) {
    hello("Elena");
    hello("User");

    Square s = new Square();
    s.l = 50;
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + area1(s));

    Rectangle r = new Rectangle();
    r.c = 10;
    r.d = 20;
    System.out.println("Площадь прямоугольника со сторонами " + r.c + " и " + r.d + " = " + area(r));
  }

  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");
    }

  public static double area (Rectangle r) {
    return r.c * r.d;
  }

  public static double area1 (Square s) {
    return s.l * s.l;
  }

}