package io.github.quickmsg.common.core.annotation;

import io.github.quickmsg.common.core.enums.Topic;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface TopicType {

    Topic topicType() default Topic.NULL;

}
