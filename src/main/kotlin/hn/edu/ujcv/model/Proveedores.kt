package hn.edu.ujcv.model
import javax.persistence.*


@Entity
@Table(name = "proveedores")//.catalog = "dbo"
data class Proveedores(val nombre:String="",val compañia:String="", val rtn:String="",val direccion:String="")
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}
