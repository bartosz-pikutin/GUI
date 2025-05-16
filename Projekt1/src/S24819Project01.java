import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.*;
import java.util.*;
import java.io.*;

public
    class S24819Project01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Java collection tester!");
        dataType selectedDataType = chooseDataType();
        int dataSize = chooseDataSize();
        collectionType selectedCollectionType = chooseCollectionType();

        testType selectedTestType = chooseTestType();
        dataPresentationType selectedDataPresentationType = chooseDataPresentationType();

        boolean confirmed = false;
        while (!confirmed) {
            configPresenter(selectedDataType, selectedCollectionType, selectedTestType, dataSize, selectedDataPresentationType);

            System.out.println("\nMenu:");
            System.out.println("1 - Change data type");
            System.out.println("2 - Change data size");
            System.out.println("3 - Change collection type");
            System.out.println("4 - Change test type");
            System.out.println("5 - Change presentation type");
            System.out.println("0 - Confirm and proceed");


            int input = sc.nextInt();
            switch (input) {
                case 1 -> selectedDataType = chooseDataType();
                case 2 -> dataSize = chooseDataSize();
                case 3 -> selectedCollectionType = chooseCollectionType();
                case 4 -> selectedTestType = chooseTestType();
                case 5 -> selectedDataPresentationType = chooseDataPresentationType();
                case 0 -> confirmed = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        DataGenerator<?> generator = selectedDataType.getGenerator();
        List<?> generatedData = generator.generate(dataSize);
        System.out.println("\nGenerated " + generatedData.size() + " elements. Showing 10:");

        generatedData.stream().limit(10).forEach(System.out::println);
        Collection<Object> collection = selectedCollectionType.create();
        collection.addAll(generatedData);
        System.out.println("\nAdded " + collection.size() + " elements to " + selectedCollectionType + " collection.");

        CollectionTester tester = switch (selectedTestType) {
            case INDEX -> new IndexTester(1000);
            case FREQDELADD -> new FreqDelAddTester();
            case FIND -> new FindTester();
            case EXISTS -> new ExistsTester(1000);
        };


        Collection<Object> testCollection = selectedCollectionType.create();
        try {
            TestResult result = tester.run(testCollection, generatedData);
            ResultPresenter presenter = switch (selectedDataPresentationType) {
                case CLI -> new ConsolePresenter();
                case CSV -> new CsvPresenter("results.csv");
            };

            presenter.present(result);
        } catch (UnsupportedOperationException e) {
            System.out.println("\n[SKIPPED] " + e.getMessage());
        }
    }

    public static dataType chooseDataType() {
        Scanner sc = new Scanner(System.in);
        dataType selectedDataType = null;
        while (selectedDataType == null) {
            System.out.println("What type of data do you want to generate?\n");
            System.out.println("1. Int" + "\n2. Double" + "\n3. Person (birth year and name)" + "\n4. MyColor (R, G, B and Sum)" +
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
        Scanner sc = new Scanner(System.in);
        int dataSize = -1;
        while (dataSize <= 0) {
            System.out.println("\nChoose a number of data to be generated: \n1. 100\n2. 500\n3. 1000\n4. 10 000\n5. other value (type in youur own value \n");
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
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        testType selectedTestType = null;
        while (selectedTestType == null) {
            System.out.println("Choose test type:\n1. INDEX (read by index)\n2. FREQDELADD (insert/remove freq.)" +
                    "\n3. FIND (specific element)\n4. EXISTS (if elements exist)");
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
        Scanner sc = new Scanner(System.in);
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

    public static void configPresenter(dataType selectedDataType, collectionType selectedCollectionType, testType selectedTestType, int dataSize, dataPresentationType selectedDataPresentationType) {
        System.out.println("\n--------Configuration info----------");
        System.out.println("Data type: " + selectedDataType);
        System.out.println("Collection: " + selectedCollectionType);
        System.out.println("Test type: " + selectedTestType);
        System.out.println("Data size: " + dataSize);
        System.out.println("Presentation method: " + selectedDataPresentationType + "\n-----------------\n\nAre you ready to proceed, or do you want to change the setings?");

    }
}

class CollectionUtils {

    public static long measureAdd(Collection<Object> collection, List<?> data) {
        long start = System.nanoTime();
        for (Object obj : data) {
            collection.add(obj);
        }
        return System.nanoTime() - start;
    }

    public static long measureRemove(Collection<Object> collection, List<?> data, int step) {
        long start = System.nanoTime();
        for (int i = 0; i < data.size(); i += step) {
            collection.remove(data.get(i));
        }
        return System.nanoTime() - start;
    }

    public static long measureContains(Collection<Object> collection, Object target) {
        long start = System.nanoTime();
        boolean found = collection.contains(target);
        System.out.println("Element was " + (found ? "found." : "not found."));
        return System.nanoTime() - start;
    }


    public static long measureMultipleContains(Collection<Object> collection, List<?> data, int repetitions) {
        Random rand = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            Object query = (i % 2 == 0)
                    ? data.get(rand.nextInt(data.size()))  // istniejące
                    : new Object();                         // nieistniejące
            collection.contains(query);
        }
        return System.nanoTime() - start;
        //w ten sposób raz sprawdza istniejące dane, a raz których nie ma w zbiorze
    }
}


// ======================KLASY OD TESTOWANIA==============================
class IndexTester implements CollectionTester {
    private final int repetitions;

    public IndexTester(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public TestResult run(Collection<Object> collection, List<?> data) {
        TestResult result = new TestResult();

        result.addTime = CollectionUtils.measureAdd(collection, data);

        if (!(collection instanceof List<?> list)) {
            throw new UnsupportedOperationException("INDEX test not supported for " + collection.getClass().getSimpleName());
        }

        int size = list.size();
        Random rand = new Random();
        long startSearch = System.nanoTime();

        for (int i = 0; i < repetitions; i++) {
            Object o = list.get(rand.nextInt(size));
            if (o == null) System.out.print("");
        }

        result.searchTime = System.nanoTime() - startSearch;
        return result;
    }
}


class FreqDelAddTester implements CollectionTester {

    @Override
    public TestResult run(Collection<Object> collection, List<?> data) {
        TestResult result = new TestResult();

        result.addTime = CollectionUtils.measureAdd(collection, data);
        result.removeTime = CollectionUtils.measureRemove(collection, data, 2);

        return result;
    }
}

class FindTester implements CollectionTester {
    @Override
    public TestResult run(Collection<Object> collection, List<?> data) {
        TestResult result = new TestResult();

        result.addTime = CollectionUtils.measureAdd(collection, data);

        // Wyszukiwanie losowego elementu z danych, tutaj wybrałem środkowy
        Object target = data.get(data.size() / 2);
        result.searchTime = CollectionUtils.measureContains(collection, target);

        return result;
    }
}

class ExistsTester implements CollectionTester {
    private final int repetitions;

    public ExistsTester(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public TestResult run(Collection<Object> collection, List<?> data) {
        TestResult result = new TestResult();

        result.addTime = CollectionUtils.measureAdd(collection, data);
        result.searchTime = CollectionUtils.measureMultipleContains(collection, data, repetitions);

        return result;
    }
}
//============================================================

class TestResult {
    long addTime;
    long searchTime;
    long removeTime;

    @Override
    public String toString() {
        return "Add time: " + addTime + " ns, Search time: " + searchTime + " ns, Remove time: " + removeTime + "ns";
    }
}



//============prezetery wyników testów================

class ConsolePresenter implements ResultPresenter {
    @Override
    public void present(TestResult result) {
        System.out.println("\n---- TEST RESULT (CLI) ----");
        System.out.println(result);
        System.out.println("---------------------------");
    }
}

class CsvPresenter implements ResultPresenter {
    private final String filename;

    public CsvPresenter(String filename) {
        this.filename = filename;
    }

    @Override
    public void present(TestResult result) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("addTime,searchTime,removeTime");
            writer.printf("%d,%d,%d%n", result.addTime, result.searchTime, result.removeTime);
            System.out.println("Test result saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving CSV: " + e.getMessage());
        }
    }
}

//==========generatory danych========================

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

class PersonGenerator implements DataGenerator<Person> {
    public List<Person> generate(int size) {
        Random rand = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Person(1920 + rand.nextInt(100), "Name" + rand.nextInt(1000)))
                .collect(Collectors.toList());
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

class CarGenerator implements DataGenerator<Car> {
    public List<Car> generate(int size) {
        String[] makes = {"Toyota", "Ford", "BMW", "Honda"};
        String[] models = {"Yaris", "GT", "330i", "Civic"};
        Random rand = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Car(
                        makes[rand.nextInt(makes.length)],
                        models[rand.nextInt(models.length)]))
                .collect(Collectors.toList());
    }
}

class BookGenerator implements DataGenerator<Book> {
    public List<Book> generate(int size) {
        Random rand = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Book("Name" + rand.nextInt(1000), "Title" + rand.nextInt(1000), rand.nextInt(1000)))
                .collect(Collectors.toList());
    }
}

//=================== klasy typów danych ======================

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

    @Override
    public int compareTo(MyColor other) {
        return Integer.compare(this.sum, other.sum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyColor other)) return false;
        return this.r == other.r && this.g == other.g && this.b == other.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }
}

class Car implements Comparable<Car> {
    String make, model;

    Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

    @Override
    public String toString() {
        return make + " " + model;
    }

    @Override
    public int compareTo(Car other) {
        int makeCmp = this.make.compareToIgnoreCase(other.make);
        if (makeCmp != 0) return makeCmp;
        return this.model.compareToIgnoreCase(other.model);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car other)) return false;
        return make.equalsIgnoreCase(other.make) && model.equalsIgnoreCase(other.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make.toLowerCase(), model.toLowerCase());
    }
}

