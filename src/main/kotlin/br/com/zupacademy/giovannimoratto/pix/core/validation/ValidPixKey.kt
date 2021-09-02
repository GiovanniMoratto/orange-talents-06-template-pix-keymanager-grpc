package br.com.zupacademy.giovannimoratto.pix.core.validation

import br.com.zupacademy.giovannimoratto.pix.registra.PixKeyRequest
import jakarta.inject.Singleton
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 *@Author giovanni.moratto
 */

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidPixKeyValidator::class])
annotation class ValidPixKey(
    val message: String = "chave Pix inválida (\${validatedValue.tipo})",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class ValidPixKeyValidator : ConstraintValidator<ValidPixKey, PixKeyRequest> {

    override fun isValid(value: PixKeyRequest?, context: ConstraintValidatorContext?): Boolean {

        if (value?.tipoDeChave == null) {
            return true
        }

        val valid = value.tipoDeChave.valid(value.chave)

        if (!valid) {
            if (context != null) {
                context.disableDefaultConstraintViolation()
                context.buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                    .addPropertyNode("chave")
                    .addConstraintViolation()
            }
        }
        return valid
    }
}