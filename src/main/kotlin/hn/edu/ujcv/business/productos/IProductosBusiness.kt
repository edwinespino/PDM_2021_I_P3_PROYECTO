package hn.edu.ujcv.business.productos

import hn.edu.ujcv.model.Productos


interface IProductosBusiness {
    fun getProduto():List<Productos>
    fun getProdutoById(idproducto: Long): Productos
    fun saveProduto(producto: Productos): Productos
    fun saveProdutos(productos: List<Productos>):List<Productos>
    fun removeProduto(idproducto: Long)
    fun updateProduto(producto: Productos): Productos
}