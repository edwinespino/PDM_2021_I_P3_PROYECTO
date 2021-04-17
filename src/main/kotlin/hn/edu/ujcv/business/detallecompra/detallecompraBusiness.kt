package hn.edu.ujcv.business.detallecompra

import hn.edu.ujcv.dao.DetalleCompraRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.DetalleCompra
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
@Service
class detallecompraBusiness:IDetalleCompraBusiness {
    @Autowired
    val detallecompraBusiness: DetalleCompraRepository?= null

    @Throws(BusinessExeptions::class)
    override fun getDetalleC():List<DetalleCompra> {
        try {
            return detallecompraBusiness!!.findAll();

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getDetalleCById(iddetalle: Long): DetalleCompra {
        val opt: Optional<DetalleCompra>
        try {
            opt = detallecompraBusiness!!.findById(iddetalle)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveDetalleC(detalle: DetalleCompra): DetalleCompra {
        try {
            return detallecompraBusiness!!.save(detalle)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveDetalleCS(detalles: List<DetalleCompra>):List<DetalleCompra>{

        try {
            return detallecompraBusiness!!.saveAll(detalles)

        } catch (e: Exception) {
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeDetalleC(iddetalle: Long) {
        val opt: Optional<DetalleCompra>
        try {
            opt = detallecompraBusiness!!.findById(iddetalle)

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona con el id +$iddetalle")
        } else {
            try {
                detallecompraBusiness!!.deleteById(iddetalle)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
        }
    }


    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateDetalleC(detalle: DetalleCompra): DetalleCompra{
        val opt: Optional<DetalleCompra>
        try {
            opt = detallecompraBusiness!!.findById(detalle.id)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona ${detalle.id}")
        } else {
            try {

                var personaExist = DetalleCompra(detalle.idcompra,detalle.cantidad,detalle.precio,detalle.total)
                return detallecompraBusiness!!.save(detalle)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }
}
