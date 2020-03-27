package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test2023 {
    private ChromeDriver driver;
    private WebDriverWait wait;
    private static String nome = "";
    private static String email = "";
    private static String senha = "";
    public static String getNome() {
        return nome;
    }
    public static void setNome(String nome) {
        Test2023.nome=nome;
    }
    public static String getEmail() {
        return email;
    }
    public static void setEmail(String email) {
        Test2023.email=email;
    }
    public static String getSenha() {
        return senha;
    }
    public static void setSenha(String senha) {
        Test2023.senha=senha;
    }
    @Before
    public void acessaUrl(){
        System.getProperty("webdriver.chrome.driver","chromedriver");
        this.driver  = new ChromeDriver();
        wait = new WebDriverWait(driver, 90);
        this.driver.get("https://srbarriga.herokuapp.com/login");
        this.driver.manage().window().maximize();
    }
    @Test
    public void validarLogin() {
        setNome("Amanda Galdino");
        setEmail("amanda@teste.com");
        setSenha("amanda123");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("email"))));
        this.driver.findElement(By.id("email")).sendKeys(getEmail());
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("senha"))));
        this.driver.findElement(By.id("senha")).sendKeys(getSenha());
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.xpath("//button[@class='btn btn-primary']"))));
        this.driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.xpath("//div[@class='alert alert-success']"))));
        String texto_valida = this.driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Bem vindo, " + getNome() + "!",texto_valida);
    }

    @After
    public void fechaBrowser(){
        this.driver.quit();
    }
}
