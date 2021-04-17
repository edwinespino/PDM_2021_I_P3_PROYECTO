package hn.edu.ujcv.web

import hn.edu.ujcv.business.formapago.IFormapagoBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.FormasPago
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class FormaPagoRestController {


    @RestController
//@RequestMapping("personas")
    @RequestMapping(Constants.URL_BASE_FORMASPAGO)
    class PersonaRestController {
        @Autowired
        val formaBusiness: IFormapagoBusiness? = null
        @GetMapping("")
        fun list(): ResponseEntity<List<FormasPago>> {
            return try {
                ResponseEntity(formaBusiness!!.getFormaPago(), HttpStatus.OK)
            }catch (e:Exception){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idForma:Long): ResponseEntity<FormasPago> {
            return try {
                ResponseEntity(formaBusiness!!.getFormaPagoById(idForma), HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @PostMapping("/addForma")
        fun insert(@RequestBody forma: FormasPago): ResponseEntity<Any> {
            return try {
                formaBusiness!!.saveFormaPago(forma)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_CLIENTES + "/" + forma.id)
                ResponseEntity(forma,responseHeader, HttpStatus.CREATED)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PostMapping("/addFormas")
        fun insert(@RequestBody formas: List<FormasPago>): ResponseEntity<Any> {
            return try {
                ResponseEntity(formaBusiness!!.saveFormasPago(formas), HttpStatus.CREATED)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody forma: FormasPago): ResponseEntity<Any> {
            return try {
                formaBusiness!!.updateFormaPago(forma)
                ResponseEntity(forma, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idForma: Long): ResponseEntity<Any> {
            return try {
                formaBusiness!!.removeFormaPago(idForma)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }

}