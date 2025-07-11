package ca.com.alvesgf.wawg2e.model

import kotlin.random.Random

data class User(var name: String, var email: String) {
    var birthDate: String? = null
    var username: String? = null
        set(value) {
            field = value
            if (internalId.isNullOrBlank()) {
                createInternalId()
            } else {
                internalId = "$value#${internalId?.substringAfter("#")}"
            }
        }
    var internalId: String? = null
        private set

    constructor(name: String, email: String, birthDate: String, username: String) : this(name, email) {
        this.birthDate = birthDate
        this.username = username
        createInternalId()
    }

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name is blank")
        }
        this.email = validateEmail()
    }

    override fun toString(): String {
        return "User(name='$name', email='$email', birthDate=$birthDate, username=$username, internalId=$internalId)"
    }

    fun createInternalId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        internalId = "$username#$tag"
    }

    fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@]{1}[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Invalid email address")
        }
    }
}
