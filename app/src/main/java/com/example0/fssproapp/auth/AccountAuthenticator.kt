package com.example0.fssproapp.auth

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils

class AccountAuthenticator(context: Context):AbstractAccountAuthenticator(context) {
    companion object{
        val ADD_ACCOUNT = "addAccount";
        val TOKEN_TYPE = "tokenType";
    }
    lateinit var sServerAuthenticate:ServerAuthenticator
    lateinit var mContext: Context
    init {
        mContext = context
        sServerAuthenticate = ServerAuthenticator(mContext)
    }
    override fun editProperties(p0: AccountAuthenticatorResponse?, p1: String?): Bundle {
        TODO("Not yet implemented")
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        val intent = Intent(mContext,LoginActivity::class.java).apply {
            putExtra(AccountManager.KEY_ACCOUNT_TYPE,accType)
            putExtra(ADD_ACCOUNT,true)
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,response)

        }
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT,intent)
        return bundle
    }

    override fun confirmCredentials(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: Bundle?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {

        val am = AccountManager.get(mContext)
        var authToken = am.peekAuthToken(account,authTokenType)
        if(TextUtils.isEmpty(authToken)){
            val password = am.getPassword(account)
            if(password != null){
                authToken = sServerAuthenticate.logIn(account?.name,password,authTokenType)

            }
        }
        if(!TextUtils.isEmpty(authToken)){
            val result = Bundle().apply {
                putString(AccountManager.KEY_ACCOUNT_NAME,account?.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE,account?.type)
                putString(AccountManager.KEY_AUTHTOKEN,authToken)

            }
            return result
        }
        val intent = Intent(mContext,LoginActivity::class.java).apply{
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,response)
            putExtra(AccountManager.KEY_ACCOUNT_TYPE,account?.type)
            putExtra(AccountManager.KEY_ACCOUNT_NAME,account?.name)
            putExtra(TOKEN_TYPE,authTokenType)
        }
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT,intent)
        return bundle

    }

    override fun getAuthTokenLabel(p0: String?): String {
        TODO("Not yet implemented")
    }

    override fun updateCredentials(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: String?,
        p3: Bundle?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun hasFeatures(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: Array<out String>?
    ): Bundle {
        TODO("Not yet implemented")
    }

}