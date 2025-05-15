import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.*;

public class S24819Project01 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Welcome to the Java collection tester!");
        dataType selectedDataType = chooseDataType();
        int dataSize = chooseDataSize();
        collectionType selectedCollectionType = chooseCollectionType();
        testType selectedTestType = chooseTestType();
        dataPresentationType selectedDataPresentationType = chooseDataPresentationType();

        boolean confirmed = false;
        while (!confirmed) {
            dataPresenter(selectedDataType, selectedCollectionType, selectedTestType, dataSize, selectedDataPresentationType);

            System.out.println("\nMenu:");
            System.out.println("1 - Change data type");
            System.out.println("2 - Change data size");
            System.out.println("3 - Change collection type");
            System.out.println("4 - Change test type");
            System.out.println("5 - Change presentation type");
            System.out.println("0 - Confirm and proceed");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1" -> selectedDataType = chooseDataType();
                case "2" -> dataSize = chooseDataSize();
                case "3" -> selectedCollectionType = chooseCollectionType();
                case "4" -> selectedTestType = chooseTestType();
                case "5" -> selectedDataPresentationType = chooseDataPresentationType();
                case "0" -> confirmed = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static dataType chooseDataType() {
        dataType selectedDataType = null;
        while (selectedDataType == null) {
            System.out.println("What type of data do you want to generate?\n");
            System.out.println("1. Int" + "\n2. Double" + "\n3. Person (birth year and name)" + "\n4. MyColor 9 R, G, B and Sum)" +
                    "\n5.Car (make, model)" + "\n6.Book (Author, title, number of pages\n");
            String choice = sc.nextLine().trim().toUpperCase();
            selectedDataType = switch (choice) {
                case "1", "INT" -> dataType.INT;
                case "2", "DOUBLE" -> dataType.DOUBLE;
                case "3", "PERSON" -> dataType.PERSON;
                case "4", "MYCOLOR" -> dataType.MYCOLOR;
                case "5", "CAR" -> dataType.CAR;
                case "6", "BOOK" -> dataType.BOOK;
                default -> {
                    System.out.println("Invalid input. Try again.");
                    yield null;
                }
            };
        }
        return selectedDataType;
    }

    public static int chooseDataSize() {
        int dataSize = -1;
        while (dataSize <= 0) {
            System.out.println("\nChoose a number of data to be generated: \n1. 100\n2. 500\n3. 1000\n4. 10000\n5. other value\n");
            String input = sc.nextLine().trim();
            dataSize = switch (input) {
                case "1" -> 100;
                case "2" -> 500;
                case "3" -> 1000;
                case "4" -> 10000;
                default -> {
                    try {
                        int val = Integer.parseInt(input);
                        if (val > 0) yield val;
                    } catch (NumberFormatException ignored) {}
                    System.out.println("Invalid input. Try again: \n");
                    yield -1;
                }
            };
        }
        return dataSize;
    }

    public static collectionType chooseCollectionType() {
        collectionType selectedCollectionType = null;
            while (selectedCollectionType == null) {
            System.out.println("\nChoose collection type:\n1. ARRAYLIST\n2. LINKEDLIST\n3. HASHSET\n4. TREESET\n");
            String choice = sc.nextLine().trim().toUpperCase();

            selectedCollectionType = switch (choice){
                case "1", "ARRAYLIST" -> collectionType.ARRAYLIST;
                case "2", "LINKEDLIST" -> collectionType.LINKEDLIST;
                case "3", "HASHSET" -> collectionType.HASHSET;
                case "4", "TREESET" -> collectionType.TREESET;
                default -> {
                    System.out.println("Invalid input. Try again.");
                    yield null;
                }
            };
        }
        return selectedCollectionType;
    }

    public static testType chooseTestType() {

        testType selectedTestType = null;
        while (selectedTestType == null) {
            System.out.println("Choose test type:\n1. INDEX (read by index)\n2. FREQDELADD (insert/remove freq.)" +
                    "\n3. FIND (linear/binary search)\n4. EXISTS (check if element exists)");
            String choice = sc.nextLine().trim();

            selectedTestType = switch (choice){
                case "1", "INDEX" -> testType.INDEX;
                case "2", "FREQDELADD" -> testType.FREQDELADD;
                case "3", "FIND" -> testType.FIND;
                case "4", "EXISTS" -> testType.EXISTS;
                default -> {
                    System.out.println("Invalid input. Try again.");
                    yield null;
                }
            };
        }
        return selectedTestType;
    }

    public static dataPresentationType chooseDataPresentationType() {

        dataPresentationType selectedPresentation = null;
        while (selectedPresentation == null) {
            System.out.println("Choose data presentation:\n1. CLI (console)\n2. CSV (save to file)");
            String input = sc.nextLine().trim().toUpperCase();
            selectedPresentation = switch (input) {
                case "1", "CLI" -> dataPresentationType.CLI;
                case "2", "CSV" -> dataPresentationType.CSV;
                default -> {
                    System.out.println("Invalid input. Try again.");
                    yield null;
                }
            };
        }
        return selectedPresentation;
    }

    public static void dataPresenter(dataType selectedDataType, collectionType selectedCollectionType, testType selectedTestType, int dataSize, dataPresentationType selectedDataPresentationType) {
        System.out.println("\n--------Configuration summary----------");
        System.out.println("Data type: " + selectedDataType);
        System.out.println("Collection: " + selectedCollectionType);
        System.out.println("Test type: " + selectedTestType);
        System.out.println("Data size: " + dataSize);
        System.out.println("Presentation: " + selectedDataPresentationType + "\n-----------------\n\nAre you ready to proceed, or do you want to change the settings?");

    }
}

