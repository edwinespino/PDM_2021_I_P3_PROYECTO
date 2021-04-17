package hn.edu.ujcv.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "detalleventa")//.catalog = "dbo"
data class DetalleVenta(val precio:Long=0, val cantidad:Long=0, val idventa:Long=0, val idproducto:Long=0)
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}