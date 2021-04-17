package hn.edu.ujcv.business.compras

import hn.edu.ujcv.model.Compra

interface ICompraBusiness {
    
    fun getCompra():List<Compra>
    fun getCompraById(idPersona: Long): Compra
    fun saveCompra(compra: Compra): Compra
    fun saveCompras(compras: List<Compra>):List<Compra>
    fun removeCompra(idCompra: Long)
    fun updateCompra(compra: Compra): Compra





}