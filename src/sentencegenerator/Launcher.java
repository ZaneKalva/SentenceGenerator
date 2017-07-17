package sentencegenerator;

/**
 * Created by Zane Kalva
 */
public class Launcher
{
	public static String FILENAME = "resources/input.txt";
	public static int SENTENCECOUNT = 4;

	public static void main (String[] args) {
		MarkovModel model = new MarkovModel();
		model.populateModel(FILENAME);

		int currentSentence = 0;

		//TODO get last X letters of "Lorem Ipsum and initialize output with the rest"
		String output = "Lorem ips";
		String state = "um ";
		output += state;

		while (currentSentence < SENTENCECOUNT) {
			String nextLetter = model.getNextLetter(state);
			if (nextLetter==null) {
				output += "\n";
				currentSentence++;
				if (currentSentence!=SENTENCECOUNT) {
					state = model.getRandomSentenceBeginning();
					output += state;
				}
			} else {
				output += nextLetter;
				state = state.substring(1, 3) + nextLetter;
			}
		}

		System.out.println(output);

	}
}
