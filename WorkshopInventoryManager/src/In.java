/*  
 * In.java
 * In.getString()
*/
import java.util.Scanner;
import java.io.Console;

public class In {
     
     public static String getString() {
          try {//Allows input to the CMD version
               Console c = System.console();
               return c.readLine();
          } catch (NullPointerException e) {//Checks if you are running the Dr. Java Version
               Scanner c = new Scanner(System.in);
               String s = c.nextLine();
               c.close();
               return s;
          } 
     } 
     
     public static char getChar() {
          try {//Try catches are used here to catch if the inputted value isn't the right data type
               return getString().charAt(0);
          } catch (StringIndexOutOfBoundsException e) {//This try catch is only here to catch the .charAt() method, incase the string was empty
               return ' ';
          } 
     } 
     
     public static int getInt() {
          try {
               return Integer.parseInt(getString());
          } catch (NumberFormatException e) {
               return 0;
          } 
     } 
     
     public static double getDouble() {
          try {
               return Double.parseDouble(getString());
          } catch (NumberFormatException e) {
               return 0.01;
          } 
     } 
     
     public static float getFloat() {
          try {
               return Float.parseFloat(getString());
          } catch (NumberFormatException e) {
               System.err.println("\n\nError 02: Number Formatting Error @ In.getFloat()\n\n");
               return 0;
          } 
     } 
     
     public static long getLong() {
          try {
               return Long.parseLong(getString());
          } catch (NumberFormatException e) {
               System.err.println("\n\nError 02: Number Formatting Error @ In.getLong()\n\n");
               return 0;
          } 
     } 
     
     public static byte getByte() {
          try {
               byte x = Byte.parseByte(getString());
               return x;
          } catch (NumberFormatException e) {
               System.err.println("\n\nError 02: Number Formatting Error @ In.getByte()\n\n");
               return 0;
          } 
     } 
     
     public static String getString(Object s) {
          System.out.println(s);
          return getString();
     } 
     
     public static char getChar(Object s) {
          System.out.println(s);
          return getChar();
     } 
     
     public static int getInt(Object s) {
          System.out.println(s);
          return getInt();
     } 
     
     public static double getDouble(Object s) {
          System.out.println(s);
          return getDouble();
     } 
     
     public static float getFloat(Object s) {
          System.out.println(s);
          return getFloat();
     } 
     
     public static long getLong(Object s) {
          System.out.println(s);
          return getLong();
     } 
     
     public static byte getByte(Object s) {
          System.out.println(s);
          return getByte();
     } 
} 