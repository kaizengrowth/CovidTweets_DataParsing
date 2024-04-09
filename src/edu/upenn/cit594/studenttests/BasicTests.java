package edu.upenn.cit594.studenttests;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BasicTests {

	/*
	 * Note no safety is provided. This routine is expected to fail with any error
	 * or exception in the student code.
	 */
	public String runMain(String[] args) throws Exception {
		PrintStream realout = System.out;

		/* Redirect stdout to a byte array for analysis */
		ByteArrayOutputStream test_output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(test_output));

		/* run the student main method */
		edu.upenn.cit594.Main.main(args);

		/* Restore the actual output so that we can still print */
		System.setOut(realout);

		return test_output.toString();
	}

	public Map<String, Integer> extractResults(String output) throws Exception {
		BufferedReader output_reader = new BufferedReader(new StringReader(output));
		Map<String, Integer> items = new HashMap<>();

		Pattern stateTweetPattern = Pattern.compile("^(?<state>.+?): (?<count>\\d+)$");
		String line;
		while ((line = output_reader.readLine()) != null) {
			Matcher m = stateTweetPattern.matcher(line);
			if (m.matches()) {
				String state = m.group("state");
				Integer count = Integer.parseInt(m.group("count"));
				items.put(state, count);
			} else {
				throw new Exception("Bad line: " + line);
			}
		}
		return items;
	}

	public List<String[]> extractLog(String logfile) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(logfile));
		List<String[]> items = new ArrayList<>();

		Pattern stateTweetPattern = Pattern.compile("^(?<state>.+?)\t(?<tweet>.+)$");
		String line;
		while ((line = reader.readLine()) != null) {
			Matcher m = stateTweetPattern.matcher(line);
			if (m.matches()) {
				String state = m.group("state");
				String tweet = m.group("tweet");
				items.add(new String[] { state, tweet });
			} else {
				reader.close();
				throw new Exception("Bad line: " + line);
			}
		}

		reader.close();
		return items;
	}

	public static String trivial_state = "Rhode Island and Providence Plantations";
	public static String trivial_text = "This assignment is worse than the flu";

	public void makeTrivialTest(String state_file, String tweet_file, String state, String location, String text) throws Exception {
		FileWriter states = new FileWriter(state_file);
		states.write(state + "," + location + "\n");
		states.close();

		FileWriter tweets = new FileWriter(tweet_file);
		tweets.write("[{\"location\":[" + location + "],\"text\":\"" + text + "\"}]\n");
		tweets.close();
	}

	public static void print2DStrings(List<String []> strings)
	{
		strings.stream().forEach((arr) -> System.out.println(Arrays.asList(arr)));
	}

	@Test
	public void testStuff() throws Exception {
		File logFile = new File("trivial_log.log");
		if(logFile.exists())
			logFile.delete();
		makeTrivialTest("trivial_states.csv", "trivial_tweets.json", trivial_state, "1.7,2.9",trivial_text);
		String results = runMain(new String[] { "trivial_tweets.json", "trivial_states.csv", "trivial_log.log"});
		System.out.println("output: " + results);
		System.out.println(extractResults(results));
		print2DStrings(extractLog("trivial_log.log"));
	}
	
	@Test
	public void testThreeThings() throws Exception {
		File logFile = new File("log.three_things");
		if(logFile.exists())
			logFile.delete();

		String results = runMain(new String[] { "flu_tweets.json", "states.csv", "log.three_things"});
		System.out.println("output: " + results);
		System.out.println(extractResults(results));
		print2DStrings(extractLog("log.three_things"));

		results = runMain(new String[] { "flu_tweets.txt", "states.csv", "log.three_things"});
		System.out.println("output: " + results);
		System.out.println(extractResults(results));
		print2DStrings(extractLog("log.three_things"));


		makeTrivialTest("trivial_states.csv", "trivial_tweets.json", trivial_state, "1.7,2.9",trivial_text);
		results = runMain(new String[] { "trivial_tweets.json", "trivial_states.csv", "log.three_things"});
		System.out.println("output: " + results);
		Map<String, Integer> resMap = extractResults(results);
		print2DStrings(extractLog("log.three_things"));
		assertTrue(resMap.size() == 1);
		
	}
}
