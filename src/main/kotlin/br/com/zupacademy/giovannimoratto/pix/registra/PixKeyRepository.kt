package br.com.zupacademy.giovannimoratto.pix.registra

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

/**
 *@Author giovanni.moratto
 */

@Repository
interface PixKeyRepository : JpaRepository<PixKeyModel, Long> {

    fun existsByChave(chave: String?): Boolean
}