class Book implements Comparable<Book> {
    String author;
    String title;
    int pages;

    Book(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Author: " + author + " Title: " + title + " Pages: " + pages;
    }

    @Override
    public int compareTo(Book other) {
        // sortuje po tytule a potem po autorze
        int titleCompare = this.title.compareToIgnoreCase(other.title);
        if (titleCompare != 0) return titleCompare;

        return this.author.compareToIgnoreCase(other.author);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book other)) return false;
        return title.equalsIgnoreCase(other.title) && author.equalsIgnoreCase(other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), author.toLowerCase());
    }
}

//==========================interfejsy========================

interface DataGenerator<T>{
    List<T> generate(int size);
}

interface CollectionTester {
    TestResult run(Collection<Object> collection, List<?> data);
}

interface ResultPresenter {
    void present(TestResult result);
}

//============enumy==============

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
    ARRAYLIST(ArrayList::new),
    LINKEDLIST(LinkedList::new),
    HASHSET(HashSet::new),
    TREESET(TreeSet::new);

    private final Supplier<Collection<Object>> supplier;

    collectionType(Supplier<Collection<Object>> supplier) {
        this.supplier = supplier;
    }

    public Collection<Object> create() {
        return supplier.get();
    }
}

enum testType {
    INDEX, FREQDELADD, FIND, EXISTS
}

enum dataPresentationType {
    CLI, CSV
}
