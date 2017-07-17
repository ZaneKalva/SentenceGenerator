package sentencegenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Zane Kalva
 */
public class MarkovModel
{
	private HashMap<String, Bag> map;
	private List sentenceBeginnings;
	private DataRepository repository;

	public 	MarkovModel () {
		map = new HashMap<String, Bag>();
		sentenceBeginnings = new ArrayList<String>();
	}

	public void add(String ngram, String nextLetter) {
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

	public void addNgramsIn(String inputText) {
		for (int i = 0 ; i < inputText.length() - 3 ; i++){
			String ngram;
			String nextLetter;

			ngram = inputText.substring(i , i + 3);
			nextLetter = inputText.substring(i + 3, i + 4);

			//System.out.println("Adding: " + ngram + " next is: " + nextLetter);

			add(ngram, nextLetter);
		}
	}

	public void populateModel(final String fileName) {
		repository = new DataRepository(fileName);
		final String[] lines = repository.getLines();
		for (String line : lines) {
			int i = 0;
			if(line!=null)
			{
				sentenceBeginnings.add(line.substring(0, 3));
				addNgramsIn(line);
			}
		}
	}

	public String getNextLetter(String state){
		if (map.containsKey(state)) {
			Bag b = map.get(state);
			return b.getNextLetterByPropability();
		}
		else {
			System.out.println("END OF SENTENCE " + state);
			return null;
		}
	}

	public String getRandomSentenceBeginning () {
		int num = (int) (Math.random()*sentenceBeginnings.size());
		return sentenceBeginnings.get(num).toString();
	}
}
