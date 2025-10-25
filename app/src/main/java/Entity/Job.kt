package Entity

import android.graphics.Bitmap

class Job {
    private var id: String=""
    private var puesto: String=""
    private var nombreEmpresa: String=""
    private var ubicacion: String=""
    private var modalidad: String=""
    private var jornadaLaboral: String=""
    private var descripcion: String=""
    private var requisitos: String=""
    private lateinit var logoEmpresa: Bitmap
    private var numeroTelefono: String=""
    private var correo: String=""

    constructor(
        id: String, puesto: String, nombreEmpresa: String,
        ubicacion: String, modalidad: String, jornadaLaboral: String,
        descripcion: String, requisitos: String, logoEmpresa: Bitmap,   // obligatorio
        numeroTelefono: String, correo: String
    ) {
        this.id = id
        this.puesto = puesto
        this.nombreEmpresa = nombreEmpresa
        this.ubicacion = ubicacion
        this.modalidad = modalidad
        this.jornadaLaboral = jornadaLaboral
        this.descripcion = descripcion
        this.requisitos = requisitos
        this.logoEmpresa = logoEmpresa
        this.numeroTelefono = numeroTelefono
        this.correo = correo

    }

    var ID : String
        get() = this.id
        set(value) {this.id=value}

    var Puesto: String
        get() = this.puesto
        set(value) { this.puesto = value }

    var NombreEmpresa: String
        get() = this.nombreEmpresa
        set(value) { this.nombreEmpresa = value }

    var Ubicacion: String
        get() = this.ubicacion
        set(value) { this.ubicacion = value }

    var Modalidad: String
        get() = this.modalidad
        set(value) { this.modalidad = value }

    var JornadaLaboral: String
        get() = this.jornadaLaboral
        set(value) { this.jornadaLaboral = value }

    var Descripcion: String
        get() = this.descripcion
        set(value) { this.descripcion = value }

    var Requisitos: String
        get() = this.requisitos
        set(value) { this.requisitos = value }

    var LogoEmpresa: Bitmap
        get() = this.logoEmpresa
        set(value) { this.logoEmpresa = value }

    var NumeroTelefono: String
        get() = this.numeroTelefono
        set(value) { this.numeroTelefono = value }

    var Correo: String
        get() = this.correo
        set(value) { this.correo = value }
}