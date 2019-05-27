package se.tst.addressbook.sandbox;



public class FirstProgram {

    public static void main(String[] args) {
      hello("Elena");
      hello("Alexey");
      task("2");

      Square s = new Square(50);
      System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

      Rectangle r = new Rectangle(10, 20);
      System.out.println("Площадь прямоугольника со сторонами " + r.c + " и " + r.d + " = " + r.area());

      Point p1 = new Point (5, 5);
      Point p2 = new Point (3,10);
      System.out.println("Расстояние между точками с координатами " + "(" + p1.x + ";" + p1.y + ")" + ";" + "(" + p2.x + ";" + p2.y + ")" + " = " + p1.distance(p2));
    }

  public static void hello(String Text){
    System.out.println("Hello, " + Text + "!");

    }
   public static void task (String Text) {
     System.out.println("Решение задания № " + Text);
   }


}