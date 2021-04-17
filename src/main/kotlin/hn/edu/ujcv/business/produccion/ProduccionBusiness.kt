package hn.edu.ujcv.business.produccion

import hn.edu.ujcv.business.ventas.IVentasBusiness
import hn.edu.ujcv.dao.ProduccionRepository
import hn.edu.ujcv.dao.VentasRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Produccion
import hn.edu.ujcv.model.Ventas
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProduccionBusiness: IProduccionBusiness{
    @Autowired
    val produccionRepository: ProduccionRepository?=null

    @Throws(BusinessExeptions::class)
    override fun getProduccion(): List<Produccion> {
        try {
            return produccionRepository!!.findAll();

        }catch (e:Exception){
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getProduccionById(idProduccion: Long): Produccion {
        val opt: Optional<Produccion>
        try {
            opt = produccionRepository!!.findById(idProduccion)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveProduccion(produccion: Produccion): Produccion {
        try {
            return produccionRepository!!.save(produccion)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveProducciones(producciones: List<Produccion>):List<Produccion> {

        try {
            return produccionRepository!!.saveAll(producciones)

        }catch (e:Exception){
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeProduccion(idProduccion: Long){
        val opt: Optional<Produccion>
        try {
            opt =  produccionRepository!!.findById(idProduccion)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la orden de produccion con el id +$idProduccion")
        }else{
            try {
                produccionRepository!!.deleteById(idProduccion)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateProduccion(produccion: Produccion): Produccion {
        val opt: Optional<Produccion>
        try {
            opt = produccionRepository!!.findById(produccion.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la orden de produccion con id  ${produccion.id}")
        }else{
            try {

                var ventaExist = Produccion(produccion.idproducto,produccion.idempleado,produccion.iddepto,produccion.descripcion,produccion.tiempo)
                return produccionRepository!!.save(produccion)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }
















}
