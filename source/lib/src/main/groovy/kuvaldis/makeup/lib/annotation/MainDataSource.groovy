package kuvaldis.makeup.lib.annotation

import com.google.inject.BindingAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 15:38
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@BindingAnnotation
public @interface MainDataSource {

}