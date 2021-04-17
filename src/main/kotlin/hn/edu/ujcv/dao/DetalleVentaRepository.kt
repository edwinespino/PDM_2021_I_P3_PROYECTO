package hn.edu.ujcv.dao

import hn.edu.ujcv.model.DetalleVenta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetalleVentaRepository:JpaRepository<DetalleVenta, Long> {

}