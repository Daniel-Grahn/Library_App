package se.yrgo.libraryapp.services;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Test
    @SuppressWarnings("deprecation")
    void correctLogin() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash = "password";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService
                .validate(username, password))
                .isEqualTo(Optional.of(id));
    }

    @Test
    @SuppressWarnings("deprecation")
    void registerUserSuccessfully() {
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        UserService userService = new UserService(userDao, encoder);
        String name = "daniel";
        String realName = "Daniel";

        String password = "yourStronPassWord";
        String hassPassw = "yourStronPassWord";

        when(userService.registerUser(name, realName, hassPassw)).thenReturn(true);
        assertThat(userService.registerUser(name, realName, password)).isTrue();
    }

    @Test
    @SuppressWarnings("deprecation")
    void shouldEscapeApostrophesInRealName() {
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        UserService userService = new UserService(userDao, encoder);

        String name = "ian";
        String realName = "Ian O'Toole";

        String password = "yourStronPassWord";
        String hassPassw = "yourStronPassWord";

        userService.registerUser("ian", "Ian O'Toole", "secret");

        when(userService.registerUser(name, realName, hassPassw)).thenReturn(true);
        assertThat(userService.registerUser(name, realName, password)).isTrue();

        verify(userDao).register(
                eq("ian"),
                eq("Ian O\\'Toole"),
                eq("secret"));
    }

    @ParameterizedTest
    @SuppressWarnings("deprecation")
    @ValueSource(strings = {
            "ab",
            "   ",
    })
    @NullAndEmptySource
    void NameisNotAvailableIfNameTooShort(String name) {
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.isNameAvailable(name)).isFalse();
    }

    @Test
    @SuppressWarnings("deprecation")
    void NameAvailableWhenNameIsValid() {
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        UserService userService = new UserService(userDao, encoder);

        String name = "Foo";

        when(userDao.isNameAvailable(name)).thenReturn(true);

        boolean result = userService.isNameAvailable(name);

        assertThat(result).isTrue();
    }

}
