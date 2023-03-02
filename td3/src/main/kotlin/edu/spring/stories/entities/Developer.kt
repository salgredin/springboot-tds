package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
class Developer {
    constructor(firstName:String, lastname:String){
        this.firstname = firstName
        this.lastname = lastname
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    open var firstname:String? = null
    open var lastname:String? = null
    @OneToMany(cascade=[CascadeType.PERSIST,CascadeType.MERGE])
    open var stories:MutableList<Story> = mutableListOf()


    fun addStory(story:Story){
        stories?.add(story)
    }
    fun giveUpStory(story:Story){
        stories?.remove(story)
    }
    @PreRemove
    fun preRemove(){
        stories?.forEach { it.developer = null }
    }



}


