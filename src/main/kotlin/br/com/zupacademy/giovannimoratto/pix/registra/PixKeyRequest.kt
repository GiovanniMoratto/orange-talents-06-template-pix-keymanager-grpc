package br.com.zupacademy.giovannimoratto.pix.registra

import br.com.zupacademy.giovannimoratto.pix.core.client.itau.ContaAssociada
import br.com.zupacademy.giovannimoratto.pix.core.grpc.TipoDeChave
import br.com.zupacademy.giovannimoratto.pix.core.grpc.TipoDeConta
import br.com.zupacademy.giovannimoratto.pix.core.validation.ValidPixKey
import br.com.zupacademy.giovannimoratto.pix.core.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ValidPixKey
@Introspected
data class PixKeyRequest(
    @field:ValidUUID
    @field:NotBlank
    val clienteId: String?,
    @field:NotNull
    val tipoDeChave: TipoDeChave?,
    @field:Size(max = 77)
    val chave: String?,
    @field:NotNull
    val tipoDeConta: TipoDeConta?
) {
    fun toModel(conta: ContaAssociada): PixKeyModel {
        return PixKeyModel(
            clienteId = UUID.fromString(this.clienteId),
            tipoDeChave = TipoDeChave.valueOf(tipoDeChave!!.name),
            chave = if (this.tipoDeChave == TipoDeChave.ALEATORIA) UUID.randomUUID().toString() else this.chave!!,
            tipoDeConta = TipoDeConta.valueOf(tipoDeConta!!.name),
            conta = conta
        )
    }
}
