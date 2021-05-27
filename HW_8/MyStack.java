package HW_8;

import java.util.EmptyStackException;

public class MyStack<E> {

    static class MyNode<E> {
        private E element;
        private MyNode<E> next;

        MyNode(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public MyNode<E> getNext() {
            return next;
        }

        public void setNext(MyNode<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Value is: " + element + " | " + next;
        }
    }

    private MyNode<E> topElement = null;
    int size = 0;

    public MyStack() {
    }

    public void push(E element) {
        MyNode<E> myNewNode = new MyNode<>(element);
        myNewNode.setNext(topElement);
        topElement = myNewNode;
        size++;
    }

    public E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return topElement.getElement();
    }

    public E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        MyNode <E> myNode = topElement.getNext();
        size--;
        return (E) myNode.getElement();
    }

    public int size() {
        return size;
    }

    public void clear() {
        topElement = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "" + topElement;
    }
}
