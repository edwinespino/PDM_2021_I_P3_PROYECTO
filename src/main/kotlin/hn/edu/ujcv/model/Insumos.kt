package hn.edu.ujcv.model


import java.util.*
import javax.persistence.*

@Entity
@Table(name = "insumos")//.catalog = "dbo"
data class Insumos(val nombre:String= "",val tipo:String= "",val cantidad:Long=0,val preciocompra:Long=0,val precioventa:Long=0)
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}