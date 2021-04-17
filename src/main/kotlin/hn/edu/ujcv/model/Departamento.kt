package hn.edu.ujcv.model

import javax.persistence.*

@Entity
@Table(name = "departamento")//.catalog = "dbo"

data class Departamento(val nombre:String="",val descripcion:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}