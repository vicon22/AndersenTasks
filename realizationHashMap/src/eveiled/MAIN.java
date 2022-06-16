package eveiled;

import java.util.HashMap;
import java.util.Map;

public class MAIN {

    public static void main(String[] args) {
        System.out.println("Hello");

        HashMap<String, Integer> integerMap = new HashMap<>();
        HashMapImpl<String, Integer> integerMap2 = new HashMapImpl<>();

        integerMap.put("first", 1);
        integerMap.put("second", 2);
        integerMap.put("third", 3);
        integerMap.put("third", 444);
        integerMap.put("forth", 20);
        integerMap.put("fifth", 50);

        integerMap.remove("third");

        System.out.println(integerMap);
        System.out.println(integerMap.containsKey("forth"));
        System.out.println(integerMap.keySet());
        System.out.println(integerMap.get("3"));

        System.out.println("------");


        for (int i = 0; i < 50; i++) {
            integerMap2.put("n" + i, i);
        }

        integerMap2.put("n4", 4444);


        System.out.println("size: " + integerMap2.size());
        System.out.println(integerMap2);
        System.out.println(integerMap2.keySet());
        System.out.println(integerMap2.get("3"));
        System.out.println("get first: " + integerMap2.get("first"));
        System.out.println("get third: " + integerMap2.get("third"));


    }
}
