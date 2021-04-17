package hn.edu.ujcv.business.ventas


import hn.edu.ujcv.model.Ventas

interface IVentasBusiness {

    fun getVenta():List<Ventas>
    fun getVentaById(idVenta: Long): Ventas
    fun saveVenta(venta: Ventas): Ventas
    fun saveVentas(ventas: List<Ventas>):List<Ventas>
    fun removeVenta(idVenta: Long)
    fun updateVenta(venta: Ventas): Ventas


}