package hn.edu.ujcv.business.detalleventa

import hn.edu.ujcv.model.DetalleVenta

interface IDetalleventaBussines{
fun getDetalleV():List<DetalleVenta>
fun getDetalleVById(idPersona: Long): DetalleVenta
fun saveDetalleV(empleado: DetalleVenta ): DetalleVenta
fun saveDetalleVS(empleados: List<DetalleVenta>):List<DetalleVenta>
fun removeDetalleV(idPersona: Long)
fun updateDetalleV(empleado: DetalleVenta): DetalleVenta
}