package com.github.devmix.sample.spark.core.dao;

import com.github.devmix.sample.spark.core.model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * @author Sergey Grachev
 */
public final class UserDao {

    @Inject
    private Sql2o sql2o;

    @Nullable
    public User findByLogin(final String login) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("select * from user where login = :login")
                    .addParameter("login", login)
                    .executeAndFetchFirst(User.class);
        }
    }
}
