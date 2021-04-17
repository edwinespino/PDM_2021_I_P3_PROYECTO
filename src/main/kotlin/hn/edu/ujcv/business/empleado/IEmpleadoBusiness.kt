package hn.edu.ujcv.business.empleado


import hn.edu.ujcv.model.Empleado
interface IEmpleadoBusiness {

    fun getEmpleado():List<Empleado>
    fun getEmpleadoById(idPersona: Long):Empleado
    fun saveEmpleado(empleado: Empleado): Empleado
    fun saveEmpleados(empleados: List<Empleado>):List<Empleado>
    fun removeEmpleado(idPersona: Long)
    /*fun getEmpleadoByNombre(nombrePersona: String):Empleado*/
    fun updateEmpleado(empleado: Empleado):Empleado


}