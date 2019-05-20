package se.tst.sandbox;

public class FirstProgram {

  public static void main(String[] args) {
    hello("Elena");
    hello("User");

    Square s = new Square(50);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(10, 20);
    System.out.println("Площадь прямоугольника со сторонами " + r.c + " и " + r.d + " = " + r.area());
  }

  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");
    }


}