package com.luxoft.jva008.module01;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReaderWriterTutor1 {
	private static final String FILES_TEST_PATH = "files/test.txt";
	private static final String TEST_LINE = "test line";

	/**
	 * Write line TEST_LINE to the file FILES_TEST_PATH, using 
	 * method write of class BufferedWriter.
	 */
	public void writeToFile() {
		try(BufferedWriter out = 
				new BufferedWriter(
						new FileWriter(FILES_TEST_PATH))) {
			out.write(TEST_LINE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Reads line from file by using method readLine() 
	 * of class BufferedReader and returns it
	 * @return
	 */
	public String readFromFile() {
		try (BufferedReader in = 
					new BufferedReader(
							new FileReader(FILES_TEST_PATH))) {
			String line = in.readLine();
			log(line);
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void testStream() {
		writeToFile();
		String s = readFromFile();
		assertEquals(TEST_LINE, s);
	}
	
	@Before
	public void createFile() {
		File f = new File(FILES_TEST_PATH);
		try {
			f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
