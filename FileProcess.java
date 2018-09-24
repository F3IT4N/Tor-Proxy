/*
 * Creator --> F3IT4N <--
 */
package fileprocess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FileProcess {

    public static void main(String[] args) {
        //for (;;) { }
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            Thread.sleep(1000);
            runWeb();
            Runtime.getRuntime().exec("taskkill /F /IM tor.exe");
            Thread.sleep(3000);
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(FileProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        //deleteFile();
    }

    private static void runWeb() throws IOException, InterruptedException {
        String driP = System.getProperty(("user.home"), "Desktop");
        Runtime.getRuntime().exec(driP + "\\Desktop\\Tor Browser\\Browser\\TorBrowser\\Tor\\tor.exe");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server=socks5://" + "127.0.0.1" + ":" + "9050","--process-per-site","--start-maximized","--disable-webrtc-encryption");
        options.addExtensions(new File("WebRTC-Leak-Shield_v1.0.4.crx"));
        options.addExtensions(new File("Do-Not-Track_v0.1.1.crx"));
        options.addExtensions(new File("Im-not-a-robot-captcha-clicker_v0.3.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        
        driver.get("");

        Thread.sleep(15000);
        driver.manage().deleteAllCookies();
        //driver.quit();
    }

    private static void deleteFile() {
        String dirPat = System.getProperty(("user.home"), "Downloads");
        File file = new File(dirPat + "\\Downloads");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }
}
