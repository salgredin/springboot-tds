package edu.spring.stories.repositories

import edu.spring.stories.entities.Story
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface StoriesRepository : CrudRepository<Story, Int> {
    fun getStoriesWithoutDev(): Story?
    fun findStory(nom:String,id: Int): Story?
}
