package br.com.zupacademy.giovannimoratto.pix

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.zupacademy.giovannimoratto.pix")
		.start()
}

