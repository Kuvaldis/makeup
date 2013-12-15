package kuvaldis.makeup.lib.annotation

import com.google.inject.BindingAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * @author Kuvaldis
 * Create date: 15.12.13 16:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.PARAMETER, ElementType.FIELD])
@BindingAnnotation
public @interface PasswordEncrypt {

}