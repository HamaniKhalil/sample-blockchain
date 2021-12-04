package data.models

import com.google.gson.Gson
import config.crypto.S
import config.crypto.exceptions.NoSignatureException
import config.crypto.exceptions.UnauthorizedSignException
import config.extensions.fromHexString
import config.extensions.toHashString
import config.extensions.toHexString
import config.extensions.toPublicKey
import data.types.Hashable
import data.types.Singable
import java.security.KeyPair
import java.security.PublicKey

class Transaction(
    val fromAddress: String?,
    val toAddress: String,
    val amount: Double
) : Hashable,
    Singable {

    private var signature: ByteArray? = null

    override fun calculateHash(): String =
        "$fromAddress$toAddress$amount".toHashString()

    override fun sign(keyPair: KeyPair) {
        if(
            fromAddress != null &&
            keyPair.public.encoded.toHexString() != fromAddress
        )
            throw UnauthorizedSignException()

        S.initSign(keyPair.private)
        S.update(calculateHash().toByteArray(Charsets.UTF_8))
        signature = S.sign()
    }

    override fun verify(): Boolean {
        if(fromAddress == null) return true

        if(signature == null || signature?.isEmpty() == true) throw NoSignatureException()

        S.initVerify(fromAddress.fromHexString().toPublicKey())
        S.update(calculateHash().toByteArray(Charsets.UTF_8))
        return S.verify(signature)
    }

    override fun toString(): String =
        Gson().toJson(this)

    companion object {
        val GENESIS = Transaction("0", "0", 0.0)
    }
}