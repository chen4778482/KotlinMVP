package txcom.gsi.kotlinmvp.ui.register

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_register.*
import timber.log.Timber
import txcom.gsi.kotlinmvp.R

import txcom.gsi.kotlinmvp.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/8.
 */
class RegisterActivity : BaseActivity(), RegisterMvpView {

    @Inject
    internal
    lateinit var mRegisterPresenter: RegisterPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()!!.inject(this)
        setContentView(R.layout.activity_register)
        mRegisterPresenter.attachView(this)
        setSupportActionBar(mToolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        buton.setOnClickListener(View.OnClickListener { Toast.makeText(this, "哈哈哈哈哈", Toast.LENGTH_SHORT).show() })
    }

    override fun onDestroy() {
        super.onDestroy()
        mRegisterPresenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun registerOk() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
