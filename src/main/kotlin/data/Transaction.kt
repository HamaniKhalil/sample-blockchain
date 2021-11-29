package data

import com.google.gson.Gson

class Transaction(
    val fromAddress: String?,
    val toAddress: String,
    val amount: Double
) {

    override fun toString(): String =
        Gson().toJson(this)

    companion object {
        val GENESIS = Transaction("0", "0", 0.0)
    }
}