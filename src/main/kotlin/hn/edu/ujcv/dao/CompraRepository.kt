package hn.edu.ujcv.dao

import hn.edu.ujcv.model.Compras
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompraRepository: JpaRepository<Compras, Long> {

}