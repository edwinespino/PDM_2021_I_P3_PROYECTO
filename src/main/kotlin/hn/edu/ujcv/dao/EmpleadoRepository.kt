package hn.edu.ujcv.dao

import hn.edu.ujcv.model.Empleado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface EmpleadoRepository:JpaRepository<Empleado,Long> {
    fun findByNombre(nombrePersona:String):Optional<Empleado>
}