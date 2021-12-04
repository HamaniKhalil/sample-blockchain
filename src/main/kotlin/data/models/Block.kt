package data.models

import com.google.gson.GsonBuilder
import config.crypto.HASH_DIFFICULTY
import config.crypto.HASH_SIZE
import config.crypto.exceptions.WrongDifficultySizeException
import config.extensions.toHashString
import data.types.Hashable
import data.types.Miner
import data.types.TransactionArray
import java.util.*
import kotlin.jvm.Throws

class Block : Hashable,
    Miner {

    val transactions: TransactionArray = TransactionArray()
    private val timestamp: Date = Date()
    var nounce: Int = 0
    var previousHash: String = "0"
    var hash: String = calculateHash()

    override fun calculateHash(): String =
        "$transactions$timestamp$nounce$previousHash".toHashString()

    @Throws(WrongDifficultySizeException::class)
    override fun mine() {
        if(HASH_DIFFICULTY.isEmpty() || HASH_DIFFICULTY.length > HASH_SIZE) throw WrongDifficultySizeException()

        println("|\tBlock mining started...\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
        while (!hash.contains(HASH_DIFFICULTY)) {
            nounce = (Math.random() * 10 * HASH_DIFFICULTY.length).toInt()
            hash = calculateHash()
        }

        println("|\tBlock have been mined, the resulting hash is : $hash\t\t\t\t|")
    }

    override fun toString(): String =
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this)

    companion object {
        val GENESIS = Block()
    }
}
