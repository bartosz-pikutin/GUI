package ex02;

public
    class MyGenericStorage<T> {

    private T[] data;
    private int count;

    public MyGenericStorage() {
        this.data = (T[]) new Object[20];
        this.count = 0;
    }

    public void add(T value){
        this.data[count++] = value;
    }

    public T get(int index){
        return this.data[index];
    }
}
