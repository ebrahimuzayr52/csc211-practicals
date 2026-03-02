import java.util.LinkedList;

public class ChainedHash {

    private LinkedList<Node>[] table;
    private int m;

    private static class Node {
        String key;
        String value;

        Node(String k, String v) {
            key = k;
            value = v;
        }
    }

    public ChainedHash(int m) {
        this.m = m;
        table = new LinkedList[m + 1];

        for (int i = 1; i <= m; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        int h = Math.abs(key.hashCode());
        return (h % m) + 1;
    }

    public void insert(String key, String value) {
        int i = hash(key);

        for (Node node : table[i]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        table[i].add(new Node(key, value));
    }

    public String lookup(String key) {
        int i = hash(key);

        for (Node node : table[i]) {
            if (node.key.equals(key))
                return node.value;
        }
        return null;
    }

    public String remove(String key) {
        int i = hash(key);

        for (Node node : table[i]) {
            if (node.key.equals(key)) {
                String val = node.value;
                table[i].remove(node);
                return val;
            }
        }
        return null;
    }
}