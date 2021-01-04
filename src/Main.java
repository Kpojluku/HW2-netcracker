import com.netcracker.part1.MyLinkedList;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 10000; i++){
            list.add(i);
            list2.add(i);
        }
        Long t1 = System.nanoTime();
        int a = list.get(2222);
        Long t2 = System.nanoTime();
        int i = list2.get(2222);
        Long t3 = System.nanoTime();
        System.out.println("Доступ к элементу");
        System.out.println("MyLinkedList = " + (t2 - t1));
        System.out.println("LinkedList = " + (t3 - t2));

    }
}
