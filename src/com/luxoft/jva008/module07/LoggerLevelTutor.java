package com.luxoft.jva008.module07;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.Test;

public class LoggerLevelTutor {
	
	@Test
	public void logLevels() throws IOException {
		Logger logger = Logger.getLogger(LoggerLevelTutor.class.getName());
		logger.setLevel(Level.ALL);
		
		try {
			Handler handler = new FileHandler("files/log.log");
//			Handler handler = new ConsoleHandler();
			logger.setUseParentHandlers(false);
			handler.setLevel(Level.ALL);
			
			logger.addHandler(handler);
//			System.out.println(logger.getHandlers()[0].toString());
			handler.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		logger.setFilter(new Filter() {
			public boolean isLoggable(LogRecord rec) {
				if (rec.getMessage().contains("filter")) {
					return false;
				}
				return true;
			}
		});
		
		logger.severe("Major disaster");
		logger.warning("Potential problem");
		logger.info("Standard output");
		logger.config("Some config notes");
		logger.fine("Fine detail");
		logger.finer("Finer detail");
		logger.finest("Finest detail");
	  }



}
