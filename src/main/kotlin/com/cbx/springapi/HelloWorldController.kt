package com.cbx.springapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HelloWorldController {
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
}

data class HelloBean(val message: String)

data class User(val id: Int, val name: String, val birthDate: Date)
