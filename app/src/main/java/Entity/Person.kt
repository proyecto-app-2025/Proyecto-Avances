package Entity

import android.graphics.Bitmap
import java.time.LocalDate

class Person() {

    private var id: String = ""
    private var name: String = ""
    private var firstLastName: String = ""
    private var secondLastName: String = ""
    private var email: String = ""
    private var phone: Int = 0
    private lateinit var birthDate: LocalDate
    private var photo: Bitmap? = null

    // Secondary constructor
    constructor(
        id: String,
        name: String,
        firstLastName: String,
        secondLastName: String,
        email: String,
        phone: Int,
        birthDate: LocalDate,
        photo: Bitmap? = null
    ) : this() {
        this.id = id
        this.name = name
        this.firstLastName = firstLastName
        this.secondLastName = secondLastName
        this.email = email
        this.phone = phone
        this.birthDate = birthDate
        this.photo = photo
    }

    // Public properties (PascalCase)
    var ID: String
        get() = id
        set(value) { id = value }

    var Name: String
        get() = name
        set(value) { name = value }

    var FirstLastName: String
        get() = firstLastName
        set(value) { firstLastName = value }

    var SecondLastName: String
        get() = secondLastName
        set(value) { secondLastName = value }

    var Email: String
        get() = email
        set(value) { email = value }

    var Phone: Int
        get() = phone
        set(value) { phone = value }

    var BirthDate: LocalDate
        get() = birthDate
        set(value) { birthDate = value }

    var Photo: Bitmap?
        get() = photo
        set(value) { photo = value }

    // Utilities
    fun FullName(): String = "$name $firstLastName $secondLastName"

}
