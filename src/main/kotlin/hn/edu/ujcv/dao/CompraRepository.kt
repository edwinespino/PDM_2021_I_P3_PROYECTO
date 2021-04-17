package hn.edu.ujcv.dao

import hn.edu.ujcv.model.Compra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompraRepository: JpaRepository<Compra,Long> {

}