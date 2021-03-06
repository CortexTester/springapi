package com.cbx.springapi

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
}

@Repository
interface PostRepository : JpaRepository<Post, Int> {
}
