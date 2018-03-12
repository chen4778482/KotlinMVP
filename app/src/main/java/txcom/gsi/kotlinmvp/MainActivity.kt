package txcom.gsi.kotlinmvp

import android.app.Application
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.mTextView
import txcom.gsi.kotlinmvp.data.mode.Name
import txcom.gsi.kotlinmvp.ui.base.BaseActivity
import txcom.gsi.kotlinmvp.ui.register.RegisterActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView.text = "hello kotlin!"
        mTextView.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        })

    }
}
