package hn.edu.ujcv.dao


import hn.edu.ujcv.model.Proveedores
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProveedorRepository: JpaRepository<Proveedores,Long>{

}