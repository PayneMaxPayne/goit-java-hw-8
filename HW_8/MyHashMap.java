package HW_8;

import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<Е, T> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private MyNode<Е, T>[] bucket;
    private int size;


    MyHashMap() {
        bucket = new MyNode[DEFAULT_CAPACITY];
    }

    private int indexHash(Е key) {
        if (Objects.isNull(key)) {
            return 0;
        }
        return Math.abs(key.hashCode()) % DEFAULT_CAPACITY;
    }


    public void put(Е key, T value) {
        int indexHash = indexHash(key);

        if (size > bucket.length * LOAD_FACTOR) {
            resize();
        }

        MyNode<Е, T> newMyNode = new MyNode<>(key, value, null);
        if (bucket[indexHash] == null) {
            bucket[indexHash] = newMyNode;
        } else {
            MyNode<Е, T> previousNode = null;
            MyNode<Е, T> currentNode = bucket[indexHash];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();

            }
            if (previousNode != null) {
                previousNode.setNext(newMyNode);
            }
        }
        size++;
    }

    public T get(Е key) {
        int indexHashGet = indexHash(key);

        MyNode<Е, T> myNode = bucket[indexHashGet];
        while (myNode != null) {
            if (myNode.getKey().equals(key)) {
                return myNode.getValue();
            }
            myNode = myNode.getNext();
        }
        return null;
    }


    public void remove(Е key) {
        int indexHashRemove = indexHash(key);

        MyNode<Е, T> prevNode = null;
        MyNode<Е, T> myNode = bucket[indexHashRemove];
        while (myNode != null) {
            if (myNode.getKey().equals(key)) {
                if (prevNode == null) {
                    bucket[indexHashRemove] = myNode.getNext();
                } else {
                    prevNode.setNext(myNode.getNext());
                }
                return;
            }
            prevNode = myNode;
            myNode = myNode.getNext();
        }
    }

    public void clear() {
        int count = 0;
        while (count < bucket.length) {
            bucket[count] = null;
            count++;
        }
        size = 0;
    }

    public int size() {
        return size;
    }


    private void resize() {
        MyNode<Е, T>[] oldBucket = Arrays.copyOf(bucket, bucket.length * 2);
        bucket = oldBucket;
    }

    static class MyNode<K, V> {
        private K key;
        private V value;
        MyNode<K, V> next;

        MyNode(K key, V value, MyNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public MyNode<K, V> getNext() {
            return next;
        }

        public void setNext(MyNode<K, V> next) {
            this.next = next;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyNode<?, ?> that = (MyNode<?, ?>) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key);
        }

    }

    
}
