package pe.com.test.seleniumwd.pom.driver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class TuBodeguitaDriver {

	private static final String URL_NODE_CHOME = "http://localhost:4445/wd/hub";
	private static final String URL_NODE_FIREFOX = "http://localhost:4445/wd/hub";
	
	public final static WebDriver inicializarDriver(String navegador, boolean remoto) {
		WebDriver webDriver = null;
		try{
			System.out.println("Ejecución Remota: " + (remoto ? "SI" : "NO"));
			switch (navegador) {
			case "firefox":
				if(remoto){
					URL server = new URL(URL_NODE_FIREFOX);
					DesiredCapabilities capabilities=DesiredCapabilities.firefox();
				    webDriver = new RemoteWebDriver(server, capabilities);
				}else{
					System.setProperty("webdriver.gecko.driver", "C:\\Programas\\geckodriver-v0.14.0-win32\\geckodriver.exe");
					webDriver = new FirefoxDriver();
				}
				break;
			case "chrome":
				if(remoto){
					URL server = new URL(URL_NODE_CHOME);
					DesiredCapabilities capabilities=DesiredCapabilities.chrome();
				    webDriver = new RemoteWebDriver(server, capabilities);
				}else{
					System.setProperty("webdriver.chrome.driver", "C:\\Programas\\ChromeDriver\\chromedriver.exe");
					webDriver = new ChromeDriver();
				}
				break;
			}
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return webDriver;
	}

	public final static void cerrarPagina(WebDriver webDriver) {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
