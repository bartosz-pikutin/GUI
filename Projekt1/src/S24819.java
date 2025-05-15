import java.util.Scanner;

public class S24819 {
    public static void main(String[] args) {

        System.out.println("Welcome to the Java collection tester!");
        System.out.println("What type of data do you want to generate?\n");
        System.out.println("1. Int" + "\n2. Double" + "\n3. Person (birth year and name)" + "\n4. MyColor 9 R, G, B and Sum)" +
                "\n5.Car (make, model)" + "\n6.Smth\n"); //do wymyślenia

        dataType selectedDataType = null;
        while (selectedDataType == null) {
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine().trim().toUpperCase();

            selectedDataType = switch (choice) {
                case "1", "INT" -> dataType.INT;
                case "2", "DOUBLE" -> dataType.DOUBLE;
                case "3", "PERSON" -> dataType.PERSON;
                case "4", "MYCOLOR" -> dataType.MYCOLOR;
                case "5", "CAR" -> dataType.CAR;
                case "6", "SMTH" -> dataType.SMTH;
                default -> {
                    System.out.println("Invalid input. Try again.");
                    yield null;
                }
            };
        }

        System.out.println("Choose a number of data to be generated: \n1. 100\n2. 500\n3. 1000\n4. 10000\n5. other value");
    }
}


enum dataType{
    INT,DOUBLE,PERSON,MYCOLOR, CAR, SMTH;
}

enum collectionType {
    ARRAYLIST, LINKEDLIST, HASHSET, TREESET;
}

enum testType {
    INDEX, DELADDFREQ, FIND, EXISTS;
}

enum dataPresentation {
    CLI, CSV;
}
