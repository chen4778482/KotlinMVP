package txcom.gsi.kotlinmvp.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

/**
 * Created by Administrator on 2018/3/5.
 */
@Qualifier//合格者
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
annotation class ApplicationContext
