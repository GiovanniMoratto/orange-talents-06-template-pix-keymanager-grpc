package br.com.zupacademy.giovannimoratto.pix.registra

import br.com.zupacademy.giovannimoratto.pix.KeyManagerRegistrarGrpcServiceGrpc
import br.com.zupacademy.giovannimoratto.pix.RegistrarChavePixRequest
import br.com.zupacademy.giovannimoratto.pix.RegistrarChavePixResponse
import br.com.zupacademy.giovannimoratto.pix.core.exceptions.handler.ErrorHandler
import br.com.zupacademy.giovannimoratto.pix.core.grpc.request.toModel
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

/**
 *@Author giovanni.moratto
 */

@ErrorHandler
@Singleton
class RegistraPixKeyEndpoint(@Inject private val service: RegistraPixKeyService) :
    KeyManagerRegistrarGrpcServiceGrpc.KeyManagerRegistrarGrpcServiceImplBase() {

    override fun registrar(
        request: RegistrarChavePixRequest?,
        responseObserver: StreamObserver<RegistrarChavePixResponse>?
    ) {
        val novaChave = request?.toModel()
        val chaveCriada = novaChave?.let { service.registra(it) }

        responseObserver?.onNext(
            RegistrarChavePixResponse.newBuilder() // 1
                .setPixId(chaveCriada?.id.toString())
                .build()
        )

        responseObserver?.onCompleted()
    }
}