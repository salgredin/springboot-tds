package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
class Story {
    constructor(name: String) {
        this.name = name
    }
    constructor(name: String,developer: Developer) {
        this.name = name
        this.developer = developer
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int = 0
    open var name: String = ""

    @ManyToOne
    open var developer: Developer? = null

    @ManyToMany
    open var tags: MutableList<Tag> = mutableListOf()
}