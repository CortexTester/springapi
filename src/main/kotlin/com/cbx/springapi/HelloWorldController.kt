package com.cbx.springapi

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import net.minidev.json.annotate.JsonIgnore
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.CascadeType.*
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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany


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
    fun helloWorldInternationalized(
        @RequestHeader(
            name = "Accept-Language",
            required = false
        ) locale: Locale
    ): String? {
        return messageSource.getMessage("good.morning.message", null, locale)
    }

    @GetMapping(value = ["/hello-world-internationalized02"], produces = ["application/json"])
    fun helloWorldInternationalized02(): String? {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale())
    }
}

data class HelloBean(val message: String)

@Entity
data class User(
    @Id
    @GeneratedValue
    var id: Int,

    @field:Size(min = 2, message = "name required 2 chars")
    var name: String,

    @field:Past
    var birthDate: Date,

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
//    @JsonManagedReference
    var posts: MutableList<Post> = ArrayList()
)

@Entity
data class Post(
    @Id
    @GeneratedValue
    var id: Int,
    var description: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    var user: User?

)