interface DataGenerator<T>{
    List<T> generate(int size);
}

class IntegerGenerator implements DataGenerator<Integer> {
    public List<Integer> generate(int size) {
        Random rand = new Random();
        return Stream.generate(() -> rand.nextInt(10_000))
                .limit(size)
                .collect(Collectors.toList());
    }
}

class DoubleGenerator implements DataGenerator<Double> {
    public List<Double> generate(int size) {
        Random rand = new Random();
        return Stream.generate(() -> rand.nextDouble() * 10_000)
                .limit(size)
                .collect(Collectors.toList());
    }
}


class Person {
    int birthYear;
    String name;

    Person(int year, String name) {
        this.birthYear = year;
        this.name = name;
    }

    public String toString() {
        return name + " (" + birthYear + ")";
    }
}

class PersonGenerator implements DataGenerator<Person> {
    public List<Person> generate(int size) {
        Random rand = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Person(1950 + rand.nextInt(60), "Name" + rand.nextInt(1000)))
                .collect(Collectors.toList());
    }
}



class MyColor implements Comparable<MyColor> {
    int r, g, b, sum;

    MyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.sum = r + g + b;
    }

    public String toString() {
        return "RGB(" + r + "," + g + "," + b + ") Sum=" + sum;
    }

    public int compareTo(MyColor other) {
        return Integer.compare(this.sum, other.sum);
    }
}

class MyColorGenerator implements DataGenerator<MyColor> {
    public List<MyColor> generate(int size) {
        Random rand = new Random();
        return Stream.generate(() -> new MyColor(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)))
                .limit(size)
                .collect(Collectors.toList());
    }
}


class Car {
    String make, model;

    Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String toString() {
        return make + " " + model;
    }
}

class CarGenerator implements DataGenerator<Car> {
    public List<Car> generate(int size) {
        String[] makes = {"Toyota", "Ford", "BMW", "Honda"};
        String[] models = {"X1", "Yaris", "Focus", "Civic"};
        Random rand = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Car(
                        makes[rand.nextInt(makes.length)],
                        models[rand.nextInt(models.length)]))
                .collect(Collectors.toList());
    }
}


class Book {
    String author;
    String title;
    int pages;

    Book(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    public String toString() {
        return "Book: " + author + " Title: " + title + " Pages: " + pages;
    }
}

class BookGenerator implements DataGenerator<Book> {
    public List<Book> generate(int size) {
        Random rand = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Book("X" + rand.nextInt(1000), "Y" + rand.nextInt(1000), rand.nextInt(10_000)))
                .collect(Collectors.toList());
    }
}




enum dataType {
    INT(() -> new IntegerGenerator()),
    DOUBLE(() -> new DoubleGenerator()),
    PERSON(() -> new PersonGenerator()),
    MYCOLOR(() -> new MyColorGenerator()),
    CAR(() -> new CarGenerator()),
    BOOK(() -> new BookGenerator());

    private final Supplier<DataGenerator<?>> supplier;

    dataType(Supplier<DataGenerator<?>> supplier) {
        this.supplier = supplier;
    }

    public DataGenerator<?> getGenerator() {
        return supplier.get();
    }
}


enum collectionType {
    ARRAYLIST, LINKEDLIST, HASHSET, TREESET
}

enum testType {
    INDEX, FREQDELADD, FIND, EXISTS
}

enum dataPresentationType {
    CLI, CSV
}
