package config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class InitializeBrowser {
	public static WebDriver driver;
	
	//For Extent report we need to initialize some variables
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	ExtentHtmlReporter htmlreporter;
	String method;
	
	@BeforeTest
	public void generateReports()
	{
		htmlreporter = new 	ExtentHtmlReporter("reports/AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
	}
	
	//to get report method name
	@BeforeMethod
	public void methodName(Method method)
	{
		parentTest = extent.createTest(method.getName());
	}
	
   @BeforeClass
  public void beforeClass() {
	  WebDriverManager.chromedriver().setup();
	  driver= new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
	  extent.flush();
  }

}
