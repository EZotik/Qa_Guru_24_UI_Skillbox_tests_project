package tests;

import config.CredentialConfig;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import pages.MyAccount;
import pages.RegistrationPage;
import utils.RandomUserDataGenerator;

import static io.qameta.allure.Allure.step;

@Tag("simple_test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тесты на регистрацию/авторизацию пользователя")
public class TestRegistrationForm extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    MyAccount myAccount = new MyAccount();
    RandomUserDataGenerator random = new RandomUserDataGenerator();
    CredentialConfig credentialConfig = ConfigFactory.create(CredentialConfig.class);

    @Test
    @Feature("Форма регистрации")
    @DisplayName("Регистрация нового пользователя")
    void newUserRegistrationTest() {
        step("Открываем главную страницу", () -> {
            registrationPage.openPage();
        });
        step("Указываем имя пользователя", () -> {
            registrationPage.setUserName(random.userName);
        });
        step("Указываем адрес почты", () -> {
            registrationPage.setEmail(random.email);
        });
        step("Указываем пароль", () -> {
            registrationPage.setPassword(random.password);
        });
        step("Кликаем на кнопку \"Зарегистрироваться\"", () -> {
            registrationPage.formRowButton();
        });
        step("Проверяем, что появилось окно с информацией о завершении регистрации", () -> {
            registrationPage.entryContent();
        });
        step("В форме о завершении регистрации присутствует заголовок", () -> {
            registrationPage.postTitle("Регистрация");
        });
        step("В форме о завершении регистрации присутствует информационное сообщение", () -> {
            registrationPage.checkingContentPage("Регистрация завершена");
        });
    }

    @Test
    @Feature("Форма авторизации")
    @DisplayName("Вход в аккаунт под зарегистрированным пользователем")
    void loginToAccountTest() {
        step("Открываем страницу с входом в личный кабинет", () -> {
            myAccount.openPage();
        });
        step("Вводим логин", () -> {
            myAccount.setUserName(credentialConfig.loginUserName());
        });
        step("Вводим пароль", () -> {
            myAccount.setPassword(credentialConfig.loginPassword());
        });
        step("Кликаем на кнопку войти", () -> {
            myAccount.clickLoginBtton();
        });
        step("Проверяем, что появилось окно с информацией о входе в личный кабинет", () -> {
            myAccount.checkingEntryContent();
        });
        step("В личном кабинете присутствует заголовок", () -> {
            myAccount.checkingPostTitle();
        });
        step("В личном кабинете  присутствует наименование пользователя", () -> {
            myAccount.checkingLoginName("Test1239");
        });
    }
}
