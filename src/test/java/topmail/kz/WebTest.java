package topmail.kz;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class WebTest{

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {"Java", "API"})
    @ParameterizedTest(name = "Check book, which have in name {0}")
    void booksSearchTest(String testData) {
        open("/books");
        $("#searchBox").setValue(testData);
        $$(".action-buttons")
        .first()
        .shouldHave((text(testData)));
    }

    @CsvSource(
            value = {"Java/ Learning JavaScript Design Patterns",
             "API/ Designing Evolvable Web APIs with ASP.NET"},
            delimiter = '/'
    )
    @ParameterizedTest(name = "Check book, which have in name {0}")
    void booksSearchTestwithExpectedText(String searchQuery, String expectedText) {
        open("/books");
        $("#searchBox").setValue(searchQuery);
        $$(".action-buttons")
                .first()
                .shouldHave((text(expectedText)));
    }
}

