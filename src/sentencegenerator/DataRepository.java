package sentencegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by Zane Kalva
 */
public class DataRepository
{

	String[] lines;

	DataRepository() {
		populateFromFile();
	}

	public void populateFromFile ()
	{
		try
		{
			File file = new File("resources/input.txt");
			Scanner input = new Scanner(file);

			lines = new String[10000];
			int counter = 0;
			while (input.hasNext())
			{
				//String nextToken = input.next();
				//or to process line by line
				final String line = input.nextLine();
				lines[counter] = line.substring(line.lastIndexOf("\t")+1);
				counter++;
			}
			input.close();
		}
		catch (final FileNotFoundException exception)
		{
			System.out.println("FileNotFound: " + exception);
		}

	}

}
