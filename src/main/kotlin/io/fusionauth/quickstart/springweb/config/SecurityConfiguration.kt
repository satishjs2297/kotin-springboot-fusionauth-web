package io.fusionauth.quickstart.springweb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity, repo: ClientRegistrationRepository): SecurityFilterChain {
        val base_uri: String = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
        val resolver = DefaultOAuth2AuthorizationRequestResolver(repo, base_uri)
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce())
        http
                .authorizeRequests { a ->
                    a
                            .antMatchers("/", "/images/**", "/css/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                }
                .oauth2Login { login ->
                    login.authorizationEndpoint().authorizationRequestResolver(resolver)
                }
        http.logout { logout ->
            logout.logoutSuccessUrl("http://localhost:9011/oauth2/logout?client_id=e9fdb985-9173-4e01-9d73-ac2d60d1dc8e")
        }
        return http.build()
    }
}
