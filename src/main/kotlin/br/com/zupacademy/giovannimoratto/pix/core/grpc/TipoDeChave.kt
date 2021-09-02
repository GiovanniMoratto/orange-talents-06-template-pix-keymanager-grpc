package br.com.zupacademy.giovannimoratto.pix.core.grpc

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator

/**
 *@Author giovanni.moratto
 */

enum class TipoDeChave {

    CPF {
        override fun valid(chave: String?): Boolean {

            if (chave.isNullOrBlank()) {
                return false;
            }

            if (!chave.matches("[0-9]+".toRegex())) {
                return false
            }

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }

        }
    },
    CELULAR {
        override fun valid(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    },
    EMAIL {
        override fun valid(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    ALEATORIA {
        override fun valid(chave: String?) = chave.isNullOrBlank()
    };

    abstract fun valid(chave: String?): Boolean
}