package hn.edu.ujcv.dao

import hn.edu.ujcv.model.FormasPago
import org.springframework.data.jpa.repository.JpaRepository

interface FormaPagoRepository:JpaRepository<FormasPago,Long> {
}