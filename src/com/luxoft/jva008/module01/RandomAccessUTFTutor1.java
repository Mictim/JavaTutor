package com.luxoft.jva008.module01;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

public class RandomAccessUTFTutor1 {

	private static final String FILES_TEST_PATH = "files/test.txt";

	/**
    * Write to the file FILES_TEST_PATH 2 UTF lines:
    * "test line" and "test line 2"
    */
	public void randomAccessWriteUTF() {
		try (RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "rw")) {
			f.writeUTF("test line");
			f.writeUTF("test line 2");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
    * Jump to the second line of the UTF, using seek,
    * read and return its value
    * @return
	*/
	public String randomAccessReadUTF() {
		try (RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r")) {
			f.seek(11);
			String line = f.readUTF();
			log("read line:" + line);
			return line;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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

	@Test
	public void testRandomAccessUTF() throws UnsupportedEncodingException  {
		randomAccessWriteUTF();
		String s1 = null, s2 = null;
		try (RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r")){
			s1 = f.readUTF();
			s2 = f.readUTF();
		} catch(IOException e) {
			e.printStackTrace();
		}
		assertEquals("test line", s1);
		assertEquals("test line 2", s2);
		String read = randomAccessReadUTF();
		assertEquals("test line 2", read);
	}

}
