import java.util.Arrays;
import java.util.Comparator;

enum Sex{
    F, M
}

enum Size {
    XS, S, M, L, XL
}

enum Country {
    PL {
        public String toString() { return "Polska"; }
    },
    NL {
        public String toString() { return "Nederland"; }
    },
    DE {
        public String toString() { return "Deutschland"; }
    }
}

class Person{

    String name;
    Sex sex;
    Size size;
    Country country;

    public Person(String name, Sex sex, Size size, Country country){
        this.name = name;
        this.country = country;
        this.sex = sex;
        this.size = size;
    }

    public String toString(){
        return name + "(" + sex + ", " + size + ", " + country +  ")";
    }
}

public class EnumsLambdas {

    // printArray static function

    public static <T, R> void printArray(R title, T[] persons){
        System.out.println("    *** " + title + " ***");
        for(T person : persons){
            System.out.println(person.toString());
        }
    }

    public static void main(String[] args) {
        Person[] persons = {
            new Person("Max",  Sex.M, Size.XL, Country.NL),
            new Person("Jan",  Sex.M, Size.S,  Country.PL),
            new Person("Eva",  Sex.F, Size.XS, Country.NL),
            new Person("Lina", Sex.F, Size.L,  Country.DE),
            new Person("Mila", Sex.F, Size.S,  Country.DE),
            new Person("Ola",  Sex.F, Size.M,  Country.PL),
        };

        Comparator<Person> sexThenSize = Comparator.comparing((Person p) -> p.sex).thenComparing((Person p) -> p.size);

        Arrays.sort(persons, sexThenSize);
        printArray("Persons by sex and then size", persons);

        Arrays.sort(persons, Comparator.comparing((Person p) -> p.size).thenComparing((Person p) -> p.name));
        printArray("Persons by size and then name", persons);

        Country[] countries = Country.values();
        Arrays.sort(countries, Comparator.comparing(Country::name));
        printArray("Countries by name", countries);
    }
}
