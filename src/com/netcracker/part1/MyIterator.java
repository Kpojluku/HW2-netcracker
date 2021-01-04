package com.netcracker.part1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<E> implements Iterator<E> {
    private Node<E> item;
    private Node<E> nextItem;
    private int index=0;
    private int size;


    @Override
    public boolean hasNext() {
        return index<size;
    }

    @Override
    public E next() {
        index++;
        item=nextItem;
        if(nextItem!=null) {
            nextItem=nextItem.getNextNode();
            return item.getElement();
        }else {
            throw new NoSuchElementException();
        }
    }

    public MyIterator(Node<E> Item, int size) {
        this.size=size;
        this.item=this.nextItem = Item;
    }
}
