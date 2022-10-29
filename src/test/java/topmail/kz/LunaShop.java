package topmail.kz;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import topmail.kz.data.Oriental;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.*;
import static topmail.kz.data.Oriental.*;

public class LunaShop  {

    @BeforeAll
    static void setUp() {
       Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    static Stream<Arguments> clotherSearchinLunaShop() {
        return Stream.of(
                Arguments.of(List.of("Cassius Sparring Tank", "Atlas Fitness Tank", "Tiberius Gym Tank", "Sinbad Fitness Tank"), Men),
                Arguments.of(List.of("Breathe-Easy Tank", "Antonia Racer Tank", "Maya Tunic", "Chloe Compete Tank"), Women)

        );
    }

    @MethodSource("clotherSearchinLunaShop")
    @ParameterizedTest (name = "Searching popular Tops in Luna shop {1}")
    void clotherSearchinLunaShop(List<String> listOfTops, Oriental locale) {
        open("https://magento.softwaretestingboard.com");
        $x("//span[text()='"+locale+"']").click();
        $x("//a[text()='Tops']").click();
        $$(".product-item-link").first(4).shouldHave(CollectionCondition.texts(listOfTops));
    }

}

