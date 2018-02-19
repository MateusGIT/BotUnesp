/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;
//org.openqa.selenium.*- contains the WebDriver class needed to instantiate a new browser loaded with a specific driver

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import java.util.List;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Mateus Oliveira
 */
public class Bot {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://sistemas.unesp.br/sentinela/login.open.action";
        String expectedTitle = "UNESP : Portal de Sistemas";
        String actualTitle = "";
        //WebDriver's get() method is used to launch a new browser session and directs it to the URL that you specify as its parameter.
        driver.get(baseUrl);
        //actualTitle = driver.getTitle();
        WebElement usuario = driver.findElement(By.id("txt_usuario"));
        WebElement senha = driver.findElement(By.name("txt_senha"));
        WebElement login = driver.findElement(By.name("btn_entrar"));
        usuario.sendKeys("mateus_kito@hotmail.com");
        senha.sendKeys("n8q5f6a2");
        login.click();
        WebElement mensagem = driver.findElement(By.linkText("Para visualizá-las, clique aqui"));
        mensagem.click();
        JavascriptExecutor motorJs;
        List<String> msgs = new ArrayList<>();
        boolean flagfinal = true; //verifica se está na ultima pagina de mensagens
        while (flagfinal) {
            List<WebElement> mensagens = driver.findElements(By.xpath("//a[contains(@href, 'javascript:submitVisualizarMensagem')]"));
            for (WebElement msg : mensagens) {
                msgs.add(msg.getAttribute("href"));
                System.out.println(msg.getAttribute("href"));
            }
            try {
                WebElement pagina = driver.findElement(By.xpath("//a[contains(@title, 'Próxima página')]"));
                pagina.click();
            } catch (Exception e) {
                System.out.println("entrou aqui");
                break;
            }
        }
        for (String string : msgs) {
            motorJs = (JavascriptExecutor) driver;
            System.out.println(string);
            motorJs.executeScript(string.split(":")[1]);
            motorJs.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            driver.findElement(By.name("btn_voltar")).click();
            //System.out.println("a"); 
        }
    }
}
