package com.cbx.springapi

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
class UserResource(private val service: UserDaoService) {
    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> {
        return service.fillAll()
    }

    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): EntityModel<User> {
        val foundUser = service.findOne(id) ?: throw UserNotFoundException("no found id:$id")
//        return foundUser

        //HATEOAS
        val resource = EntityModel.of(foundUser)
        val linkTo = linkTo(methodOn(this.javaClass).retrieveAllUsers())
        resource.add(linkTo.withRel("all-users"))
        return  resource

    }

    @PostMapping("/users")
    fun saveUser(@Valid @RequestBody user:User) : ResponseEntity<Void> {
        val saveUser = service.saveUser(user)
        val toUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((saveUser.id)).toUri()
        return ResponseEntity.created(toUri).build()
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Int) : ResponseEntity<*> {
        return if(service.deleteUser(id)) {
            ResponseEntity.ok("")
        }else{
            throw UserNotFoundException("no found id:$id")
        }
    }
}
