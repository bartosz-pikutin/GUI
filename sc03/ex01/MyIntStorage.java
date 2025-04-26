package ex01;

public
    class MyIntStorage {

    private int[] data;
    private int count;

    public MyIntStorage() {
        this.data = new int[20];
        this.count = 0;
    }

    public void add(int value){
        this.data[count++] = value;
    }

    public int get(int index){
        return this.data[index];
    }

}
