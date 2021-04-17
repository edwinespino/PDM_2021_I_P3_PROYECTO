package hn.edu.ujcv.business.proveedores

import hn.edu.ujcv.model.Proveedores

interface IProveedoresBusiness {
    fun getProveedor():List<Proveedores>
    fun getProveedorById(idProveedor: Long): Proveedores
    fun saveProveedor(proveedor:Proveedores): Proveedores
    fun saveProveedor(proveedores: List<Proveedores>):List<Proveedores>
    fun removeProveedor(idProveedor: Long)
    /*fun getProveedorByNombre(nombre: String): Proveedores*/
    fun updateProveedor(proveedor: Proveedores):Proveedores

}