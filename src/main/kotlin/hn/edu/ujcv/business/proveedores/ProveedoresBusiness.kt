package hn.edu.ujcv.business.proveedores


import hn.edu.ujcv.dao.ProveedorRepository
import hn.edu.ujcv.model.Proveedores
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Empleado
import java.util.*

@Service
class ProveedoresBusiness:IProveedoresBusiness {
    @Autowired
    val proveedoresRepository: ProveedorRepository?=null

    @Throws(BusinessExeptions::class)
    override fun getProveedor(): List<Proveedores> {
        try {
            return proveedoresRepository!!.findAll();

        }catch (e:Exception){
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getProveedorById(idProveedor: Long): Proveedores {
        val opt: Optional<Proveedores>
        try {
            opt = proveedoresRepository!!.findById(idProveedor)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveProveedor(proveedor:Proveedores): Proveedores {
        try {
            return proveedoresRepository!!.save(proveedor)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveProveedor(proveedores: List<Proveedores>):List<Proveedores> {

        try {
            return proveedoresRepository!!.saveAll(proveedores)

        }catch (e:Exception){
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeProveedor(idProveedor: Long){
        val opt: Optional<Proveedores>
        try {
            opt =  proveedoresRepository!!.findById(idProveedor)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona con el id +$idProveedor")
        }else{
            try {
                proveedoresRepository!!.deleteById(idProveedor)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateProveedor(Proveedor:Proveedores):Proveedores {
        val opt: Optional<Proveedores>
        try {
            opt = proveedoresRepository!!.findById(Proveedor.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona ${Proveedor.id}")
        }else{
            try {

                var personaExist = Proveedores(Proveedor.nombre, Proveedor.compañia,Proveedor.rtn, Proveedor.direccion)
                return proveedoresRepository!!.save(Proveedor)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }

    /*@Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getProveedorByNombre(nombre: String): Proveedores {
        val opt: Optional<Proveedores>
        try {
            opt = proveedoresRepository!!.findByNombre(nombre)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent)
        {
            throw NotFoundException("No se encontro la persona $nombre")
        }
        return opt.get()
    }*/














}
