package hn.edu.ujcv.utils


class Constants {
    companion object C2{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_CLIENTES = "$URL_BASE/clientes"
        const val URL_BASE_EMPLEADO = "$URL_BASE/empleados"
    }


}