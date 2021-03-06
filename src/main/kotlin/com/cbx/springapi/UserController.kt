package com.cbx.springapi

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("jpa")
class UserController(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {
    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> {
        return userRepository.findAll()
    }

    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): EntityModel<User> {
        val foundUser = userRepository.findById(id)
        if (!foundUser.isPresent) throw UserNotFoundException("no found id:$id")
//        return foundUser

        //HATEOAS
        val resource = EntityModel.of(foundUser.get())
        val linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.javaClass).retrieveAllUsers())
        resource.add(linkTo.withRel("all-users"))
        return resource

    }

    @PostMapping("/users")
    fun saveUser(@Valid @RequestBody user: User): ResponseEntity<Void> {
        val saveUser = userRepository.save(user)
        val toUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((saveUser.id)).toUri()
        return ResponseEntity.created(toUri).build()
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<*> {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            ResponseEntity.ok("")
        } else {
            throw UserNotFoundException("no found id:$id")
        }
    }

    @GetMapping("/users/{id}/posts")
    fun retrieveAllUserPosts(@PathVariable id: Int): List<Post> {
        val user = userRepository.findById(id)
        if (!user.isPresent) throw UserNotFoundException("no found id:$id")
        return user.get().posts
    }

    @PostMapping("/users/{id}/posts")
    fun savePost(@PathVariable id: Int, @RequestBody post: Post): ResponseEntity<Void> {
        val userOptional = userRepository.findById(id)
        if (!userOptional.isPresent) throw UserNotFoundException("no found id:$id")
        var user = userOptional.get()
        post.user = user
        postRepository.save(post)

        val toUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((post.id)).toUri()
        return ResponseEntity.created(toUri).build()
    }

}
