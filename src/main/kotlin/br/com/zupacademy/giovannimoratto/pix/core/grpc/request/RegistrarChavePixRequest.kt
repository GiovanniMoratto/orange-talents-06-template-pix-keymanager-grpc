package br.com.zupacademy.giovannimoratto.pix.core.grpc.request

import br.com.zupacademy.giovannimoratto.pix.RegistrarChavePixRequest
import br.com.zupacademy.giovannimoratto.pix.TipoDeChaveGrpc
import br.com.zupacademy.giovannimoratto.pix.TipoDeContaGrpc
import br.com.zupacademy.giovannimoratto.pix.core.grpc.TipoDeChave
import br.com.zupacademy.giovannimoratto.pix.core.grpc.TipoDeConta
import br.com.zupacademy.giovannimoratto.pix.registra.PixKeyRequest


/**
 *@Author giovanni.moratto
 */

fun RegistrarChavePixRequest.toModel(): PixKeyRequest {
    return PixKeyRequest(
        clienteId = clienteId,
        tipoDeChave = when (tipoDeChave) {
            TipoDeChaveGrpc.TIPO_DE_CHAVE_DESCONHECIDA -> null
            else -> TipoDeChave.valueOf(tipoDeChave.name)
        },
        chave = chave,
        tipoDeConta = when (tipoDeConta) {
            TipoDeContaGrpc.TIPO_CONTA_DESCONHECIDA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name)
        }
    )

}