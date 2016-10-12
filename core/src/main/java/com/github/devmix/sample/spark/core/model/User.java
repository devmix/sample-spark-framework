package com.github.devmix.sample.spark.core.model;

import lombok.ToString;
import lombok.Value;

/**
 * @author Sergey Grachev
 */
@Value
@ToString
public final class User {
    private Long id;
    private String login;
    private String password;
}
