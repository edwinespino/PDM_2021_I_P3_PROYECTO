package hn.edu.ujcv.model

import javax.persistence.*

@Entity
@Table(name = "formadepago")//.catalog = "dbo"
data class FormasPago (val descripcion:String="", val estado:String =""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}