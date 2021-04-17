package hn.edu.ujcv.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "detallecompra")//.catalog = "dbo"
data class DetalleCompra(val idcompra:Long=0, val cantidad:Long=0,val precio:Long=0,val total:Long=0 )
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long=0
}