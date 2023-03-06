package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
class Story() {

    constructor(name: String) : this() {
        this.name = name
        this.developer = null
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