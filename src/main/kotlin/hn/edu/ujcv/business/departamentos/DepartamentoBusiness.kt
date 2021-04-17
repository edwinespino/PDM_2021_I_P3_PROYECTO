package hn.edu.ujcv.business.departamentos

import hn.edu.ujcv.dao.DeptoRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Departamento
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
@Service
class DepartamentoBusiness:IDepartamentoBusiness {
        @Autowired
        val departamentoRepository: DeptoRepository? = null


        @Throws(BusinessExeptions::class)
        override fun getDepto(): List<Departamento> {
            try {
                return departamentoRepository!!.findAll();
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)

            }
        }

        @Throws(BusinessExeptions::class, NotFoundException::class)
        override fun getDeptoById(iddepto: Long): Departamento {
            val opt: Optional<Departamento>
            try {
                opt = departamentoRepository!!.findById(iddepto)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
            return opt.get()
        }

        @Throws(BusinessExeptions::class)
        override fun saveDepto(depto: Departamento): Departamento {
            try {
                return departamentoRepository!!.save(depto)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
        }

        @Throws(BusinessExeptions::class)
        override fun saveDeptos(deptos: List<Departamento>): List<Departamento> {

            try {
                return departamentoRepository!!.saveAll(deptos)

            } catch (e: Exception) {
                throw   BusinessExeptions(e.message)
            }
        }

        @Throws(BusinessExeptions::class, NotFoundException::class)
        override fun removeDepto(iddepto: Long) {

            val opt: Optional<Departamento>
            try {
                opt = departamentoRepository!!.findById(iddepto)

            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
            if (!opt.isPresent) {
                throw NotFoundException("No se ha encontrado la persona con el id +$iddepto")
            } else {
                try {
                    departamentoRepository!!.deleteById(iddepto)
                } catch (e: Exception) {
                    throw BusinessExeptions(e.message)
                }
            }

        }

        @Throws(BusinessExeptions::class, NotFoundException::class)
        override fun updateDepto(depto: Departamento): Departamento {
            val opt: Optional<Departamento>
            try {
                opt = departamentoRepository!!.findById(depto.id)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
            if(!opt.isPresent){
                throw NotFoundException("No se ha encontrado la persona ${deptoid}")
            }else{
                try {

                    var departamentoExist = Departamento(depto.nombre,depto.descripcion)
                    return departamentoRepository!!.save(depto)
                }catch (e:Exception){
                    throw BusinessExeptions(e.message)
                }

            }
            return opt.get()
        }

    }
}