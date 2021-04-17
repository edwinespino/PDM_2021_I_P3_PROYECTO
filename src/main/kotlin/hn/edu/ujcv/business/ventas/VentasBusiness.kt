package hn.edu.ujcv.business.ventas



import hn.edu.ujcv.dao.VentasRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException

import hn.edu.ujcv.model.Ventas
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class VentasBusiness: IVentasBusiness {
    @Autowired
    val ventasRepository: VentasRepository?=null

    @Throws(BusinessExeptions::class)
    override fun getVenta(): List<Ventas> {
        try {
            return ventasRepository!!.findAll();

        }catch (e:Exception){
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getVentaById(idVenta: Long): Ventas {
        val opt: Optional<Ventas>
        try {
            opt = ventasRepository!!.findById(idVenta)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveVenta(ventas: Ventas): Ventas {
        try {
            return ventasRepository!!.save(ventas)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveVentas(ventas: List<Ventas>):List<Ventas> {

        try {
            return ventasRepository!!.saveAll(ventas)

        }catch (e:Exception){
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeVenta(idVenta: Long){
        val opt: Optional<Ventas>
        try {
            opt =  ventasRepository!!.findById(idVenta)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona con el id +$idVenta")
        }else{
            try {
                ventasRepository!!.deleteById(idVenta)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateVenta(venta: Ventas): Ventas {
        val opt: Optional<Ventas>
        try {
            opt = ventasRepository!!.findById(venta.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la venta con id  ${venta.id}")
        }else{
            try {

                var ventaExist = Ventas(venta.descripcion,venta.idempleado,venta.cai,venta.idcliente,venta.numerotarjeta, venta.formadepago,venta.fechaventa,venta.fechaentrega)
                return ventasRepository!!.save(venta)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }
















}
