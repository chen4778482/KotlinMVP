package txcom.gsi.kotlinmvp.data.remode

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable
import txcom.gsi.kotlinmvp.data.mode.Base
import txcom.gsi.kotlinmvp.data.mode.Name
import txcom.gsi.kotlinmvp.utils.MyGsonTypeAdapterFactory

/**
 * Created by Administrator on 2018/3/6.
 */
interface Services {

    @FormUrlEncoded
    @POST("/cont/sys/sendSmsCode")
    fun sendSmsCode(@FieldMap params: Map<String, String>): Observable<Base>

    @FormUrlEncoded
    @POST("/cont/sys/appRegister")
    fun appRegister(@FieldMap params: Map<String, String>): Observable<Base>

    /******** Helper class that sets up a new services  */
    object Creator {
        fun newService(): Services {
            val gson = GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(Services::class.java)
        }
    }

    companion object {
        val ENDPOINT = "http://www.osanwen.com"
    }
}

