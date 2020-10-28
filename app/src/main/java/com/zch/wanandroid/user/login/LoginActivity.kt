package com.zch.wanandroid.user.login

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zch.base.LiveDataEventManager
import com.zch.base.cache.LoginCache
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.db.User
import com.zch.base.db.UserRepo
import com.zch.base.rxlifecycle.RxLifecycleActivity
import com.zch.base.utils.ListUtil
import com.zch.base.utils.ToastUtil
import com.zch.wanandroid.R
import com.zch.wanandroid.user.LoginResp
import kotlinx.android.synthetic.main.activity_login0.*

/**
 * Created by zch on 2019/01/16.
 */
//@Route(path = ARouterPathConstant.User.LOGIN_ACTIVITY)
class LoginActivity : RxLifecycleActivity(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login0)

        viewAccount.run {
            setContent(LoginCache.getCurrentAccount())
            getEditText().setSelection(getContent().length)
        }

        viewAccount.ivMore.setOnClickListener {
            val userList = UserRepo.getUserList()
            if (ListUtil.isEmpty(userList)) {
                ToastUtil.showShortText(getString(R.string.no_history_account_hint))
                return@setOnClickListener
            }
            showHistoryAccountDialog(userList!!)
        }

        btnLogin.setOnClickListener {
            if (checkValidity()) {
                LoginPresenter(this).login(viewAccount.getContent(), viewPwd.getContent())
            }
        }

        tvGoToRegister.setOnClickListener {
            ARouter.getInstance().build(ARouterPathConstant.User.REGISTER_ACTIVITY).navigation()
        }
    }

    private fun showHistoryAccountDialog(userList: MutableList<User>) {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_history_account, null)
        val dialog: AlertDialog = AlertDialog.Builder(this).create()

        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.viewHistoryAccount)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val historyAccountAdapter = HistoryAccountAdapter(userList)
        recyclerView.adapter = historyAccountAdapter.run {
            bindToRecyclerView(recyclerView)
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                viewAccount.run {
                    setContent(userList[position].account)
                    getEditText().setSelection(getContent().length)
                }
                dialog.dismiss()
            }
            onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.ivDelete -> {
                        dialog.dismiss()
                        showDeleteHistoryAccountDialog(userList[position])
                    }
                }
            }
            this
        }

        dialog.show()
        dialog.window?.setContentView(view)
    }

    private fun showDeleteHistoryAccountDialog(user: User) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.tip))
                .setMessage(getString(R.string.is_delete_account_hint, user.account))
                .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                    viewAccount.setContent("")
                    UserRepo.delete(user.account!!)
                    if (user.account == LoginCache.getCurrentAccount()) {
                        LoginCache.setCurrentAccount("")
                    }
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
                .show()
    }

    private fun checkValidity(): Boolean {
        if (viewAccount.getContent().isEmpty()) {
            ToastUtil.showShortText(getString(R.string.account_can_not_empty))
            return false
        }
        if (viewPwd.getContent().isEmpty()) {
            ToastUtil.showShortText(getString(R.string.pwd_can_not_empty))
            return false
        }
        return true
    }

    override fun onLoginSuccess(loginResp: LoginResp?) {
        ToastUtil.showShortText(getString(R.string.login_success_hint))
        LiveDataEventManager.with(LiveDataEventManager.EVENT_LOGIN_SUCCESS).value = true
        finish()
    }

    override fun onLoginFail(errMsg: String) {
        ToastUtil.showShortText(errMsg)
    }
}