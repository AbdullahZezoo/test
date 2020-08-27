/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;


import static com.google.common.base.Predicates.equalTo;
import java.io.BufferedReader;
import java.io.File;
import java.net.URISyntaxException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.openqa.selenium.remote.http.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 *
 * @author AbdullahZezo
 */
public class TestingNGTest {
    
    public WebDriver driver ;

        
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\AbdullahZezo\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
      
    @Test(priority=1)
    public void validateRegister() throws InterruptedException {
       // Navigate to a web page
	    driver.get("https://www.phptravels.net/register");
	 
	    // Perform actions on HTML elements, entering text and submitting the form
	    WebElement firstnameElement = driver.findElement(By.name("firstname"));
	    WebElement lastnameElement = driver.findElement(By.name("lastname"));
	    WebElement emailElement = driver.findElement(By.name("email"));
	    WebElement phoneElement = driver.findElement(By.name("phone"));
	    WebElement passwordElement = driver.findElement(By.name("password"));
	    WebElement confirmpasswordElement = driver.findElement(By.name("confirmpassword"));
	    WebElement buttonElement = driver.findElement(By.xpath("//*[@id='headersignupform']/div[8]/button"));

	    firstnameElement.sendKeys("Abdullah");
	    lastnameElement.sendKeys("Abdelaziz");
	    emailElement.sendKeys("abdullahabdelaziz000@gmail.com");
	    phoneElement.sendKeys("01226302807");
	    passwordElement.sendKeys("Ao012011");
	    confirmpasswordElement.sendKeys("Ao012011");
	    buttonElement.submit();
	    
            Thread.sleep(5000);
            String url = driver.getCurrentUrl();
            String _url = "https://www.phptravels.net/account/" ;
            System.out.println(url);
            Assert.assertEquals(url, _url);
          
           
   }
       
    @Test(priority=2)
    public void validateLogin() throws InterruptedException {
        // Navigate to a web page
        driver.get("https://www.phptravels.net/login");

        // Perform actions on HTML elements, entering text and submitting the form
        WebElement usernameElement = driver.findElement(By.name("username"));
        WebElement passwordElement = driver.findElement(By.name("password"));
        WebElement buttonElement   = driver.findElement(By.xpath("//*[@id='loginfrm']/button"));

        usernameElement.sendKeys("abdullahabdelaziz000@gmail.com");
        passwordElement.sendKeys("Ao012011");
        buttonElement.click();
        
        Thread.sleep(5000);
        String url = driver.getCurrentUrl();
        String _url = "https://www.phptravels.net/account/" ;
        System.out.println(url);
        Assert.assertEquals(url, _url);
    }
    
   
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException{
		
	if(result.getStatus()==ITestResult.FAILURE){
           takeScreenShot(result.getName());
            driver.quit();
        }
        driver.close();
        
    }
    
     public void takeScreenShot(String methodName) throws IOException {
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //The below method will save the screen shot in d drive with test method name
            String filePath = "C:\\Users\\AbdullahZezo\\Documents\\NetBeansProjects\\tester\\test\\FailedTestsScreenshots\\" ;
            FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
            System.out.println("***Placed screen shot in "+filePath+" ***");
    }
    
    public void getResponse()
      throws ClientProtocolException, IOException, URISyntaxException {

        // Given
        HttpGet request = new HttpGet("https://www.phptravels.net/register");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line="";
		StringBuffer sb = new StringBuffer();
		while((line=br.readLine())!=null)
		{
			sb.append(line);
		}
		System.out.println(response.getStatusLine().getStatusCode());
		//System.out.println(sb);
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//Response//googleMap.json");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
    }
      
    
   /* public void getStatus() throws URISyntaxException, IOException {
        HttpRequestInterceptor requestInterceptor = new HttpRequestInterceptor() {
            @Override
            public void process(org.apache.http.HttpRequest request, org.apache.http.protocol.HttpContext context) throws HttpException, IOException {
                if(request.containsHeader("sample-header")) {
                }
                //Printing remaining list of headers
                org.apache.http.Header[] headers= request.getAllHeaders();
                for (int i = 0; i<headers.length;i++) {
                   System.out.println(headers[i].getName());
                }
            }
                
            
        };
      //Creating a CloseableHttpClient object
       CloseableHttpClient httpclient =
       HttpClients.custom().addInterceptorFirst(requestInterceptor).build();

      //Creating a request object
      HttpGet httpget1 = new HttpGet("https://www.phptravels.net/register");

      //Executing the request
      org.apache.http.HttpResponse httpresponse = (org.apache.http.HttpResponse) httpclient.execute(httpget1);

      //Printing the status line
      System.out.println(httpresponse.getStatusLine());
      
    }
*/
}
    
