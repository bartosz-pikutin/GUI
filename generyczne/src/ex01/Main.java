package ex01;

import java.util.ArrayList;

public
    class Main {

    public static void main(String[] args) {
        {
            int[] tab = new int[]{10, 20, 30};

            for(int v : tab)
                System.out.print(v + ", ");
            System.out.println();
        }

        {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(10);
            arrayList.add(20);
            arrayList.add(30);

            for(Integer v : arrayList)
                System.out.print(v + ", ");
            System.out.println();
        }
    }
}
