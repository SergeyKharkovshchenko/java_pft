package ru.stqa.pft.sandbox;

/**
 * Created by Sergey on 24.04.2016.
 */
public class Equality {
  public static void main (String [] args){
    String s1 = "firefox";
    String s2 = "fire" + "fox";

    System.out.println( s1 == s2);
    System.out.println( s1.equals (s2));
  }
}
