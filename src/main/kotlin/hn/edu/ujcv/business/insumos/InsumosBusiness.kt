package hn.edu.ujcv.business.insumos

import hn.edu.ujcv.dao.InsumosRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Insumos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
@Service
class InsumosBusiness:IInsumosBusiness {
    @Autowired
    val insumosRepository: InsumosRepository? = null


    @Throws(BusinessExeptions::class)
    override fun getInsumo(): List<Insumos> {
        try {
            return insumosRepository!!.findAll();

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getInsumoById(idinsumo: Long): Insumos {
        val opt: Optional<Insumos>
        try {
            opt = insumosRepository!!.findById(idinsumo)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveInsumo(insumo: Insumos): Insumos {
        try {
            return insumosRepository!!.save(insumo)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveInsumos(insumos: List<Insumos>): List<Insumos> {

        try {
            return insumosRepository!!.saveAll(insumos)

        } catch (e: Exception) {
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeInsumo(idinsumo: Long) {
        val opt: Optional<Insumos>
        try {
            opt = insumosRepository!!.findById(idinsumo)

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona con el id +$idinsumo")
        } else {
            try {
                insumosRepository!!.deleteById(idinsumo)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
        }

    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateInsumo(insumo: Insumos): Insumos {
        val opt: Optional<Insumos>
        try {
            opt = insumosRepository!!.findById(insumo.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la persona ${insumo.id}")
        }else{
            try {

                var insumoExist = Insumos(insumo.nombre,insumo.tipo, insumo.cantidad, insumo.preciocompra,insumo.precioventa)
                return insumosRepository!!.save(insumo)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }


}