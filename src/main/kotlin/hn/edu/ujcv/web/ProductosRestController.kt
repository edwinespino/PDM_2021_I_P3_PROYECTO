package hn.edu.ujcv.web

import hn.edu.ujcv.business.produccion.IProduccionBusiness
import hn.edu.ujcv.business.productos.IProductosBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Produccion
import hn.edu.ujcv.model.Productos
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class ProductosRestController {
    @RestController

    @RequestMapping(Constants.URL_BASE_PRODUCTOS)
    class ProduccionRestController {
        @Autowired
        val productosBusiness: IProductosBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<Productos>> {
            return try {
                ResponseEntity(productosBusiness!!.getProduto(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idproducto: Long): ResponseEntity<Productos> {
            return try {
                ResponseEntity(productosBusiness!!.getProdutoById(idproducto), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addProducto")
        fun insert(@RequestBody producto: Productos): ResponseEntity<Any> {
            return try {
                productosBusiness!!.saveProduto(producto)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_PRODUCTOS + "/" + producto.id)
                ResponseEntity(producto, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addProductos")
        fun insert(@RequestBody productos:List<Productos>): ResponseEntity<Any> {
            return try {
                ResponseEntity(productosBusiness!!.saveProdutos(productos), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody producto: Productos): ResponseEntity<Any> {
            return try {
                productosBusiness!!.updateProduto(producto)
                ResponseEntity(producto, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idproducto: Long): ResponseEntity<Any> {
            return try {
                productosBusiness!!.removeProduto(idproducto)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}