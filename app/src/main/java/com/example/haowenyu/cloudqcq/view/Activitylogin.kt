package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.Presenter
import com.example.haowenyu.cloudqcq.R
import com.example.haowenyu.cloudqcq.datamodel.user
import kotlinx.coroutines.ExperimentalCoroutinesApi

class Activitylogin : Activity(), Contract.View {


    lateinit var maccount:EditText
    lateinit var mpassword:EditText
    lateinit var accountl:LinearLayout
    lateinit var passwordl:LinearLayout
    lateinit var button: Button
    var mpresenter:Presenter = Presenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initEdit()

    }

    @ExperimentalCoroutinesApi
    override fun loginSuccess(iuser: user?) {

    this.startActivity<PlayerlistActivity>{

        putExtra("iuser",iuser!!.bindings[0].id)
        putExtra("userid",iuser.bindings[0].id.toString())

    }
}

    override fun loginFailed(msg: String?) {

    }



    @ExperimentalCoroutinesApi
    fun initEdit(){
        button = findViewById(R.id.login_button)
        button.setOnClickListener {
            if (maccount.text.isBlank()){
                Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show()
            }else if (mpassword.text.isBlank()){
                Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show()
            }else {
                mpresenter.doLogin(maccount.text.toString(),mpassword.text.toString(),this)
            }
        }
        val draw_unfocus:Drawable? = getDrawable(R.drawable.edit_unfocused)
        val draw_focused:Drawable? = getDrawable(R.drawable.edit_focused)
        maccount = findViewById(R.id.account)
        mpassword = findViewById(R.id.password)
        accountl = findViewById(R.id.edit_number)
        passwordl = findViewById(R.id.edit_password)
        maccount.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    accountl.background = draw_focused
                }else accountl.background = draw_unfocus
            }
        }
        mpassword.setOnFocusChangeListener{ v,hasFocus ->
            run {
                if (hasFocus) {
                    passwordl.background = draw_focused
                }else passwordl.background = draw_unfocus
            }
        }

    }

    private inline fun <reified T : Activity> Activity.startActivity(initializer: Intent.() -> Unit) {
        startActivity(
            Intent(this, T::class.java).apply(initializer)
        )
    }

}