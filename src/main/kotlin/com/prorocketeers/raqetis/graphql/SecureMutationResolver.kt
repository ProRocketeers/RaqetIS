package com.prorocketeers.raqetis.graphql

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.MutationMapping

@Controller
class SecureMutationResolver {

    @MutationMapping
    fun securedAction(@Argument data: String): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = authentication?.principal as? OAuth2User
        val email = user?.getAttribute<String>("email")

        return if (email != null && email.endsWith("@prorocketeers.com")) {
            "Access granted for $email with data: $data"
        } else {
            "Access denied"
        }
    }
}
