class Person {
    private String name;
    private Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }

    int compareTo(Person p2) {
        return Integer.compare(this.age, p2.age);
    }

    public static void sort(Person[] people) {
        Person temp = people[0];
        for(int i = 0; i < people.length; i++) {
            for(int j = i + 1; j < people.length; j++) {
                if(people[i].compareTo(people[j]) == -1) {
                    temp = people[i];
                    people[i] = people[j];
                    people[j] = temp;
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Person p1 = new Person("John Doe", 30);
        Person p2 = new Person("Darian Piks", 22);
        Person p3 = new Person("Jakub Hoff", 30);
        Person p4 = new Person("Tomasz Tomaszew", 45);
        Person p5 = new Person("Krem DoRak", 37);
        Person[] People = {p1, p2, p3, p4, p5};

        for(Person p : People) {
            System.out.println(p);
        }

        System.out.println("============SORT===============");

        Person.sort(People);
        for(Person p : People) {
            System.out.println(p);

        }
        System.out.println(p1.compareTo(p2));
    }
}