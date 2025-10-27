package br.ita.mvc.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Selenium functional tests for the Translation Web Application.
 * Tests translation functionality with words that exist and don't exist in the dictionary.
 */
public class TranslatorSeleniumIT {
    
    private WebDriver driver;
    private String baseUrl = "http://localhost:8080/translator-webapp";
    
    @Before
    public void setUp() {
        // Configure Chrome options for headless mode (useful for CI/CD)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        // Initialize ChromeDriver
        driver = new ChromeDriver(options);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Test 1: Translate a word that exists in the dictionary - "hello"
     * Expected: Should translate to "olá"
     */
    @Test
    public void testTranslateExistingWord_Hello() {
        // Navigate to the application
        driver.get(baseUrl + "/index.jsp");
        
        // Find the input field and enter "hello"
        WebElement wordInput = driver.findElement(By.id("word"));
        wordInput.sendKeys("hello");
        
        // Find and click the translate button
        WebElement translateButton = driver.findElement(By.cssSelector("button[type='submit']"));
        translateButton.click();
        
        // Wait for the result page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("translation")));
        
        // Verify the translation
        WebElement originalWordElement = driver.findElement(By.id("originalWord"));
        WebElement translationElement = driver.findElement(By.id("translation"));
        
        assertNotNull("Original word element should exist", originalWordElement);
        assertNotNull("Translation element should exist", translationElement);
        
        assertEquals("Original word should be 'hello'", "hello", originalWordElement.getText());
        assertEquals("Translation should be 'olá'", "olá", translationElement.getText());
    }
    
    /**
     * Test 2: Translate another word that exists in the dictionary - "computer"
     * Expected: Should translate to "computador"
     */
    @Test
    public void testTranslateExistingWord_Computer() {
        // Navigate to the application
        driver.get(baseUrl + "/index.jsp");
        
        // Find the input field and enter "computer"
        WebElement wordInput = driver.findElement(By.id("word"));
        wordInput.sendKeys("computer");
        
        // Find and click the translate button
        WebElement translateButton = driver.findElement(By.cssSelector("button[type='submit']"));
        translateButton.click();
        
        // Wait for the result page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("translation")));
        
        // Verify the translation
        WebElement originalWordElement = driver.findElement(By.id("originalWord"));
        WebElement translationElement = driver.findElement(By.id("translation"));
        
        assertNotNull("Original word element should exist", originalWordElement);
        assertNotNull("Translation element should exist", translationElement);
        
        assertEquals("Original word should be 'computer'", "computer", originalWordElement.getText());
        assertEquals("Translation should be 'computador'", "computador", translationElement.getText());
    }
    
    /**
     * Test 3: Translate a word that does NOT exist in the dictionary - "programming"
     * Expected: Should return the original word "programming"
     */
    @Test
    public void testTranslateNonExistingWord() {
        // Navigate to the application
        driver.get(baseUrl + "/index.jsp");
        
        // Find the input field and enter "programming"
        WebElement wordInput = driver.findElement(By.id("word"));
        wordInput.sendKeys("programming");
        
        // Find and click the translate button
        WebElement translateButton = driver.findElement(By.cssSelector("button[type='submit']"));
        translateButton.click();
        
        // Wait for the result page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("translation")));
        
        // Verify the translation (should be the same as input)
        WebElement originalWordElement = driver.findElement(By.id("originalWord"));
        WebElement translationElement = driver.findElement(By.id("translation"));
        
        assertNotNull("Original word element should exist", originalWordElement);
        assertNotNull("Translation element should exist", translationElement);
        
        assertEquals("Original word should be 'programming'", "programming", originalWordElement.getText());
        assertEquals("Translation should be 'programming' (word not found)", "programming", translationElement.getText());
    }
}
