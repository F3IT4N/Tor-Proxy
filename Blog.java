package blog;

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

public class Blog {

    public static void main(String[] args){
        
            try {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                Thread.sleep(1000);
                runWeb();
                Runtime.getRuntime().exec("taskkill /F /IM tor.exe");
                Thread.sleep(3000);
            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            }
            deleteFile();
    }
    
    private static void runWeb() throws IOException, InterruptedException {
        CustomBlog my = new CustomBlog();
        String driP = System.getProperty(("user.home"), "Desktop");
        Runtime.getRuntime().exec(driP + "\\Tor\\tor.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server=socks5://" + "127.0.0.1" + ":" + "9050");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.quit();
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
