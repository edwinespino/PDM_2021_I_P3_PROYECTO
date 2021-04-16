package hn.edu.ujcv.business.cliente

import hn.edu.ujcv.model.Cliente

interface IClienteBusiness {

    fun getCliente():List<Cliente>
    fun getClienteById(idCliente:Long): Cliente
    fun saveCliente(cliente: Cliente): Cliente
    fun saveClientes(clientes: List<Cliente>):List<Cliente>
    fun removeCliente(idCliente: Long)
    fun getClienteByNombre(nombreCliente: String): Cliente
    fun updateCliente(cliente: Cliente): Cliente


}