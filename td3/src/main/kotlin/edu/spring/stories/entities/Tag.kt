package edu.spring.stories.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
class Tag {
    constructor(label:String, color:String){
        this.label = label
        this.color = color
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int? = 0

    open var label:String? = null

    open var color:String? = null

    @ManyToMany(mappedBy = "tags")
    open var stories:ArrayList<Story>? = null

}