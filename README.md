# KotlinMVP
学习用例
这是一个使用kotlin写的一个MVP+Dagger2+Retrofit2.0+Rxjava的一个框架
主要是用来学习kotlin语言

首先要在你的android studio 上下载安装 kotlin插件
至于怎么安装请你自行百度

依赖的框架

RxLifecycle:主要是用来解决RxJava内存泄漏（虽然demo中并没有怎么用到）
github地址 :https://github.com/trello/RxLifecycle

timber：用来打印log日志的，基于Android原生Log的logger，小巧易扩展
android 原生的日志log用着很多的缺点：
1，为了找某条log是从哪里打出来的，还要花点功夫。
2，即使找到了，怎么知道运行时是在哪个线程？
3，日志去了不再来。在未连接调试的手机上，或者调试中不小心重启App了，日志就没了。

至于如何使用：请自行百度，没什么难度
github地址 :https://github.com/trello/RxLifecycle


AutoValue ：自动生成代码，使得程序可能更短，更清晰。
https://github.com/google/auto/blob/master/value/userguide/index.md

至于Dagger2（注入框架）+Retrofit2.0（网络请求）+Rxjava（这个是一个观察者与被观察者的关系）这样的东西网上一大堆，我也不一一写明了。
android studio 也有直接将java代码的直接转化成kotlin代码的
Java转Kotlin
打开要转的文件

方法1
Ctrl+Shift+Alt+K

方法2
Code - Convert Java File To Kotlin File

Kotlikn 转 Java
Tools>Kotlin>Show Kotlin Bytecode
Decompile

