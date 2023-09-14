package io.fusionauth.quickstart.springweb.model

class Change {
    @JvmField var error: String? = null
    @JvmField var total: String? = null
    @JvmField var nickels: Int? = null
    @JvmField var pennies: Int? = null

    fun getError(): String? {
        return error
    }

    fun setError(error: String?) {
        this.error = error
    }

    fun getTotal(): String? {
        return total
    }

    fun setTotal(total: String?) {
        this.total = total
    }

    fun getNickels(): Int? {
        return nickels
    }

    fun setNickels(nickels: Int?) {
        this.nickels = nickels
    }

    fun getPennies(): Int? {
        return pennies
    }

    fun setPennies(pennies: Int?) {
        this.pennies = pennies
    }

    fun getMessage(): String {
        return "We can make change for $total with $nickels nickels and $pennies pennies!"
    }
}
