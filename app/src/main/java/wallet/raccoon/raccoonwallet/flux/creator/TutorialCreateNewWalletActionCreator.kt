package wallet.raccoon.raccoonwallet.flux.creator

import wallet.raccoon.raccoonwallet.db.Database
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.TutorialCreateNewWalletActionType
import wallet.raccoon.raccoonwallet.model.db.Wallet
import wallet.raccoon.raccoonwallet.usecase.TutorialCreateNewWalletUseCase

class TutorialCreateNewWalletActionCreator(
    private val useCase: TutorialCreateNewWalletUseCase,
    private val dispatch: (TutorialCreateNewWalletActionType) -> Unit
) : DisposableMapper() {

    suspend fun createAndInsertWallet(walletName: String) {
        val wallet = useCase.createWallet(walletName)

        Database.query(useCase.insertWallet(wallet), {
            val createdWallet = Wallet(
                id = it,
                salt = wallet.salt,
                name = wallet.name,
                publicKey = wallet.publicKey,
                encryptedSecretKey = wallet.encryptedSecretKey,
                address = wallet.address,
                isMultisig = wallet.isMultisig
            )

            dispatch(TutorialCreateNewWalletActionType.CreateAndInsertWallet(createdWallet))
        })
    }
}
