package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.DELETE;
import static ru.netology.DataHelper.generateDate;


class DeliveryTest {
    @BeforeEach
    void shouldOpenWeb() {
        open("http://localhost:9999");
    }

    @Test
    void shouldAcceptInformation() {
        $("[data-test-id=city] input").setValue(DataHelper.getNewCity());
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.CONTROL, "a", DELETE), generateDate(3));
        $("[data-test-id='name'] input").setValue(DataHelper.getNewName());
        $("[data-test-id='phone'] input").setValue(DataHelper.getNewPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Встреча успешно"))
                .shouldBe(visible, Duration.ofSeconds(50));
        //$(withText(dateFirst))
        //      .shouldBe(visible);
        //$("[data-test-id='date'] input")
        //        .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.CONTROL, "a", DELETE), generateDate(5));
        $$("button").get(1).click();
        $(withText("У вас уже запланирована встреча на другую дату."))
                .shouldBe(visible, Duration.ofSeconds(45));
        $(withText("Перепланировать")).click();
        $(withText("Встреча успешно ")).shouldBe(visible, Duration.ofSeconds(45));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Встреча успешно запланирована на  " + generateDate(5)), Duration.ofSeconds(15));

    }

}

