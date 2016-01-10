package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	static WebDriver driver;
	
	public static String getProperties(String resourceName, String key) {

		String value = "not set";
		
		String rescorceName = resourceName;
		// could also be a constant
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
		    props.load(resourceStream);
			// get the property value and print it out
			System.out.println(props.getProperty(key));
			value = props.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;

	  }
	
	public static WebDriver initializeDriver() {
		WebDriver driver = DriverProvider.getDriver();
		// DriverProvider.navigateToBaseUrl(driver);
		return driver;
	}

	public static String getDockerHubIp() throws SocketException {
		String ipDocker;
		try {
			NetworkInterface networkInterface = NetworkInterface.getByName("docker0");
			List<InterfaceAddress> ls = networkInterface.getInterfaceAddresses();
			InterfaceAddress ifa = ls.get(1);
			ipDocker = ifa.getAddress().getHostAddress();
			System.out.println("ip: " + ipDocker);

		} catch (Exception e) {
			System.out.println("docker0 interface nicht aktiv");
			ipDocker = "Docker Hub not running";
		}
		return ipDocker;
	}

	public static void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static Cookie storeCookies(WebDriver driver){
		
		File f = new File("browser.data");
	    try{
	         f.delete();
	         f.createNewFile();
	         FileWriter fos = new FileWriter(f);
	         BufferedWriter bos = new BufferedWriter(fos);

	         for(Cookie ck : driver.manage().getCookies()) {
	        	    bos.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()
	        	    	    +";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
	        	    bos.newLine();
	         }
	         bos.flush();
	         bos.close();
	         fos.close();
	     }catch(Exception ex){
	         ex.printStackTrace();
	     }
		return null;
		
	}
	public static void replaceCookie(WebDriver driver){
		try{
		     File f2 = new File("browser.data");
		     FileReader fr = new FileReader(f2);
		     BufferedReader br = new BufferedReader(fr);
		     String line;
		     while((line=br.readLine())!=null){
		         StringTokenizer str = new StringTokenizer(line,";");
		         while(str.hasMoreTokens()){
		             String name = str.nextToken();
		             String value = str.nextToken();
		             String domain = str.nextToken();
		             String path = str.nextToken();
		             Date expiry = null;
		             String dt;
		             /*if(!(dt=str.nextToken()).equals("null")){
		                 expiry = new Date(dt);
		             }*/
		             boolean isSecure = new Boolean(str.nextToken()).booleanValue();
		             Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);
		             driver.manage().addCookie(ck);
		         }
		     }
		}catch(Exception ex){
		     ex.printStackTrace();
		}
		
	}
	public static void renameLoggerFile() {
		File logOld = new File("logs/temp.log");		  
	    File logNew = new File("logs/logger_" + getTime() + ".log");
	    System.out.println("timestampfile: " + logNew);
	    boolean bool = true;
	    bool = logOld.renameTo(logNew);
	    System.out.println("rename: " + bool);
	}
	
	public static String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date).toString();
	}
}
