package txcom.gsi.kotlinmvp.injection.Model

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import txcom.gsi.kotlinmvp.data.remode.Services
import txcom.gsi.kotlinmvp.injection.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Administrator on 2018/3/5.
 */
@Module
public class ApplicationModule(protected val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun providesContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideService(): Services {
        return Services.Creator.newService()
    }
}