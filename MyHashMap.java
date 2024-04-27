import java.util.LinkedList;
import java.util.Objects;

class MyEntry<K, V> {

    K key;

    V value;

    @Override
    public String toString() {
        return "MyEntry{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }

    public MyEntry(K key, V value, MyEntry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    MyEntry next;


}

public class MyHashMap<K, V> {


    private int size;

    private static int DEFAULT_SIZE = 16;

    private LinkedList<MyEntry>[] nodes = new LinkedList[DEFAULT_SIZE];

    private int getIndex(K key) {
        return Objects.hash(key) % nodes.length;
    }

    public V put(K key, V value) {
        int index = getIndex(key);

        if (nodes[index] == null) {
            MyEntry entry = new MyEntry(key, value, null);
            nodes[index] = new LinkedList<>();
            nodes[index].add(entry);
            size++;

        } else {
            for (MyEntry entry : nodes[index]) {
                if (entry.key.equals(key))
                    entry.value = value;

            }

            nodes[index].add(new MyEntry<>(key, value, null));
            size++;
        }

        System.out.println(nodes);
        return (V) key;
    }

    public V get(K key) {

        int index = getIndex(key);
        if (nodes[index] != null) {
            for (MyEntry entry : nodes[index]) {
                if (entry.key.equals(key))
                    return (V) entry.value;
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        if (nodes[index] != null) {
            for (MyEntry entry : nodes[index]) {
                if (entry.key.equals(key))
                    return true;
            }
        }
        return false;
    }

    public void remove(K key) {
        int index = getIndex(key);
        MyEntry toRemove = null;
        if (nodes[index] != null) {
            for (MyEntry entry : nodes[index]) {
                if (entry.key.equals(key))
                    toRemove = entry;
            }
        }
        nodes[index].remove(toRemove);
        size--;

    }


    public static void main(String[] args) {

        MyHashMap<String, Integer> map = new MyHashMap();
        map.put("A", 1);
        System.out.println(map.get("A"));
        map.put("B", 2);
        System.out.println(map.get("B"));
        map.put("A", 3);
        System.out.println(map.get("A"));

        System.out.println(map.size());

        map.remove("B");
        System.out.println(map.containsKey("A"));



    }
}
