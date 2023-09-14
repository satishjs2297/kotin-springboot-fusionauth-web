package io.fusionauth.quickstart.springweb

import io.fusionauth.quickstart.springweb.model.Change
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.math.BigDecimal
import java.math.RoundingMode

@Controller
class MakeChangeController {
    private val logger: Logger = LoggerFactory.getLogger(MakeChangeController::class.java)

    @GetMapping("/make-change")
    fun get(model: Model, @AuthenticationPrincipal principal: OidcUser): String {
        model.addAttribute("change", Change())
        model.addAttribute("profile", principal.claims)
        return "make-change"
    }

    @PostMapping("/make-change")
    fun post(
            @ModelAttribute change: Change,
            model: Model,
            @AuthenticationPrincipal principal: OidcUser
    ): String {
        model.addAttribute("profile", principal.claims)
        change.error = null
        try {
            if (change.total != null) {
                val totalValue = BigDecimal(change.total)
                change.nickels = totalValue.divide(BigDecimal(5), RoundingMode.HALF_UP).toInt()
                change.pennies = totalValue.remainder(BigDecimal(5)).multiply(BigDecimal(100)).toInt()
            }
        } catch (e: Exception) {
            logger.error("Error making change", e)
            change.error = "An error occurred while making change"
        }
        model.addAttribute("change", change)
        return "make-change"
    }
}