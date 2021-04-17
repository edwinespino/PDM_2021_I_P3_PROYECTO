package hn.edu.ujcv.model

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "ventas")//.catalog = "dbo"
data class Ventas(val descripcion:String="",val idempleado:Long=0, val cai:Long=0,val idcliente:Long=0,val numerotarjeta:Long=0,val formadepago:Long=0,val fechaventa:Date?=null, val fechaentrega:Date?=null)
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}