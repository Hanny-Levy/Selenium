
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Selenium {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.println("Welcome to Ashkelon Academic College");
        System.out.println("Write your user and password here to access your personal information .");
        System.out.println("Username:");
        username = scanner.nextLine();
        System.out.println("Password:");
        password = scanner.nextLine();
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\חני\\Desktop\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        try {
            List<WebElement> personalInfo = driver.findElements(By.tagName("li"));
            for (int i = 0; i <= personalInfo.size(); i++) {
                if (personalInfo.get(i).getText().equals("מידע אישי")) {
                    personalInfo.get(i).click();
                    break;
                }
            }
            WebElement usernameElement = driver.findElement(By.id("Ecom_User_ID"));
            if (usernameElement != null)
                usernameElement.sendKeys(username);
            WebElement passwordElement = driver.findElement(By.id("Ecom_Password"));
            if (passwordElement != null)
                passwordElement.sendKeys(password);
            WebElement signIn = driver.findElement(By.id("wp-submit"));
            signIn.click();

        } catch (Exception exception) {

        }

        try {
            List<WebElement> moodle = driver.findElements(By.tagName("a"));
            for (int i = 0; i < moodle.size(); i++) {
                if (moodle.get(i).getText().equals("מערכת Moodle")) {
                    moodle.get(i).click();
                    break;
                }
            }
            WebElement userMenu = driver.findElements(By.className("dropdown")).get(0);
            if (userMenu != null)
                userMenu.click();
            WebElement coursesUpdates = driver.findElement(By.id("actionmenuaction-1"));
            if (coursesUpdates != null)
                coursesUpdates.click();
            coursesUpdates = driver.findElement(By.id("groupingdropdown"));
            if (coursesUpdates != null)
                coursesUpdates.click();
            WebElement allCoursesFilter = driver.findElements(By.className("dropdown-item")).get(8);
            allCoursesFilter.click();
            List <WebElement> allCourses=driver.findElements(By.className("aalink coursename mr-2"));
            if (allCourses.size()!=0)
                for (int i=0 ;i<allCourses.size();i++)
                    System.out.println(allCourses.get(i).getText());


        }
           catch(Exception exception)

    {
        System.out.println("Wrong username or password try again!");
    }


    //}
    //  List <WebElement> courses = driver.findElements(By.tagName("span"));
    // System.out.println(moodle.size());
    //  for (int i=0 ; i<courses.size();i++){
    //      System.out.println(i + courses.get(i).findElement(By.className("multiline")).getText());
    //  }

}
    }












