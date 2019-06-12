package se.tst.addressbook.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main (String[] args) {
    String[] langs = {"Java", "Phyton", "C#", "PHP"};

    List<String> languages = Arrays.asList("Java", "Phyton", "C#", "PHP");
      

    for (String l : languages) {
      System.out.println("Очень хочу выучить " + l);
    }

  }
}
