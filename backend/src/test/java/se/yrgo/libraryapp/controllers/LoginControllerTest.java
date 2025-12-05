package se.yrgo.libraryapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import io.jooby.*;
import io.jooby.annotations.*;
import static org.assertj.core.api.Assertions.*;
import org.h2.engine.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.*;

import se.yrgo.libraryapp.dao.*;
import se.yrgo.libraryapp.entities.*;
import se.yrgo.libraryapp.entities.forms.LoginData;
import se.yrgo.libraryapp.services.UserService;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class LoginControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private RoleDao roleDao;

    @Mock
    private SessionDao sessionDao;

    @BeforeEach
    void setUp() {
        // LoginController loginController = new LoginController(userService, roleDao,
        // sessionDao);
    }

    @Test
    void shouldGetRolesWhenLogin() {
        Context context = Mockito.mock(Context.class);
        String sessionCookie = "abc123";
        LoginData login = new LoginData("alice", "pwd123");

        UserId userId = new UserId(1);
        UUID newSession = UUID.randomUUID();

        List<Role> roles = List.of(Role.ADMIN.fromString("admin"));

        Mockito.when(userService.validate("alice", "pwd123"))
                .thenReturn(Optional.of(userId));

        Mockito.when(roleDao.get(userId))
                .thenReturn(roles);

        Mockito.when(sessionDao.create(userId))
                .thenReturn(newSession);

        LoginController loginController = new LoginController(userService, roleDao, sessionDao);
        List<Role> result = loginController.login(context, sessionCookie, login);

        assertThat(roles).isEqualTo(result);
        Mockito.verify(context).setResponseCookie(Mockito.any(Cookie.class));
    }

    @Test
    void shouldNotGetRolesIfNoUserWhenLogin() {
        Context context = Mockito.mock(Context.class);
        String sessionCookie = UUID.randomUUID().toString();
        LoginData login = new LoginData("alice", "pwd123");

        List<Role> roles = List.of();

        Mockito.when(userService.validate("alice", "pwd123"))
                .thenReturn(Optional.empty()); // did not found alice

        LoginController loginController = new LoginController(userService, roleDao, sessionDao);
        List<Role> result = loginController.login(context, sessionCookie, login);

        assertThat(roles).isEqualTo(result);
        Mockito.verify(context).setResponseCode(StatusCode.UNAUTHORIZED);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "baba" })
    void shouldNotGetRolesIfSessionIsInvalidWhenLogin(String cookie) {
        Context context = Mockito.mock(Context.class);
        String sessionCookie = cookie;
        LoginData login = new LoginData("alice", "pwd123");

        List<Role> roles = List.of();

        LoginController loginController = new LoginController(userService, roleDao, sessionDao);
        List<Role> result = loginController.login(context, sessionCookie, login);

        assertThat(roles).isEqualTo(result);
    }

    @Test
    void shouldGetRolesIfLoggedIn() {
        String sessionCookie = UUID.randomUUID().toString();
        LoginController loginController = new LoginController(userService, roleDao, sessionDao);

        UserId uid = new UserId(1);
        List<Role> roles = List.of(Role.ADMIN.fromString("admin"));

        Mockito.when(sessionDao.validate(UUID.fromString(sessionCookie))).thenReturn(uid);
        Mockito.when(roleDao.get(uid)).thenReturn(roles);
        List<Role> result = loginController.isLoggedIn(sessionCookie);

        assertThat(result).isEqualTo(roles);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "baba" })
    void shouldNotGetRolesIfLoggedOut(String sessionCookie) {
        LoginController loginController = new LoginController(userService, roleDao, sessionDao);

        // Kör metoden med null, tom eller ogiltig UUID
        List<Role> result = loginController.isLoggedIn(sessionCookie);

        // Förväntat resultat: tom lista
        assertThat(result).isEmpty();

        // sessionDao.validate ska aldrig anropas eftersom det fångas internt
        Mockito.verifyNoInteractions(roleDao);
    }

}
