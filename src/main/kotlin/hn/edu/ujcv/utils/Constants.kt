package hn.edu.ujcv.utils


class Constants {
    companion object  {
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_EMPLEADOS = "$URL_BASE/empleados"
        const val URL_BASE_CLIENTES = "$URL_BASE/clientes"
        const val URL_BASE_PROVEEDORES = "$URL_BASE/proveedores"
        const val URL_BASE_COMPRAS = "$URL_BASE/compras"
        const val URL_BASE_INSUMOS= "$URL_BASE/insumos"
        const val URL_BASE_DEPARTAMENTOS="$URL_BASE/departamentos"
        const val URL_BASE_DETALLEVENTAS= "$URL_BASE/detalleventa"
        const val URL_BASE_FORMASPAGO= "$URL_BASE/formaspago"
        const val URL_BASE_PRODUCCION= "$URL_BASE/produccion"
        const val URL_BASE_VENTAS= "$URL_BASE/ventas"
        const val URL_BASE_DETALLECOMPRAS= "$URL_BASE/detallecompras"


    }



}