package sentencegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Zane Kalva
 */
public class DataRepository {

    private List<String> lineList;

    DataRepository(final String fileName) {
        populateFromFile(fileName);
    }

    public List<String> getLineList() {
        return lineList;
    }

    public void populateFromFile(final String fileName) {
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            lineList = new ArrayList<String>();
            int counter = 0;
            while (input.hasNext()) {
                final String line = input.nextLine();
                lineList.add(line.substring(line.lastIndexOf("\t") + 1));
            }
            input.close();
        } catch (final FileNotFoundException exception) {
            System.err.println("FileNotFound: " + exception);
        }
    }
}
