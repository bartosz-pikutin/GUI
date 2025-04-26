package ex01;

public
    class Student {

    private String name;
    private int sNumber;

    public Student(String name, int sNumber) {
        this.name = name;
        this.sNumber = sNumber;
    }

    @Override
    public String toString() {
        return "Student(" + this.name +", "+this.sNumber+")";
    }

    public String getName() {
        return name;
    }

    public int getsNumber() {
        return sNumber;
    }
}
