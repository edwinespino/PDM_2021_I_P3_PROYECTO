package hn.edu.ujcv.model

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "compras")//.catalog = "dbo"

data class Compra(val cai:String="", val proveedores:Long=0, val numerotarjeta:Long=0, val formapago:Long=0, val fechaentrega:Date?=null, val fechacompra:Date?=null, val insumos:Long=0) { @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}