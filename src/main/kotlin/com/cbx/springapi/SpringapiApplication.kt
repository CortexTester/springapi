package com.cbx.springapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@SpringBootApplication
class SpringapiApplication{
    @Bean
    fun localeResolver():LocaleResolver{
//        val localeResolver = SessionLocaleResolver()
        val localeResolver = AcceptHeaderLocaleResolver()
        localeResolver.defaultLocale = Locale.US
        return localeResolver
    }

    //
    @Bean
    fun bundleMessageSource():ResourceBundleMessageSource{
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("messages")
        return messageSource
    }
}

fun main(args: Array<String>) {
    runApplication<SpringapiApplication>(*args)
}
