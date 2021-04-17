package hn.edu.ujcv.business.departamentos

import hn.edu.ujcv.model.Departamento

interface IDepartamentoBusiness {
    fun getDepto(): List<Departamento>
    fun getDeptoById(iddepto: Long): Departamento
    fun saveDepto(depto: Departamento): Departamento
    fun saveDeptos(deptos: List<Departamento>): List<Departamento>
    fun removeDepto(iddepto: Long)
    fun updateDepto(depto: Departamento): Departamento
}
