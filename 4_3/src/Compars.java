import java.util.Arrays;
import java.util.Comparator;

class MyComp implements Comparator<Integer> {
    public static final int
                BY_VAL=0, BY_VAL_REV=1,
                BY_NUM_OF_DIVS=2, BY_SUM_OF_DIGS=3;

    int kryterium;
    public MyComp(int kryterium) {
        this.kryterium = kryterium;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
       if(kryterium == 0) {
           return o1.compareTo(o2);
       }
       else if(kryterium == 1) {
           return o2.compareTo(o1);
       }
       else if(kryterium == 2) {
           int o1Div = 0, o2Div = 0;
           for (int i = 1; i <= o1; i++) {
               if (o1 % i == 0) {
                   o1Div++;
               }
           }
           for (int i = 1; i <= o2; i++) {
               if (o2 % i == 0) {
                   o2Div++;
               }
           }
           if(o1Div == o2Div)
               return o1.compareTo(o2);
           return Integer.compare(o1Div, o2Div);
       }
       else if(kryterium == 3) {
            int o1Sum = 0, o2Sum = 0;
            double o1Double = o1.doubleValue(), o2Double = o2.doubleValue();
            do{
                o1Sum += (int) (o1Double % 10);
                o1Double = o1Double / 10;
            }
            while(o1Double>1);
           do{
               o2Sum += (int) (o2Double % 10);
               o2Double = o2Double / 10;
           }
           while(o2Double>1);
           return o1Sum - o2Sum;
       }
       return 0;
    }
}

public class Compars {
    public static void main(String[] args) {
        Integer[] a = {1,5,33,12,98,15};
        printTable("Original    ", a);

        Arrays.sort(a,new MyComp(MyComp.BY_VAL));
        printTable("ByVal       ", a);

        Arrays.sort(a,new MyComp(MyComp.BY_VAL_REV));
        printTable("ByValRev    ", a);

        Arrays.sort(a,new MyComp(MyComp.BY_NUM_OF_DIVS));
        printTable("ByNumOfDivs ", a);

        Arrays.sort(a,new MyComp(MyComp.BY_SUM_OF_DIGS));
        printTable("BySumOfDigs ", a);
    }

    static void printTable(String mess, Integer[] a) {
        System.out.print(mess + "[ ");
        for (int d : a) System.out.print(d + " ");
        System.out.print("]\n");
    }
}
