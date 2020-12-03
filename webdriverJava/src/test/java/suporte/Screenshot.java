package suporte;
 
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
import java.io.File;
 
public class Screenshot {
 
    public static void tirarScreenshot(WebDriver navegador, String arquivo) {
        File screenchot = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenchot, new File(arquivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}