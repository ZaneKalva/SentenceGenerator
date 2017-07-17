package sentencegenerator;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Zane Kalva
 */
public class Bag {
    private HashMap<String, Integer> data;
    private int size;

    public Bag(){
        data = new HashMap<String, Integer>();
    }

    public int getSize() {
        return size;
    }

    public void add(String letter) {
        size++;
        if (data.containsKey(letter)) {
            int num = data.get(letter);
            data.put(letter, ++num);
        } else {
            data.put(letter, 1);
        }
    }


    public String getNextLetterByPropability() {
        int num = (int)(Math.random()*getSize());

        int sum = 0;
        for (String letter:data.keySet()) {
            sum += data.get(letter);
            if (num < sum) return letter;
        }
        return "ERROR";
    }

    public String toString() {
        return data.toString();
    }
}
