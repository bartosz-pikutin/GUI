import ex02.MyGenericStorage;
import ex02.Student;

import java.util.Arrays;
import java.util.Comparator;

public
    class Main {

    public static void main(String[] args) {
        MyGenericStorage<Student> studStorage = new MyGenericStorage<>();

        studStorage.add(new Student("Jan", 1701));

        System.out.println(
            studStorage.get(0)
        );

        studStorage.get(0).show();

//=========================================================

        MyGenericStorage storage = new MyGenericStorage();

        storage.add(new Student("Ola", 867));

        System.out.println(
            storage.get(0)
        );

        //storage.get(0).show();//err


//===============================================================

        Student[] students = {
            new Student("Jan", 1701),
            new Student("Wla", 867),
            new Student("Tomaszew", 534)
        };

        Arrays.sort(students);

        System.out.println("=========================");
        for(Student student : students)
            System.out.println(student);

//=========================================================

        Arrays.sort(
            students, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o2.getsNumber() - o1.getsNumber();
                }
            }
        );

        System.out.println("=========================");
        for(Student student : students)
            System.out.println(student);

//=========================================================

        Arrays.sort(
            students, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        );

        System.out.println("=========================");
        for(Student student : students)
            System.out.println(student);

//=========================================================

        Arrays.sort(
            students, (o1, o2) -> o1.getName().compareTo(o2.getName())
        );

        System.out.println("=========================");
        for(Student student : students)
            System.out.println(student);


//=========================================================

        Arrays.sort(
            students, (o1, o2) -> {
                int res = o1.getName().compareTo(o2.getName());
                if(res == 0)
                    return o1.getsNumber() - o2.getsNumber();
                return res;
            }
        );

        System.out.println("=========================");
        for(Student student : students)
            System.out.println(student);



    }
}
