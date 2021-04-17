package hn.edu.ujcv.business.formapago

import hn.edu.ujcv.dao.FormaPagoRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.FormasPago
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


    @Service
    class FormapagoBusiness: IFormapagoBusiness {
        @Autowired
        val formapagoRepository: FormaPagoRepository? = null


        @Throws(BusinessExeptions::class)
        override fun getFormaPago(): List<FormasPago> {
            try {
                return formapagoRepository!!.findAll();

            } catch (e: Exception) {
                throw BusinessExeptions(e.message)

            }
        }

        @Throws(BusinessExeptions::class, NotFoundException::class)
        override fun getFormaPagoById(idForma: Long): FormasPago {
            val opt: Optional<FormasPago>
            try {
                opt = formapagoRepository!!.findById(idForma)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
            return opt.get()
        }

        @Throws(BusinessExeptions::class)
        override fun saveFormaPago(formaPago: FormasPago): FormasPago {
            try {
                return formapagoRepository!!.save(formaPago)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
        }

        @Throws(BusinessExeptions::class)
        override fun saveFormasPago(formas: List<FormasPago>): List<FormasPago> {

            try {
                return formapagoRepository!!.saveAll(formas)

            } catch (e: Exception) {
                throw   BusinessExeptions(e.message)
            }
        }

        @Throws(BusinessExeptions::class, NotFoundException::class)
        override fun removeFormaPago(idForma: Long) {
            val opt: Optional<FormasPago>
            try {
                opt = formapagoRepository!!.findById(idForma)

            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
            if (!opt.isPresent) {
                throw NotFoundException("No se ha encontrado la persona con el id +$idForma")
            } else {
                try {
                    formapagoRepository!!.deleteById(idForma)
                } catch (e: Exception) {
                    throw BusinessExeptions(e.message)
                }
            }

        }

        @Throws(BusinessExeptions::class, NotFoundException::class)
        override fun updateFormaPago(formas: FormasPago): FormasPago {
            val opt: Optional<FormasPago>
            try {
                opt = formapagoRepository!!.findById(formas.id)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
            if(!opt.isPresent){
                throw NotFoundException("No se ha encontrado la persona ${formas.id}")
            }else{
                try {

                    var formaExist = FormasPago(formas.descripcion,formas.estado)
                    return formapagoRepository!!.save(formas)
                }catch (e:Exception){
                    throw BusinessExeptions(e.message)
                }

            }
            return opt.get()
        }
}


