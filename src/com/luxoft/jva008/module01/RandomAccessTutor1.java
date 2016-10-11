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

public class RandomAccessTutor1 {
	
	private static final String FILES_TEST_PATH = "files/test.txt";

	/**
     * The method should open the file RandomAccessFile on path FILES_TEST_PATH
     * and write there the number 10 (type Integer), and then the string "test line"
     */
	public void randomAccessWrite() {

		try {
			RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "rw");
			f.writeInt(10);
			f.writeChars("test line");
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
     * The method should open the file RandomAccessFile on path FILES_TEST_PATH,
     * skip the number 10 and the word "test" (without reading them),
     * jump to the fifth letter, read and return the string "line"
     */
	public String randomAccessRead() {
		try (RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r"))
		{
			f.seek(14);
			String line = f.readLine();
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
	public void testRandomAccess() throws UnsupportedEncodingException  {
		randomAccessWrite();
		String s = null;
		int i = -1;
		
		try (RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r"))
		{
			i = f.readInt();
			s = f.readLine();
			f.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(i, 10);
		s = new String(s.getBytes("UTF-8"), "Unicode");
		assertEquals("test line", s);
		String read = randomAccessRead();
		read = new String(read.getBytes("UTF-8"), "Unicode");		
		assertEquals("line", read);
	}

}
