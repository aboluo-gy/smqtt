package io.github.quickmsg.broker.common.http.annotation;



import io.github.quickmsg.broker.common.http.enums.HttpType;

import java.lang.annotation.*;

/**
 * @Author luxurong
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Router {

    String value();

    HttpType type() default HttpType.GET;

}
