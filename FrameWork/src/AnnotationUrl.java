/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1839.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author ITU
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
 
public @interface AnnotationUrl {
    public String url() default "/";
}
