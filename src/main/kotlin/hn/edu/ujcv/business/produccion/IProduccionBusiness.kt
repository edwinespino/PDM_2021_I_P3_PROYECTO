package hn.edu.ujcv.business.produccion

import hn.edu.ujcv.model.Produccion

interface IProduccionBusiness {
    fun getProduccion():List<Produccion>
    fun getProduccionById(idProduccion: Long): Produccion
    fun saveProduccion(produccion: Produccion): Produccion
    fun saveProducciones(producciones: List<Produccion>):List<Produccion>
    fun removeProduccion(idProduccion: Long)
    fun updateProduccion(produccion: Produccion): Produccion



}