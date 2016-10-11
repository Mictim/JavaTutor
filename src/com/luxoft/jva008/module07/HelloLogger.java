package com.luxoft.jva008.module07;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloLogger {
	public static void main(String[] args) {
	    Logger logger = Logger.getLogger(HelloLogger.class.getName());

	    logger.severe("Major disaster");
	    logger.warning("Potential problem");
	    logger.info("Standard output");
	    logger.config("Some config notes");
	    logger.fine("Fine detail");
	    logger.finer("Finer detail");
	    logger.finest("Finest detail");
	    
	    Logger base = Logger.getLogger("org.jdom");
	    Logger elt =  Logger.getLogger("org.jdom.Element");
	    Logger attr = Logger.getLogger("org.jdom.Attribute");
	    // base == elt.getParent()

	    elt.info("Displayed");
	    attr.info("Displayed");
	    base.setLevel(Level.SEVERE);
	    elt.info("Hidden");
	    attr.info("Hidden");
	    elt.setLevel(Level.INFO);
	    elt.info("Displayed");
	    attr.info("Hidden");

	    elt.setLevel(null);
	    elt.info("Hidden");

	  }


}
