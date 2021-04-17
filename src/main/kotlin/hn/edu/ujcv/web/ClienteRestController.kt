package hn.edu.ujcv.web

import hn.edu.ujcv.business.cliente.IClienteBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Cliente
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class ClienteRestController {
    @RestController
//@RequestMapping("clientes")
    @RequestMapping(Constants.URL_BASE_CLIENTES)
    class ClienteRestController {
        @Autowired
        val clienteBusiness: IClienteBusiness? = null
        @GetMapping("")
        fun list(): ResponseEntity<List<Cliente>> {
            return try {
                ResponseEntity(clienteBusiness!!.getCliente(), HttpStatus.OK)
            }catch (e:Exception){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idCliente:Long): ResponseEntity<Cliente> {
            return try {
                ResponseEntity(clienteBusiness!!.getClienteById(idCliente), HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addCliente")
        fun insert(@RequestBody cliente: Cliente): ResponseEntity<Any> {
            return try {
                clienteBusiness!!.saveCliente(cliente)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_CLIENTES + "/" + cliente.id)
                ResponseEntity(cliente,responseHeader, HttpStatus.CREATED)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PostMapping("/addClientes")
        fun insert(@RequestBody clientes: List<Cliente>): ResponseEntity<Any> {
            return try {
                ResponseEntity(clienteBusiness!!.saveClientes(clientes), HttpStatus.CREATED)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody cliente: Cliente): ResponseEntity<Any> {
            return try {
                clienteBusiness!!.updateCliente(cliente)
                ResponseEntity(cliente, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idCliente: Long): ResponseEntity<Any> {
            return try {
                clienteBusiness!!.removeCliente(idCliente)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}