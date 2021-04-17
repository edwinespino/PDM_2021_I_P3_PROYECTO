package hn.edu.ujcv.web

import hn.edu.ujcv.business.proveedores.IProveedoresBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Proveedores
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class ProveedoresRestController {
    @RestController
//@RequestMapping("personas")
    @RequestMapping(Constants.URL_BASE_PROVEEDORES)
    class PersonaRestController {
        @Autowired
        val proveedoresBusiness: IProveedoresBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<Proveedores>> {
            return try {
                ResponseEntity(proveedoresBusiness!!.getProveedor(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idProveedor: Long): ResponseEntity<Proveedores> {
            return try {
                ResponseEntity(proveedoresBusiness!!.getProveedorById(idProveedor), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @PostMapping("/addProveedor")
        fun insert(@RequestBody proveedor: Proveedores): ResponseEntity<Any> {
            return try {
                proveedoresBusiness!!.saveProveedor(proveedor)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_PROVEEDORES + "/" + proveedor.id)
                ResponseEntity(proveedor, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addProveedores")
        fun insert(@RequestBody proveedores: List<Proveedores>): ResponseEntity<Any> {
            return try {
                ResponseEntity(proveedoresBusiness!!.saveProveedor(proveedores), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody proveedor: Proveedores ): ResponseEntity<Any> {
            return try {
                proveedoresBusiness!!.updateProveedor(proveedor)
                ResponseEntity(proveedor, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idProveedor: Long): ResponseEntity<Any> {
            return try {
               proveedoresBusiness!!.removeProveedor(idProveedor)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

    }
}
