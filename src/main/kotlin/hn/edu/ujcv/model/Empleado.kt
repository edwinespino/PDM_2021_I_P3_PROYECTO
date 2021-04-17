package hn.edu.ujcv.model

import javax.persistence.*


@Entity
@Table(name = "empleado")//.catalog = "dbo"
data class Empleado ( val nombrecompleto:String="", val telefono:Long=0, val correo: String ="", val clave:String="", val cargo:String=""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}