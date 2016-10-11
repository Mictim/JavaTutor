package com.luxoft.jva008.module01;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReaderWriterTutor {
	private static final String FILES_TEST_PATH = "files/test.txt";
	private static final String TEST_LINE = "test line";

	/**
	 * Write line TEST_LINE to the file FILES_TEST_PATH, using 
	 * method write of class BufferedWriter.
	 */
	public void writeToFile() {
	}
	
	/**
	 * Reads line from file by using method readLine() 
	 * of class BufferedReader and returns it
	 * @return
	 */
	public String readFromFile() {
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
