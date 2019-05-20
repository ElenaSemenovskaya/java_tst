package se.tst.sandbox;

public class FirstProgram {

  public static void main(String[] args) {
    hello("Elena");
    hello("User");

    Square s = new Square(50);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

    Rectangle r = new Rectangle(10, 20);
    System.out.println("Площадь прямоугольника со сторонами " + r.c + " и " + r.d + " = " + area(r));
  }

  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");
    }

  public static double area (Rectangle r) {
    return r.c * r.d;
  }

  public static double area (Square s) {
    return s.l * s.l;
  }

}