import java.util.*;


public class Main {
     public static void main(String[] args)
     {
         Random rd = new Random();
         Scanner sc  = new Scanner(System.in);
         int number = rd.nextInt(1, 101);
         String name;
         System.out.print("enter your name: ");
         name = sc.nextLine();
         System.out.println("your name is " + name);
         System.out.printf("the random number is %d\n", number);
         int lenght = name.length();
         char letter= name.charAt(0);
         int index = name.indexOf(" ");
         int lastindex = name.lastIndexOf(" ");
         
         System.out.printf("the length of the name is %d\n", lenght);
         System.out.println("The first letter is " + letter);
         System.out.printf("the sapce is at the %d position\n", index);
         System.out.printf("the last char is at the %d position\n", lastindex);
         
         
         name = name.toUpperCase();
         System.out.printf("%s\n", name);
         name = name.toLowerCase();
         System.out.printf("%s\n", name);

         name = name.trim();
         name = name.replace("i", "o");
         System.out.printf("%s\n", name);
        
         sc.close();
         
     }

}