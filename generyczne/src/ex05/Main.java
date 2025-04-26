package ex05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public
    class Main {

    public static void main(String[] args) {
        List<Person> personList =
            List.of(
                new Person("Jan"),
                new Person("Ola")
            );

        for(Person p : personList)
            System.out.print(p+", ");
        System.out.println();

//        personList.remove(0); // ex

        personList.get(0).setName("John Doe");

        for(Person p : personList)
            System.out.print(p+", ");
        System.out.println();
    }
}
