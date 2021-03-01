package com.cbx.springapi

import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Predicate

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

    fun saveUser(user: User): User {
        user.id = if (user.id == 0) users.size + 1 else user.id
        users.add(user)
        return user
    }

    fun findOne(id: Int): User? {
        return users.firstOrNull { x -> x.id == id }
    }

    fun deleteUser(id: Int): Boolean {
        return users.removeIf { x: User -> x.id == id }
    }


}
