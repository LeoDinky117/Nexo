package com.nexo.app.nexo.model.datasource

import com.nexo.app.nexo.model.dto.CuentaDTO

interface CuentaDataSource{
    suspend fun obtenerCuentasPorusuario(idUsuario: Int): List<CuentaDTO>
    suspend fun crearCuenta(cuenta: CuentaDTO): Boolean
    suspend fun actualizarSaldo(idCuenta: Int, nuevoSaldo: Double): Boolean
}