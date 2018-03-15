package com.systemhub;

/**
 * Created by oleksandr.serdiuk on 22/07/2017.
 */

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class AcceptanceTest {

    @BeforeMethod
    public static void openSite() {
        open("https://member.systemhub.com/");
    }

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 20000;
    }

    @AfterMethod
    public static void logout() {
        closeWebDriver();
    }

    @Test
    public void SuccessfulClassicSignIn() { //После логина нажимается иконка с юзером и проверяется есть ли кнопка Logout
        $("#application_models_forms_LoginForm_username").setValue("cro.gen4@gmail.com");
        $("#application_models_forms_LoginForm_password").setValue("123456").pressEnter();
        $("#userDropdownMenu").click();
        $(".js-page-loading.logout").click();
    }

    @Test (priority = 1)
    public void SuccessfulSignInviaGoogle() {
        $(".google-login-text").click();
        $("#identifierId").setValue("cro.gen4@gmail.com").pressEnter();
        $(byXpath(".//*[@id='password']/div[1]/div/div[1]/input")).setValue("Apple210189").pressEnter();
        $("#userDropdownMenu").hover().click();
        $(".logout").should(exist);
    }


}
