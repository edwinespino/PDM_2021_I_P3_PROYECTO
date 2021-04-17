package hn.edu.ujcv.business.insumos


import hn.edu.ujcv.model.Insumos

interface IInsumosBusiness {

    fun getInsumo(): List<Insumos>
    fun getInsumoById(idinsumo: Long): Insumos
    fun saveInsumo(insumo: Insumos): Insumos
    fun saveInsumos(insumos: List<Insumos>): List<Insumos>
    fun removeInsumo(idinsumo: Long)
    fun updateInsumo(insumo: Insumos): Insumos
}