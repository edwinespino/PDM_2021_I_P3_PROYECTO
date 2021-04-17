package hn.edu.ujcv.business.detallecompra

import hn.edu.ujcv.model.DetalleCompra


interface IDetalleCompraBusiness {
    fun getDetalleC():List<DetalleCompra>
    fun getDetalleCById(iddetalle: Long): DetalleCompra
    fun saveDetalleC(detalle: DetalleCompra): DetalleCompra
    fun saveDetalleCS(detalles: List<DetalleCompra>):List<DetalleCompra>
    fun removeDetalleC(iddetalle: Long)
    fun updateDetalleC(detalle: DetalleCompra): DetalleCompra
}