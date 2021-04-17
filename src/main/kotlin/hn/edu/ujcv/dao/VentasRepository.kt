package hn.edu.ujcv.dao

import hn.edu.ujcv.model.Ventas
import org.springframework.data.jpa.repository.JpaRepository

interface VentasRepository:JpaRepository<Ventas, Long> {
}