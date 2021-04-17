package hn.edu.ujcv.web

import hn.edu.ujcv.business.compras.ICompraBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Compra
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class CompraRestController {

    @RestController

    @RequestMapping(Constants.URL_BASE_COMPRAS)
    class PersonaRestController {
        @Autowired
        val compraBusiness: ICompraBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<Compra>> {
            return try {
                ResponseEntity(compraBusiness!!.getCompra(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idCompra: Long): ResponseEntity<Compra> {
            return try {
                ResponseEntity(compraBusiness!!.getCompraById(idCompra), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @PostMapping("/addCompra")
        fun insert(@RequestBody compra: Compra): ResponseEntity<Any> {
            return try {
                compraBusiness!!.saveCompra(compra)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_COMPRAS + "/" + compra.id)
                ResponseEntity(compra, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addCompras")
        fun insert(@RequestBody compras: List<Compra>): ResponseEntity<Any> {
            return try {
                ResponseEntity(compraBusiness!!.saveCompras(compras), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PutMapping("")
        fun update(@RequestBody compra: Compra): ResponseEntity<Any> {
            return try {
                compraBusiness!!.updateCompra(compra)
                ResponseEntity(compra, HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idCompra: Long): ResponseEntity<Any> {
            return try {
                compraBusiness!!.removeCompra(idCompra)
                ResponseEntity(HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}







