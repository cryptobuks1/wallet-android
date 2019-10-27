package com.mycelium.wapi.wallet.genericdb

import com.mycelium.generated.wallet.database.AccountContext
import com.mycelium.generated.wallet.database.WalletDB
import java.util.*

class AccountContextsBacking(walletDB: WalletDB): GenericBacking {
    private val queries = walletDB.accountContextQueries

    override fun loadAccountContexts() = queries.selectAll()
                .executeAsList()

    override fun loadAccountContext(accountId: UUID) = queries.selectByUUID(accountId)
            .executeAsOneOrNull()

    override fun createAccountContext(context: AccountContext) {
        queries.insertFullObject(context)
    }

    override fun updateAccountContext(context: AccountContext) {
        queries.update(context.accountName, context.balance, context.archived, context.uuid)
    }

    override fun deleteAccountContext(uuid: UUID) {
        queries.delete(uuid)
    }
}