package com.example.demo;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class BrowserTest {

    @Test
    public void chromeTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        Thread.sleep(3000);
        driver.findElement(By.id("LocationName")).sendKeys("Toronto");
        driver.findElement(By.id("LocationButton")).click();
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void firefoxTest() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        Thread.sleep(3000);
        driver.findElement(By.id("input-vaadin-text-field-3")).sendKeys("Montreal");
        driver.findElement(By.id("LocationButton")).click();
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void edgeTest() throws InterruptedException {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.get("http://localhost:8080/");
        Thread.sleep(3000);
        driver.findElement(By.id("LocationName")).sendKeys("New York");
        driver.findElement(By.id("LocationButton")).click();
        Thread.sleep(3000);
        driver.quit();
    }



}
