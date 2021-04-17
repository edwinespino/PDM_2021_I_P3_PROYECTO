package hn.edu.ujcv.dao
import hn.edu.ujcv.model.Departamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeptoRepository :JpaRepository<Departamento, Long> {

}