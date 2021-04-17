package hn.edu.ujcv.dao

import hn.edu.ujcv.model.Productos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductosRepository:JpaRepository<Productos,Long> {
}