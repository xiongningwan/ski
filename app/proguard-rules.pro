# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 保留Annotation不混淆 这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*,InnerClasses
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# 忽略警告
-ignorewarnings
# 设置是否允许改变作用域
-allowaccessmodification
# 把混淆类中的方法名也混淆了
-useuniqueclassmembernames
# apk 包内所有 class 的内部结构
-dump class_files.txt
# 未混淆的类和成员
-printseeds seeds_txt
# 列出从apk中删除的代码
-printusage unused.txt
# 混淆前后的映射
-printmapping mapping.txt
#不会移除无用的方法、域或者类
# -dontshrink
-keeppackagenames com.yabo.cpsdk.**
#添加混淆字典
#-obfuscationdictionary ./dictionary.txt
#-classobfuscationdictionary ./dictionary.txt
#-packageobfuscationdictionary ./dictionary.txt

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class * extends android.view.view
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
#Androidx
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

-keep public class * extends android.view.View{
     *** get*();
     void set*(***);
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers class * extends androidx.appcompat.app{
    public void *(android.view.View);
}


-keepclasseswithmembernames class * {
    native <methods>;
}

####################################################################################################
#注解不能混淆
-keepattributes *Annotation*
####################################################################################################

####################################################################################################
#---------------------依赖注入---------------------
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
#---------------------依赖注入----------------------
####################################################################################################

####################################################################################################
#不能混淆枚举
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
####################################################################################################

####################################################################################################
#Native 方法
-keepclasseswithmembernames class * {
    native <methods>;
}
####################################################################################################

####################################################################################################
#JS 调用Java 方法
-keepattributes *JavascriptInterface*
####################################################################################################


####################################################################################################
#Parcelable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class * implements java.io.Serializable {
    public *;
}
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}
####################################################################################################

####################################################################################################
#Gson
-keep class com.google.gson.** {*;}
-keep class sun.misc.Unsafe {*;}
-keep class com.google.gson.stream.** {*;}
-keep class com.google.gson.examples.android.model.** {*;}
-keep class com.google.** {
    <fields>;
    <methods>;
}
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-dontwarn sun.misc.**
####################################################################################################

####################################################################################################
#agentweb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
#Java 注入类不要混淆 ， 例如 sample 里面的 AndroidInterface 类 ， 需要 Keep 。
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }
####################################################################################################

####################################################################################################
##XBanner 图片轮播混淆配置
-keep class com.stx.xhb.xbanner.**{*;}
####################################################################################################

####################################################################################################
#youthBanner
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
####################################################################################################


###############################################################################################
#BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}
##############################################################################################

##############################################################################################
#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule
##############################################################################################

##############################################################################################
#OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
##############################################################################################

##############################################################################################
#RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
##############################################################################################

##############################################################################################
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}
##############################################################################################


##############################################################################################
#---------------------------------------Rxbus--------------------------------------------------
-keep class com.hwangjr.rxbus.** { *; }
#---------------------------------------Rxbus------------------------------------------------
##############################################################################################


##############################################################################################
#---------------------------------------Xupdate--------------------------------------------------
-keep class com.xuexiang.xupdate.entity.** { *; }

#//注意，如果你使用的是自定义Api解析器解析，还需要给你自定义Api实体配上混淆，如下是本demo中配置的自定义Api实体混淆规则：
-keep class com.xuexiang.xupdatedemo.entity.** { *; }
#---------------------------------------Xupdate------------------------------------------------
##############################################################################################


##############################################################################################
#---------------------------------------Immersionbar--------------------------------------------------
-keep class com.gyf.immersionbar.* {*;}
 -dontwarn com.gyf.immersionbar.**
#---------------------------------------Immersionbar------------------------------------------------
##############################################################################################


#com.ski.box
####################################################################################################
-keep class com.ski.box.bean.**{*;}
-keep class com.ski.box.utils.**{*;}
-keep class com.yb.core.**{*;}
###############################################################################################


