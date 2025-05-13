import java.util.Scanner;

public class S24819 {
    public static void main(String[] args) {

        System.out.println("Welcome to the Java collection tester!");
        System.out.println("What type of data do you want to generate?\n");
        System.out.println("1. Int" + "\n2. Double" + "\n3. Person (birth year and name)" + "\n4. MyColor 9 R, G, B and Sum)" +
                "\n5.Car (make, model)" + "\n6.Smth\n"); //do wymyślenia

        while (true) {
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            try {
                dataType type = dataType.valueOf(choice);
                break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid data type: \n");
            }
        }

        System.out.println("Choose a number of data to be generated: \n1. 100\n2. 500\n3. 1000\n4. 10000\n5. other value");
    }
}


enum dataType{
    Int,Double,Person,MyColor, Car, Smth;
}