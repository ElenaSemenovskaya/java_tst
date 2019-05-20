package se.tst.sandbox;

public class FirstProgram {

    public static void main(String[] args) {
      hello("Elena");
      hello("User");

      Square s = new Square(50);
      System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

      Rectangle r = new Rectangle(10, 20);
      System.out.println("Площадь прямоугольника со сторонами " + r.c + " и " + r.d + " = " + r.area());

      Point p1 = new Point (5, 5);
      Point p2 = new Point (10,10);
      System.out.println("Расстояние между точками = " + distance(p1, p2));
    }

  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");
    }
    
  public static double distance (Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2));
  }

}