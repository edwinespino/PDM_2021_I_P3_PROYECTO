package hn.edu.ujcv.web

import hn.edu.ujcv.business.empleado.IEmpleadoBusiness
import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Empleado
import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class EmpleadoRestController  {


    @RestController
//@RequestMapping("personas")
    @RequestMapping(Constants.URL_BASE_EMPLEADOS)
    class PersonaRestController {
        @Autowired
        val empleadoBusiness: IEmpleadoBusiness? = null
        @GetMapping("")
        fun list(): ResponseEntity<List<Empleado>> {
            return try {
                ResponseEntity(empleadoBusiness!!.getEmpleado(), HttpStatus.OK)
            }catch (e:Exception){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idPersona:Long): ResponseEntity<Empleado> {
            return try {
                ResponseEntity(empleadoBusiness!!.getEmpleadoById(idPersona), HttpStatus.OK)
            }catch (e:BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e:NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @PostMapping("/addEmpleado")
        fun insert(@RequestBody empleado: Empleado): ResponseEntity<Any> {
            return try {
                empleadoBusiness!!.saveEmpleado(empleado)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_CLIENTES + "/" + empleado.id)
                ResponseEntity(empleado,responseHeader, HttpStatus.CREATED)
            }catch (e:BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PostMapping("/addEmpleados")
        fun insert(@RequestBody empleados: List<Empleado>): ResponseEntity<Any> {
            return try {
                ResponseEntity(empleadoBusiness!!.saveEmpleados(empleados), HttpStatus.CREATED)
            }catch (e:BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
         fun update(@RequestBody empleado: Empleado): ResponseEntity<Any> {
            return try {
                empleadoBusiness!!.updateEmpleado(empleado)
                ResponseEntity(empleado, HttpStatus.OK)
            }catch (e:BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e:NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idPersona: Long): ResponseEntity<Any> {
            return try {
                empleadoBusiness!!.removeEmpleado(idPersona)
                ResponseEntity(HttpStatus.OK)
            }catch (e:BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e:NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }



}