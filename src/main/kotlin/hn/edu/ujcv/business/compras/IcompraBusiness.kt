package hn.edu.ujcv.business.compras

import hn.edu.ujcv.model.Compras
import hn.edu.ujcv.model.Empleado

interface IcompraBusiness {
    fun getCompra():List<Compras>
    fun getCompraById(idPersona: Long): Compras
    fun saveCompra(compra: Compras): Compras
    fun saveCompras(compras: List<Compras>):List<Compras>
    fun removeCompra(idCompra: Long)
    fun updateCompra(compra: Compras): Compras





}