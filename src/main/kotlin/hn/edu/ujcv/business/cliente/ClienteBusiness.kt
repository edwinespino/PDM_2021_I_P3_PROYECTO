package hn.edu.ujcv.business.cliente

import hn.edu.ujcv.dao.ClientRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class ClienteBusiness: IClienteBusiness {
    @Autowired
    val clientRepository: ClientRepository?=null

    @Throws(BusinessExeptions::class)
    override fun getCliente(): List<Cliente> {
        try {
            return clientRepository!!.findAll();

        }catch (e:Exception){
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getClienteById(idCliente: Long): Cliente {
        val opt: Optional<Cliente>
        try {
            opt = clientRepository!!.findById(idCliente)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveCliente(cliente: Cliente): Cliente {
        try {
            return clientRepository!!.save(cliente)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveClientes(clientes: List<Cliente>): List<Cliente> {

        try {
            return clientRepository!!.saveAll(clientes)

        }catch (e:Exception){
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeCliente(idCliente: Long) {
        val opt: Optional<Cliente>
        try {
            opt = clientRepository!!.findById(idCliente)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona con el id +$idCliente")
        }else{
            try {
                clientRepository!!.deleteById(idCliente)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateCliente(cliente: Cliente): Cliente {
        val opt: Optional<Cliente>
        try {
            opt = clientRepository!!.findById(cliente.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona ${cliente.id}")
        }else{
            try {

                var clienteExist = Cliente(cliente.nombrecompleto,cliente.telefono ,cliente.correo,cliente.direccion,cliente.dni,cliente.rtn)
                return clientRepository!!.save(cliente)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }

    /*@Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getClienteByNombre(nombreCliente: String): Cliente {
        val opt:Optional<Cliente>
        try {
            opt = clientRepository!!.findBYNombre(nombreCliente)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent)
        {
            throw NotFoundException("No se encontro la persona $nombreCliente")
        }
        return opt.get()
    }*/















}