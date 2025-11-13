package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    @ParameterizedTest
    @CsvSource({
            "daniel, daniel",
            "Hej! 123 värld, hej  värld",
            "' Hej då ', ' hej då '",
            "HELLO, hello",
            "Café ÖÄÅ, café öäå",
            "123!¤%, ''",
            "'', ''" })
    void onlyLettersAndWhitespaceTest(String str, String expected) {
        assertThat(Utils.onlyLettersAndWhitespace(str).equals(expected)).isTrue();

    }

    @Test
    void onlyLettersAndWhitespace_NullTest() {
        String str = null;
        assertThat(Utils.onlyLettersAndWhitespace(str).equals("")).isTrue();

    }

    @ParameterizedTest
    @CsvSource({
            "D4ni31, daniel",
            "H3LL0, hello",
            "5p4c3, space",
            "7r33, tree",
            "1n53c7, lnsect",
            "C4fé ÖÄÅ 123!, café öäå le",
            "'1 4m 1337 h4x0r', 'l am leet haxor'"
    })
    void cleanAndUnLeetTest(String str, String expected) {
        String cleanStr = Utils.cleanAndUnLeet(str);
        assertThat(cleanStr.equals(expected)).isTrue();
    }

}
