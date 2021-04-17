package hn.edu.ujcv.business.productos

import hn.edu.ujcv.dao.ProduccionRepository
import hn.edu.ujcv.dao.ProductosRepository
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Produccion
import hn.edu.ujcv.model.Productos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
@Service
class ProductosBusiness: IProductosBusiness {
    @Autowired
    val productosRepository: ProductosRepository?=null

    @Throws(BusinessExeptions::class)
    override fun getProduto(): List<Productos> {
        try {
            return productosRepository!!.findAll();

        }catch (e:Exception){
            throw BusinessExeptions(e.message)

        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun  getProdutoById(idproducto: Long): Productos {
        val opt: Optional<Productos>
        try {
            opt = productosRepository!!.findById(idproducto)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        return opt.get()
    }

    @Throws(BusinessExeptions::class)
    override fun saveProduto(producto: Productos): Productos {
        try {
            return productosRepository!!.save(producto)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class)
    override fun saveProdutos(productos: List<Productos>):List<Productos> {

        try {
            return productosRepository!!.saveAll(productos)

        }catch (e:Exception){
            throw   BusinessExeptions(e.message)
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun removeProduto(idproducto: Long){
        val opt: Optional<Productos>
        try {
            opt =  productosRepository!!.findById(idproducto)

        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la orden de produccion con el id +$idproducto")
        }else{
            try {
                productosRepository!!.deleteById(idproducto)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }
        }
    }

    @Throws(BusinessExeptions::class, NotFoundException::class)
    override fun updateProduto(producto: Productos): Productos{
        val opt: Optional<Productos>
        try {
            opt = productosRepository!!.findById(producto.id)
        }catch (e:Exception){
            throw BusinessExeptions(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se ha encontrado la orden de produccion con id  ${producto.id}")
        }else{
            try {

                var productoExist = Productos(producto.nombre,producto.descripcion,producto.preciocompra,producto.precioventa)
                return productosRepository!!.save(producto)
            }catch (e:Exception){
                throw BusinessExeptions(e.message)
            }

        }
        return opt.get()
    }
}