package com.luxoft.jva008.module01;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class DataStreamTutor1 {
	private static final String FILES_TEST_PATH = "files/test.txt";
	private static final String TEST_LINE = "test line";

	/**
    * Writes a string TEST_LINE to the file FILES_TEST_PATH, using
    * method writeUTF of class DataOutputStream.
    * Also use BufferedOutputStream for buffering.
    * Then close the stream.
    */
	public void writeToFile() {
		try (DataOutputStream out = 
				new DataOutputStream( 
						new BufferedOutputStream(
								new FileOutputStream(FILES_TEST_PATH)))) {
			out.writeUTF(TEST_LINE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
    * Reads a line from a file using the method readUTF and returns it.
    * @return
	*/
	public String readFromFile() {
		try (DataInputStream in =
					new DataInputStream( 
							new BufferedInputStream(
									new FileInputStream(FILES_TEST_PATH)))) {
			String fileContent = in.readUTF();
			return fileContent;
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
