package util;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
            
            extent.addSystemInfo("Environment", "QA");
            extent.loadConfig(new File("src/test/resources/extent-config.xml"));
        }
        
        return extent;
    }
}
