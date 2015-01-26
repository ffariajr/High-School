package sets_and_maps;

import java.util.Scanner;
import java.util.TreeSet;

public class WordCount
{
	private TreeSet<String> set;

	public WordCount()
	{
		set = new TreeSet<String>();
	}

	private boolean addWord(String word)
	{
		return set.add(word);
	}

	public double add(String phrase)
	{
		int total = 0;
		int worked = 0;
		while (true)
		{
			phrase = phrase.trim();
			if (phrase.length() == 0)
				break;
			else if (phrase.indexOf(" ") < 0) phrase = phrase + " ";

			if (addWord(phrase.substring(0, phrase.indexOf(" ")))) worked++;
			total++;

			phrase = phrase.substring(phrase.indexOf(" "));
		}
		return (1.0 * worked) / total;
	}

	public int wordsSoFar()
	{
		return set.size();
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		WordCount count = new WordCount();
		System.out.println("Welcome to WordCount");
		System.out.println("Enter *quit* to quit");
		System.out.println("Enter *words* to return number of unique words");
		while (true)
		{
			System.out.println("enter sentence: ");
			String phrase = in.nextLine();
			if ("*quit*".equals(phrase)) break;
			if ("*words*".equals(phrase))
				System.out.println("there are " + count.wordsSoFar() + " unique words.");
			else count.add(phrase);

		}
		System.out.println("there are " + count.wordsSoFar() + " unique words.");
	}
}
