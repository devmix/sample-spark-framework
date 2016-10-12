package com.github.devmix.sample.spark.core;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.sql2o.Sql2o;
import spark.template.freemarker.FreeMarkerEngine;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Sergey Grachev
 */
public final class Resources {

    @Produces
    @Singleton
    public static Sql2o sql2o() {
        return new Sql2o("jdbc:h2:./db", null, null);
    }

    @Produces
    @Singleton
    public static FreeMarkerEngine freeMarkerEngine() {
        final Configuration configuration = new Configuration();
        configuration.setTemplateLoader(new ClassTemplateLoader(Resources.class, "/views"));
        return new FreeMarkerEngine(configuration);
    }
}
