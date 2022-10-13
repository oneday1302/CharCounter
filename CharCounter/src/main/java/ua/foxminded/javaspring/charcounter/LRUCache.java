package ua.foxminded.javaspring.charcounter;

import java.util.HashMap;

public class LRUCache {

    class Node {
        Node previous;
        Node next;
        String key;
        String value;

        public Node(Node previous, Node next, String key, String value) {
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<String, Node> cache;
    private Node leastRecentlyUsed;
    private Node mostRecentlyUsed;
    private int maxSize;
    private int currentSize;

    public LRUCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }

        this.maxSize = maxSize;
        currentSize = 0;
        leastRecentlyUsed = new Node(null, null, null, null);
        mostRecentlyUsed = leastRecentlyUsed;
        cache = new HashMap<>();
    }

    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        Node tempNode = cache.get(key);
        if (tempNode == null) {
            return null;
        } else if (tempNode.key == mostRecentlyUsed.key) {
            return mostRecentlyUsed.value;
        }

        Node nextNode = tempNode.next;
        Node previousNode = tempNode.previous;

        if (tempNode.key == leastRecentlyUsed.key) {
            nextNode.previous = null;
            leastRecentlyUsed = nextNode;
        } else if (tempNode.key != mostRecentlyUsed.key) {
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }

        tempNode.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = tempNode;
        mostRecentlyUsed = tempNode;
        mostRecentlyUsed.next = null;

        return tempNode.value;
    }

    public void put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }

        if (cache.containsKey(key)) {
            return;
        }

        Node newNode = new Node(mostRecentlyUsed, null, key, value);
        mostRecentlyUsed.next = newNode;
        cache.put(key, newNode);
        mostRecentlyUsed = newNode;

        if (currentSize == maxSize) {
            cache.remove(leastRecentlyUsed.key);
            leastRecentlyUsed = leastRecentlyUsed.next;
            leastRecentlyUsed.previous = null;
        } else if (currentSize < maxSize) {
            if (currentSize == 0) {
                leastRecentlyUsed = newNode;
            }
            currentSize++;
        }
    }

    public boolean containsKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        return cache.containsKey(key);
    }
}
