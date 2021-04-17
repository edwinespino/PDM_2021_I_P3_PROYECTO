package hn.edu.ujcv.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cliente")//.catalog = "dbo"
data class Cliente (val nombrecompleto:String="", val telefono:Long=0, val dni:Long=0, val rtn:String="",val correo: String ="", val direccion:String="")
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}
