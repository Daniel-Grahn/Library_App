package se.yrgo.libraryapp.dao;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.*;

import com.radcortez.flyway.test.annotation.H2;

import se.yrgo.libraryapp.entities.DdsClassification;

@Tag("integration")
@H2
public class ClassificationDaoIntergratonTest {
    private static DataSource ds;

    @BeforeAll
    static void initDataSource() {
        // this way we do not need to create a new datasource every time
        final JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test");
        ClassificationDaoIntergratonTest.ds = ds;
    }

    @Test
    void shouldGetAvailable(){
        ClassificationDao classificationDao = new ClassificationDao(ds);

        DdsClassification mathClass = new DdsClassification(510, "Mathematics");

        List<DdsClassification> list = classificationDao.getAvailable();
        assertThat(list)
            .isNotEmpty()
            .contains(mathClass);
    }
}
