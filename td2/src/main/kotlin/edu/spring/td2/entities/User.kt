package edu.spring.td2.entities


import jakarta.persistence.*


@Entity
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=null
    @Column(length=30)
    open var firstname:String?=null
    @Column(length=30)
    open var lastname:String?=null
    @Column(nullable = false, length=255,unique=true)
    open lateinit var email:String
    @Column(nullable = false, length=60)
    open lateinit var password:String
    @Column(length=45)
    open var domain:String?=null
    @Column(length=45)
    open var aliases:String?=null
    @Column
    open var suspended:Boolean=false

    @ManyToOne
    @JoinColumn(name="idOrganisation",nullable=false)
    open lateinit var organisation: Organisation

    @ManyToMany
    @JoinTable(name = "user_groups")
    open val groups:Set<Group>?=HashSet()
}