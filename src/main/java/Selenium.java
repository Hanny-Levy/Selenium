import com.sun.deploy.util.JVMParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Selenium {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username , password ;
        System.out.println("Welcome to Ashkelon Academic College");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\חני\\Desktop\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        List<WebElement> personalInfo= driver.findElements(By.tagName("li"));
        for (int i = 0; i <= personalInfo.size(); i++) {
            if (personalInfo.get(i).getText().equals("מידע אישי")) {
                personalInfo.get(i).click();
                break;
            }
        }
        System.out.println("Write your user and password here to access your personal information .");
        List <WebElement> topHeader;
        do {
            System.out.println("Username:");
            username=scanner.nextLine();
            System.out.println("Password:");
            password=scanner.nextLine();
            WebElement usernameElement = driver.findElement(By.id("Ecom_User_ID"));
            usernameElement.sendKeys(username);
            WebElement passwordElement=driver.findElement(By.id("Ecom_Password"));
            passwordElement.sendKeys(password);
            WebElement signIn=driver.findElement(By.id("wp-submit"));
            signIn.click();
            topHeader=driver.findElements(By.id("menu-top-header"));
            System.out.println("Wrong username or password try again!");
        }
        while (topHeader.size()!=3);




        List <WebElement> moodle = driver.findElements(By.tagName("a"));
        for (int i=0; i<moodle.size();i++){
            if (moodle.get(i).getText().equals("מערכת Moodle")){
                moodle.get(i).click();
                break;
            }
        }

        WebElement userCourses = driver.findElement(By.id("actionmenuaction-2"));
       userCourses.click();

      //  List <WebElement> courses = driver.findElements(By.tagName("span"));
       // System.out.println(moodle.size());
      //  for (int i=0 ; i<courses.size();i++){
      //      System.out.println(i + courses.get(i).findElement(By.className("multiline")).getText());
      //  }

    }


        }









