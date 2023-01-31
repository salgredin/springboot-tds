package edu.spring.td1.models

class Item constructor (var nom:String) {
    var evaluation: Int = 0
    override fun equals(other: Any?): Boolean {
        if (other !is Item) {
            return false;
        }
        return other.nom == this.nom

    }
    override fun hashCode(): Int{
        return nom.hashCode()
    }
}
