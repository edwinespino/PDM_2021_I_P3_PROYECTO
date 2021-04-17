package hn.edu.ujcv.dao

import hn.edu.ujcv.model.DetalleCompra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetalleCompraRepository: JpaRepository<DetalleCompra, Long> {
}