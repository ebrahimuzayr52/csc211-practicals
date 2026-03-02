public class OpenedHash {
    private String[] keys;
    private String[] values;
    private int m;
    private int size;

    public OpenedHash(int m){
        this.m=m;
        keys = new String[m+1];
        values = new String[m+1];
        size = 0;
    }
     private int hash(String key) {
        int h =Math.abs(key.hashCode());
        return (h%m)+1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size >= m;
    }
    public void insert(String key, String value) {
        if (isFull()) return;

        int i = hash(key);

        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i % m) + 1;   
        }
        if (keys[i] == null) size++;

        keys[i] = key;
        values[i] = value;
    }
    public String lookup(String key) {
        int i = hash(key);

        while (keys[i] != null) {
            if (keys[i].equals(key))
                return values[i];

            i = (i % m) + 1;
        }
        return null;
    }
     public String remove(String key) {
        int i = hash(key);

        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                String val = values[i];
                keys[i] = null;
                values[i] = null;
                size--;
                return val;
            }
            i = (i % m) + 1;
        }
        return null;
}  
}