package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultComponent {
    //этот метод по сути будем использователь несколько раз для проверки разных данных
    public void checkResult(String key, String value) {
        //по тексту (byText) нашли ключ - это , например Student Name. Далее вышли наверх на 1 эелемент
        //и проверили, что там у него среди вложенных элементов есть - например, Anna Ivanova и далее по аналогии с другими полями
        $(".table").$(byText(key)).parent().shouldHave(text(value));
    }

    public void notResult(String key, String value) {
        $(".table").shouldNotBe();
    }
}
