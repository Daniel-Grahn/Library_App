package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class UsernameTest {
    @ParameterizedTest
    @ValueSource(strings = { "bosse", "daniel", "p@ssport0", "p@ssport9", "p@ssport11",
                "iudzvhbvlfbher123456789bvhdzbvbzhdbv"})
    void correctUsername(String userName) {
        assertThat(Username.validate(userName)).isTrue();
       
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { "name with space", "hej", "daniel?", "ödlan", "åsnan"})
    void incorrectUsername(String userName) {
       
        assertThat(Username.validate(userName)).isFalse();
    }
}
