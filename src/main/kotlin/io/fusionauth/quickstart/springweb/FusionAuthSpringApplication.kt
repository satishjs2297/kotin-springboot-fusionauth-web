package io.fusionauth.quickstart.springweb

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FusionAuthSpringApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FusionAuthSpringApplication::class.java, *args)
        }
    }
}