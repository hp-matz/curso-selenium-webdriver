package tests;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import suporte.Generator;
import suporte.Screenshot;
 
public class InformacoesUsuarioTest {
	private WebDriver navegador;
	
	@Rule
	public TestName test = new TestName();
	
	@Before
	public void setUp() {
		
		//Abrindo navegador
		System.setProperty("webdriver.edge.driver", "C:\\Users\\SempreIT\\drivers\\msedgedriver.exe");
		navegador = new EdgeDriver();
		//tempo pro test ser executado
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
		//navegando para página
		navegador.get("http:\\www.juliodelima.com.br/taskit/");
				
		//clickar no link de nome "Sign in"
		navegador.findElement(By.linkText("Sign in")).click();
				
		//identificar formulário do id "signinbox"
		WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));
				
		//digitar "julio0001" no "login" com id"signinbox"
		formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");
				
		//digitar "123456" em "password" com id"signbox"
		formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
				
		//clickar no link "SIGN IN"
		navegador.findElement(By.linkText("SIGN IN")).click();
				
		//clickar no link de class "me"
		navegador.findElement(By.className("me")).click();
				
		//clickar no link "more date about you"
		navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		
	}
	
	//@Test
	public void testAdcionarUmaInformacaoAdcionalDoUsuario() {
		
		//clickar no botão atráves do xpath "//button[@data-target="addmoredata"]"
		navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
		
		//achar popup "moredata"
		WebElement popupMoredata = navegador.findElement(By.id("addmoredata"));
		
		//clickar ns comboBox "type" e selecionar a opção "Phone"
		WebElement campoType = popupMoredata.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText("Phone");
		
	
		//no campo de nome "contact" digitar "+12345678900"
		popupMoredata.findElement(By.name("contact")).sendKeys("+12345678900");
	
		//clickar no link de nome "save"
		popupMoredata.findElement(By.linkText("SAVE")).click();
		
		//validar popup de id"toast-container" mensagem "Your contact has been added!"
		WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText();
		assertEquals("Your contact has been added!", mensagem);
		
		}
	
	@Test
	public void testRemoverInformacaoUsuario() {
		//clickar no elemento pelo xpath: //span[text()="+12345678900"]/following-sibling::a
		navegador.findElement(By.xpath("//span[text()=\"+12345678900\"]/following-sibling::a")).click();
	
		//confirmar a janela JS
		navegador.switchTo().alert().accept();
		
		//validar mensagem == "Rest in peace, dear phone!" 
		WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText();
		assertEquals("Rest in peace, dear phone!", mensagem);
		
		String screenshotArquivo = "C:\\Users\\SempreIT\\test-report\\udemy\\" +Generator.dataHoraArquivo() + test.getMethodName() + ".png";
		Screenshot.tirarScreenshot(navegador, screenshotArquivo);
		
		//aguardar até 10s pra mensagemPop sumir
		WebDriverWait aguardar = new WebDriverWait(navegador, 10);
		aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
		
		//clickar no link de nome "Logout"
		navegador.findElement(By.linkText("Logout")).click();
	
	}
	
	
	@After
	public void tearDown() {
		
		//fechar a guia
		navegador.close();
				
	}
}
