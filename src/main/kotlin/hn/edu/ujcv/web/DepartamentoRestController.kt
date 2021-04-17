package hn.edu.ujcv.web

import hn.edu.ujcv.business.cliente.IClienteBusiness
import hn.edu.ujcv.business.departamentos.IDepartamentoBusiness

import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Cliente
import hn.edu.ujcv.model.Departamento
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class DepartamentoRestController {
    @RestController
//@RequestMapping("departamento")
    @RequestMapping(Constants.URL_BASE_DEPARTAMENTOS)
    class DepartamentoRestController {
        @Autowired
        val departamentoBusiness: IDepartamentoBusiness? = null
        @GetMapping("")
        fun list(): ResponseEntity<List<Departamento>> {
            return try {
                ResponseEntity(departamentoBusiness!!.getDepto(), HttpStatus.OK)
            }catch (e:Exception){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") iddepto:Long): ResponseEntity<Departamento> {
            return try {
                ResponseEntity(departamentoBusiness!!.getDeptoById(iddepto), HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addDepto")
        fun insert(@RequestBody depto: Departamento): ResponseEntity<Any> {
            return try {
                departamentoBusiness!!.saveDepto(depto)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_DEPARTAMENTOS + "/" + depto.id)
                ResponseEntity(depto,responseHeader, HttpStatus.CREATED)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PostMapping("/addDeptos")
        fun insert(@RequestBody deptos: List<Departamento>): ResponseEntity<Any> {
            return try {
                ResponseEntity(departamentoBusiness!!.saveDeptos(deptos), HttpStatus.CREATED)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody depto: Departamento): ResponseEntity<Any> {
            return try {
                departamentoBusiness!!.updateDepto(depto)
                ResponseEntity(depto, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") iddepto: Long): ResponseEntity<Any> {
            return try {
                departamentoBusiness!!.removeDepto(iddepto)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}