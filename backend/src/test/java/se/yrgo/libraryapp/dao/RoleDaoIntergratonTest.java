package se.yrgo.libraryapp.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.radcortez.flyway.test.annotation.H2;

import se.yrgo.libraryapp.entities.UserId;

@Tag("integration")
@H2
public class RoleDaoIntergratonTest {
    private static DataSource ds;

    @BeforeAll
    static void initDataSource() {
        // this way we do not need to create a new datasource every time
        final JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test");
        RoleDaoIntergratonTest.ds = ds;
    }

    @Test
    void shouldgetRole(){
        RoleDao roleDao = new RoleDao(ds);
        UserId userId = new UserId(1);

        assertThat(roleDao.get(userId)).isEmpty();
    }
}
