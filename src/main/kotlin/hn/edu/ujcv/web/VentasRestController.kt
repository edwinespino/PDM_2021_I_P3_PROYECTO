package hn.edu.ujcv.web

import hn.edu.ujcv.business.ventas.IVentasBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Ventas
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class VentasRestController {
    @RestController

    @RequestMapping(Constants.URL_BASE_VENTAS)
    class InsumosRestController {
        @Autowired
        val ventasBusiness: IVentasBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<Ventas>> {
            return try {
                ResponseEntity(ventasBusiness!!.getVenta(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idventa: Long): ResponseEntity<Ventas> {
            return try {
                ResponseEntity(ventasBusiness!!.getVentaById(idventa), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addVenta")
        fun insert(@RequestBody venta: Ventas): ResponseEntity<Any> {
            return try {
                ventasBusiness!!.saveVenta(venta)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_INSUMOS + "/" + venta.id)
                ResponseEntity(venta, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addVentas")
        fun insert(@RequestBody insumos:List<Ventas>): ResponseEntity<Any> {
            return try {
                ResponseEntity(ventasBusiness!!.saveVentas(insumos), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody venta: Ventas): ResponseEntity<Any> {
            return try {
                ventasBusiness!!.updateVenta(venta)
                ResponseEntity(venta, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idventa: Long): ResponseEntity<Any> {
            return try {
                ventasBusiness!!.removeVenta(idventa)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}