package se.yrgo.libraryapp.validators;

import static org.assertj.core.api.Assertions.assertThat;

// import java.util.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class RealNameTest {
    // private static final Set<String> invalidWords = new HashSet<>();

    @ParameterizedTest
    @ValueSource(strings = { "daniel", "lkvlsmvlksv", ""})
    void validatesGodName(String name) {
        Boolean valid = RealName.validate(name);
        assertThat(valid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "bullspit",
            "frick",
            "heck",
            " heck ",
            "HECK",
            "H3CK",
            "shiet",
             })
    @NullSource
    void validatesBadName(String name) {
        Boolean valid = RealName.validate(name);
        assertThat(valid).isFalse();
    }

}