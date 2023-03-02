package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
class Story {
    constructor(name:String){
        this.name = name
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int? = 0
    open var name: String?=null
    @ManyToOne
    open var developer:Developer? = null

    @ManyToMany(mappedBy = "stories")
    open var tags:ArrayList<Tag>? = null
}