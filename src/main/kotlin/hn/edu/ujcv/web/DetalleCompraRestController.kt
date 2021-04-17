package hn.edu.ujcv.web

import hn.edu.ujcv.business.detallecompra.IDetalleCompraBusiness
import hn.edu.ujcv.business.detalleventa.IDetalleventaBussines
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.DetalleCompra
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class DetalleCompraRestController {
    @RestController

    @RequestMapping(Constants.URL_BASE_DETALLECOMPRAS)
    class DetalleCompraRestController {
        @Autowired
        val detallecompraBusiness: IDetalleCompraBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<DetalleCompra>> {
            return try {
                ResponseEntity(detallecompraBusiness!!.getDetalleC(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") iddetalle: Long): ResponseEntity<DetalleCompra> {
            return try {
                ResponseEntity(detallecompraBusiness!!.getDetalleCById(iddetalle), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addDetalleCompra")
        fun insert(@RequestBody detalle: DetalleCompra): ResponseEntity<Any> {
            return try {
                detallecompraBusiness!!.saveDetalleC(detalle)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_DETALLECOMPRAS+ "/" + detalle.id)
                ResponseEntity(detalle, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addDetalleVentas")
        fun insert(@RequestBody detalles: List<DetalleCompra>): ResponseEntity<Any> {
            return try {
                ResponseEntity(detallecompraBusiness!!.saveDetalleCS(detalles), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PutMapping("")
        fun update(@RequestBody detalle: DetalleCompra): ResponseEntity<Any> {
            return try {
                detallecompraBusiness!!.updateDetalleC(detalle)
                ResponseEntity(detalle, HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

    }
}