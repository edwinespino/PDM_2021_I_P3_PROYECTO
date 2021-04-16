package hn.edu.ujcv.business.empleado

import hn.edu.ujcv.dao.EmpleadoRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class EmpleadoBusiness: IEmpleadoBusiness {
    @Autowired
    val empleadoRepository: EmpleadoRepository?=null

    @Throws(BusinessExeptions::class)
    override fun getEmpleado(): List<Empleado> {
        try {
            return empleadoRepository!!.findAll();

        }catch (e:Exception){
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getEmpleadoById(idPersona: Long): Empleado {
        val opt: Optional<Empleado>
        try {
            opt = empleadoRepository!!.findById(idPersona)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveEmpleado(empleado: Empleado): Empleado {
        try {
            return empleadoRepository!!.save(empleado)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveEmpleados(empleados: List<Empleado>): List<Empleado> {

        try {
            return empleadoRepository!!.saveAll(empleados)

        }catch (e:Exception){
         throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeEmpleado(idPersona: Long) {
        val opt:Optional<Empleado>
        try {
            opt = empleadoRepository!!.findById(idPersona)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona con el id +$idPersona")
        }else{
            try {
                empleadoRepository!!.deleteById(idPersona)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateEmpleado(empleado: Empleado): Empleado {
        val opt:Optional<Empleado>
        try {
            opt = empleadoRepository!!.findById(empleado.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona ${empleado.id}")
        }else{
            try {

                var personaExist = Empleado(empleado.nombre,empleado.apellido, empleado.telefono,empleado.correo,empleado.cargo,empleado.clave)
                return empleadoRepository!!.save(empleado)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getEmpleadoByNombre(nombrePersona: String): Empleado {
        val opt:Optional<Empleado>
        try {
            opt = empleadoRepository!!.findByNombre(nombrePersona)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent)
        {
            throw NotFoundException("No se encontro la persona $nombrePersona")
        }
        return opt.get()
    }














}