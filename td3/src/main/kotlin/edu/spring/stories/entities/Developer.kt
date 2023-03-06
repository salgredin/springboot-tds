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

    open var firstname:String= ""
    open var lastname:String = ""
    @OneToMany(cascade=[CascadeType.PERSIST,CascadeType.MERGE])
    @Column(nullable = true)
    open var stories:MutableList<Story> = mutableListOf()


    fun addStory(story:Story):Boolean{
        story.developer=this
        return stories.add(story)
    }
    fun giveUpStory(story:Story):Boolean{

        story.developer=null
        return stories.remove(story)
    }
    @PreRemove
    fun preRemove():Unit{
        stories.forEach { it.developer = null }
    }



}


