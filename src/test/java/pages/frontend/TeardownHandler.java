package pages.frontend;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;

import utility.AbstractTest;

public class TeardownHandler {
	private Logger logger = Logger.getLogger(AbstractTest.class);
	
	@AfterSuite(alwaysRun = true)
	public void renameLoggerFile(){
			logger.info("shutdown");
			utility.Utils.renameLoggerFile();
	}
}

