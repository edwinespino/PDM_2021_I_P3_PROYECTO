package hn.edu.ujcv.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cliente")//.catalog = "dbo"
data class Cliente (val nombrecompleto:String="", val telefono:String="", val correo: String ="", val direccion:String="", val dni:String="", val rtn:String="")
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}
