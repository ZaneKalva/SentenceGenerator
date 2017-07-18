package sentencegenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zane Kalva
 */
public class MarkovModel {
    public static int NGRAMLENGTH = 4;
    private HashMap<String, Bag> map;
    private List sentenceBeginnings;
    private DataRepository repository;

    public MarkovModel() {
        map = new HashMap<>();
        sentenceBeginnings = new ArrayList<String>();
    }

    public void add(final String ngram, final String nextLetter) {
        if (map.containsKey(ngram)) {
            Bag b = map.get(ngram);
            b.add(nextLetter);
            map.put(ngram, b);
        } else {
            Bag b = new Bag();
            b.add(nextLetter);
            map.put(ngram, b);
        }
    }

    public void addNgramsIn(final String inputText) {
        String ngram;
        String nextLetter;

        for (int i = 0; i < inputText.length() - NGRAMLENGTH; i++) {
            ngram = inputText.substring(i, i + NGRAMLENGTH);
            nextLetter = inputText.substring(i + NGRAMLENGTH, i + NGRAMLENGTH + 1);
            add(ngram, nextLetter);
        }
    }

    public void populateModel(final String fileName) {
        repository = new DataRepository(fileName);
        final List<String> lineList = repository.getLineList();
        for (String line : lineList) {
            if (line != null) {
                sentenceBeginnings.add(line.substring(0, NGRAMLENGTH));
                addNgramsIn(line);
            }
        }
    }

    public String getNextLetter(final String state) {
        if (map.containsKey(state)) {
            Bag b = map.get(state);
            return b.getNextLetterByPropability();
        } else {
            return null;
        }
    }

    public String getRandomSentenceBeginning() {
        int num = (int) (Math.random() * sentenceBeginnings.size());
        return sentenceBeginnings.get(num).toString();
    }
}
