package MTMAutomation.DispatchTest.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	
	public ReadConfig()
{
	File src = new File("./src/main/resources/config.properties");

	try {
		FileInputStream fis = new FileInputStream(src);
		pro = new Properties();
		pro.load(fis);
	} catch (Exception e) {
		System.out.println("Exception is " + e.getMessage());
	}
}
	public String getProperty(String propertyName) {
		return this.pro.getProperty(propertyName);
	}

}
