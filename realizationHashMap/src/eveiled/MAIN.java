package eveiled;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        IMap<String, Integer> integerMap2 = new HashMapImpl<>();

        for (int i = 0; i < 5; i++) {
            integerMap2.put("n" + i, i);
        }

        System.out.println("size: " + integerMap2.size());
        System.out.println(integerMap2);
        System.out.println("-----");

        integerMap2.put("n4", 4444);

        System.out.println("get n4: " + integerMap2.get("n4"));
        System.out.println("contains n4: " + integerMap2.containsKey("n4"));

        integerMap2.remove("n4");

        System.out.println("get n4 after remove: " + integerMap2.get("n4"));
        System.out.println("contains n4 after remove: " + integerMap2.containsKey("n4"));

        System.out.println("size: " + integerMap2.size());
        System.out.println(integerMap2);
        System.out.println(integerMap2.keySet());


    }
}
