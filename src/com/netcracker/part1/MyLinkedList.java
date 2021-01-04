package com.netcracker.part1;

import java.util.Iterator;

public class MyLinkedList<E> implements ILinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;


    @Override
    public void add(E element) {
        if (size == 0) {
            first = new Node<E>(element);
            last = first;
            size = 1;
        } else {
            Node<E> node = new Node<E>(element);
            last.setNextNode(node);
            last = node;
            size++;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("IND > SIZE");
        }
        if (index == size) {
            add(element);
            return;
        }
        if (index == 0 && size > 0) {
            first = new Node<E>(element, first);
            size++;
            return;
        }
        Node<E> cursor = getCursor(index);
        Node<E> elem = new Node<E>(element, cursor.getNextNode().getNextNode());
        cursor.setNextNode(elem);
        size++;
    }

    private Node<E> getCursor(int index) {
        Node<E> cursor = first;
        for (int i = 0; i < index - 1; i++) {
            cursor = cursor.getNextNode();
        }
        return cursor;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return getCursor(index + 1).getElement();
    }

    @Override
    public int indexOf(E element) {
        Node<E> cursor = first;
        for (int i = 0; i < size; i++) {
            if (cursor.getElement().equals(element))
                return i;
            cursor = cursor.getNextNode();
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        Node<E> cursor = first;
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            first = cursor.getNextNode();
            cursor.setNextNode(null);
            size--;
            return cursor.getElement();
        }
        cursor = getCursor(index);
        E result = cursor.getNextNode().getElement();
        cursor.setNextNode(cursor.getNextNode().getNextNode());
        size--;
        //Сборщик мусора сделает остальное
        return result;
    }

    @Override
    public E set(int index, E element) {
        E result;
        if (index == 0) {
            result = first.getElement();
            first.setElement(element);
            return result;
        }
        Node<E> cursor = getCursor(index);
        result = cursor.getNextNode().getElement();
        cursor.getNextNode().setElement(element);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        if (size == 0) return null;
        E[] arr = (E[]) java.lang.reflect.Array.newInstance(
                first.getElement().getClass(), size);
        Node<E> cursor = first;
        arr[0] = first.getElement();
        for (int i = 1; i < size; i++) {
            cursor = cursor.getNextNode();
            arr[i] = cursor.getElement();
        }
        return arr;
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (true) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>(first, size);
    }

}
