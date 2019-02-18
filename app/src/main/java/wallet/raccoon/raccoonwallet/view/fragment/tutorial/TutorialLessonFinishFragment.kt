package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.View
import android.widget.Button
import wallet.raccoon.raccoonwallet.MainActivity
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment

class TutorialLessonFinishFragment : BaseFragment() {
  override fun layoutRes() = getLayout(getWalletCreatedType())

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
  }

  private fun setupViews() {
    view?.findViewById<Button>(R.id.button)
        ?.setOnClickListener {
          val intent = MainActivity.createIntent(it.context, false)
          intent.flags = FLAG_ACTIVITY_CLEAR_TOP
          startActivity(intent)

          // TODO 各種プロバイダーを有効化
//          PinCodeProvider.clearCache()
//          WalletProvider.clearCache()

          finish()
        }
  }

  private fun getLayout(walletCreatedType: WalletCreatedType): Int {
    return when (walletCreatedType) {
      WalletCreatedType.NEWBIE ->
        R.layout.fragment_tutorial_lesson_finish_newbie
      WalletCreatedType.LOGIN ->
        R.layout.fragment_tutorial_lesson_finish_pin_code
      WalletCreatedType.RACCOON ->
        R.layout.fragment_tutorial_lesson_finish_raccoon
    }
  }

  private fun getWalletCreatedType() = arguments?.get(KEY_WALLET_CREATED_TYPE) as WalletCreatedType

  companion object {
    private const val TOOLBAR_STRING_RES = R.string.wallet_created_fragment_title
    private const val KEY_WALLET_CREATED_TYPE = "wallet_created_type"
    fun newInstance(walletCreatedType: WalletCreatedType): TutorialLessonFinishFragment {
      val fragment = TutorialLessonFinishFragment()
      val bundle = Bundle()
      bundle.putSerializable(KEY_WALLET_CREATED_TYPE, walletCreatedType)
      bundle.putInt(ARG_CONTENTS_NAME_ID, TOOLBAR_STRING_RES)
      fragment.arguments = bundle
      return fragment
    }
  }
}

enum class WalletCreatedType {
  NEWBIE,
  LOGIN,
  RACCOON
}
