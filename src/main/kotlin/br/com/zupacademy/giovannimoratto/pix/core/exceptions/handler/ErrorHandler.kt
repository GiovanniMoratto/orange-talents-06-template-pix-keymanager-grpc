package br.com.zupacademy.giovannimoratto.pix.core.exceptions.handler

import io.micronaut.aop.Around
import io.micronaut.context.annotation.Type

/**
 *@Author giovanni.moratto
 */

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Around
@Type(ExceptionHandlerInterceptor::class)
annotation class ErrorHandler()
