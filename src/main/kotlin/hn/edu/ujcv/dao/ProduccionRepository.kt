package hn.edu.ujcv.dao

import hn.edu.ujcv.model.Produccion
import org.springframework.data.jpa.repository.JpaRepository

interface ProduccionRepository:JpaRepository<Produccion, Long> {
}