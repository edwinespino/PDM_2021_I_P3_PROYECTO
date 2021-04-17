package hn.edu.ujcv.model

import javax.persistence.*


@Entity
@Table(name = "empleado")//.catalog = "dbo"
data class Empleado (val nombrecompleto:String="", val telefono:String="", val telefono: String ="", val correo:String="", val cargo:String="", val clave:String=""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}