package hn.edu.ujcv.web

import hn.edu.ujcv.business.detalleventa.IDetalleventaBussines
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.DetalleVenta
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


class DetalleVentaRestController {

    @RestController

    @RequestMapping(Constants.URL_BASE_DETALLEVENTAS)
    class PersonaRestController {
        @Autowired
        val detallevBusiness: IDetalleventaBussines? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<DetalleVenta>> {
            return try {
                ResponseEntity(detallevBusiness!!.getDetalleV(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }







        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idDetalleV: Long): ResponseEntity<DetalleVenta> {
            return try {
                ResponseEntity(detallevBusiness!!.getDetalleVById(idDetalleV), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @PostMapping("/addDetalleVenta")
        fun insert(@RequestBody detallev: DetalleVenta): ResponseEntity<Any> {
            return try {
                detallevBusiness!!.saveDetalleV(detallev)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_DETALLEVENTAS + "/" + detallev.id)
                ResponseEntity(detallev, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addDetalleVentas")
        fun insert(@RequestBody detalleventas: List<DetalleVenta>): ResponseEntity<Any> {
            return try {
                ResponseEntity(detallevBusiness!!.saveDetalleVS(detalleventas), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PutMapping("")
        fun update(@RequestBody detallev: DetalleVenta): ResponseEntity<Any> {
            return try {
                detallevBusiness!!.updateDetalleV(detallev)
                ResponseEntity(detallev, HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idDetalleV: Long): ResponseEntity<Any> {
            return try {
                detallevBusiness!!.removeDetalleV(idDetalleV)
                ResponseEntity(HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}

