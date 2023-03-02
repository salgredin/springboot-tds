package edu.spring.stories.repositories

import edu.spring.stories.entities.Developer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

class DeveloperRepository {
    @Repository
    interface DeveloperRepository : CrudRepository<Developer, Int> {
        fun findByUsername(username: String): Developer?
    }

}