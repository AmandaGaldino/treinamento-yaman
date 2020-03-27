package tests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Test2022 {
    private ChromeDriver driver;
    private WebDriverWait wait;
    private static String nome = "";
    private static String email = "";
    private static String senha = "";

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Test2022.nome = nome;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Test2022.email = email;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Test2022.senha = senha;
    }

    @Before
    public void acessaUrl() {
        System.getProperty("webdriver.chrome.driver", "chromedriver");
        this.driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 90);
        this.driver.get("https://srbarriga.herokuapp.com/login");
        this.driver.manage().window().maximize();
    }

    @Test
    public void movimentacao() {
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
        Assert.assertEquals("Bem vindo, " + getNome() + "!", texto_valida);
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.xpath("//a[@href='/movimentacao']"))));
        this.driver.findElement(By.xpath("//a[@href='/movimentacao']")).click();
        Select select = new Select(this.driver.findElement(By.xpath("//select[@id='tipo']")));
        select.selectByVisibleText("Despesa");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("data_transacao"))));
        this.driver.findElement(By.id("data_transacao")).sendKeys("06/03/2020");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("data_pagamento"))));
        this.driver.findElement(By.id("data_pagamento")).sendKeys("18/01/2020");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("descricao"))));
        this.driver.findElement(By.id("descricao")).sendKeys("ALUGUEL");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("interessado"))));
        this.driver.findElement(By.id("interessado")).sendKeys("Amanda Galdino");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("valor"))));
        this.driver.findElement(By.id("valor")).sendKeys("500");
        Select selectConta = new Select(this.driver.findElement(By.xpath("//select[@id='conta']")));
        selectConta.selectByVisibleText("Leticia");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("status_pago"))));
        this.driver.findElement(By.id("status_pago")).click();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.xpath("//button[@class='btn btn-primary']"))));
        this.driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        String movimentacaoComSucesso = this.driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Movimentação adicionada com sucesso!", movimentacaoComSucesso);
    }

    @After
    public void fechaBrowser() {
        this.driver.quit();
    }
}