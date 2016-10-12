package com.github.devmix.sample.spark.core.dao;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Sergey Grachev
 */
public final class InitializationDao {

    @Inject
    private Sql2o sql2o;

    private void onInit(@Observes final ContainerInitialized event) {
        createUserTableAndData();
    }

    private void createUserTableAndData() {
        try (final Connection c = sql2o.beginTransaction()) {
            c.createQuery("DROP TABLE user IF EXISTS").executeUpdate();

            c.createQuery("CREATE TABLE user (\n" +
                    "    id bigint auto_increment primary key,\n" +
                    "    login varchar(255) not null,\n" +
                    "    password varchar(255)\n" +
                    ");").executeUpdate();

            c.createQuery("INSERT INTO user (login, password) VALUES (:login, :password);")
                    .addParameter("login", "user")
                    .addParameter("password", "pass")
                    .executeUpdate();

            c.commit();
        }
    }
}
