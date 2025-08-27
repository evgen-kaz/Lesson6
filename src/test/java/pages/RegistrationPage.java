package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    public static String email = "AnnaIvanova@test.ru";
    public static String emailWong = "AnnaIvanovatestru";
    public static String phone = "1234567890";
    public static String address = "USA";
    public static String firstName = "Anna";
    public static String lastName = "Ivanova";
    public static String subjects = "Physics";
    public static String hobbies = "Reading";
    public static String file = "cat.jfif";
    public static String stateCity = "Rajasthan";
    public static String city = "Jaipur";

    //напрмяую запрещаем с этими элементами работать. Для этого есть методы взаимодействия ниже
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            calendarInput = $ ("#dateOfBirthInput"),
            subjectsInput = $ ("#subjectsInput"),
            hobbiesCheckbox = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            stateCityInput = $("#stateCity-wrapper"),
            cityInput = $("#city"),
            submit = $("#submit"),
            modalContent = $(".modal-content") ;


    //создаем объект класса CalendarComponent
    CalendarComponent calendarComponent = new CalendarComponent();
    //создали объект класса ModalComponent
    RegistrationResultComponent modalComponent = new RegistrationResultComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
        //RegistrationPage вместо void и return this - это
        // чтобы вернулась эта же страница (объект) и чтобы не пришлось по сто раз из класса
        //RegistrationTest к ней обращаться через переменную registrationPage
        //return this = return new RegistrationPage();
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        userEmailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String phone) {
        userNumber.setValue(phone);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click(); //находим и кликаем на календарь
        //вызываем из класса CalendarComponent метод (установки даты) setDate
        calendarComponent.setDate(day, month, year);
        return this; //возвращаем объект RegistrationPage
    }

    //этот метод по сути будем использователь несколько раз для проверки разных данных
    //вынесен в отдельный компонент метод модального окна checkResult, т.к. он может встречаться не один раз
    public RegistrationPage checkResultInModal(String key, String value) {
        modalComponent.checkResult(key, value);
        return this;
    }

    public RegistrationPage notResultInModal(String key, String value) {
        modalComponent.notResult(key, value);
        return this;
    }

    public RegistrationPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String hobbies) {
        hobbiesCheckbox.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage setUploadPicture(String file) {
        uploadPicture.uploadFromClasspath(file);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String stateCity) {
        stateInput.click();
        stateCityInput.$(byText(stateCity)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput.click();
        cityInput.$(byText(city)).click();
        return this;
    }

    public RegistrationPage clickTheButton() {
        submit.click();
        return this;
    }

    public RegistrationPage getTextInAModalWindow() {
        modalContent.shouldNotBe();
        return this;
    }


}
