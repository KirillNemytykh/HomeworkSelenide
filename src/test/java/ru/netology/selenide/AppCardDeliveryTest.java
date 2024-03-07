package ru.netology.selenide;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class AppCardDeliveryTest {

    private String generateData(int addDays, String pattern) {
            return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeCompletedSuccessfuly() {
        Selenide.open("http://0.0.0.0:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String plannigDate = generateData(5,"dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(plannigDate);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(" .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldBe(Condition.exactText("Встреча успешно забронирована на " + plannigDate));

    }
}
