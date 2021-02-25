package com.cbx.springapi

import org.springframework.stereotype.Component
import java.util.*

@Component
class UserDaoService {
    var users = arrayListOf<User>()
    init {
        users.add(User(1, "Adam", Date()))
        users.add(User(2, "Eve", Date()))
        users.add(User(3, "Jack", Date()))
    }
    fun fillAll(): List<User> {
             return users
    }
    fun saveUser(user: User) {
        users.add(user)
    }
    fun findOne(id: Int):User? {
        return users.firstOrNull{x->x.id == id}
    }

}
