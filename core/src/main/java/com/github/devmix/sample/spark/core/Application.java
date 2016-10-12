package com.github.devmix.sample.spark.core;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * @author Sergey Grachev
 */
public final class Application {

    public static void main(final String[] args) {
        final WeldContainer container = new Weld().initialize();
        Runtime.getRuntime().addShutdownHook(new Thread(container::shutdown));
    }
}
