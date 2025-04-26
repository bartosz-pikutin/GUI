import java.util.Iterator;

class Roll implements Iterable<Integer>{


    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}

public class RollDice {
    public static void main(String[] args) {
        for (int turn = 0; turn < 10; ++turn) {
            for (Integer i : new Roll())
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
