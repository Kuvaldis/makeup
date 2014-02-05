package kuvaldis.makeup.app.annotation

import com.google.inject.BindingAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 14:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@BindingAnnotation
public @interface HttpServer {

}