import data.models.Block
import data.models.Ledger
import data.models.Transaction

fun main(args: Array<String>) {
    println("+-------------------------------------------------------------------------------------------------------------------------------+")
    println("|\t############################################## Launching N-Blockchain App... ##############################################\t|")

    println("|\tCreating the ledger...\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    val ledger = Ledger()
    println("|\tLedger created :D\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    println("|\tGenesis block is :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    println(Block.GENESIS.toString())

    ledger.addTransaction(
        Transaction(
            "0x0001",
            "0x0003",
            7.0
        )
    )

    ledger.addTransaction(
        Transaction(
            "0x0002",
            "0x0004",
            65.0
        )
    )

    ledger.addBlockWithReward("khalil")
    ledger.addBlock()

    ledger.getBalanceForAddress("0x0001")
    ledger.getBalanceForAddress("0x0002")
    ledger.getBalanceForAddress("0x0003")
    ledger.getBalanceForAddress("0x0004")
    ledger.getBalanceForAddress("khalil")

    println(
        String.format(
            "|\tIs the ledger valid ? %s\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|",
            ledger.isValid()
        )
    )
    println("+-------------------------------------------------------------------------------------------------------------------------------+")
}
