package utility;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;


public abstract class AbstractTest {

		
		private Logger logger = Logger.getLogger(AbstractTest.class);
		
		@AfterSuite(alwaysRun = true)
		public void renameLoggerFile(){
				logger.info("shutdown");
				utility.Utils.renameLoggerFile();
		}
	
}
