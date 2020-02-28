import java.util.HashMap;
import java.util.Map;

class LRUCache {
    static class ListNode {
        public ListNode prev, next;
        public int key, val;

        public ListNode() {}

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    ListNode head, tail;
    Map<Integer, ListNode> table;
    int capacity;

    private void appendNode(ListNode node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToLast(ListNode node) {
        removeNode(node);
        appendNode(node);
    }

    private ListNode popFirst() {
        ListNode first = head.next;
        removeNode(first);
        return first;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        table = new HashMap<>();

        head = new ListNode();
        tail = new ListNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        ListNode node = table.get(key);

        if (node == null)
            return -1;

        moveToLast(node);

        return node.val;
    }

    public void put(int key, int value) {
        ListNode node = table.get(key);

        if (node != null) {
            node.val = value;
            moveToLast(node);
            return;
        }

        if (table.size() == capacity) {
            ListNode first = popFirst();
            table.remove(first.key);
        }

        node = new ListNode(key, value);
        table.put(key, node);
        appendNode(node);
    }
}

class LRU_Cache146 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assert cache.get(1) == 1;       // returns 1
        cache.put(3, 3);    // evicts key 2
        assert cache.get(2) == -1;       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assert cache.get(1) == -1;       // returns -1 (not found)
        assert cache.get(3) == 3;       // returns 3
        assert cache.get(4) == 4;       // returns 4
    }
}