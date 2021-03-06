package com.cbx.springapi

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import net.minidev.json.annotate.JsonIgnore
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FilterController {
    @GetMapping(path = ["/filter"])
    fun retrieveSomeBean(): SomeBean {
        return SomeBean("f1", "f2", "f3")
    }
}

data class SomeBean(val field1: String, val field2: String, @JsonIgnore val field3: String)
