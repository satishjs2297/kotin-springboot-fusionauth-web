package io.fusionauth.quickstart.springweb

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
    @Value("\${spring.application.name}")
    lateinit var appName: String

    @RequestMapping("/")
    fun homePage(model: Model): String {
        model.addAttribute("appName", appName)
        return "home"
    }
}