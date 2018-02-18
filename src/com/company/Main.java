package com.company;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.seleniumhq.jetty9.server.HttpChannelState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {


    WebDriver driver;
    JavascriptExecutor jse;
    String destinationFile;
    String imageUrl;
    SimpleDateFormat sdf;

    public void startBrowser(){

    try {
        System.setProperty("webdriver.chrome.driver", "/Users/vijayraghavan/Downloads/Selenium/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/Users/vijayraghavan/Library/Application Support/Google/Chrome");


        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        
        driver.get("http://www.bing.com");


        Thread.sleep(2000);


       String name =  driver.findElement(By.id("bgDiv")).getCssValue("background-image");

        String allval[] = name.split("\"");

        System.out.println(name);
        System.out.println(Arrays.toString(allval));

        sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();


        imageUrl = allval[1];
        destinationFile = "/Users/vijayraghavan/Desktop/image" + sdf.format(date).toString() + ".jpg";

        saveImage(imageUrl, destinationFile);




        whatsapp();

            /*driver.findElement(By.xpath("//img[@class='']")).click();
            Thread.sleep(1000);

           *//* List<WebElement> all = driver.findElements(By.xpath("//li[@class='_10anr vidHz _28zBA']"));
            all.get(2).sendKeys("/Users/vijayraghavan/Downloads/DPFHQi6X4AEFopB.jpg-large.jpeg");*//*



            driver.findElement(By.xpath("//div[@title='Upload photo']")).sendKeys("/Users/vijayraghavan/Downloads/DPFHQi6X4AEFopB.jpg-large.jpeg");

            */




/*
        driver.findElement(By.id("hp_scroll_listen_pad")).click();

        driver.findElement(By.id("hp_scroll_listen_pad")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("hp_scroll_listen_pad")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("hp_scroll_listen_pad")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("hp_scroll_listen_pad")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("hp_scroll_listen_pad")).sendKeys(Keys.ARROW_DOWN);*/


//        startExecution();


    driver.quit();




        setWallpaper(destinationFile);

        Thread.sleep(5000);

    deleteFile(destinationFile);

    }catch (Exception e){
        System.out.println(e.getMessage());
    }


    }

    public void whatsapp(){
        try {
            driver.navigate().to("https://web.whatsapp.com/");
            Thread.sleep(10000);
            driver.findElement(By.xpath("//img[@class='Qgzj8 gqwaM']")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//input[@type='file']")).sendKeys(destinationFile);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@data-icon='minus']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='_3hV1n yavlE']")).click();
            Thread.sleep(60000);
        }catch (Exception e){

        }

    }


    public void deleteFile(String fileName){
        File file = new File(fileName);
        if(file.delete())
            System.out.println("File deleted");
        else
            System.out.println("File not deleted");
    }


    public void setWallpaper(String file)
            throws Exception {
        String as[] = {
                "osascript",
                "-e", "tell application \"Finder\"",
                "-e", "set desktop picture to POSIX file \"" + file + "\"",
                "-e", "end tell"
        };
        Runtime runtime = Runtime.getRuntime();
        Process process;
        process = runtime.exec(as);

    }


    public void saveImage(String imageUrl, String destinationFile)throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }



    public static void main(String[] args) {
	// write your code here
        Main m = new Main();
        m.startBrowser();

    }
}
