public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public int compareTo(Person p) {
        return Integer.compare(this.age, p.age);
    }

    static void sort(Person[] people){
        int ilosc = people.length;
        Person[] temp = new Person[1];
        for(int i = 0; i < ilosc -1; i++){
            int wynik;
            wynik = people[i].compareTo(people[i+1]);
            if(wynik == -1 )
                temp[0] = people[i+1];
                people[i+1] = people[i];
                people[i]= temp[0];
            if(wynik == 0)
                temp[0] = people[i+1];
                people[i+1] = people[i];
                people[i]= temp[0];
            if(wynik == 1)
                System.out.println(1);
        }
    }

    public static void main(String[] args) {

        Person p1 = new Person("Adam", 22);
        Person p2 = new Person("Daniel", 22);
        Person p3 = new Person("Kamil", 18);
        Person p4 = new Person("Julia", 24);
        Person p5 = new Person("Dawid", 400);

        //System.out.println(p3.compareTo(p4));
        //System.out.println(p1.compareTo(p2));
        //System.out.println(p2.compareTo(p3));

        Person[] people = {p1, p2, p3};
        sort(people);

    }
}