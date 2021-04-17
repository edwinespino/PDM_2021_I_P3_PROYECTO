package hn.edu.ujcv.web

import hn.edu.ujcv.business.produccion.IProduccionBusiness
import hn.edu.ujcv.business.ventas.IVentasBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Produccion
import hn.edu.ujcv.model.Ventas
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class ProduccionRestController {
    @RestController

    @RequestMapping(Constants.URL_BASE_PRODUCCION)
    class InsumosRestController {
        @Autowired
        val produccionBusiness: IProduccionBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<Produccion>> {
            return try {
                ResponseEntity(produccionBusiness!!.getProduccion(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idProduccion: Long): ResponseEntity<Produccion> {
            return try {
                ResponseEntity(produccionBusiness!!.getProduccionById(idProduccion), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addProduccion")
        fun insert(@RequestBody produccion: Produccion): ResponseEntity<Any> {
            return try {
                produccionBusiness!!.saveProduccion(produccion)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_PRODUCCION + "/" + produccion.id)
                ResponseEntity(produccion, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addProducciones")
        fun insert(@RequestBody producciones:List<Produccion>): ResponseEntity<Any> {
            return try {
                ResponseEntity(produccionBusiness!!.saveProducciones(producciones), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody produccion: Produccion): ResponseEntity<Any> {
            return try {
                produccionBusiness!!.updateProduccion(produccion)
                ResponseEntity(produccion, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idproduccion: Long): ResponseEntity<Any> {
            return try {
                produccionBusiness!!.removeProduccion(idproduccion)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}