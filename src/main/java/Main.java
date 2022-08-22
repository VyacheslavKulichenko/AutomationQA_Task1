import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Main {
    public static void main(String[] args) {
         System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedriver.exe");
         WebDriver driver1 = new ChromeDriver();

         driver1.get("https://www.ukr.net");
         WebElement element = driver1.findElement(By.xpath("(//a[text()='Політика'])"));
         String parametr = element.getAttribute("offsetWidth");
         System.out.println(parametr);
         // DOM changes - page is refreshed, or element is removed and re-added
         element.click();

        //1.Обновить страницу
        driver1.navigate().refresh();
        driver1.findElement (By.xpath("(//a[text()='Політика'])")). click ();

        //2. Использовать ExpectedConditions, неявные и явные ожидание пока вебэлемент на странице не станет доступным
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement element2 = (new WebDriverWait(driver1, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[text()='Політика'])"))));

        //3.Использование Try Catch Block
            try {
                 driver1.findElement(By.xpath("(//a[text()='Політика'])")).click();
            } catch (StaleElementReferenceException e) {
                System.out.println("Attempting to recover from StaleElementReferenceException ...");

            }
        }

    }

