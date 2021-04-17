package hn.edu.ujcv.business.detalleventa

import hn.edu.ujcv.business.empleado.IEmpleadoBusiness
import hn.edu.ujcv.dao.DetalleVentaRepository
import hn.edu.ujcv.dao.EmpleadoRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.DetalleVenta
import hn.edu.ujcv.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DetalleventaBusiness: IDetalleventaBussines {
    @Autowired
    val detalleventaBusiness: DetalleVentaRepository? = null

    @Throws(BusinessExeptions::class)
    override fun getDetalleV(): List<DetalleVenta> {
        try {
            return detalleventaBusiness!!.findAll();

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getDetalleVById(idDetalleV: Long): DetalleVenta {
        val opt: Optional<DetalleVenta>
        try {
            opt = detalleventaBusiness!!.findById(idDetalleV)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveDetalleV(detallev: DetalleVenta): DetalleVenta {
        try {
            return detalleventaBusiness!!.save(detallev)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveDetalleVS(detallevs: List<DetalleVenta>): List<DetalleVenta> {

        try {
            return detalleventaBusiness!!.saveAll(detallevs)

        } catch (e: Exception) {
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeDetalleV(idDetalleV: Long) {
        val opt: Optional<DetalleVenta>
        try {
            opt = detalleventaBusiness!!.findById(idDetalleV)

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona con el id +$idDetalleV")
        } else {
            try {
                detalleventaBusiness!!.deleteById(idDetalleV)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateDetalleV(detallev: DetalleVenta): DetalleVenta {
        val opt: Optional<DetalleVenta>
        try {
            opt = detalleventaBusiness!!.findById(detallev.id)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona ${detallev.id}")
        } else {
            try {

                var personaExist = DetalleVenta(
                    detallev.precio,
                    detallev.cantidad,
                    detallev.idventa,
                    detallev.idproducto,
                )
                return detalleventaBusiness!!.save(detallev)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }
}