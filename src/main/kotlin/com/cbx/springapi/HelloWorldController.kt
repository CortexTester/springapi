package com.cbx.springapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.constraints.Past
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.annotation.RequestHeader


@RestController
class HelloWorldController(private val messageSource: MessageSource) {


    //    @RequestMapping(method = [RequestMethod.GET], path = ["/hi"])
    @GetMapping(path = ["hi"])
    fun helloWorld(): String {
        return "hi world!"
    }

    @GetMapping(path = ["bean"])
    fun helloBean(): HelloBean {
        return HelloBean("haha bean!")
    }

    @GetMapping(path = ["beanname/{name}"])
    fun helloBeanName(@PathVariable name: String): HelloBean {
        return HelloBean("hi $name")
    }

    @GetMapping(path = ["/hello-world-internationalized"])
    fun helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) locale: Locale): String? {
        return messageSource.getMessage("good.morning.message", null, locale)
    }
}

data class HelloBean(val message: String)

data class User(
    var id: Int,
    @field:Size(min = 2, message = "name required 2 chars") val name: String,
    @field:Past val birthDate: Date
)
