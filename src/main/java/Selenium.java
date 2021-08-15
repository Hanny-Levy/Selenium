
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Selenium {
    public static void main(String[] args) {
        boolean flag=false;
        System.out.println("Welcome to Ashkelon Academic College");
        while (!flag) {
            Scanner stringScanner = new Scanner(System.in);
            Scanner intScanner = new Scanner(System.in);
            String username = "", password = "";
            System.out.println("Write your user and password here to access your personal information: ");

            System.out.println("Username:");
            username = stringScanner.nextLine();
            System.out.println("Password:");
            password = stringScanner.nextLine();


            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\חני\\Desktop\\chromedriver.exe");
            ChromeDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
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
                exception.printStackTrace();
            }
            boolean login = false;

            try {
                List<WebElement> moodle = driver.findElements(By.tagName("a"));
                for (int i = 0; i < moodle.size(); i++) {
                    if (moodle.get(i).getText().equals("מערכת Moodle")) {
                        moodle.get(i).click();
                        login = true;
                        break;
                    }
                }
                if (!login) {
                    System.out.println("Wrong username or password try again!");
                } else {
                    try {
                        WebElement userMenu = driver.findElements(By.className("dropdown")).get(Def.USER_MENU);
                        if (userMenu != null)
                            userMenu.click();
                        WebElement coursesUpdates = driver.findElement(By.id("actionmenuaction-1"));
                        if (coursesUpdates != null)
                            coursesUpdates.click();
                        coursesUpdates = driver.findElement(By.id("groupingdropdown"));
                        if (coursesUpdates != null)
                            coursesUpdates.click();
                        List<WebElement> filters = driver.findElements(By.className("dropdown-item"));
                            WebElement allCoursesFilter = filters.get(Def.ALL_COURSES_FILTER);
                        allCoursesFilter.click();
                        WebElement cardDropFilter = driver.findElement(By.id("displaydropdown"));
                        cardDropFilter.click();
                        WebElement cardFilter = filters.get(Def.CARD_FILTER);
                        cardFilter.click();
                        WebElement sortingFilter = driver.findElement(By.id("sortingdropdown"));
                        sortingFilter.click();
                        WebElement courseNameFilter = filters.get(Def.COURSE_NAME_FILTER);
                        courseNameFilter.click();

                        TimeUnit.SECONDS.sleep(2);


                        List<WebElement> urlElements = driver.findElements(By.tagName("a"));
                        ArrayList<String> coursesUrl = new ArrayList<>();
                        List<String> coursesTitle = new LinkedList<>();

                        for (int i = 0; i < urlElements.size(); i++) {
                            String url = urlElements.get(i).getAttribute("href");
                            if (url.contains("https://moodle.aac.ac.il/course") && urlElements.get(i).getAttribute("class").contains("aalink coursename mr-2")) {
                                coursesUrl.add(url);
                                coursesTitle.add(urlElements.get(i).getText().replaceAll("שם הקורס", "").replaceAll("\n", ""));

                            }

                        }
                        System.out.println("Your courses are:");
                        TimeUnit.SECONDS.sleep(1);

                        for (int i = 0; i < coursesTitle.size() - Def.RECENTLY_VIEWED_COURSES; i++) {
                            System.out.println(coursesTitle.get(i) + " :" + i);
                        }
                        System.out.println();
                        System.out.println("Select a course you want to see more details about: \n" +
                                "(By his number)");
                        int userCourse;
                        try {
                            userCourse = intScanner.nextInt();
                            if (userCourse >= coursesTitle.size()-Def.RECENTLY_VIEWED_COURSES || userCourse < 0) {
                                System.out.println("This course does not exist Try again");
                                userCourse = intScanner.nextInt();
                            }
                            driver.get((coursesUrl.get(userCourse)));
                            TimeUnit.SECONDS.sleep(3);
                            userMenu = driver.findElements(By.className("dropdown")).get(Def.USER_MENU);
                            if (userMenu != null)
                                userMenu.click();
                            WebElement logoutMoodle = driver.findElement(By.id("actionmenuaction-6"));
                            if (logoutMoodle != null)
                                logoutMoodle.click();
                            TimeUnit.SECONDS.sleep(2);
                            WebElement logoutPersonalInfo = driver.findElements(By.tagName("li")).get(Def.LOGOUT_PERSONAL_INFO);
                            if (logoutPersonalInfo != null){
                                logoutPersonalInfo.click();
                                flag=true;
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (InputMismatchException exception) {
                        System.out.println("Please type only course number!");

                    }

                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }
    }


}





