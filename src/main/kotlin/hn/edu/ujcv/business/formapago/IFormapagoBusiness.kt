package hn.edu.ujcv.business.formapago

import hn.edu.ujcv.model.Empleado
import hn.edu.ujcv.model.FormasPago

interface IFormapagoBusiness {
    fun getFormaPago():List<FormasPago>
    fun getFormaPagoById(idForma: Long): FormasPago
    fun saveFormaPago(forma: FormasPago): FormasPago
    fun saveFormasPago(formas: List<FormasPago>):List<FormasPago>
    fun removeFormaPago(idPersona: Long)

    fun updateFormaPago(empleado: FormasPago): FormasPago


}