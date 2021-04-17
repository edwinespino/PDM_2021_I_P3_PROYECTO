package hn.edu.ujcv.business.compras

import hn.edu.ujcv.dao.CompraRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Compra
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompraBusiness:ICompraBusiness {

    @Autowired
    val compraRepositorio: CompraRepository? = null

    @Throws(BusinessExeptions::class)
    override fun getCompra(): List<Compra> {
        try {
            return compraRepositorio!!.findAll();

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun getCompraById(idCompra: Long): Compra {
        val opt: Optional<Compra>
        try {
            opt = compraRepositorio!!.findById(idCompra)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveCompra(compra: Compra): Compra {
        try {
            return compraRepositorio!!.save(compra)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveCompras(compras: List<Compra>): List<Compra> {

        try {
            return compraRepositorio!!.saveAll(compras)

        } catch (e: Exception) {
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeCompra(idCompra: Long) {
        val opt: Optional<Compra>
        try {
            opt = compraRepositorio!!.findById(idCompra)

        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona con el id +$idCompra")
        } else {
            try {
                compraRepositorio!!.deleteById(idCompra)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateCompra(compra: Compra): Compra {
        val opt: Optional<Compra>
        try {
            opt = compraRepositorio!!.findById(compra.id)
        } catch (e: Exception) {
            throw BusinessExeptions(e.message)
        }
        if (!opt.isPresent) {
            throw NotFoundException("No se ha encontrado la persona ${compra.id}")
        } else {
            try {

                var personaExist = Compra(
                    compra.cai,
                    compra.proveedores,
                    compra.numerotarjeta,
                    compra.formapago,
                    compra.fechaentrega,
                    compra.fechacompra,
                    compra.insumos
                )
                return compraRepositorio!!.save(compra)
            } catch (e: Exception) {
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }


}