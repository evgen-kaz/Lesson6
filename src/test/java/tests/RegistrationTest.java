package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static pages.RegistrationPage.*;

public class RegistrationTest extends TestBase {
    //создали экземпляр страницы и к ней же обращаемся
    RegistrationPage registrationPage = new RegistrationPage();

    @DisplayName("Успешная регистрация с полным набором данных")
    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender("Female")
                .setUserNumber(phone)
                .setDateOfBirth("03", "December", "1900")
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setUploadPicture(file)
                .setAddress(address)
                .setState(stateCity)
                .setCity(city)
                .clickTheButton()
                .checkResultInModal("Student Name", firstName + " " + lastName)
                .checkResultInModal("Student Email", email)
                .checkResultInModal("Gender", "Female")
                .checkResultInModal("Mobile", phone)
                .checkResultInModal("Date of Birth", "03 December,1900")
                .checkResultInModal("Subjects", subjects)
                .checkResultInModal("Hobbies", hobbies)
                .checkResultInModal("Picture", file)
                .checkResultInModal("Address", address)
                .checkResultInModal("State and City", stateCity + " " + city);
    }

    @DisplayName("Успешная регистрация с минимальным набором данных")
    @Test
    void successfulRegistrationOnWithAMinimumCountOfData() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender("Female")
                .setUserNumber(phone)
                .clickTheButton()
                .getTextInAModalWindow()
                .checkResultInModal("Student Name", firstName + " " + lastName)
                .checkResultInModal("Gender", "Female")
                .checkResultInModal("Mobile", phone);
    }

    @DisplayName("Невозможна регистрация с неправильным email")
    @Test
    void registrationIsNotPossibleWithWrongEmailTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(emailWong)
                .setGender("Female")
                .setUserNumber(phone)
                .clickTheButton()
                .getTextInAModalWindow()
                .notResultInModal("Student Name", firstName + " " + lastName)
                .notResultInModal("Student Email", emailWong)
                .notResultInModal("Gender", "Female")
                .notResultInModal("Mobile", phone);
    }
}
