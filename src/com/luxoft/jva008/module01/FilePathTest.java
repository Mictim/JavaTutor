package com.luxoft.jva008.module01;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FilePathTest {
	
	@Test
	public void testPath() {
		File f = new File("test/.././file.txt");
		System.out.println("Name: " + f.getName());
		System.out.println("Path: " + f.getPath());
		System.out.println("Absolute Path: " + f.getAbsolutePath());
		
		try {
			System.out.println("Canonical Path: " + f.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
