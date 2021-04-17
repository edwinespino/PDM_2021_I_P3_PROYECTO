package hn.edu.ujcv.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "productos")//.catalog = "dbo"
data class Productos(val nombre:String="",val descripcion:String="",val preciocompra:Long=0,val precioventa:Long=0 )
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}