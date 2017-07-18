package sentencegenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Zane Kalva
 */
public class Launcher {
    public static String FILENAME = "resources/input.txt";

    public static void main(String[] args) throws IOException {
        MarkovModel model = new MarkovModel();
        model.populateModel(FILENAME);
        generateSentences(model);
    }

    public static void generateSentences(final MarkovModel model) throws IOException {
        int sentenceCount = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String separator = System.getProperty("line.separator");

        System.out.println("Ievadiet teikumu skaitu:");
        try {
            sentenceCount = Integer.parseInt(br.readLine());
            if (sentenceCount > 0) {
                getSenteces(sentenceCount, model);
            } else {
                System.err.println("Sis nav naturals skaitlis!");
            }
            System.out.println(separator);
            generateSentences(model);

        } catch (NumberFormatException nfe) {
            System.err.println("Nepareizs formats!");
            System.out.println(separator);
            generateSentences(model);
        }
    }

    public static void getSenteces(final int sentenceCount, final MarkovModel model) {
        int currentSentence = 0;
        StringBuilder output = new StringBuilder();
        output.append("Lorem ip");
        String state = "sum ";
        output.append(state);
        String nextLetter;

        while (currentSentence < sentenceCount) {
            nextLetter = model.getNextLetter(state);
            if (nextLetter == null) {
                output.append("\n");
                currentSentence++;
                if (currentSentence != sentenceCount) {
                    state = model.getRandomSentenceBeginning();
                    output.append(state);
                }
            } else {
                output.append(nextLetter);
                state = state.substring(1, MarkovModel.NGRAMLENGTH) + nextLetter;
            }
        }
        System.out.println(output.toString());
    }
}