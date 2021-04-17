package hn.edu.ujcv.web

import hn.edu.ujcv.business.insumos.IInsumosBusiness

import hn.edu.ujcv.exceptions.BusinessExeptions
import hn.edu.ujcv.exceptions.NotFoundException
import hn.edu.ujcv.model.Insumos

import hn.edu.ujcv.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class InsumosRestController {
    @RestController

    @RequestMapping(Constants.URL_BASE_INSUMOS)
    class InsumosRestController {
        @Autowired
        val insumosBusiness: IInsumosBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<Insumos>> {
            return try {
                ResponseEntity(insumosBusiness!!.getInsumo(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadBydId(@PathVariable("id") idinsumo: Long): ResponseEntity<Insumos> {
            return try {
                ResponseEntity(insumosBusiness!!.getInsumoById(idinsumo), HttpStatus.OK)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @PostMapping("/addInsumo")
        fun insert(@RequestBody insumo: Insumos): ResponseEntity<Any> {
            return try {
                insumosBusiness!!.saveInsumo(insumo)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_INSUMOS + "/" + insumo.id)
                ResponseEntity(insumo, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addInsumos")
        fun insert(@RequestBody insumos:List<Insumos>): ResponseEntity<Any> {
            return try {
                ResponseEntity(insumosBusiness!!.saveInsumos(insumos), HttpStatus.CREATED)
            } catch (e: BusinessExeptions) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        @PutMapping("")
        fun update(@RequestBody insumo: Insumos ): ResponseEntity<Any> {
            return try {
                insumosBusiness!!.updateInsumo(insumo)
                ResponseEntity(insumo, HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idinsumo: Long): ResponseEntity<Any> {
            return try {
           insumosBusiness!!.removeInsumo(idinsumo)
                ResponseEntity(HttpStatus.OK)
            }catch (e: BusinessExeptions){
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }catch (e: NotFoundException){
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}