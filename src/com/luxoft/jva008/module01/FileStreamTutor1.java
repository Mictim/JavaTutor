package com.luxoft.jva008.module01;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class FileStreamTutor1 {
	private static final String FILES_TEST_PATH = "files/test.txt";
	private static final String TEST_LINE = "test line";

	/**
    * Writes a string TEST_LINE to the file FILES_TEST_PATH ,
    * converting the string into array of bytes.
    * For the writing, use the class FileOutputStream.
    */
	public void writeToFile() {
		try (FileOutputStream fileOutputStream = new FileOutputStream(FILES_TEST_PATH)) {
			fileOutputStream.write(TEST_LINE.length());
			byte[] s = TEST_LINE.getBytes();
			fileOutputStream.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
    * Reads a line from a file and returns it using FileInputStream.
    * @return
	*/
	public String readFromFile() {
		try (FileInputStream fileInputStream = new FileInputStream(FILES_TEST_PATH)) {
			int i = fileInputStream.read();
			byte[] s = new byte[i];
			fileInputStream.read(s, 0, i);
			String str = new String(s);
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void testFileStream() {
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
