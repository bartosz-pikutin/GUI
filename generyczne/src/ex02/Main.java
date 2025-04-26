package ex02;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public
    class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        System.out.print("LinkedList: ");
        for(Object v : linkedList)
            System.out.print(v + ", ");
        System.out.println();

        listOperations(linkedList);
        queueOperations(linkedList);
        dequeuOperations(linkedList);
    }

    public static void listOperations(List<Integer> list){

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.print("List: ");
        for(Object v : list)
            System.out.print(v + ", ");
        System.out.println();

        System.out.println("get(1): " + list.get(1));

        System.out.print("List: ");
        for(Object v : list)
            System.out.print(v + ", ");
        System.out.println();

        System.out.println("remove(1): " + list.remove(1));

        System.out.print("List: ");
        for(Object v : list)
            System.out.print(v + ", ");
        System.out.println();
    }

    public static void queueOperations(Queue<Integer> queue){

        queue.add(40);
        queue.add(50);

        System.out.print("Queue: ");
        for(Object v : queue)
            System.out.print(v + ", ");
        System.out.println();

        System.out.println("peek(): "+queue.peek());

        System.out.print("Queue: ");
        for(Object v : queue)
            System.out.print(v + ", ");
        System.out.println();

        System.out.println("poll: " + queue.poll());

        System.out.print("Queue: ");
        for(Object v : queue)
            System.out.print(v + ", ");
        System.out.println();
    }

    public static void dequeuOperations(Deque<Integer> deque){
        deque.add(60);
        deque.addFirst(70);
        deque.addLast(80);

        System.out.print("Dequeue: ");
        for(Object v : deque)
            System.out.print(v + ", ");
        System.out.println();

        System.out.println("getFirst(): " + deque.getFirst());
        System.out.println("getLast(): " + deque.getLast());

        System.out.print("Dequeue: ");
        for(Object v : deque)
            System.out.print(v + ", ");
        System.out.println();

        System.out.println("removeLast(): " + deque.removeLast());
        System.out.println("removeFirst(): " + deque.removeFirst());
        System.out.println("remove(): " + deque.remove());

        System.out.print("Dequeue: ");
        for(Object v : deque)
            System.out.print(v + ", ");
        System.out.println();

    }
}
