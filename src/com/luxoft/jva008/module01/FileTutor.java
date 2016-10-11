package com.luxoft.jva008.module01;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FileTutor {
	
	/**
	 * The method must create a folder "test" and a file "test.txt" inside it 
	 * Also, output in the full path to the log file you have created
	 */
	public void createFile() {
	}
	
	/**
	 * This method should remove the "test" folder and the "test/test.txt" file
	 */
	public void deleteFile() {
	}
	
	
	@Test
	public void testCreateFile() {
		createFile();
		File f = new File("test/test.txt");
		assertTrue(f.exists());
	}
	
	@Test
	public void testDeleteFile() {
		deleteFile();
		File f = new File("test/test.txt");
		assertFalse(f.exists());
		assertFalse(new File("test").exists());
	}

}
