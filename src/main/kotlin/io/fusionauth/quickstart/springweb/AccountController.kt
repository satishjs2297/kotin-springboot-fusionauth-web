package io.fusionauth.quickstart.springweb

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class AccountController {
    @RequestMapping("/account")
    fun userPage(model: Model, @AuthenticationPrincipal principal: OidcUser?): String {
        if (principal != null) {
            model.addAttribute("profile", principal.claims)
        }
        return "account"
    }
}