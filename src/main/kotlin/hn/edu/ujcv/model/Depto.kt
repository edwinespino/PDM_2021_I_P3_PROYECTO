package hn.edu.ujcv.model
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "departamento")//.catalog = "dbo"

data class Depto(val nombre:String="",val descripcion:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}