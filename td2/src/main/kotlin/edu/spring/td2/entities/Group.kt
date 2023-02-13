package edu.spring.td2.entities

import jakarta.persistence.*


@Entity
open class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=null
    @Column(nullable = false, length=60)
    open lateinit var name:String
    @Column(nullable = false, length=60)
    open lateinit var email:String
    @Column(length=45)
    open var aliases:String?=null

    @ManyToOne
    @JoinColumn(name="idOrganisation", nullable=false)
    open lateinit var organisation:Organisation

    @ManyToMany(mappedBy="groups")
    open val users: Set<User>?=HashSet()

}