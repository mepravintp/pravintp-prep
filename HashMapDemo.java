import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {

        HashMap<String, List<Integer>> map = new HashMap<>();

        // computIfAbsent - we can provide compute function
        map.computeIfAbsent("TEST1", s -> new ArrayList<>()).add(1);

        //only works when key is absent
        map.putIfAbsent("TEST1", new ArrayList<>());

        System.out.println(map);

    }
}
