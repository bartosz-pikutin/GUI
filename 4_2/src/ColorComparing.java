import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.Comparator;

class MyColor extends java.awt.Color implements Comparable<MyColor> {

    public MyColor(int red, int green, int blue) {
        super(red, green, blue);
    }

    public int compareTo(MyColor o) {
        int sumo1 = super.getRed() + super.getGreen() + super.getBlue();
        int sumo2 = o.getRed() + o.getGreen() + o.getBlue();
        return Integer.compare(sumo1, sumo2);
    }

    @Override
    public String toString() {
        return "(" + super.getRed() + "," + super.getGreen() + "," + super.getBlue() + ")";
    }
}

enum ColComponent{
    RED,
    GREEN,
    BLUE,
    NONE
}

class MyColorCompar implements Comparator<MyColor> {

    ColComponent color;
    public MyColorCompar(ColComponent c) {
        color = c;
    }

    @Override
    public int compare(MyColor o1, MyColor o2) {
    if(color == ColComponent.RED) {
        //return Integer.compare(o1.getRed(), o2.getRed());
        return o1.getRed() - o2.getRed();
    }
    else if(color == ColComponent.GREEN) {
        //return Integer.compare(o1.getGreen(), o2.getGreen());
        return o1.getGreen() - o2.getGreen();
    }
    else if(color == ColComponent.BLUE) {
        //return Integer.compare(o1.getBlue(), o2.getBlue());
        return o1.getBlue() - o2.getBlue();
    }
    else if(color == ColComponent.NONE) {
        int sumo1 = o1.getRed() + o1.getGreen() + o1.getBlue();
        int sumo2 = o2.getRed() + o2.getGreen() + o2.getBlue();
        return sumo1 - sumo2;
    }
    return 0;
    }
}


public class ColorComparing{
    public static void main(String[] args) {
        List<MyColor> list = Arrays.asList(
            new MyColor(  1,  2,  3),
            new MyColor(255,  0,  0),
            new MyColor( 55, 55,100),
            new MyColor( 10,255, 10)
        );
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new MyColorCompar(ColComponent.RED));
        System.out.println(list);

        Collections.sort(list, new MyColorCompar(ColComponent.GREEN));
        System.out.println(list);

        Collections.sort(list, new MyColorCompar(ColComponent.BLUE));
        System.out.println(list);

        Collections.sort(list, new MyColorCompar(ColComponent.NONE));
        System.out.println(list);
    }
}