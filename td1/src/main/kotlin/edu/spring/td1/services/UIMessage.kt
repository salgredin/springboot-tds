package edu.spring.td1.services

class UIMessage {
    class Message(var title:String, var message:String,var type:String,var icon:String)
   // companion object{
        //fun message(title: String, message: String, type:String, icon:String)=Message(title,message,type,icon)
       // fun message(title: String,message: String)=Message(title,message, "success", "info circle")
        companion object {
            fun message(icon : String, type : String, title : String, message : String) : Message {
                return Message(icon, type, title, message)
            }

            fun message(title: String, message: String) : Message {
                return Message("fas fa-info-circle", "info", title, message)
            }
        }
    //}
}