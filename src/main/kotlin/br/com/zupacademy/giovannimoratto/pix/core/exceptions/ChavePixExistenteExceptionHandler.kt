package br.com.zupacademy.giovannimoratto.pix.core.exceptions

import br.com.zupacademy.giovannimoratto.pix.core.exceptions.handler.ExceptionHandler
import io.grpc.Status
import jakarta.inject.Singleton

/**
 *@Author giovanni.moratto
 */

@Singleton
class ChavePixExistenteExceptionHandler : ExceptionHandler<ChavePixExistenteException> {

    override fun handle(e: ChavePixExistenteException): ExceptionHandler.StatusWithDetails {
        return ExceptionHandler.StatusWithDetails(
            Status.ALREADY_EXISTS
                .withDescription(e.message)
                .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is ChavePixExistenteException
    }
}