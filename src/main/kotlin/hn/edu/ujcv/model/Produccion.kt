package hn.edu.ujcv.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "produccion")//.catalog = "dbo"
data class Produccion(val idproducto:Long=0, val idempleado:Long=0, val iddepto:Long=0, val descripcion:String="", val tiempo:String="")
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}