package br.com.zupacademy.giovannimoratto.pix.registra

import br.com.zupacademy.giovannimoratto.pix.core.client.ErpItauClient
import br.com.zupacademy.giovannimoratto.pix.core.exceptions.ChavePixExistenteException
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

/**
 *@Author giovanni.moratto
 */

@Validated
@Singleton
class RegistraPixKeyService(
    @Inject val repository: PixKeyRepository,
    @Inject val itauClient: ErpItauClient
) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registra(@Valid novaChave: PixKeyRequest): PixKeyModel {

        // 1. verifica se chave já existe no sistema
        if (repository.existsByChave(novaChave.chave))
            throw ChavePixExistenteException("Chave Pix '${novaChave.chave}' existente")

        // 2. busca dados da conta no ERP do ITAU
        val response = itauClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não encontrado no Itau")

        // 3. grava no banco de dados
        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave
    }
}