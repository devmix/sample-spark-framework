package com.github.devmix.sample.spark.core.controllers;

import com.github.devmix.sample.spark.core.dao.UserDao;
import com.github.devmix.sample.spark.core.model.User;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collections;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.title;
import static spark.Spark.get;

/**
 * @author Sergey Grachev
 */
public final class UserController {

    @Inject
    private FreeMarkerEngine templateEngine;

    @Inject
    private UserDao userDao;

    private void registerRoutes(@Observes final ContainerInitialized event) {
        get("/hello", (req, res) -> "Hello World");

        get("/template", (req, res) -> {
            final User user = userDao.findByLogin("user");

            assert user != null;

            return new ModelAndView(Collections.singletonMap("user", user.getLogin()), "sample.ftl");
        }, templateEngine);

        get("/html", (req, res) -> {
            final User user = userDao.findByLogin("user");

            assert user != null;

            return html().with(
                    head().with(
                            title("Welcome!")),
                    body().with(
                            h1("Welcome " + user.getLogin() + '!')))
                    .render();
        });
    }
}
