package com.dzx;

import brut.androlib.res.util.ExtMXSerializer;
import com.dzx.util.BinaryOperationUtil;
import com.dzx.util.LUtils;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import org.jd.core.v1.api.loader.Loader;
import org.jd.core.v1.api.printer.Printer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Random;

public class Test2 {
    static Loader loader = new Loader() {
        @Override
        public byte[] load(String internalName) {
//            InputStream is = this.getClass().getResourceAsStream(internalName);
//            InputStream is = this.getClass().getResourceAsStream("/" + internalName + ".class");
            InputStream is = null;
            try {
                is = new FileInputStream(new File(internalName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (is == null) {
                return null;
            } else {
                try (InputStream in = is; ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int read = in.read(buffer);

                    while (read > 0) {
                        out.write(buffer, 0, read);
                        read = in.read(buffer);
                    }

                    return out.toByteArray();
                } catch (IOException e) {
                }
            }
            return null;
        }

        @Override
        public boolean canLoad(String internalName) {
//            return this.getClass().getResource(internalName) != null;
            return new File(internalName).exists();
//            return this.getClass().getResource("/" + internalName + ".class") != null;
        }
    };


    static Printer printer = new Printer() {
        protected static final String TAB = "  ";
        protected static final String NEWLINE = "\n";

        protected int indentationCount = 0;
        protected StringBuilder sb = new StringBuilder();

        @Override
        public String toString() {
            return sb.toString();
        }

        @Override
        public void start(int maxLineNumber, int majorVersion, int minorVersion) {
        }

        @Override
        public void end() {
        }

        @Override
        public void printText(String text) {
            sb.append(text);
        }

        @Override
        public void printNumericConstant(String constant) {
            sb.append(constant);
        }

        @Override
        public void printStringConstant(String constant, String ownerInternalName) {
            sb.append(constant);
        }

        @Override
        public void printKeyword(String keyword) {
            sb.append(keyword);
        }

        @Override
        public void printDeclaration(int type, String internalTypeName, String name, String descriptor) {
            sb.append(name);
        }

        @Override
        public void printReference(int type, String internalTypeName, String name, String descriptor, String ownerInternalName) {
            sb.append(name);
        }

        @Override
        public void indent() {
            this.indentationCount++;
        }

        @Override
        public void unindent() {
            this.indentationCount--;
        }

        @Override
        public void startLine(int lineNumber) {
            for (int i = 0; i < indentationCount; i++) sb.append(TAB);
        }

        @Override
        public void endLine() {
            sb.append(NEWLINE);
        }

        @Override
        public void extraLine(int count) {
            while (count-- > 0) sb.append(NEWLINE);
        }

        @Override
        public void startMarker(int type) {
        }

        @Override
        public void endMarker(int type) {
        }
    };

    public static void main(String[] args) {

        byte[] bytes = "你好".getBytes();
        LUtils.i(BinaryOperationUtil.outByteArrayToHexString(bytes));

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        LUtils.i(byteBuffer.order());
        LUtils.i(Integer.toHexString(byteBuffer.getInt()));
        byteBuffer.clear();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        LUtils.i(Integer.toHexString(byteBuffer.getInt()));
        byteBuffer.clear();
        byteBuffer.put(5, (byte) 0);


        LUtils.i(BinaryOperationUtil.outByteArrayToHexString(bytes));
        LUtils.i(BinaryOperationUtil.outByteArrayToHexString(byteBuffer.array()));
        LUtils.i(byteBuffer.array()==bytes);

    }


    public static String getPdfFileText(String fileName) throws IOException {
        PdfReader reader = new PdfReader(fileName);
        PdfDocument document = new PdfDocument(reader);
        LUtils.i(document.getNumberOfPdfObjects());
        LUtils.i(document.getDefaultPageSize());
        LUtils.i(document.getFirstPage());
//2.ActivityThread，App的真正入口。当开启App之后，调用main()开始运行，开启消息循环队列，这就
//是传说的UI线程或者叫主线程。与ActivityManagerService一起完成Activity的管理工作。
//3.ApplicationThread，用来实现ActivityManagerServie与ActivityThread之间的交互。在
//ActivityManagerSevice需要管理相关Application中的Activity的生命周期时，通过ApplicationThread
//的代理对象与ActivityThread通信。
//4.ApplicationThreadProxy，是ApplicationThread在服务器端的代理，负责和客户端的
//ApplicationThread通信。AMS就是通过该代理与ActivityThread进行通信的。
//5.Instrumentation，每一个应用程序只有一个Instrumetation对象，每个Activity内都有一个对该对象
//的引用，Instrumentation可以理解为应用进程的管家，ActivityThread要创建或暂停某个Activity时，
//都需要通过Instrumentation来进行具体的操作。
//6.ActivityStack，Activity在AMS的栈管理，用来记录经启动的Activity的先后关系，状态信息等。通过
//ActivtyStack决定是否需要启动新的进程。
//7.ActivityRecord，ActivityStack的管理对象，每个Acivity在AMS对应一个ActivityRecord，来记录
//Activity状态以及其他的管理信息。其实就是服务器端的Activit对象的映像。
//8.TaskRecord，AMS抽象出来的一个“任务”的概念，是记录ActivityRecord的栈，一个“Task”包含若干个
//ActivityRecord。AMS用TaskRecord确保Activity启动和退出的顺序。如果你清楚Activity的4种
//launchMode，那么对这概念应该不陌生。
//8、App启动流程（Activity的冷启动流程）。
//点击应用图标后会去启动应用的Launcher Activity，如果Launcer Activity所在的进程没有创建，还会创
//建新进程，整体的流程就是一个Activity的启动流程。
//Activity的启动流程图（放大可查看）如下所示：
//Intent intent =
//mActivity.getPackageManager().getLaunchIntentForPackage(packageName);
//mActivity.startActivity(intent);整个流程涉及的主要角色有：
//Instrumentation: 监控应用与系统相关的交互行为。
//AMS：组件管理调度中心，什么都不干，但是什么都管。
//ActivityStarter：Activity启动的控制器，处理Intent与Flag对Activity启动的影响，具体说来有：1
//寻找符合启动条件的Activity，如果有多个，让用户选择；2 校验启动参数的合法性；3 返回int参
//数，代表Activity是否启动成功。
//ActivityStackSupervisior：这个类的作用你从它的名字就可以看出来，它用来管理任务栈。
//ActivityStack：用来管理任务栈里的Activity。
//ActivityThread：最终干活的人，Activity、Service、BroadcastReceiver的启动、切换、调度等各
//种操作都在这个类里完成。
//注：这里单独提一下ActivityStackSupervisior，这是高版本才有的类，它用来管理多个ActivityStack，
//早期的版本只有一个ActivityStack对应着手机屏幕，后来高版本支持多屏以后，就有了多个
//ActivityStack，于是就引入了ActivityStackSupervisior用来管理多个ActivityStack。
//整个流程主要涉及四个进程：
//调用者进程，如果是在桌面启动应用就是Launcher应用进程。
//ActivityManagerService等待所在的System Server进程，该进程主要运行着系统服务组件。
//Zygote进程，该进程主要用来fork新进程。
//新启动的应用进程，该进程就是用来承载应用运行的进程了，它也是应用的主线程（新创建的进程
//就是主线程），处理组件生命周期、界面绘制等相关事情。
//有了以上的理解，整个流程可以概括如下：
//1、点击桌面应用图标，Launcher进程将启动Activity（MainActivity）的请求以Binder的方式发
//送给了AMS。
//2、AMS接收到启动请求后，交付ActivityStarter处理Intent和Flag等信息，然后再交给
//ActivityStackSupervisior/ActivityStack 处理Activity进栈相关流程。同时以Socket方式请求Zygote
//进程fork新进程。
//3、Zygote接收到新进程创建请求后fork出新进程。
//4、在新进程里创建ActivityThread对象，新创建的进程就是应用的主线程，在主线程里开启
//Looper消息循环，开始处理创建Activity。5、ActivityThread利用ClassLoader去加载Activity、创建Activity实例，并回调Activity的
//onCreate()方法，这样便完成了Activity的启动。
//最后，再看看另一幅启动流程图来加深理解：
//9、ActivityThread工作原理。
//10、说下四大组件的启动过程，四大组件的启动与销毁的方式。
//广播发送和接收的原理了解吗？
//继承BroadcastReceiver，重写onReceive()方法。
//通过Binder机制向ActivityManagerService注册广播。
//通过Binder机制向ActivityMangerService发送广播。
//ActivityManagerService查找符合相应条件的广播（IntentFilter/Permission）的
//BroadcastReceiver，将广播发送到BroadcastReceiver所在的消息队列中。
//BroadcastReceiver所在消息队列拿到此广播后，回调它的onReceive()方法。
//11、AMS是如何管理Activity的？
//12、理解Window和WindowManager。
//1.Window用于显示View和接收各种事件，Window有三种型：应用Window(每个Activity对应一个
//Window)、子Widow(不能单独存在，附属于特定Window)、系统window(toast和状态栏)
//2.Window分层级，应用Window在1-99、子Window在1000-1999、系统Window在2000-
//2999.WindowManager提供了增改View的三个功能。
//3.Window是个抽象概念：每一个Window对应着一个ViewRootImpl，Window通过ViewRootImpl来和
//View建立联系，View是Window存在的实体，只能通过WindowManager来访问Window。4.WindowManager的实现是WindowManagerImpl，其再委托WindowManagerGlobal来对Window
//进行操作，其中有四种List分别储存对应的View、ViewRootImpl、WindowManger.LayoutParams和
//正在被删除的View。
//5.Window的实体是存在于远端的WindowMangerService，所以增删改Window在本端是修改上面的几
//个List然后通过ViewRootImpl重绘View，通过WindowSession(每Window个对应一个)在远端修改
//Window。
//6.Activity创建Window：Activity会在attach()中创建Window并设置其回调(onAttachedToWindow()、
//dispatchTouchEvent())，Activity的Window是由Policy类创建PhoneWindow实现的。然后通过
//Activity#setContentView()调用PhoneWindow的setContentView。
//13、WMS是如何管理Window的？
//14、大体说清一个应用程序安装到手机上时发生了什么？
//APK的安装流程如下所示：
//复制APK到/data/app目录下，解压并扫描安装包。
//资源管理器解析APK里的资源文件。
//解析AndroidManifest文件，并在/data/data/目录下创建对应的应用数据目录。
//然后对dex文件进行优化，并保存在dalvik-cache目录下。
//将AndroidManifest文件解析出的四大组件信息注册到PackageManagerService中。
//安装完成后，发送广播。
//15、Android的打包流程？（即描述清点击 Android Studio 的
//build 按钮后发生了什么？）apk里有哪些东西？签名算法的原理？
//apk打包流程Android的包文件APK分为两个部分：代码和资源，所以打包方面也分为资源打包和代码打包两个方
//面，下面就来分析资源和代码的编译打包原理。
//APK整体的的打包流程如下图所示：
//具体说来：
//通过AAPT工具进行资源文件（包括AndroidManifest.xml、布局文件、各种xml资源等）的打包，
//生成R.java文件。通过AIDL工具处理AIDL文件，生成相应的Java文件。
//通过Java Compiler编译R.java、Java接口文件、Java源文件，生成.class文件。
//通过dex命令，将.class文件和第三方库中的.class文件处理生成classes.dex，该过程主要完成Java
//字节码转换成Dalvik字节码，压缩常量池以及清除冗余信息等工作。
//通过ApkBuilder工具将资源文件、DEX文件打包生成APK文件。
//通过Jarsigner工具，利用KeyStore对生成的APK文件进行签名。
//如果是正式版的APK，还会利用ZipAlign工具进行对齐处理，对齐的过程就是将APK文件中所有的
//资源文件距离文件的起始距位置都偏移4字节的整数倍，这样通过内存映射访问APK文件的速度会
//更快，并且会减少其在设备上运行时的内存占用。
//apk组成
//dex：最终生成的Dalvik字节码。
//res：存放资源文件的目录。
//asserts：额外建立的资源文件夹。
//lib：如果存在的话，存放的是ndk编出来的so库。
//META-INF：存放签名信息
//MANIFEST.MF（清单文件）：其中每一个资源文件都有一个SHA-256-Digest签名，MANIFEST.MF文件
//的SHA256（SHA1）并base64编码的结果即为CERT.SF中的SHA256-Digest-Manifest值。
//CERT.SF（待签名文件）：除了开头处定义的SHA256（SHA1）-Digest-Manifest值，后面几项的值是
//对MANIFEST.MF文件中的每项再次SHA256并base64编码后的值。
//CERT.RSA（签名结果文件）：其中包含了公钥、加密算法等信息。首先对前一步生成的MANIFEST.MF
//使用了SHA256（SHA1）-RSA算法，用开发者私钥签名，然后在安装时使用公钥解密。最后，将其与未
//加密的摘要信息（MANIFEST.MF文件）进行对比，如果相符，则表明内容没有被修改。
//androidManifest：程序的全局清单配置文件。
//resources.arsc：编译后的二进制资源文件。
//签名算法的原理
//为什么要签名？
//确保Apk来源的真实性。
//确保Apk没有被第三方篡改。
//什么是签名？
//在Apk中写入一个“指纹”。指纹写入以后，Apk中有任何修改，都会导致这个指纹无效，Android系统在
//安装Apk进行签名校验时就会不通过，从而保证了安全性。
//数字摘要
//对一个任意长度的数据，通过一个Hash算法计算后，都可以得到一个固定长度的二进制数据，这个数
//据就称为“摘要”。
//补充：
//散列算法的基础原理：将数据（如一段文字）运算变为另一固定长度值。
//SHA-1：在密码学中，SHA-1（安全散列算法1）是一种加密散列函数，它接受输入并产生一个160
//位（20 字节）散列值，称为消息摘要 。
//MD5：MD5消息摘要算法（英语：MD5 Message-Digest Algorithm），一种被广泛使用的密码
//散列函数，可以产生出一个128位（16字节）的散列值（hash value），用于确保信息传输完整一
//致。
//SHA-2：名称来自于安全散列算法2（英语：Secure Hash Algorithm 2）的缩写，一种密码散列函
//数算法标准，其下又可再分为六个不同的算法标准，包括了：SHA-224、SHA-256、SHA-384、SHA-512、SHA-512/224、SHA-512/256。
//特征：
//唯一性
//固定长度：比较常用的Hash算法有MD5和SHA1，MD5的长度是128拉，SHA1的长度是160位。
//不可逆性
//签名和校验的主要过程
//签名就是在摘要的基础上再进行一次加密，对摘要加密后的数据就可以当作数字签名。
//签名过程：
//1、计算摘要：通过Hash算法提取出原始数据的摘要。
//2、计算签名：再通过基于密钥（私钥）的非对称加密算法对提取出的摘要进行加密，加密后的数
//据就是签名信息。
//3、写入签名：将签名信息写入原始数据的签名区块内。
//校验过程：
//1、首先用同样的Hash算法从接收到的数据中提取出摘要。
//2、解密签名：使用发送方的公钥对数字签名进行解密，解密出原始摘要。
//3、比较摘要：如果解密后的数据和提取的摘要一致，则校验通过；如果数据被第三方篡改过，解
//密后的数据和摘要将会不一致，则校验不通过。
//数字证书
//如何保证公钥的可靠性呢？答案是数字证书，数字证书是身份认证机构（Certificate Authority）颁发
//的，包含了以下信息：
//证书颁发机构
//证书颁发机构签名
//证书绑定的服务器域名
//证书版本、有效期
//签名使用的加密算法（非对称算法，如RSA）
//公钥等
//接收方收到消息后，先向CA验证证书的合法性，再进行签名校验。
//注意：Apk的证书通常是自签名的，也就是由开发者自己制作，没有向CA机构申请。Android在安装
//Apk时并没有校验证书本身的合法性，只是从证书中提取公钥和加密算法，这也正是对第三方Apk重新
//签名后，还能够继续在没有安装这个Apk的系统中继续安装的原因。
//keystore和证书格式
//keystore文件中包含了私钥、公钥和数字证书。根据编码不同，keystore文件分为很多种，Android使
//用的是Java标准keystore格式JKS(Java Key Storage)，所以通过Android Studio导出的keystore文件是
//以.jks结尾的。
//keystore使用的证书标准是X.509，X.509标准也有多种编码格式，常用的有两种：pem（Privacy
//Enhanced Mail）和der（Distinguished Encoding Rules）。jks使用的是der格式，Android也支持直
//接使用pem格式的证书进行签名。
//两种证书编码格式的区别：
//DER（Distinguished Encoding Rules）
//二进制格式，所有类型的证书和私钥都可以存储为der格式。
//PEM（Privacy Enhanced Mail）base64编码，内容以-----BEGIN xxx----- 开头，以-----END xxx----- 结尾。
//jarsigner和apksigner的区别
//Android提供了两种对Apk的签名方式，一种是基于JAR的签名方式，另一种是基于Apk的签名方式，它
//们的主要区别在于使用的签名文件不一样：jarsigner使用keystore文件进行签名；apksigner除了支持
//使用keystore文件进行签名外，还支持直接指定pem证书文件和私钥进行签名。
//在签名时，除了要指定keystore文件和密码外，也要指定alias和key的密码，这是为什么呢？
//keystore是一个密钥库，也就是说它可以存储多对密钥和证书，keystore的密码是用于保护keystore本
//身的，一对密钥和证书是通过alias来区分的。所以jarsigner是支持使用多个证书对Apk进行签名的，
//apksigner也同样支持。
//Android Apk V1 签名原理
//1、解析出 CERT.RSA 文件中的证书、公钥，解密 CERT.RSA 中的加密数据。
//2、解密结果和 CERT.SF 的指纹进行对比，保证 CERT.SF 没有被篡改。
//3、而 CERT.SF 中的内容再和 MANIFEST.MF 指纹对比，保证 MANIFEST.MF 文件没有被篡改。
//4、MANIFEST.MF 中的内容和 APK 所有文件指纹逐一对比，保证 APK 没有被篡改。
//16、说下安卓虚拟机和java虚拟机的原理和不同点?（JVM、
//Davilk、ART三者的原理和区别）
//JVM 和Dalvik虚拟机的区别
//JVM:.java -> javac -> .class -> jar -> .jar
//架构: 堆和栈的架构.
//DVM:.java -> javac -> .class -> dx.bat -> .dex
//架构: 寄存器(cpu上的一块高速缓存)
//Android2个虚拟机的区别（一个5.0之前，一个5.0之后）
//什么是Dalvik：Dalvik是Google公司自己设计用于Android平台的Java虚拟机。Dalvik虚拟机是Google
//等厂商合作开发的Android移动设备平台的核心组成部分之一，它可以支持已转换为.dex(即Dalvik
//Executable)格式的Java应用程序的运行，.dex格式是专为Dalvik应用设计的一种压缩格式，适合内存和
//处理器速度有限的系统。Dalvik经过优化，允许在有限的内存中同时运行多个虚拟机的实例，并且每一
//个Dalvik应用作为独立的Linux进程执行。独立的进程可以防止在虚拟机崩溃的时候所有程序都被关
//闭。
//什么是ART:Android操作系统已经成熟，Google的Android团队开始将注意力转向一些底层组件，其中
//之一是负责应用程序运行的Dalvik运行时。Google开发者已经花了两年时间开发更快执行效率更高更省
//电的替代ART运行时。ART代表Android Runtime,其处理应用程序执行的方式完全不同于Dalvik，
//Dalvik是依靠一个Just-In-Time(JIT)编译器去解释字节码。开发者编译后的应用代码需要通过一个解释器
//在用户的设备上运行，这一机制并不高效，但让应用能更容易在不同硬件和架构上运行。ART则完全改
//变了这套做法，在应用安装的时候就预编译字节码为机器语言，这一机制叫Ahead-Of-Time(AOT)编
//译。在移除解释代码这一过程后，应用程序执行将更有效率，启动更快。
//ART优点：
//系统性能的显著提升。
//应用启动更快、运行更快、体验更流畅、触感反馈更及时。
//更长的电池续航能力。
//支持更低的硬件。ART缺点：
//更大的存储空间占用，可能会增加10%-20%。
//更长的应用安装时间。
//17、安卓采用自动垃圾回收机制，请说下安卓内存管理的原理？
//18、Android中App是如何沙箱化的,为何要这么做？
//19、一个图片在app中调用R.id后是如何找到的？
//20、JNI
//Java调用C++
//在Java中声明Native方法（即需要调用的本地方法）
//编译上述 Java源文件javac（得到 .class文件） 3。 通过 javah 命令导出JNI的头文件（.h文件）
//使用 Java需要交互的本地代码 实现在 Java中声明的Native方法
//编译.so库文件
//通过Java命令执行 Java程序，最终实现Java调用本地代码
//C++调用Java
//从classpath路径下搜索ClassMethod这个类，并返回该类的Class对象。
//获取类的默认构造方法ID。
//查找实例方法的ID。
//创建该类的实例。
//调用对象的实例方法。
//JNIEXPORT void JNICALL
//Java_com_study_jnilearn_AccessMethod_callJavaInstaceMethod
//(JNIEnv *env, jclass cls)
//{
//jclass clazz = NULL;
//jobject jobj = NULL;
//jmethodID mid_construct = NULL;
//jmethodID mid_instance = NULL;
//jstring str_arg = NULL;
//// 1、从classpath路径下搜索ClassMethod这个类，并返回该类的Class对象
//clazz = (*env)->FindClass(env, "com/study/jnilearn/ClassMethod");
//if (clazz == NULL) {
//printf("找不到'com.study.jnilearn.ClassMethod'这个类");
//return;
//} /
/// 2、获取类的默认构造方法ID
//mid_construct = (*env)->GetMethodID(env,clazz, "<init>","()V");
//if (mid_construct == NULL) {
//printf("找不到默认的构造方法");
//return;
//} /
/// 3、查找实例方法的ID
//mid_instance = (*env)->GetMethodID(env, clazz, "callInstanceMethod", "
//(Ljava/lang/String;I)V");如何在jni中注册native函数，有几种注册方式？
//21、请介绍一下NDK？
//三、Android优秀三方库源码
//1、你项目中用到哪些开源库？说说其实现原理？
//一、网络底层框架：OkHttp实现原理
//这个库是做什么用的？
//网络底层库，它是基于http协议封装的一套请求客户端，虽然它也可以开线程，但根本上它更偏向真正
//的请求，跟HttpClient, HttpUrlConnection的职责是一样的。其中封装了网络请求get、post等底层操
//作的实现。
//为什么要在项目中使用这个库？
//OkHttp 提供了对最新的 HTTP 协议版本 HTTP/2 和 SPDY 的支持，这使得对同一个主机发出的所
//有请求都可以共享相同的套接字连接。
//如果 HTTP/2 和 SPDY 不可用，OkHttp 会使用连接池来复用连接以提高效率。
//OkHttp 提供了对 GZIP 的默认支持来降低传输内容的大小。
//OkHttp 也提供了对 HTTP 响应的缓存机制，可以避免不必要的网络请求。
//当网络出现问题时，OkHttp 会自动重试一个主机的多个 IP 地址。
//这个库都有哪些用法？对应什么样的使用场景？
//get、post请求、上传文件、上传表单等等。
//这个库的优缺点是什么，跟同类型库的比较？
//优点：在上面
//if (mid_instance == NULL) {
//return;
//} /
/// 4、创建该类的实例
//jobj = (*env)->NewObject(env,clazz,mid_construct);
//if (jobj == NULL) {
//printf("在com.study.jnilearn.ClassMethod类中找不到callInstanceMethod方
//法");
//return;
//} /
/// 5、调用对象的实例方法
//str_arg = (*env)->NewStringUTF(env,"我是实例方法");
//(*env)->CallVoidMethod(env,jobj,mid_instance,str_arg,200);
//// 删除局部引用
//(*env)->DeleteLocalRef(env,clazz);
//(*env)->DeleteLocalRef(env,jobj);
//(*env)->DeleteLocalRef(env,str_arg);
//}缺点：使用的时候仍然需要自己再做一层封装。
//这个库的核心实现原理是什么？如果让你实现这个库的某些核心功能，你会考虑怎么去实现？
//OkHttp内部的请求流程：使用OkHttp会在请求的时候初始化一个Call的实例，然后执行它的execute()
//方法或enqueue()方法，内部最后都会执行到getResponseWithInterceptorChain()方法，这个方法里
//面通过拦截器组成的责任链，依次经过用户自定义普通拦截器、重试拦截器、桥接拦截器、缓存拦截
//器、连接拦截器和用户自定义网络拦截器以及访问服务器拦截器等拦截处理过程，来获取到一个响应并
//交给用户。其中，除了OKHttp的内部请求流程这点之外，缓存和连接这两部分内容也是两个很重要的
//点，掌握了这3点就说明你理解了OkHttp。
//各个拦截器的作用：
//interceptors：用户自定义拦截器
//retryAndFollowUpInterceptor：负责失败重试以及重定向
//BridgeInterceptor：请求时，对必要的Header进行一些添加，接收响应时，移除必要的Header
//CacheInterceptor：负责读取缓存直接返回（根据请求的信息和缓存的响应的信息来判断是否存
//在缓存可用）、更新缓存
//ConnectInterceptor：负责和服务器建立连接
//ConnectionPool：
//1、判断连接是否可用，不可用则从ConnectionPool获取连接，ConnectionPool无连接，创建新连
//接，握手，放入ConnectionPool。
//2、它是一个Deque，add添加Connection，使用线程池负责定时清理缓存。
//3、使用连接复用省去了进行 TCP 和 TLS 握手的一个过程。
//networkInterceptors：用户定义网络拦截器
//CallServerInterceptor：负责向服务器发送请求数据、从服务器读取响应数据
//你从这个库中学到什么有价值的或者说可借鉴的设计思想？
//使用责任链模式实现拦截器的分层设计，每一个拦截器对应一个功能，充分实现了功能解耦，易维护。
//手写拦截器？
//网络请求缓存处理，okhttp如何处理网络缓存的？
//HttpUrlConnection 和 okhttp关系？
//Volley与OkHttp的对比：
//Volley：支持HTTPS。缓存、异步请求，不支持同步请求。协议类型是Http/1.0, Http/1.1，网络传输使
//用的是 HttpUrlConnection/HttpClient，数据读写使用的IO。
//OkHttp：支持HTTPS。缓存、异步请求、同步请求。协议类型是Http/1.0, Http/1.1, SPDY, Http/2.0,
//WebSocket，网络传输使用的是封装的Socket，数据读写使用的NIO（Okio）。
//SPDY协议类似于HTTP，但旨在缩短网页的加载时间和提高安全性。SPDY协议通过压缩、多路复用和
//优先级来缩短加载时间。
//Okhttp的子系统层级结构图如下所示：网络配置层：利用Builder模式配置各种参数，例如：超时时间、拦截器等，这些参数都会由Okhttp分
//发给各个需要的子系统。
//重定向层：负责重定向。
//Header拼接层：负责把用户构造的请求转换为发送给服务器的请求，把服务器返回的响应转换为对用
//户友好的响应。
//HTTP缓存层：负责读取缓存以及更新缓存。
//连接层：连接层是一个比较复杂的层级，它实现了网络协议、内部的拦截器、安全性认证，连接与连接
//池等功能，但这一层还没有发起真正的连接，它只是做了连接器一些参数的处理。
//数据响应层：负责从服务器读取响应的数据。
//在整个Okhttp的系统中，我们还要理解以下几个关键角色：
//OkHttpClient：通信的客户端，用来统一管理发起请求与解析响应。
//Call：Call是一个接口，它是HTTP请求的抽象描述，具体实现类是RealCall，它由CallFactory创建。
//Request：请求，封装请求的具体信息，例如：url、header等。
//RequestBody：请求体，用来提交流、表单等请求信息。
//Response：HTTP请求的响应，获取响应信息，例如：响应header等。ResponseBody：HTTP请求的响应体，被读取一次以后就会关闭，所以我们重复调用
//responseBody.string()获取请求结果是会报错的。
//Interceptor：Interceptor是请求拦截器，负责拦截并处理请求，它将网络请求、缓存、透明压缩等功
//能都统一起来，每个功能都是一个Interceptor，所有的Interceptor最 终连接成一个
//Interceptor.Chain。典型的责任链模式实现。
//StreamAllocation：用来控制Connections与Streas的资源分配与释放。
//RouteSelector：选择路线与自动重连。
//RouteDatabase：记录连接失败的Route黑名单。
//自己去设计网络请求框架，怎么做？
//从网络加载一个10M的图片，说下注意事项？
//http怎么知道文件过大是否传输完毕的响应？
//谈谈你对WebSocket的理解？
//WebSocket与socket的区别？
//二、网络封装框架：Retrofit实现原理
//这个库是做什么用的？
//Retrofit 是一个 RESTful 的 HTTP 网络请求框架的封装。Retrofit 2.0 开始内置 OkHttp，前者专注于接
//口的封装，后者专注于网络请求的高效。
//为什么要在项目中使用这个库？
//1、功能强大：
//支持同步、异步
//支持多种数据的解析 & 序列化格式
//支持RxJava
//2、简洁易用：
//通过注解配置网络请求参数
//采用大量设计模式简化使用
//3、可扩展性好：
//功能模块高度封装
//解耦彻底，如自定义Converters
//这个库都有哪些用法？对应什么样的使用场景？
//任何网络场景都应该优先选择，特别是后台API遵循Restful API设计风格 & 项目中使用到RxJava。
//这个库的优缺点是什么，跟同类型库的比较？
//优点：在上面
//缺点：扩展性差，高度封装所带来的必然后果，如果服务器不能给出统一的API形式，会很难处
//理。
//这个库的核心实现原理是什么？如果让你实现这个库的某些核心功能，你会考虑怎么去实现？
//Retrofit主要是在create方法中采用动态代理模式（通过访问代理对象的方式来间接访问目标对象）实
//现接口方法，这个过程构建了一个ServiceMethod对象，根据方法注解获取请求方式，参数类型和参数
//注解拼接请求的链接，当一切都准备好之后会把数据添加到Retrofit的RequestBuilder中。然后当我们
//主动发起网络请求的时候会调用okhttp发起网络请求，okhttp的配置包括请求方式，URL等在Retrofit
//的RequestBuilder的build()方法中实现，并发起真正的网络请求。你从这个库中学到什么有价值的或者说可借鉴的设计思想？
//内部使用了优秀的架构设计和大量的设计模式，在我分析过Retrofit最新版的源码和大量优秀的Retrofit
//源码分析文章后，我发现，要想真正理解Retrofit内部的核心源码流程和设计思想，首先，需要对它使
//用到的九大设计模式有一定的了解，下面我简单说一说：
//1、创建Retrofit实例：
//使用建造者模式通过内部Builder类建立了一个Retroift实例。
//网络请求工厂使用了工厂方法模式。
//2、创建网络请求接口的实例：
//首先，使用外观模式统一调用创建网络请求接口实例和网络请求参数配置的方法。
//然后，使用动态代理动态地去创建网络请求接口实例。
//接着，使用了建造者模式 & 单例模式创建了serviceMethod对象。
//再者，使用了策略模式对serviceMethod对象进行网络请求参数配置，即通过解析网络请求接口方
//法的参数、返回值和注解类型，从Retrofit对象中获取对应的网络的url地址、网络请求执行器、网
//络请求适配器和数据转换器。
//最后，使用了装饰者模式ExecuteCallBack为serviceMethod对象加入线程切换的操作，便于接受
//数据后通过Handler从子线程切换到主线程从而对返回数据结果进行处理。
//3、发送网络请求：
//在异步请求时，通过静态delegate代理对网络请求接口的方法中的每个参数使用对应的
//ParameterHanlder进行解析。
//4、解析数据
//5、切换线程：
//使用了适配器模式通过检测不同的Platform使用不同的回调执行器，然后使用回调执行器切换线
//程，这里同样是使用了装饰模式。
//6、处理结果
//Android：主流网络请求开源库的对比（Android-Async-Http、Volley、OkHttp、Retrofit）
//https://www.jianshu.com/p/050c6db5af5a
//三、响应式编程框架：RxJava实现原理
//RxJava 变换操作符 map flatMap concatMap buffer？
//map：【数据类型转换】将被观察者发送的事件转换为另一种类型的事件。
//flatMap：【化解循环嵌套和接口嵌套】将被观察者发送的事件序列进行拆分 & 转换 后合并成一
//个新的事件序列，最后再进行发送。
//concatMap：【有序】与 flatMap 的 区别在于，拆分 & 重新合并生成的事件序列 的顺序与被观
//察者旧序列生产的顺序一致。
//buffer：定期从被观察者发送的事件中获取一定数量的事件并放到缓存区中，然后把这些数据集合
//打包发射。
//RxJava中map和flatmap操作符的区别及底层实现
//手写rxjava遍历数组。
//你认为Rxjava的线程池与你们自己实现任务管理框架有什么区别？
//四、图片加载框架：Glide实现原理
//这个库是做什么用的？Glide是Android中的一个图片加载库，用于实现图片加载。
//为什么要在项目中使用这个库？
//1、多样化媒体加载：不仅可以进行图片缓存，还支持Gif、WebP、缩略图，甚至是Video。
//2、通过设置绑定生命周期：可以使加载图片的生命周期动态管理起来。
//3、高效的缓存策略：支持内存、Disk缓存，并且Picasso只会缓存原始尺寸的图片，内Glide缓存的是
//多种规格，也就是Glide会根据你ImageView的大小来缓存相应大小的图片尺寸。
//4、内存开销小：默认的Bitmap格式是RGB_565格式，而Picasso默认的是ARGB_8888格式，内存开销
//小一半。
//这个库都有哪些用法？对应什么样的使用场景？
//1、图片加载：Glide.with(this).load(imageUrl).override(800,
//800).placeholder().error().animate().into()。
//2、多样式媒体加载：asBitamp、asGif。
//3、生命周期集成。
//4、可以配置磁盘缓存策略ALL、NONE、SOURCE、RESULT。
//这个库的优缺点是什么，跟同类型库的比较？
//库比较大，源码实现复杂。
//这个库的核心实现原理是什么？如果让你实现这个库的某些核心功能，你会考虑怎么去实现？
//Glide&with：
//1、初始化各式各样的配置信息（包括缓存，请求线程池，大小，图片格式等等）以及glide对象。
//2、将glide请求和application/SupportFragment/Fragment的生命周期绑定在一块。
//Glide&load：
//设置请求url，并记录url已设置的状态。
//3、Glide&into：
//1、首先根据转码类transcodeClass类型返回不同的ImageViewTarget：BitmapImageViewTarget、
//DrawableImageViewTarget。
//2、递归建立缩略图请求，没有缩略图请求，则直接进行正常请求。
//3、如果没指定宽高，会根据ImageView的宽高计算出图片宽高，最终执行到onSizeReay()方法中的
//engine.load()方法。
//4、engine是一个负责加载和管理缓存资源的类
//常规三级缓存的流程：强引用->软引用->硬盘缓存
//当我们的APP中想要加载某张图片时，先去LruCache中寻找图片，如果LruCache中有，则直接取出来
//使用，如果LruCache中没有，则去SoftReference中寻找（软引用适合当cache，当内存吃紧的时候才
//会被回收。而weakReference在每次system.gc（）就会被回收）（当LruCache存储紧张时，会把最近
//最少使用的数据放到SoftReference中），如果SoftReference中有，则从SoftReference中取出图片使
//用，同时将图片重新放回到LruCache中，如果SoftReference中也没有图片，则去硬盘缓存中中寻找，
//如果有则取出来使用，同时将图片添加到LruCache中，如果没有，则连接网络从网上下载图片。图片
//下载完成后，将图片保存到硬盘缓存中，然后放到LruCache中。
//Glide的三层缓存机制：Glide缓存机制大致分为三层：内存缓存、弱引用缓存、磁盘缓存。
//取的顺序是：内存、弱引用、磁盘。
//存的顺序是：弱引用、内存、磁盘。
//三层存储的机制在Engine中实现的。先说下Engine是什么？Engine这一层负责加载时做管理内存缓存
//的逻辑。持有MemoryCache、Map<Key, WeakReference<EngineResource<?>>>。通过load（）来
//加载图片，加载前后会做内存存储的逻辑。如果内存缓存中没有，那么才会使用EngineJob这一层来进
//行异步获取硬盘资源或网络资源。EngineJob类似一个异步线程或observable。Engine是一个全局唯一
//的，通过Glide.getEngine()来获取。
//需要一个图片资源，如果Lrucache中有相应的资源图片，那么就返回，同时从Lrucache中清除，放到
//activeResources中。activeResources map是盛放正在使用的资源，以弱引用的形式存在。同时资源
//内部有被引用的记录。如果资源没有引用记录了，那么再放回Lrucache中，同时从activeResources中
//清除。如果Lrucache中没有，就从activeResources中找，找到后相应资源引用加1。如果Lrucache和
//activeResources中没有，那么进行资源异步请求（网络/diskLrucache），请求成功后，资源放到
//diskLrucache和activeResources中。
//Glide源码机制的核心思想：
//使用一个弱引用map activeResources来盛放项目中正在使用的资源。Lrucache中不含有正在使用的资
//源。资源内部有个计数器来显示自己是不是还有被引用的情况，把正在使用的资源和没有被使用的资源
//分开有什么好处呢？？因为当Lrucache需要移除一个缓存时，会调用resource.recycle()方法。注意到
//该方法上面注释写着只有没有任何consumer引用该资源的时候才可以调用这个方法。那么为什么调用
//resource.recycle()方法需要保证该资源没有任何consumer引用呢？glide中resource定义的
//recycle（）要做的事情是把这个不用的资源（假设是bitmap或drawable）放到bitmapPool中。
//bitmapPool是一个bitmap回收再利用的库，在做transform的时候会从这个bitmapPool中拿一个
//bitmap进行再利用。这样就避免了重新创建bitmap，减少了内存的开支。而既然bitmapPool中的
//bitmap会被重复利用，那么肯定要保证回收该资源的时候（即调用资源的recycle（）时），要保证该
//资源真的没有外界引用了。这也是为什么glide花费那么多逻辑来保证Lrucache中的资源没有外界引用
//的原因。
//你从这个库中学到什么有价值的或者说可借鉴的设计思想？
//Glide的高效的三层缓存机制，如上。
//Glide如何确定图片加载完毕？
//Glide使用什么缓存？
//Glide内存缓存如何控制大小？
//计算一张图片的大小
//图片占用内存的计算公式：图片高度 * 图片宽度 * 一个像素占用的内存大小。所以，计算图片占用内存
//大小的时候，要考虑图片所在的目录跟设备密度，这两个因素其实影响的是图片的宽高，android会对
//图片进行拉升跟压缩。
//加载bitmap过程（怎样保证不产生内存溢出）
//由于Android对图片使用内存有限制，若是加载几兆的大图片便内存溢出。Bitmap会将图片的所有像素
//（即长x宽）加载到内存中，如果图片分辨率过大，会直接导致内存OOM，只有在BitmapFactory加载
//图片时使用BitmapFactory.Options对相关参数进行配置来减少加载的像素。
//BitmapFactory.Options相关参数详解：(1).Options.inPreferredConfig值来降低内存消耗。
//比如：默认值ARGB_8888改为RGB_565,节约一半内存。
//(2).设置Options.inSampleSize 缩放比例，对大图片进行压缩 。
//(3).设置Options.inPurgeable和inInputShareable：让系统能及时回收内存。
//(4).使用decodeStream代替decodeResource等其他方法。
//Android中软引用与弱引用的应用场景。
//Java 引用类型分类：
//在 Android 应用的开发中，为了防止内存溢出，在处理一些占用内存大而且生命周期较长的对象时候，
//可以尽量应用软引用和弱引用技术。
//1、软/弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃
//圾回收器回收，Java 虚拟机就会把这个软引用加入到与之关联的引用队列中。利用这个队列可以
//得知被回收的软/弱引用的对象列表，从而为缓冲器清除已失效的软 / 弱引用。
//A：inPurgeable：设置为True时，表示系统内存不足时可以被回收，设置为False时，表示不能被回收。
//B：inInputShareable：设置是否深拷贝，与inPurgeable结合使用，inPurgeable为false时，该参
//数无意义。2、如果只是想避免 OOM 异常的发生，则可以使用软引用。如果对于应用的性能更在意，想尽快
//回收一些占用内存比较大的对象，则可以使用弱引用。
//3、可以根据对象是否经常使用来判断选择软引用还是弱引用。如果该对象可能会经常使用的，就
//尽量用软引用。如果该对象不被使用的可能性更大些，就可以用弱引用。
//Android里的内存缓存和磁盘缓存是怎么实现的。
//内存缓存基于LruCache实现，磁盘缓存基于DiskLruCache实现。这两个类都基于Lru算法和
//LinkedHashMap来实现。
//LRU算法可以用一句话来描述，如下所示：
//LRU是Least Recently Used的缩写，最近最少使用算法，从它的名字就可以看出，它的核心原则是如果
//一个数据在最近一段时间没有使用到，那么它在将来被访问到的可能性也很小，则这类数据项会被优先
//淘汰掉。
//LruCache原理
//之前，我们会使用内存缓存技术实现，也就是软引用或弱引用，在Android 2.3（APILevel 9）开始，垃
//圾回收器会更倾向于回收持有软引用或弱引用的对象，这让软引用和弱引用变得不再可靠。
//其实LRU缓存的实现类似于一个特殊的栈，把访问过的元素放置到栈顶（若栈中存在，则更新至栈顶；
//若栈中不存在则直接入栈），然后如果栈中元素数量超过限定值，则删除栈底元素（即最近最少使用的
//元素）。
//它的内部存在一个 LinkedHashMap 和 maxSize，把最近使用的对象用强引用存储在 LinkedHashMap
//中，给出来 put 和 get 方法，每次 put 图片时计算缓存中所有图片的总大小，跟 maxSize 进行比较，
//大于 maxSize，就将最久添加的图片移除，反之小于 maxSize 就添加进来。
//LruCache的原理就是利用LinkedHashMap持有对象的强引用，按照Lru算法进行对象淘汰。具体说来
//假设我们从表尾访问数据，在表头删除数据，当访问的数据项在链表中存在时，则将该数据项移动到表
//尾，否则在表尾新建一个数据项。当链表容量超过一定阈值，则移除表头的数据。
//详细来说就是LruCache中维护了一个集合LinkedHashMap，该LinkedHashMap是以访问顺序排序
//的。当调用put()方法时，就会在结合中添加元素，并调用trimToSize()判断缓存是否已满，如果满了就
//用LinkedHashMap的迭代器删除队头元素，即近期最少访问的元素。当调用get()方法访问缓存对象
//时，就会调用LinkedHashMap的get()方法获得对应集合元素，同时会更新该元素到队尾。
//LruCache put方法核心逻辑
//在添加过缓存对象后，调用trimToSize()方法，来判断缓存是否已满，如果满了就要删除近期最少使用
//的对象。trimToSize()方法不断地删除LinkedHashMap中队头的元素，即近期最少访问的，直到缓存大
//小小于最大值（maxSize）。
//LruCache get方法核心逻辑
//当调用LruCache的get()方法获取集合中的缓存对象时，就代表访问了一次该元素，将会更新队列，保
//持整个队列是按照访问顺序排序的。
//为什么会选择LinkedHashMap呢？
//这跟LinkedHashMap的特性有关，LinkedHashMap的构造函数里有个布尔参数accessOrder，当它为
//true时，LinkedHashMap会以访问顺序为序排列元素，否则以插入顺序为序排序元素。
//LinkedHashMap原理
//LinkedHashMap 几乎和 HashMap 一样：从技术上来说，不同的是它定义了一个 Entry<K,V>
//header，这个 header 不是放在 Table 里，它是额外独立出来的。LinkedHashMap 通过继承
//hashMap 中的 Entry<K,V>,并添加两个属性 Entry<K,V> before,after,和 header 结合起来组成一个双向
//链表，来实现按插入顺序或访问顺序排序。DisLruCache原理
//DiskLruCache与LruCache原理相似，只是多了一个journal文件来做磁盘文件的管理，如下所示：
//注：这里的缓存目录是应用的缓存目录/data/data/pckagename/cache，未root的手机可以通过以下命
//令进入到该目录中或者将该目录整体拷贝出来：
//我们来分析下这个文件的内容：
//第一行：libcore.io.DiskLruCache，固定字符串。
//第二行：1，DiskLruCache源码版本号。
//第三行：1，App的版本号，通过open()方法传入进去的。
//第四行：1，每个key对应几个文件，一般为1.
//第五行：空行
//第六行及后续行：缓存操作记录。
//第六行及后续行表示缓存操作记录，关于操作记录，我们需要了解以下三点：
//DIRTY 表示一个entry正在被写入。写入分两种情况，如果成功会紧接着写入一行CLEAN的记录；如果
//失败，会增加一行REMOVE记录。注意单独只有DIRTY状态的记录是非法的。
//当手动调用remove(key)方法的时候也会写入一条REMOVE记录。
//READ就是说明有一次读取的记录。
//CLEAN的后面还记录了文件的长度，注意可能会一个key对应多个文件，那么就会有多个数字。
//Bitmap 压缩策略
//加载 Bitmap 的方式：
//BitmapFactory 四类方法：
//decodeFile( 文件系统 )
//decodeResourece( 资源 )
//decodeStream( 输入流 )
//decodeByteArray( 字节数 )
//BitmapFactory.options 参数:
//inSampleSize 采样率，对图片高和宽进行缩放，以最小比进行缩放（一般取值为 2 的指数）。通
//常是根据图片宽高实际的大小/需要的宽高大小，分别计算出宽和高的缩放比。但应该取其中最小
//的缩放比，避免缩放图片太小，到达指定控件中不能铺满，需要拉伸从而导致模糊。
//libcore.io.DiskLruCache
//1 1 1 D
//IRTY 1517126350519
//CLEAN 1517126350519 5325928
//REMOVE 1517126350519
////进入/data/data/pckagename/cache目录
//adb shell
//run-as com.your.packagename
//cp /data/data/com.your.packagename/
////将/data/data/pckagename目录拷贝出来
//adb backup -noapk com.your.packagenameinJustDecodeBounds 获取图片的宽高信息，交给 inSampleSize 参数选择缩放比。通过
//inJustDecodeBounds = true，然后加载图片就可以实现只解析图片的宽高信息，并不会真正的加
//载图片，所以这个操作是轻量级的。当获取了宽高信息，计算出缩放比后，然后在将
//inJustDecodeBounds = false,再重新加载图片，就可以加载缩放后的图片。
//高效加载 Bitmap 的流程:
//1、将 BitmapFactory.Options 的 inJustDecodeBounds 参数设为 true 并加载图片
//2、从 BitmapFactory.Options 中取出图片原始的宽高信息，对应于 outWidth 和 outHeight 参数
//3、根据采样率规则并结合目标 view 的大小计算出采样率 inSampleSize
//4、将 BitmapFactory.Options 的 inJustDecodeBounds 设置为 false 重新加载图片
//Bitmap的处理：
//当使用ImageView的时候，可能图片的像素大于ImageView，此时就可以通过BitmapFactory.Option来
//对图片进行压缩，inSampleSize表示缩小2^(inSampleSize-1)倍。
//BitMap的缓存：
//1.使用LruCache进行内存缓存。
//2.使用DiskLruCache进行硬盘缓存。
//实现一个ImageLoader的流程
//同步异步加载、图片压缩、内存硬盘缓存、网络拉取
//1.同步加载只创建一个线程然后按照顺序进行图片加载
//2.异步加载使用线程池，让存在的加载任务都处于不同线程
//3.为了不开启过多的异步任务，只在列表静止的时候开启图片加载
//具体为：
//1、ImageLoader作为一个单例，提供了加载图片到指定控件的方法：直接从内存缓存中获取对
//象，如果没有则用一个ThreadPoolExecutor去执行Runnable任务来加载图片。
//ThreadPoolExecutor的创建需要指定核心线程数CPU数+1，最大线程数CPU数*2+1，线程闲置超
//市时长10s,这几个关键数据，还可以加入ThreadFactory参数来创建定制化的线程。
//2、ImageLoader的具体实现loadBitmap：先从内存缓存LruCache中加载，如果为空再从磁盘缓
//存中加载，加载成功后记得存入内存缓存，如果为空则从网络中直接下载输出流到磁盘缓存，然后
//再从磁盘中加载，如果为空并且磁盘缓存没有被创建的话，直接通过BitmapFactory的
//decodeStream获取网络请求的输入流获取Bitmap对象。
//3、v4包的LruCache可以兼容到2.2版本，LruCache采用LinkedHashMap存储缓存对象。创建对
//象只需要提供缓存容量并重写sizeOf方法：作用是计算缓存对象的大小。有时需要重写
//entryRemoved方法，用于回收一些资源。
//4、DiskLruCache通过open方法创建，设置缓存路径，缓存容量。缓存添加通过Editor对象创建
//输出流，下载资源到输出流完成后，commit，如果失败则abort撤回。然后刷新磁盘缓存。缓存
//查找通过Snapshot对象获取输入流，获取FileDescriptor，通过FileDescriptor解析出Bitmap对
//象。
//5、列表中需要加载图片的时候，当列表在滑动中不进行图片加载，当滑动停止后再去加载图片。
//Bitmap在decode的时候申请的内存如何复用，释放时机
//图片库对比
//http://stackoverflow.com/questions/29363321/picasso-v-s-imageloader-v-s-fresco-vs-glidehttp://www.trinea.cn/android/android-image-cache-compare/
//Fresco与Glide的对比：
//Glide：相对轻量级，用法简单优雅，支持Gif动态图，适合用在那些对图片依赖不大的App中。
//Fresco：采用匿名共享内存来保存图片，也就是Native堆，有效的的避免了OOM，功能强大，但是库
//体积过大，适合用在对图片依赖比较大的App中。
//Fresco的整体架构如下图所示：DraweeView：继承于ImageView，只是简单的读取xml文件的一些属性值和做一些初始化的工作，图
//层管理交由Hierarchy负责，图层数据获取交由负责。
//DraweeHierarchy：由多层Drawable组成，每层Drawable提供某种功能（例如：缩放、圆角）。
//DraweeController：控制数据的获取与图片加载，向pipeline发出请求，并接收相应事件，并根据不同
//事件控制Hierarchy，从DraweeView接收用户的事件，然后执行取消网络请求、回收资源等操作。
//DraweeHolder：统筹管理Hierarchy与DraweeHolder。
//ImagePipeline：Fresco的核心模块，用来以各种方式（内存、磁盘、网络等）获取图像。
//Producer/Consumer：Producer也有很多种，它用来完成网络数据获取，缓存数据获取、图片解码等
//多种工作，它产生的结果由Consumer进行消费。
//IO/Data：这一层便是数据层了，负责实现内存缓存、磁盘缓存、网络缓存和其他IO相关的功能。
//纵观整个Fresco的架构，DraweeView是门面，和用户进行交互，DraweeHierarchy是视图层级，管理
//图层，DraweeController是控制器，管理数据。它们构成了整个Fresco框架的三驾马车。当然还有我们
//幕后英雄Producer，所有的脏活累活都是它干的，最佳劳模👍
//理解了Fresco整体的架构，我们还有了解在这套矿建里发挥重要作用的几个关键角色，如下所示：
//Supplier：提供一种特定类型的对象，Fresco里有很多以Supplier结尾的类都实现了这个接口。
//SimpleDraweeView：这个我们就很熟悉了，它接收一个URL，然后调用Controller去加载图片。该类
//继承于GenericDraweeView，GenericDraweeView又继承于DraweeView，DraweeView是Fresco的顶
//层View类。
//PipelineDraweeController：负责图片数据的获取与加载，它继承于AbstractDraweeController，由
//PipelineDraweeControllerBuilder构建而来。AbstractDraweeController实现了DraweeController接
//口，DraweeController 是Fresco的数据大管家，所以的图片数据的处理都是由它来完成的。
//GenericDraweeHierarchy：负责SimpleDraweeView上的图层管理，由多层Drawable组成，每层
//Drawable提供某种功能（例如：缩放、圆角），该类由GenericDraweeHierarchyBuilder进行构建，
//该构建器 将placeholderImage、retryImage、failureImage、progressBarImage、background、
//overlays与pressedStateOverlay等 xml文件或者Java代码里设置的属性信息都传入
//GenericDraweeHierarchy中，由GenericDraweeHierarchy进行处理。
//DraweeHolder：该类是一个Holder类，和SimpleDraweeView关联在一起，DraweeView是通过
//DraweeHolder来统一管理的。而DraweeHolder又是用来统一管理相关的Hierarchy与Controller
//DataSource：类似于Java里的Futures，代表数据的来源，和Futures不同，它可以有多个result。
//DataSubscriber：接收DataSource返回的结果。
//ImagePipeline：用来调取获取图片的接口。
//Producer：加载与处理图片，它有多种实现，例如：NetworkFetcherProducer，
//LocalAssetFetcherProducer，LocalFileFetchProducer。从这些类的名字我们就可以知道它们是干什
//么的。 Producer由ProducerFactory这个工厂类构建的，而且所有的Producer都是像Java的IO流那
//样，可以一层嵌套一层，最终只得到一个结果，这是一个很精巧的设计👍
//Consumer：用来接收Producer产生的结果，它与Producer组成了生产者与消费者模式。
//注：Fresco源码里的类的名字都比较长，但是都是按照一定的命令规律来的，例如：以Supplier结尾的
//类都实现了Supplier接口，它可以提供某一个类型的对象（factory, generator, builder, closure等）。
//以Builder结尾的当然就是以构造者模式创建对象的类。
//Bitmap如何处理大图，如一张30M的大图，如何预防OOM?
//http://blog.csdn.net/guolin_blog/article/details/9316683
//https://blog.csdn.net/lmj623565791/article/details/493009890
//使用BitmapRegionDecoder动态加载图片的显示区域。
//Bitmap对象的理解
//自己去实现图片库，怎么做？（对扩展开发，对修改封闭，同时又保持独立性，参考Android源码设计
//模式解析实战的图片加载库案例即可）
//写个图片浏览器，说出你的思路？五、事件总线框架：EventBus实现原理
//六、内存泄漏检测框架：LeakCanary实现原理
//这个库是做什么用？
//内存泄露检测框架。
//为什么要在项目中使用这个库？
//针对Android Activity组件完全自动化的内存泄漏检查，在最新的版本中，还加入了
//android.app.fragment的组件自动化的内存泄漏检测。
//易用集成，使用成本低。
//友好的界面展示和通知。
//这个库都有哪些用法？对应什么样的使用场景？
//直接从application中拿到全局的 refWatcher 对象，在Fragment或其他组件的销毁回调中使用
//refWatcher.watch(this)检测是否发生内存泄漏。
//这个库的优缺点是什么，跟同类型库的比较？
//检测结果并不是特别的准确，因为内存的释放和对象的生命周期有关也和GC的调度有关。
//这个库的核心实现原理是什么？如果让你实现这个库的某些核心功能，你会考虑怎么去实现？
//主要分为如下7个步骤：
//1、RefWatcher.watch()创建了一个KeyedWeakReference用于去观察对象。
//2、然后，在后台线程中，它会检测引用是否被清除了，并且是否没有触发GC。
//3、如果引用仍然没有被清除，那么它将会把堆栈信息保存在文件系统中的.hprof文件里。
//4、HeapAnalyzerService被开启在一个独立的进程中，并且HeapAnalyzer使用了HAHA开源库解
//析了指定时刻的堆栈快照文件heap dump。
//5、从heap dump中，HeapAnalyzer根据一个独特的引用key找到了KeyedWeakReference，并
//且定位了泄露的引用。
//6、HeapAnalyzer为了确定是否有泄露，计算了到GC Roots的最短强引用路径，然后建立了导致
//泄露的链式引用。
//7、这个结果被传回到app进程中的DisplayLeakService，然后一个泄露通知便展现出来了。
//简单来说就是：
//在一个Activity执行完onDestroy()之后，将它放入WeakReference中，然后将这个WeakReference类型
//的Activity对象与ReferenceQueque关联。这时再从ReferenceQueque中查看是否有该对象，如果没
//有，执行gc，再次查看，还是没有的话则判断发生内存泄露了。最后用HAHA这个开源库去分析dump
//之后的heap内存（主要就是创建一个HprofParser解析器去解析出对应的引用内存快照文件
//snapshot）。
//流程图：源码分析中一些核心分析点：
//AndroidExcludedRefs：它是一个enum类，它声明了Android SDK和厂商定制的SDK中存在的内存泄
//露的case，根据AndroidExcludedRefs这个类的类名就可看出这些case都会被Leakcanary的监测过滤
//掉。
//buildAndInstall()（即install方法）这个方法应该仅仅只调用一次。
//debuggerControl : 判断是否处于调试模式，调试模式中不会进行内存泄漏检测。为什么呢？因为在调
//试过程中可能会保留上一个引用从而导致错误信息上报。
//watchExecutor : 线程控制器，在 onDestroy() 之后并且主线程空闲时执行内存泄漏检测。
//gcTrigger : 用于 GC，watchExecutor 首次检测到可能的内存泄漏，会主动进行 GC，GC 之后会再检测
//一次，仍然泄漏的判定为内存泄漏，最后根据heapDump信息生成相应的泄漏引用链。
//gcTrigger的runGc()方法：这里并没有使用System.gc()方法进行回收，因为system.gc()并不会每次都执
//行。而是从AOSP中拷贝一段GC回收的代码，从而相比System.gc()更能够保证进行垃圾回收的工作。
//子线程延时1000ms；
//System.runFinalization();
//install方法内部最终还是调用了application的registerActivityLifecycleCallbacks()方法，这样就能够监
//听activity对应的生命周期事件了。
//在RefWatcher#watch()中使用随机的UUID保证了每个检测对象对应的key 的唯一性。
//在KeyedWeakReference内部，使用了key和name标识了一个被检测的WeakReference对象。在其构
//造方法中将弱引用和引用队列 ReferenceQueue 关联起来，如果弱引用reference持有的对象被GC回
//收，JVM就会把这个弱引用加入到与之关联的引用队列referenceQueue中。即 KeyedWeakReference
//持有的 Activity 对象如果被GC回收，该对象就会加入到引用队列 referenceQueue 中。
//使用Android SDK的API Debug.dumpHprofData() 来生成 hprof 文件。
//Runtime.getRuntime().gc();在HeapAnalyzerService（类型为IntentService的ForegroundService）的runAnalysis()方法中，为了
//避免减慢app进程或占用内存，这里将HeapAnalyzerService设置在了一个独立的进程中。
//你从这个库中学到什么有价值的或者说可借鉴的设计思想？
//BlockCanary原理：
//该组件利用了主线程的消息队列处理机制，应用发生卡顿，一定是在dispatchMessage中执行了耗时操
//作。我们通过给主线程的Looper设置一个Printer，打点统计dispatchMessage方法执行的时间，如果
//超出阀值，表示发生卡顿，则dump出各种信息，提供开发者分析性能瓶颈。
//七、依赖注入框架：ButterKnife实现原理
//ButterKnife对性能的影响很小，因为没有使用使用反射，而是使用的Annotation Processing
//Tool(APT)，注解处理器，javac中用于编译时扫描和解析Java注解的工具。在编译阶段执行的，它的原
//理就是读入Java源代码，解析注解，然后生成新的Java代码。新生成的Java代码最后被编译成Java字节
//码，注解解析器不能改变读入的Java类，比如不能加入或删除Java方法。
//AOP IOC 的好处以及在 Android 开发中的应用
//八、依赖全局管理框架：Dagger2实现原理
//九、数据库框架：GreenDao实现原理
//数据库框架对比？
//数据库的优化
//数据库数据迁移问题
//数据库索引的数据结构
//平衡二叉树
//1、非叶子节点只能允许最多两个子节点存在。
//2、每一个非叶子节点数据分布规则为左边的子节点小当前节点的值，右边的子节点大于当前节点
//的值(这里值是基于自己的算法规则而定的，比如hash值)。
//3、树的左右两边的层级数相差不会大于1。
//使用平衡二叉树能保证数据的左右两边的节点层级相差不会大于1.，通过这样避免树形结构由于删除增
//加变成线性链表影响查询效率，保证数据平衡的情况下查找数据的速度近于二分法查找。目前大部分数据库系统及文件系统都采用B-Tree或其变种B+Tree作为索引结构。
//B-Tree
//B树和平衡二叉树稍有不同的是B树属于多叉树又名平衡多路查找树（查找路径不只两个）。
//1、排序方式：所有节点关键字是按递增次序排列，并遵循左小右大原则。
//2、子节点数：非叶节点的子节点数>1，且<=M ，且M>=2，空树除外（注：M阶代表一个树节点
//最多有多少个查找路径，M=M路,当M=2则是2叉树,M=3则是3叉）。
//3、关键字数：枝节点的关键字数量大于等于ceil(m/2)-1个且小于等于M-1个（注：ceil()是个朝正
//无穷方向取整的函数 如ceil(1.1)结果为2)。
//4、所有叶子节点均在同一层、叶子节点除了包含了关键字和关键字记录的指针外也有指向其子节
//点的指针只不过其指针地址都为null对应下图最后一层节点的空格子。
//B树相对于平衡二叉树的不同是，每个节点包含的关键字增多了，把树的节点关键字增多后树的层级比
//原来的二叉树少了，减少数据查找的次数和复杂度。
//B+Tree
//规则：
//1、B+跟B树不同B+树的非叶子节点不保存关键字记录的指针，只进行数据索引。
//2、B+树叶子节点保存了父节点的所有关键字记录的指针，所有数据地址必须要到叶子节点才能获
//取到。所以每次数据查询的次数都一样。
//3、B+树叶子节点的关键字从小到大有序排列，左边结尾数据都会保存右边节点开始数据的指针。
//4、非叶子节点的子节点数=关键字数（来源百度百科）（根据各种资料 这里有两种算法的实现方
//式，另一种为非叶节点的关键字数=子节点数-1（来源维基百科)，虽然他们数据排列结构不一样，
//但其原理还是一样的Mysql 的B+树是用第一种方式实现）。特点：
//1、B+树的层级更少：相较于B树B+每个非叶子节点存储的关键字数更多，树的层级更少所以查询数据
//更快。
//2、B+树查询速度更稳定：B+所有关键字数据地址都存在叶子节点上，所以每次查找的次数都相同所以
//查询速度要比B树更稳定。
//3、B+树天然具备排序功能：B+树所有的叶子节点数据构成了一个有序链表，在查询大小区间的数据时
//候更方便，数据紧密性很高，缓存的命中率也会比B树高。
//4、B+树全节点遍历更快：B+树遍历整棵树只需要遍历所有的叶子节点即可，而不需要像B树一样需要
//对每一层进行遍历，这有利于数据库做全表扫描。
//B树相对于B+树的优点是，如果经常访问的数据离根节点很近，而B树的非叶子节点本身存有关键字其
//数据的地址，所以这种数据检索的时候会要比B+树快。
//B*Tree
//B*树是B+树的变种，相对于B+树他们的不同之处如下：
//1、首先是关键字个数限制问题，B+树初始化的关键字初始化个数是cei(m/2)，b树的初始化个数
//为（cei(2/3m)）。
//2、B+树节点满时就会分裂，而B*树节点满时会检查兄弟节点是否满（因为每个节点都有指向兄弟
//的指针），如果兄弟节点未满则向兄弟节点转移关键字，如果兄弟节点已满，则从当前节点和兄弟
//节点各拿出1/3的数据创建一个新的节点出来。
//在B+树的基础上因其初始化的容量变大，使得节点空间使用率更高，而又存有兄弟节点的指针，可以向
//兄弟节点转移关键字的特性使得B*树分解次数变得更少。结论：
//1、相同思想和策略：从平衡二叉树、B树、B+树、B*树总体来看它们贯彻的思想是相同的，都是
//采用二分法和数据平衡策略来提升查找数据的速度。
//2、不同的方式的磁盘空间利用：不同点是他们一个一个在演变的过程中通过IO从磁盘读取数据的
//原理进行一步步的演变，每一次演变都是为了让节点的空间更合理的运用起来，从而使树的层级减
//少达到快速查找数据的目的；
//还不理解请查看：平衡二叉树、B树、B+树、B*树 理解其中一种你就都明白了。
//四、热修复、插件化、模块化、组件化、Gradle
//1、热修复和插件化
//Android中ClassLoader的种类&特点
//BootClassLoader（Java的BootStrap ClassLoader）：
//用于加载Android Framework层class文件。
//PathClassLoader（Java的App ClassLoader）：
//用于加载已经安装到系统中的apk中的class文件。
//DexClassLoader（Java的Custom ClassLoader）：
//用于加载指定目录中的class文件。
//BaseDexClassLoader：
//是PathClassLoader和DexClassLoader的父类。
//热修补技术是怎样实现的，和插件化有什么区别？
//插件化：动态加载主要解决3个技术问题：
//1、使用ClassLoader加载类。
//2、资源访问。
//3、生命周期管理。
//插件化是体现在功能拆分方面的，它将某个功能独立提取出来，独立开发，独立测试，再插入到主应用
//中。以此来减少主应用的规模。
//热修复：原因：因为一个dvm中存储方法id用的是short类型，导致dex中方法不能超过65536个。
//代码热修复原理：
//将编译好的class文件拆分打包成两个dex，绕过dex方法数量的限制以及安装时的检查，在运行时
//再动态加载第二个dex文件中。
//热修复是体现在bug修复方面的，它实现的是不需要重新发版和重新安装，就可以去修复已知的
//bug。
//利用PathClassLoader和DexClassLoader去加载与bug类同名的类，替换掉bug类，进而达到修复
//bug的目的，原理是在app打包的时候阻止类打上CLASS_ISPREVERIFIED标志，然后在热修复的时
//候动态改变BaseDexClassLoader对象间接引用的dexElements，替换掉旧的类。
//相同点:
//都使用ClassLoader来实现加载新的功能类，都可以使用PathClassLoader与DexClassLoader。
//不同点：
//热修复因为是为了修复Bug的，所以要将新的类替代同名的Bug类，要抢先加载新的类而不是Bug类，
//所以多做两件事：在原先的app打包的时候，阻止相关类去打上CLASS_ISPREVERIFIED标志，还有在热
//修复时动态改变BaseDexClassLoader对象间接引用的dexElements，这样才能抢先代替Bug类，完成
//系统不加载旧的Bug类.。 而插件化只是增加新的功能类或者是资源文件，所以不涉及抢先加载新的类
//这样的使命，就避过了阻止相关类去打上CLASS_ISPREVERIFIED标志和还有在热修复时动态改变
//BaseDexClassLoader对象间接引用的dexElements.
//所以插件化比热修复简单，热修复是在插件化的基础上在进行替换旧的Bug类。
//热修复原理：
//资源修复：
//很多热修复框架的资源修复参考了Instant Run的资源修复的原理。
//传统编译部署流程如下：
//Instant Run编译部署流程如下：
//Hot Swap：修改一个现有方法中的代码时会采用Hot Swap。
//Warm Swap：修改或删除一个现有的资源文件时会采用Warm Swap。
//Cold Swap：有很多情况，如添加、删除或修改一个字段和方法、添加一个类等。
//Instant Run中的资源热修复流程：
//1、创建新的AssetManager，通过反射调用addAssetPath方法加载外部的资源，这样新创建的
//AssetManager就含有了外部资源。
//2、将AssetManager类型的mAssets字段的引用全部替换为新创建的AssetManager。
//代码修复：
//1、类加载方案：
//65536限制：
//65536的主要原因是DVM Bytecode的限制，DVM指令集的方法调用指令invoke-kind索引为16bits，最
//多能引用65535个方法。
//LinearAlloc限制：
//DVM中的LinearAlloc是一个固定的缓存区，当方法数超过了缓存区的大小时会报错。Dex分包方案主要做的是在打包时将应用代码分成多个Dex，将应用启动时必须用到的类和这些类的直
//接引用类放到Dex中，其他代码放到次Dex中。当应用启动时先加载主Dex，等到应用启动后再动态地
//加载次Dex，从而缓解了主Dex的65536限制和LinearAlloc限制。
//加载流程：
//根据dex文件的查找流程，我们将有Bug的类Key.class进行修改，再将Key.class打包成包含dex的
//补丁包Patch.jar，放在Element数组dexElements的第一个元素，这样会首先找到Patch.dex中的
//Key.class去替换之前存在Bug的Key.class，排在数组后面的dex文件中存在Bug的Key.class根据
//ClassLoader的双亲委托模式就不会被加载。
//类加载方案需要重启App后让ClassLoader重新加载新的类，为什么需要重启呢？
//这是因为类是无法被卸载的，要想重新加载新的类就需要重启App，因此采用类加载方案的热修复
//框架是不能即时生效的。
//各个热修复框架的实现细节差异：
//QQ空间的超级补丁和Nuwa是按照上面说的将补丁包放在Element数组的第一个元素得到优先加
//载。
//微信的Tinker将新旧APK做了diff，得到path.dex，再将patch.dex与手机中APK的classes.dex做合
//并，生成新的classes.dex，然后在运行时通过反射将classes.dex放在Elements数组的第一个元
//素。
//饿了么的Amigo则是将补丁包中每个dex对应的Elements取出来，之后组成新的Element数组，在
//运行时通过反射用新的Elements数组替换掉现有的Elements数组。
//2、底层替换方案：
//当我们要反射Key的show方法，会调用
//Key.class.getDeclaredMethod("show").invoke(Key.class.newInstance());，最终会在native层将传入
//的javaMethod在ART虚拟机中对应一个ArtMethod指针，ArtMethod结构体中包含了Java方法的所有信
//息，包括执行入口、访问权限、所属类和代码执行地址等。
//替换ArtMethod结构体中的字段或者替换整个ArtMethod结构体，这就是底层替换方案。
//AndFix采用的是替换ArtMethod结构体中的字段，这样会有兼容性问题，因为厂商可能会修改
//ArtMethod结构体，导致方法替换失败。
//Sophix采用的是替换整个ArtMethod结构体，这样不会存在兼容问题。
//底层替换方案直接替换了方法，可以立即生效不需要重启。采用底层替换方案主要是阿里系为主，包括
//AndFix、Dexposed、阿里百川、Sophix。
//3、Instant Run方案：
//什么是ASM？
//ASM是一个java字节码操控框架，它能够动态生成类或者增强现有类的功能。ASM可以直接产生class文
//件，也可以在类被加载到虚拟机之前动态改变类的行为。
//Instant Run在第一次构建APK时，使用ASM在每一个方法中注入了类似的代码逻辑：当$change不为
//null时，则调用它的access$dispatch方法，参数为具体的方法名和方法参数。当MainActivity的
//onCreate方法做了修改，就会生成替换类MainActivity$override，这个类实现了IncrementalChange
//接口，同时也会生成一个AppPatchesLoaderImpl类，这个类的getPatchedClasses方法会返回被修改
//的类的列表（里面包含了MainActivity），根据列表会将MainActivity的$change设置为
//MainActivity$override。最后这个$change就不会为null，则会执行MainActivity$override的
//access$dispatch方法，最终会执行onCreate方法，从而实现了onCreate方法的修改。
//借鉴Instant Run原理的热修复框架有Robust和Aceso。动态链接库修复：
//重新加载so。
//加载so主要用到了System类的load和loadLibrary方法，最终都会调用到nativeLoad方法。其会调用
//JavaVMExt的LoadNativeLibrary函数来加载so。
//so修复主要有两个方案：
//1、将so补丁插入到NativeLibraryElement数组的前部，让so补丁的路径先被返回和加载。
//2、调用System的load方法来接管so的加载入口。
//为什么选用插件化？
//在Android传统开发中，一旦应用的代码被打包成APK并被上传到各个应用市场，我们就不能修改应用
//的源码了，只能通过服务器来控制应用中预留的分支代码。但是很多时候我们无法预知需求和突然发生
//的情况，也就不能提前在应用代码中预留分支代码，这时就需要采用动态加载技术，即在程序运行时，
//动态加载一些程序中原本不存在的可执行文件并运行这些文件里的代码逻辑。其中可执行文件包括动态
//链接库so和dex相关文件（dex以及包含dex的jar/apk文件）。随着应用开发技术和业务的逐步发展，
//动态加载技术派生出两个技术：热修复和插件化。其中热修复技术主要用来修复Bug，而插件化技术则
//主要用于解决应用越来越庞大以及功能模块的解耦。详细点说，就是为了解决以下几种情况：
//1、业务复杂、模块耦合：随着业务越来越复杂，应用程序的工程和功能模块数量会越来越多，一
//个应用可能由几十甚至几百人来协同开发，其中的一个工程可能就由一个小组来进行开发维护，如
//果功能模块间的耦合度较高，修改一个模块会影响其它功能模块，势必会极大地增加沟通成本。
//2、应用间的接入：当一个应用需要接入其它应用时，如淘宝，为了将流量引流到其它的淘宝应用
//如：飞猪旅游、口碑外卖、聚划算等等应用，如使用常规技术有两个问题：可能要维护多个版本的
//问题或单个应用体积将会非常庞大的问题。
//3、65536限制，内存占用大。
//插件化的思想：
//安装的应用可以理解为插件，这些插件可以自由地进行插拔。
//插件化的定义：
//插件一般是指经过处理的APK，so和dex等文件，插件可以被宿主进行加载，有的插件也可以作为APK
//独立运行。
//将一个应用按照插件的方式进行改造的过程就叫作插件化。
//插件化的优势：
//低耦合
//应用间的接入和维护更便捷，每个应用团队只需要负责自己的那一部分。
//应用及主dex的体积也会相应变小，间接地避免了65536限制。
//第一次加载到内存的只有淘宝客户端，当使用到其它插件时才会加载相应插件到内存，以减少内存
//占用。
//插件化框架对比：
//最早的插件化框架：2012年大众点评的屠毅敏就推出了AndroidDynamicLoader框架。
//目前主流的插件化方案有滴滴任玉刚的VirtualApk、360的DroidPlugin、RePlugin、Wequick的
//Small框架。
//如果加载的插件不需要和宿主有任何耦合，也无须和宿主进行通信，比如加载第三方App，那么推
//荐使用RePlugin，其他情况推荐使用VirtualApk。由于VirtualApk在加载耦合插件方面是插件化框架的首选，具有普遍的适用性，因此有必要对它的源码进行了解。
//插件化原理：
//Activity插件化：
//主要实现方式有三种：
//反射：对性能有影响，主流的插件化框架没有采用此方式。
//接口：dynamic-load-apk采用。
//Hook：主流。
//Hook实现方式有两种：Hook IActivityManager和Hook Instrumentation。主要方案就是先用一个在
//AndroidManifest.xml中注册的Activity来进行占坑，用来通过AMS的校验，接着在合适的时机用插件
//Activity替换占坑的Activity。
//Hook IActivityManager：
//1、占坑、通过校验：
//在Android 7.0和8.0的源码中IActivityManager借助了Singleton类实现单例，而且该单例是静态的，因
//此IActivityManager是一个比较好的Hook点。
//接着，定义替换IActivityManager的代理类IActivityManagerProxy，由于Hook点IActivityManager是
//一个接口，建议这里采用动态代理。
//拦截startActivity方法，获取参数args中保存的Intent对象，它是原本要启动插件TargetActivity的
//Intent。
//新建一个subIntent用来启动StubActivity，并将前面得到的TargetActivity的Intent保存到
//subIntent中，便于以后还原TargetActivity。
//最后，将subIntent赋值给参数args，这样启动的目标就变为了StubActivity，用来通过AMS的校
//验。
//然后，用代理类IActivityManagerProxy来替换IActivityManager。
//当版本大于等于26时，使用反射获取ActivityManager的IActivityManagerSingleton字段，小于时
//则获取ActivityManagerNative中的gDefault字段。
//然后，通过反射获取对应的Singleton实例，从上面得到的2个字段中拿到对应的
//IActivityManager。
//最后，使用Proxy.newProxyInstance()方法动态创建代理类IActivityManagerProxy，用
//IActivityManagerProxy来替换IActivityManager。
//2、还原插件Activity：
//前面用占坑Activity通过了AMS的校验，但是我们要启动的是插件TargetActivity，还需要用插件
//TargetActivity来替换占坑的SubActivity，替换时机为图中步骤2之后。
//在ActivityThread的H类中重写的handleMessage方法会对LAUNCH_ACTIVITY类型的消息进行处
//理，最终会调用Activity的onCreate方法。在Handler的dispatchMessage处理消息的这个方法
//中，看到如果Handelr的Callback类型的mCallBack不为null，就会执行mCallback的
//handleMessage方法，因此mCallback可以作为Hook点。我们可以用自定义的Callback来替换
//mCallback。
//自定义的Callback实现了Handler.Callback，并重写了handleMessage方法，当收到消息的类型为
//LAUNCH_ACTIVITY时，将启动SubActivity的Intent替换为启动TargetActivity的Intent。然后使用反射
//将Handler的mCallback替换为自定义的CallBack即可。使用时则在application的attachBaseContext
//方法中进行hook即可。
//3、插件Activity的生命周期：AMS和ActivityThread之间的通信采用了token来对Activity进行标识，并且此后的Activity的生命周
//期处理也是根据token来对Activity进行标识的，因为我们在Activity启动时用插件TargetActivity替
//换占坑SubActivity，这一过程在performLaunchActivity之前，因此performLaunchActivity的
//r.token就是TargetActivity。所以TargetActivity具有生命周期。
//Hook Instrumentation：
//Hook Instrumentation实现同样也需要用到占坑Activity，与Hook IActivity实现不同的是，用占坑
//Activity替换插件Activity以及还原插件Activity的地方不同。
//分析：在Activity通过AMS校验前，会调用Activity的startActivityForResult方法，其中调用了
//Instrumentation的execStartActivity方法来激活Activity的生命周期。并且在ActivityThread的
//performLaunchActivity中使用了mInstrumentation的newActivity方法，其内部会用类加载器来创建
//Activity的实例。
//方案：在Instrumentation的execStartActivity方法中用占坑SubActivity来通过AMS的验证，在
//Instrumentation的newActivity方法中还原TargetActivity，这两部操作都和Instrumentation有关，因
//此我们可以用自定义的Instumentation来替换掉mInstrumentation。具体为：
//首先检查TargetActivity是否已经注册，如果没有则将TargetActivity的ClassName保存起来用于后
//面还原。接着把要启动的TargetActivity替换为StubActivity，最后通过反射调用execStartActivity
//方法，这样就可以用StubActivity通过AMS的验证。
//在newActivity方法中创建了此前保存的TargetActivity，完成了还原TargetActivity。最后使用反射
//用InstrumentationProxy替换mInstumentation。
//资源插件化：
//资源的插件化和热修复的资源修复都借助了AssetManager。
//资源的插件化方案主要有两种：
//1、合并资源方案，将插件的资源全部添加到宿主的Resources中，这种方案插件可以访问宿主的
//资源。
//2、构建插件资源方案，每个插件都构造出独立的Resources，这种方案插件不可以访问宿主资
//源。
//so的插件化：
//so的插件化方案和so热修复的第一种方案类似，就是将so插件插入到NativelibraryElement数组中，并
//且将存储so插件的文件添加到nativeLibraryDirectories集合中就可以了。
//插件的加载机制方案：
//1、Hook ClassLoader。
//2、委托给系统的ClassLoader帮忙加载。
//2、模块化和组件化
//模块化的好处
//https://www.jianshu.com/p/376ea8a19a17
//分析现有的组件化方案：
//很多大厂的组件化方案是以 多工程 + 多 Module 的结构(微信, 美团等超级 App 更是以 多工程 + 多
//Module + 多 P 工程(以页面为单元的代码隔离方式) 的三级工程结构), 使用 Git Submodule 创建多个子
//仓库管理各个模块的代码, 并将各个模块的代码打包成 AAR 上传至私有 Maven 仓库使用远程版本号依
//赖的方式进行模块间代码的隔离。组件化开发的好处：
//避免重复造轮子，可以节省开发和维护的成本。
//可以通过组件和模块为业务基准合理地安排人力，提高开发效率。
//不同的项目可以共用一个组件或模块，确保整体技术方案的统一性。
//为未来插件化共用同一套底层模型做准备。
//跨组件通信：
//跨组件通信场景：
//第一种是组件之间的页面跳转 (Activity 到 Activity, Fragment 到 Fragment, Activity 到 Fragment,
//Fragment 到 Activity) 以及跳转时的数据传递 (基础数据类型和可序列化的自定义类类型)。
//第二种是组件之间的自定义类和自定义方法的调用(组件向外提供服务)。
//跨组件通信方案分析：
//第一种组件之间的页面跳转不需要过多描述了, 算是 ARouter 中最基础的功能, API 也比较简单, 跳
//转时想传递不同类型的数据也提供有相应的 API。
//第二种组件之间的自定义类和自定义方法的调用要稍微复杂点, 需要 ARouter 配合架构中的 公共
//服务(CommonService) 实现：
//提供服务的业务模块：
//在公共服务(CommonService) 中声明 Service 接口 (含有需要被调用的自定义方法), 然后在自己的模块
//中实现这个 Service 接口, 再通过 ARouter API 暴露实现类。
//使用服务的业务模块：
//通过 ARouter 的 API 拿到这个 Service 接口(多态持有, 实际持有实现类), 即可调用 Service 接口中声明
//的自定义方法, 这样就可以达到模块之间的交互。
//此外，可以使用 AndroidEventBus 其独有的 Tag, 可以在开发时更容易定位发送事件和接受事件的代码,
//如果以组件名来作为 Tag 的前缀进行分组, 也可以更好的统一管理和查看每个组件的事件, 当然也不建议
//大家过多使用 EventBus。
//如何管理过多的路由表？
//RouterHub 存在于基础库, 可以被看作是所有组件都需要遵守的通讯协议, 里面不仅可以放路由地址常
//量, 还可以放跨组件传递数据时命名的各种 Key 值, 再配以适当注释, 任何组件开发人员不需要事先沟通
//只要依赖了这个协议, 就知道了各自该怎样协同工作, 既提高了效率又降低了出错风险, 约定的东西自然
//要比口头上说的强。
//Tips: 如果您觉得把每个路由地址都写在基础库的 RouterHub 中, 太麻烦了, 也可以在每个组件内部建立
//一个私有 RouterHub, 将不需要跨组件的路由地址放入私有 RouterHub 中管理, 只将需要跨组件的路由
//地址放入基础库的公有 RouterHub 中管理, 如果您不需要集中管理所有路由地址的话, 这也是比较推荐
//的一种方式。
//ARouter路由原理：
//ARouter维护了一个路由表Warehouse，其中保存着全部的模块跳转关系，ARouter路由跳转实际上还
//是调用了startActivity的跳转，使用了原生的Framework机制，只是通过apt注解的形式制造出跳转规
//则，并人为地拦截跳转和设置跳转条件。
//多模块开发的时候不同的负责人可能会引入重复资源，相同的字符串，相同的icon
//等但是文件名并不一样，怎样去重？3、gradle
//gradle熟悉么，自动打包知道么？
//如何加快 Gradle 的编译速度？
//Gradle的Flavor能否配置sourceset？
//Gradle生命周期
//五、设计模式与架构设计
//1、设计模式
//谈谈你对Android设计模式的理解
//项目中常用的设计模式
//手写生产者/消费者模式
//2、架构设计
//MVC MVP MVVM原理和区别？
//架构设计的目的
//通过设计是模块程序化，从而做到高内聚低耦合，让开发者能更专注于功能实现本身，提供程序开发效
//率、更容易进行测试、维护和定位问题等等。而且，不同的规模的项目应该选用不同的架构设计。
//MVC
//MVC是模型(model)－视图(view)－控制器(controller)的缩写，其中M层处理数据，业务逻辑等；V层处
//理界面的显示结果；C层起到桥梁的作用，来控制V层和M层通信以此来达到分离视图显示和业务逻辑
//层。在Android中的MVC划分是这样的：
//视图层(View)：一般采用XML文件进行界面的描述，也可以在界面中使用动态布局的方式。
//控制层(Controller)：由Activity承担。
//模型层(Model)：数据库的操作、对网络等的操作，复杂业务计算等等。
//MVC缺点
//在Android开发中，Activity并不是一个标准的MVC模式中的Controller，它的首要职责是加载应用的布
//局和初始化用户界面，并接受和处理来自用户的操作请求，进而作出响应。随着界面及其逻辑的复杂度
//不断提升，Activity类的职责不断增加，以致变得庞大臃肿。
//MVP
//MVP框架由3部分组成：View负责显示，Presenter负责逻辑处理，Model提供数据。
//View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity)。
//Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合)。
//Presenter:作为View与Model交互的中间纽带，处理与用户交互的逻辑。
//View interface:需要View实现的接口，View通过View interface与Presenter进行交互，降低耦
//合，方便使用MOCK对Presenter进行单元测试。
//MVP的Presenter是框架的控制者，承担了大量的逻辑操作，而MVC的Controller更多时候承担一种转
//发的作用。因此在App中引入MVP的原因，是为了将此前在Activty中包含的大量逻辑操作放到控制层
//中，避免Activity的臃肿。MVP与MVC的主要区别:
//1、（最主要区别）View与Model并不直接交互，而是通过与Presenter交互来与Model间接交
//互。而在MVC中View可以与Model直接交互。
//2、Presenter与View的交互是通过接口来进行的，更有利于添加单元测试。
//MVP的优点
//1、模型与视图完全分离，我们可以修改视图而不影响模型。
//2、可以更高效地使用模型，因为所有的交互都发生在一个地方——Presenter内部。
//3、我们可以将一个Presenter用于多个视图，而不需要改变Presenter的逻辑。这个特性非常的有
//用，因为视图的变化总是比模型的变化频繁。
//4、如果我们把逻辑放在Presenter中，那么我们就可以脱离用户接口来测试这些逻辑（单元测
//试）。
//UI层一般包括Activity，Fragment，Adapter等直接和UI相关的类，UI层的Activity在启动之后实例化相
//应的Presenter，App的控制权后移，由UI转移到Presenter，两者之间的通信通过BroadCast、
//Handler、事件总线机制或者接口完成，只传递事件和结果。
//MVP的执行流程：首先V层通知P层用户发起了一个网络请求，P层会决定使用负责网络相关的M层去发
//起请求网络，最后，P层将完成的结果更新到V层。
//MVP的变种：Passive View
//View直接依赖Presenter，但是Presenter间接依赖View，它直接依赖的是View实现的接口。相对于
//View的被动，那Presenter就是主动的一方。对于Presenter的主动，有如下的理解：
//Presenter是整个MVP体系的控制中心，而不是单纯的处理View请求的人。
//View仅仅是用户交互请求的汇报者，对于响应用户交互相关的逻辑和流程，View不参与决策，真
//正的决策者是Presenter。
//View向Presenter发送用户交互请求应该采用这样的口吻：“我现在将用户交互请求发送给你，你
//看着办，需要我的时候我会协助你”。
//对于绑定到View上的数据，不应该是View从Presenter上“拉”回来的，应该是Presenter主动“推”给
//View的。（这里借鉴了IOC做法）
//View尽可能不维护数据状态，因为其本身仅仅实现单纯的、独立的UI操作；Presenter才是整个体
//系的协调者，它根据处理用于交互的逻辑给View和Model安排工作。
//MVP架构存在的问题与解决办法
//1、加入模板方法
//将逻辑操作从V层转移到P层后，可能有一些Activity还是比较膨胀，此时，可以通过继承BaseActivity的
//方式加入模板方法。注意，最好不要超过3层继承。
//2、Model内部分层
//模型层（Model）中的整体代码量是最大的，此时可以进行模块的划分和接口隔离。
//3、使用中介者和代理
//在UI层和Presenter之间设置中介者Mediator，将例如数据校验、组装在内的轻量级逻辑操作放在
//Mediator中；在Presenter和Model之间使用代理Proxy；通过上述两者分担一部分Presenter的逻辑操
//作，但整体框架的控制权还是在Presenter手中。
//MVVMMVVM可以算是MVP的升级版，其中的VM是ViewModel的缩写，ViewModel可以理解成是View的数据
//模型和Presenter的合体，ViewModel和View之间的交互通过Data Binding完成，而Data Binding可以
//实现双向的交互，这就使得视图和控制层之间的耦合程度进一步降低，关注点分离更为彻底，同时减轻
//了Activity的压力。
//MVC->MVP->MVVM演进过程
//MVC -> MVP -> MVVM 这几个软件设计模式是一步步演化发展的，MVVM 是从 MVP 的进一步发展与规
//范，MVP 隔离了MVC中的 M 与 V 的直接联系后，靠 Presenter 来中转，所以使用 MVP 时 P 是直接调
//用 View 的接口来实现对视图的操作的，这个 View 接口的东西一般来说是 showData、showLoading
//等等。M 与 V已经隔离了，方便测试了，但代码还不够优雅简洁，所以 MVVM 就弥补了这些缺陷。在
//MVVM 中就出现的 Data Binding 这个概念，意思就是 View 接口的 showData 这些实现方法可以不写
//了，通过 Binding 来实现。
//三种模式的相同点
//M层和V层的实现是一样的。
//三种模式的不同点
//三者的差异在于如何粘合View和Model，实现用户的交互操作以及变更通知。
//Controller：接收View的命令，对Model进行操作，一个Controller可以对应多个View。
//Presenter：Presenter与Controller一样，接收View的命令，对Model进行操作；与Controller不
//同的是Presenter会反作用于View，Model的变更通知首先被Presenter获得，然后Presenter再去
//更新View。通常一个Presenter只对应于一个View。据Presenter和View对逻辑代码分担的程度不
//同，这种模式又有两种情况：普通的MVP模式和Passive View模式。
//ViewModel：注意这里的“Model”指的是View的Model，跟MVVM中的一个Model不是一回事。所
//谓View的Model就是包含View的一些数据属性和操作的这么一个东东，这种模式的关键技术就是
//数据绑定（data binding），View的变化会直接影响ViewModel，ViewModel的变化或者内容也
//会直接体现在View上。这种模式实际上是框架替应用开发者做了一些工作，开发者只需要较少的
//代码就能实现比较复杂的交互。
//补充：基于AOP的架构设计
//AOP(Aspect-Oriented Programming, 面向切面编程)，诞生于上个世纪90年代，是对OOP(ObjectOriented Programming, 面向对象编程)的补充和完善。OOP引入封装、继承和多态性等概念来建立一
//种从上道下的对象层次结构，用以模拟公共行为的一个集合。当我们需要为分散的对象引入公共行为的
//时候，即定义从左到右的关系时，OOP则显得无能为力。例如日志功能。日志代码往往水平地散布在所
//有对象层次中，而与它所散布到的对象的核心功能毫无关系。对于其他类型的代码，如安全性、异常处
//理和透明的持续性也是如此。这种散布在各处的无关的代码被称为横切（Cross-Cutting）代码，在
//OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。而AOP技术则恰恰相反，它利用一
//种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模
//块，并将其名为“Aspect”，即方面。所谓“方面”，简单地说，就是将那些与业务无关，却为业务模块所
//共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可
//操作性和可维护性。
//在Android App中的横切关注点有Http, SharedPreferences, Log, Json, Xml, File, Device, System, 格式
//转换等。Android App的需求差别很大，不同的需求横切关注点必然是不一样的。一般的App工程中应
//该有一个Util Package来存放相关的切面操作，在项目多了之后可以将其中使用较多的Util封装为一个
//Jar包/aar文件/远程依赖的方式供工程调用。
//在使用MVP和AOP对App进行纵向和横向的切割之后，能够使得App整体的结构更清晰合理，避免局部
//的代码臃肿，方便开发、测试以及后续的维护。这样纵，横两次对于App代码的分割已经能使得程序不
//会过多堆积在一个Java文件里，但靠一次开发过程就写出高质量的代码是很困难的，趁着项目的间歇
//期，对代码进行重构很有必要。最后的建议
//如果“从零开始”，用什么设计架构的问题属于想得太多做得太少的问题。
//从零开始意味着一个项目的主要技术难点是基本功能实现。当每一个功能都需要考虑如何做到的时候，
//我觉得一般人都没办法考虑如何做好。
//因为，所有的优化都是站在最上层进行统筹规划。在这之前，你必须对下层的每一个模块都非常熟悉，
//进而提炼可复用的代码、规划逻辑流程。
//MVC的情况下怎么把Activity的C和V抽离？
//MVP 架构中 Presenter 定义为接口有什么好处；
//MVP如何管理Presenter的生命周期，何时取消网络请求？
//aop思想
//Fragment如果在Adapter中使用应该如何解耦？
//项目框架里有没有Base类，BaseActivity和BaseFragment这种封装导致的问
//题，以及解决方法？
//设计一个音乐播放界面，你会如何实现，用到那些类，如何设计，如何定义接口，
//如何与后台交互，如何缓存与下载，如何优化(15分钟时间)
//从0设计一款App整体架构，如何去做？
//说一款你认为当前比较火的应用并设计(比如：直播APP，P2P金融，小视频等)
//实现一个库，完成日志的实时上报和延迟上报两种功能，该从哪些方面考虑？
//六、其它高频面试题
//1、如何保证一个后台服务不被杀死？（相同问题：如何保证service
//在后台不被kill？）比较省电的方式是什么？
//保活方案
//1、AIDL方式单进程、双进程方式保活Service。（基于onStartCommand() return START_STICKY）
//START_STICKY 在运行onStartCommand后service进程被kill后，那将保留在开始状态，但是不保留那
//些传入的intent。不久后service就会再次尝试重新创建，因为保留在开始状态，在创建 service后将保
//证调用onstartCommand。如果没有传递任何开始命令给service，那将获取到null的intent。
//除了华为此方案无效以及未更改底层的厂商不起作用外（START_STICKY字段就可以保持Service不被
//杀）。此方案可以与其他方案混合使用
//2、降低oom_adj的值（提升service进程优先级）：
//Android中的进程是托管的，当系统进程空间紧张的时候，会依照优先级自动进行进程的回收。
//Android将进程分为6个等级,它们按优先级顺序由高到低依次是:
//1.前台进程 (Foreground process)
//2.可见进程 (Visible process)
//3.服务进程 (Service process)
//4.后台进程 (Background process)5.空进程 (Empty process)
//当service运行在低内存的环境时，将会kill掉一些存在的进程。因此进程的优先级将会很重要，可以使
//用startForeground 将service放到前台状态。这样在低内存时被kill的几率会低一些。
//常驻通知栏（可通过启动另外一个服务关闭Notification，不对oom_adj值有影响）。
//使用”1像素“的Activity覆盖在getWindow()的view上。
//此方案无效果
//循环播放无声音频（黑科技，7.0下杀不掉）。
//成功对华为手机保活。小米8下也成功突破20分钟
//3、监听锁屏广播：使Activity始终保持前台。
//4、使用自定义锁屏界面：覆盖了系统锁屏界面。
//5、通过android:process属性来为Service创建一个进程。
//6、跳转到系统白名单界面让用户自己添加app进入白名单。
//复活方案
//1、onDestroy方法里重启service
//service + broadcast 方式，就是当service走onDestory的时候，发送一个自定义的广播，当收到广播
//的时候，重新启动service。
//2、JobScheduler：原理类似定时器，5.0,5.1,6.0作用很大，7.0时候有一定影响（可以在电源管理中给
//APP授权）。
//只对5.0，5.1、6.0起作用。
//3、推送互相唤醒复活：极光、友盟、以及各大厂商的推送。
//4、同派系APP广播互相唤醒：比如今日头条系、阿里系。
//此外还可以监听系统广播判断Service状态，通过系统的一些广播，比如：手机重启、界面唤醒、应用状
//态改变等等监听并捕获到，然后判断我们的Service是否还存活。
//结论：高版本情况下可以使用弹出通知栏、双进程、无声音乐提高后台服务的保活
//概率。
//2、Android动画框架实现原理。
//Animation 框架定义了透明度，旋转，缩放和位移几种常见的动画，而且控制的是整个View。实现原
//理：
//每次绘制视图时，View 所在的 ViewGroup 中的 drawChild 函数获取该View 的 Animation 的
//Transformation 值，然后调用canvas.concat(transformToApply.getMatrix())，通过矩阵运算完成动画
//帧，如果动画没有完成，继续调用 invalidate() 函数，启动下次绘制来驱动动画，动画过程中的帧之间
//间隙时间是绘制函数所消耗的时间，可能会导致动画消耗比较多的CPU资源，最重要的是，动画改变的
//只是显示，并不能响应事件。
//3、Activity-Window-View三者的差别？
//Activity像一个工匠（控制单元），Window像窗户（承载模型），View像窗花（显示视图）
//LayoutInflater像剪刀，Xml配置像窗花图纸。
//在Activity中调用attach，创建了一个Window，
//创建的window是其子类PhoneWindow，在attach中创建PhoneWindow。在Activity中调用setContentView(R.layout.xxx)，
//其中实际上是调用的getWindow().setContentView()，
//内部调用了PhoneWindow中的setContentView方法。
//创建ParentView：
//作为ViewGroup的子类，实际是创建的DecorView(作为FramLayout的子类），
//将指定的R.layout.xxx进行填充，
//通过布局填充器进行填充【其中的parent指的就是DecorView】，
//调用ViewGroup的removeAllView()，先将所有的view移除掉，添加新的view：addView()。
//参考文章
//4、低版本SDK如何实现高版本api？
//1、在使用了高版本API的方法前面加一个 @TargetApi(API号)。
//2、在代码上用版本判断来控制不同版本使用不同的代码。
//5、说说你对Context的理解？
//6、Android的生命周期和启动模式
//由A启动B Activity，A为栈内复用模式，B为标准模式，然后再次启动A或者杀死
//B，说说A，B的生命周期变化，为什么？
//Activity的启动模式有哪些？栈里是A-B-C，先想直接到A，BC都清理掉，有几种
//方法可以做到？这几种方法产生的结果是有几个A的实例？
//7、ListView和RecyclerView系列
//RecyclerView和ListView有什么区别？局部刷新？前者使用时多重type场景下怎
//么避免滑动卡顿。懒加载怎么实现，怎么优化滑动体验。
//ListView、RecyclerView区别？
//一、使用方面：
//ListView的基础使用：
//继承重写 BaseAdapter 类
//自定义 ViewHolder 和 convertView 一起完成复用优化工作
//RecyclerView 基础使用关键点同样有两点：
//继承重写 RecyclerView.Adapter 和 RecyclerView.ViewHolder
//设置布局管理器，控制布局效果
//RecyclerView 相比 ListView 在基础使用上的区别主要有如下几点：
//ViewHolder 的编写规范化了
//RecyclerView 复用 Item 的工作 Google 全帮你搞定，不再需要像 ListView 那样自己调用 setTag
//RecyclerView 需要多出一步 LayoutManager 的设置工作
//二、布局方面：
//RecyclerView 支持 线性布局、网格布局、瀑布流布局 三种，而且同时还能够控制横向还是纵向滚动。三、API提供方面：
//ListView 提供了 setEmptyView ，addFooterView 、 addHeaderView.
//RecyclerView 供了 notifyItemChanged 用于更新单个 Item View 的刷新，我们可以省去自己写局部更
//新的工作。
//四、动画效果：
//RecyclerView 在做局部刷新的时候有一个渐变的动画效果。继承 RecyclerView.ItemAnimator 类，并
//实现相应的方法，再调用 RecyclerView的 setItemAnimator(RecyclerView.ItemAnimator animator)
//方法设置完即可实现自定义的动画效果。
//五、监听 Item 的事件：
//ListView 提供了单击、长按、选中某个 Item 的监听设置。
//RecyclerView与ListView缓存机制的不同
//想改变listview的高度，怎么做？
//listview跟recyclerview上拉加载的时候分别应该如何处理？
//如何自己实现RecyclerView的侧滑删除？
//RecyclerView的ItemTouchHelper的实现原理
//8、如何实现一个推送，消息推送原理？推送到达率的问题？
//一：客户端不断的查询服务器，检索新内容，也就是所谓的pull 或者轮询方式。
//二：客户端和服务器之间维持一个TCP/IP长连接，服务器向客户端push。
//https://blog.csdn.net/clh604/article/details/20167263
//https://www.jianshu.com/p/45202dcd5688
//9、动态权限系列。
//动态权限适配方案，权限组的概念
//Runtime permission，如何把一个预置的app默认给它权限？不要授权。
//10、自定义View系列。
//Canvas的底层机制，绘制框架，硬件加速是什么原理，canvas lock的缓冲区是
//怎么回事？
//双指缩放拖动大图
//TabLayout中如何让当前标签永远位于屏幕中间
//TabLayout如何设置指示器的宽度包裹内容？
//自定义View如何考虑机型适配？
//合理使用warp_content，match_parent。
//尽可能地使用RelativeLayout。针对不同的机型，使用不同的布局文件放在对应的目录下，android会自动匹配。
//尽量使用点9图片。
//使用与密度无关的像素单位dp，sp。
//引入android的百分比布局。
//切图的时候切大分辨率的图，应用到布局当中，在小分辨率的手机上也会有很好的显示效果。
//11、对谷歌新推出的Room架构。
//12、没有给权限如何定位，特定机型定位失败，如何解决?
//13、Debug跟Release的APK的区别？
//14、android文件存储，各版本存储位置的权限控制的演进，外部存
//储，内部存储
//15、有什么提高编译速度的方法？
//16、Scroller原理。
//Scroller执行流程里面的三个核心方法
//1、在mScroller.startScroll()中为滑动做了一些初始化准备，比如：起始坐标，滑动的距离和方向以及
//持续时间(有默认值)，动画开始时间等。
//2、mScroller.computeScrollOffset()方法主要是根据当前已经消逝的时间来计算当前的坐标点。因为
//在mScroller.startScroll()中设置了动画时间，那么在computeScrollOffset()方法中依据已经消逝的时间
//就很容易得到当前时刻应该所处的位置并将其保存在变量mCurrX和mCurrY中。除此之外该方法还可判
//断动画是否已经结束。
//17、Hybrid系列。
//webwiew了解？怎么实现和javascript的通信？相互双方的通信。
//@JavascriptInterface在？版本有bug，除了这个还有其他调用android方法的方
//案吗？
//Android中Java和JavaScript交互
//mScroller.startScroll()；
//mScroller.computeScrollOffset()；
//view.computeScroll()；
//webView.addJavaScriptInterface(new Object(){xxx}, "xxx");
//1 答
//案：可以使用WebView控件执行JavaScript脚本，并且可以在JavaScript中执行Java代码。要想让
//WebView控件执行JavaScript，需要调用WebSettings.setJavaScriptEnabled方法，代码如下：
//WebView webView = (WebView)findViewById(R.id.webview);
//WebSettings webSettings = webView.getSettings();
////设置WebView支持JavaScript
//webSettings.setJavaScriptEnabled(true);
//webView.setWebChromeClient(new WebChromeClient());18、如果在当前线程内使用Handler postdelayed 两个消息，一个
//延迟5s，一个延迟10s，然后使当前线程sleep 5秒，以上消息的执行
//时间会如何变化？
//答：照常执行
//扩展：sleep时间<=5 对两个消息无影响，5< sleep时间 <=10 对第一个消息有影响，第一个消息会延迟
//到sleep后执行，sleep时间>10 对两个时间都有影响，都会延迟到sleep后执行。
//19、Android中进程内存的分配，能不能自己分配定额内存？
//20、下拉状态栏是不是影响activity的生命周期，如果在onStop的
//时候做了网络请求，onResume的时候怎么恢复
//21、Android长连接，怎么处理心跳机制。
//长连接：长连接是建立连接之后, 不主动断开. 双方互相发送数据, 发完了也不主动断开连接, 之后有需要
//发送的数据就继续通过这个连接发送.
//心跳包：其实主要是为了防止NAT超时，客户端隔一段时间就主动发一个数据，探测连接是否断开。
//服务器处理心跳包：假如客户端心跳间隔是固定的, 那么服务器在连接闲置超过这个时间还没收到心跳
//时, 可以认为对方掉线, 关闭连接. 如果客户端心跳会动态改变, 应当设置一个最大值, 超过这个最大值才
//认为对方掉线. 还有一种情况就是服务器通过TCP连接主动给客户端发消息出现写超时, 可以直接认为对
//方掉线.
//22、CrashHandler实现原理？
//JavaScript调用Java方法需要使用WebView.addJavascriptInterface方法设置JavaScript调用的
//Java方法，代码如下：
//webView.addJavascriptInterface(new Object()
//{
////JavaScript调用的方法
//public String process(String value)
//{
////处理代码
//return result;
//}
//}, "demo"); //demo是Java对象映射到JavaScript中的对象名
//可以使用下面的JavaScript代码调用process方法，代码如下：
//<script language="javascript">
//function search()
//{
////调用searchWord方法
//result.innerHTML = "<font color='red'>" + window.demo.process('data') +
//"</font>";
//}
//获取app crash的信息保存在本地然后在下一次打开app的时候发送到服务器。23、SurfaceView和View的最本质的区别？
//SurfaceView是在一个新起的单独线程中可以重新绘制画面，而view必须在UI的主线程中更新画面。
//在UI的主线程中更新画面可能会引发问题，比如你更新的时间过长，那么你的主UI线程就会被你正在画
//的函数阻塞。那么将无法响应按键、触屏等消息。当使用SurfaceView由于是在新的线程中更新画面所
//以不会阻塞你的UI主线程。但这也带来了另外一个问题，就是事件同步。比如你触屏了一下，你需要在
//SurfaceView中的thread处理，一般就需要有一个event queue的设计来保存touchevent，这会稍稍复
//杂一点，因为涉及到线程安全。
//24、Android程序运行时权限与文件系统权限
//1、Linux 文件系统权限。不同的用户对文件有不同的读写执行权限。在android系统中，system和应用
//程序是分开的，system里的数据是不可更改的。
//2、Android中有3种权限，进程权限UserID，签名，应用申明权限。每次安装时，系统根据包名为应用
//分配唯一的userID，不同的userID运行在不同的进程里，进程间的内存是独立的，不可以相互访问，除
//非通过特定的Binder机制。
//Android提供了如下的一种机制，可以使两个apk打破前面讲的这种壁垒。
//在AndroidManifest.xml中利用sharedUserId属性给不同的package分配相同的userID，通过这样做，
//两个package可以被当做同一个程序，系统会分配给两个程序相同的UserID。当然，基于安全考虑，两
//个package需要有相同的签名，否则没有验证也就没有意义了。
//25、曲面屏的适配。
//26、TextView调用setText方法的内部执行流程。
//27、怎么控制另外一个进程的View显示（RemoteView）？
//28、如何实现右滑finish activity？
//29、如何在整个系统层面实现界面的圆角效果。（即所有的APP打开
//界面都会是圆角）
//30、非UI线程可以更新UI吗?
//可以，当访问UI时，ViewRootImpl会调用checkThread方法去检查当前访问UI的线程是哪个，如果不
//是UI线程则会抛出异常。执行onCreate方法的那个时候ViewRootImpl还没创建，无法去检查当前线
//程.ViewRootImpl的创建在onResume方法回调之后。
//非UI线程是可以刷新UI的，前提是它要拥有自己的ViewRoot,即更新UI的线程和创建ViewRoot的线程是
//同一个，或者在执行checkThread()前更新UI。
//31、如何解决git冲突？
//void checkThread() {
//if (mThread != Thread.currentThread()) {
//throw new CalledFromWrongThreadException(
//"Only the original thread that created a view hierarchy can
//touch its views.");
//}
//}32、单元测试有没有做过，说说熟悉的单元测试框架？
//首先，Android测试主要分为三个方面：
//单元测试（Junit4、Mockito、PowerMockito、Robolectric）
//UI测试（Espresso、UI Automator）
//压力测试（Monkey）
//WanAndroid项目和XXX项目中使用用到了单元测试和部分自动化UI测试，其中单元测试使用的是
//Junit4+Mockito+PowerMockito+Robolectric。下面我分别简单介绍下这些测试框架：
//1、Junit4：
//使用@Test注解指定一个方法为一个测试方法，除此之外，还有如下常用注解@BeforeClass-
//>@Before->@Test->@After->@AfterClass以及@Ignore。
//Junit4的主要测试方法就是断言，即assertEquals()方法。然后，你可以通过实现TestRule接口的方式重
//写apply()方法去自定义Junit Rule，这样就可以在执行测试方法的前后做一些通用的初始化或释放资源
//等工作，接着在想要的测试类中使用@Rule注解声明使用JsonChaoRule即可。（注意被@Rule注解的变
//量必须是final的。最后，我们直接运行对应的单元测试方法或类，如果你想要一键运行项目中所有的单
//元测试类，直接点击运行Gradle Projects下的app/Tasks/verification/test即可，它会在module下的
//build/reports/tests/下生成对应的index.html报告。
//Junit4它的优点是速度快，支持代码覆盖率如jacoco等代码质量的检测工具。缺点就是无法单独对
//Android UI，一些类进行操作，与原生Java有一些差异。
//2、Mockito：
//可以使用mock()方法模拟各种各样的对象，以替代真正的对象做出希望的响应。除此之外，它还有很多
//验证方法调用的方式如Mockit.when(调用方法).thenReturn(验证的返回值)、verfiy(模拟对象).验证方法
//等等。
//这里有一点要补充下：简单的测试会使整体的代码更简洁，更可读、更可维护。如果你不能把测试写的
//很简单，那么请在测试时重构你的代码。
//最后，对于Mockito来说，它的优点是有各种各样的方式去验证"模仿对象"的互动或验证发生的某些行
//为。而它的缺点就是不支持mock匿名类、final类、static方法private方法。
//3、PowerMockito：
//因此，为了解决Mockito的缺陷，PoweMockito出现了，它扩展了Mockito，支持mock匿名类、final
//类、static方法、private方法。只要使用它提供的api如PowerMockito.mockStatic()去mock含静态方法
//或字段的类，PowerMockito.suppress(PowerMockito.method(类.class， 方法名)即可。
//4、Robolectric
//前面3种我们说的都是Java相关的单元测试方法，如果想在Java单元测试里面进行Android单元测试，还
//得使用Robolectric，它提供了一套能运行在JVM的Android代码。它提供了一系列类似
//ShadowToast.getLatestToast()、ShadowApplication.getInstance()这种方式来获取Android平台对应
//的对象。可以看到它的优点就是支持大部分Android平台依赖类的底层引用与模拟。缺点就是在异步测
//试的情况下有些问题，这是可以结合Mockito来将异步转为同步即可解决。
//最后，自动化UI测试项目中我使用的是Expresso，它提供了一系列类似onView().check().perform()的
//方式来实现点击、滑动、检测页面显示等自动化的UI测试效果，这里在我的WanAndroid项目下的
//BasePageTest基类里面封装了一系列通用的方法，有兴趣可以去看看。
//33、实现一个Json解析器(可以通过正则提高速度)。
//34、Jenkins持续集成。35、为什么 Google 会推出Fragment ，有什么好处和用途？ 直接
//用 View 代替不行么？
//36、工作中有没有用过或者写过什么工具？脚本，插件等等；比如：
//多人协同开发可能对一些相同资源都各自放了一份，有没有方法自动
//检测这种重复之类的。
//37、如何绕过9.0限制？
//如何限制？
//1、阻止java反射和JNI。
//2、当获取方法或Field时进行检测。
//3、怎么检测？
//区分出是系统调用还是开发者调用：
//根据堆栈，回溯Class，查看ClassLoader是否是BootStrapClassLoader。
//区分后，再区分是否是hidden api：
//Method，Field都有access_flag，有一些备用字段，hidden信息存储其中。
//如何绕过？
//1、不用反射：
//利用一个fakelib，例如写一个android.app.ActivityThread#currentActivityThread空实现，直接调
//用；
//2、伪装系统调用：
//jni修改一个class的classloder为BootStrapClassLoader，麻烦。
//利用系统方法去反射：
//利用原反射，即：getDeclaredMethod这个方法是系统的方法，通过getDeclaredmethod反射去执行
//hidden api。
//3、修改Method，Field中存储hidden信息的字段：
//利用jni去修改。
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//Android基础面试题 （⭐⭐⭐）
//1、什么是ANR 如何避免它？
//答：在Android上，如果你的应用程序有一段时间响应不够灵敏，系统会向用户显示一个对话框，这个
//对话框称作应
//用程序无响应（ANR：Application NotResponding）对话框。
//用户可以选择让程序继续运行，但是，他们在使用你的
//应用程序时，并不希望每次都要处理这个对话框。因此
//，在程序里对响应性能的设计很重要这样，这样系统就不会显
//示ANR给用户。
//不同的组件发生ANR的时间不一样，Activity是5秒，BroadCastReceiver是10秒，Service是20秒（均为
//前台）。
//如果开发机器上出现问题，我们可以通过查看/data/anr/traces.txt即可，最新的ANR信息在最开始部
//分。
//主线程被IO操作（从4.0之后网络IO不允许在主线程中）阻塞。
//主线程中存在耗时的计算
//主线程中错误的操作，比如Thread.wait或者Thread.sleep等 Android系统会监控程序的响应状
//况，一旦出现下面两种情况，则弹出ANR对话框
//应用在5秒内未响应用户的输入事件（如按键或者触摸）
//BroadcastReceiver未在10秒内完成相关的处理
//Service在特定的时间内无法处理完成 20秒
//修正：
//1、使用AsyncTask处理耗时IO操作。
//2、使用Thread或者HandlerThread时，调用
//Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)设置优先级，否则仍然会降低
//程序响应，因为默认Thread的优先级和主线程相同。
//3、使用Handler处理工作线程结果，而不是使用Thread.wait()或者Thread.sleep()来阻塞主线程。
//4、Activity的onCreate和onResume回调中尽量避免耗时的代码。
//BroadcastReceiver中onReceive代码也要尽量减少耗时，建议使用IntentService处理。
//解决方案：
//将所有耗时操作，比如访问网络，Socket通信，查询大
//量SQL 语句，复杂逻辑计算等都放在子线程中去，然
//后通过handler.sendMessage、runonUIThread、AsyncTask、RxJava等方式更新UI。无论如何都要确
//保用户界面的流畅
//度。如果耗时操作需要让用户等待，那么可以在界面上显示度条。
//深入回答
//2、Activity和Fragment生命周期有哪些？3、横竖屏切换时候Activity的生命周期
//不设置Activity的android:configChanges时，切屏会重新回调各个生命周期，切横屏时会执行一次，切
//竖屏时会执行两次。
//设置Activity的android:configChanges=”orientation”时，切屏还是会调用各个生命周期，切换横竖屏
//只会执行一次
//设置Activity的android:configChanges=”orientation |keyboardHidden”时，切屏不会重新调用各个生
//命周期，只会执行onConfigurationChanged方法
//4、AsyncTask的缺陷和问题，说说他的原理。
//AsyncTask是什么？
//AsyncTask是一种轻量级的异步任务类，它可以在线程池中执行后台任务，然后把执行的进度和最终结
//果传递给主线程并在主线程中更新UI。
//AsyncTask是一个抽象的泛型类，它提供了Params、Progress和Result这三个泛型参数，其中Params
//表示参数的类型，Progress表示后台任务的执行进度和类型，而Result则表示后台任务的返回结果的类
//型，如果AsyncTask不需要传递具体的参数，那么这三个泛型参数可以用Void来代替。
//关于线程池：
//AsyncTask对应的线程池ThreadPoolExecutor都是进程范围内共享的，且都是static的，所以是
//Asynctask控制着进程范围内所有的子类实例。由于这个限制的存在，当使用默认线程池时，如果线程
//数超过线程池的最大容量，线程池就会爆掉(3.0后默认串行执行，不会出现个问题)。针对这种情况，可
//以尝试自定义线程池，配合Asynctask使用。
//关于默认线程池：
//AsyncTask里面线程池是一个核心线程数为CPU + 1，最大线程数为CPU * 2 + 1，工作队列长度为128
//的线程池，线程等待队列的最大等待数为28，但是可以自定义线程池。线程池是由AsyncTask来处理
//的，线程池允许tasks并行运行，需要注意的是并发情况下数据的一致性问题，新数据可能会被老数据
//覆盖掉。所以希望tasks能够串行运行的话，使用SERIAL_EXECUTOR。
//AsyncTask在不同的SDK版本中的区别：
//调用AsyncTask的execute方法不能立即执行程序的原因及改善方案通过查阅官方文档发现，AsyncTask
//首次引入时，异步任务是在一个独立的线程中顺序的执行，也就是说一次只执行一个任务，不能并行的
//执行，从1.6开始，AsyncTask引入了线程池，支持同时执行5个异步任务，也就是说只能有5个线程运
//行，超过的线程只能等待，等待前的线程直到某个执行完了才被调度和运行。换句话说，如果进程中的
//AsyncTask实例个数超过5个，那么假如前5都运行很长时间的话，那么第6个只能等待机会了。这是AsyncTask的一个限制，而且对于2.3以前的版本无法解决。如果你的应用需要大量的后台线程去执行任
//务，那么只能放弃使用AsyncTask，自己创建线程池来管理Thread。不得不说，虽然AsyncTask较
//Thread使用起来方便，但是它最多只能同时运行5个线程，这也大大局限了它的作用，你必须要小心设
//计你的应用，错开使用AsyncTask时间，尽力做到分时，或者保证数量不会大于5个，否就会遇到上面提
//到的问题。可能是Google意识到了AsynTask的局限性了，从Android 3.0开始对AsyncTask的API做出了
//一些调整：每次只启动一个线程执行一个任务，完了之后再执行第二个任务，也就是相当于只有一个后
//台线程在执行所提交的任务。
//一些问题：
//1.生命周期
//很多开发者会认为一个在Activity中创建的AsyncTask会随着Activity的销毁而销毁。然而事实并非如
//此。AsynTask会一直执行，直到doInBackground()方法执行完毕，然后，如果cancel(boolean)被调用,
//那么onCancelled(Result result)方法会被执行；否则，执行onPostExecute(Result result)方法。如果
//我们的Activity销毁之前，没有取消AsyncTask，这有可能让我们的应用崩溃(crash)。因为它想要处理的
//view已经不存在了。所以，我们是必须确保在销毁活动之前取消任务。总之，我们使用AsyncTask需要
//确保AsyncTask正确的取消。
//2.内存泄漏
//如果AsyncTask被声明为Activity的非静态内部类，那么AsyncTask会保留一个对Activity的引用。如果
//Activity已经被销毁，AsyncTask的后台线程还在执行，它将继续在内存里保留这个引用，导致Activity
//无法被回收，引起内存泄漏。
//3.结果丢失
//屏幕旋转或Activity在后台被系统杀掉等情况会导致Activity的重新创建，之前运行的AsyncTask会持有
//一个之前Activity的引用，这个引用已经无效，这时调用onPostExecute()再去更新界面将不再生效。
//4.并行还是串行
//在Android1.6之前的版本，AsyncTask是串行的，在1.6之后的版本，采用线程池处理并行任务，但是从
//Android 3.0开始，为了避免AsyncTask所带来的并发错误，又采用一个线程来串行执行任务。可以使用
//executeOnExecutor()方法来并行地执行任务。
//AsyncTask原理
//AsyncTask中有两个线程池（SerialExecutor和THREAD_POOL_EXECUTOR）和一个
//Handler（InternalHandler），其中线程池SerialExecutor用于任务的排队，而线程池
//THREAD_POOL_EXECUTOR用于真正地执行任务，InternalHandler用于将执行环境从线程池切换
//到主线程。
//sHandler是一个静态的Handler对象，为了能够将执行环境切换到主线程，这就要求sHandler这
//个对象必须在主线程创建。由于静态成员会在加载类的时候进行初始化，因此这就变相要求
//AsyncTask的类必须在主线程中加载，否则同一个进程中的AsyncTask都将无法正常工作。
//5、onSaveInstanceState() 与 onRestoreIntanceState()
//Activity的 onSaveInstanceState() 和 onRestoreInstanceState()并不是生命周期方法，它们不同于
//onCreate()、onPause()等生命周期方法，它们并不一定会被触发。当应用遇到意外情况（如：内存不
//足、用户直接按Home键）由系统销毁一个Activity时，onSaveInstanceState() 会被调用。但是当用户
//主动去销毁一个Activity时，例如在应用中按返回键，onSaveInstanceState()就不会被调用。因为在这
//种情况下，用户的行为决定了不需要保存Activity的状态。通常onSaveInstanceState()只适合用于保存
//一些临时性的状态，而onPause()适合用于数据的持久化保存。
//在activity被杀掉之前调用保存每个实例的状态,以保证该状态可以在onCreate(Bundle)或者
//onRestoreInstanceState(Bundle) (传入的Bundle参数是由onSaveInstanceState封装好的)中恢复。这
//个方法在一个activity被杀死前调用，当该activity在将来某个时刻回来时可以恢复其先前状态。例如，如果activity B启用后位于activity A的前端，在某个时刻activity A因为系统回收资源的问题要被
//杀掉，A通过onSaveInstanceState将有机会保存其用户界面状态，使得将来用户返回到activity A时能
//通过onCreate(Bundle)或者onRestoreInstanceState(Bundle)恢复界面的状态
//深入理解
//6、android中进程的优先级？
//1. 前台进程：
//即与用户正在交互的Activity或者Activity用到的Service等，如果系统内存不足时前台进程是最晚被杀死
//的
//2. 可见进程：
//可以是处于暂停状态(onPause)的Activity或者绑定在其上的Service，即被用户可见，但由于失了焦点而
//不能与用户交互
//3. 服务进程：
//其中运行着使用startService方法启动的Service，虽然不被用户可见，但是却是用户关心的，例如用户
//正在非音乐界面听的音乐或者正在非下载页面下载的文件等；当系统要空间运行，前两者进程才会被终
//止
//4. 后台进程：
//其中运行着执行onStop方法而停止的程序，但是却不是用户当前关心的，例如后台挂着的QQ，这时的
//进程系统一旦没了有内存就首先被杀死
//5. 空进程：
//不包含任何应用程序的进程，这样的进程系统是一般不会让他存在的
//7、Bunder传递对象为什么需要序列化？Serialzable和Parcelable的区别？
//因为bundle传递数据时只支持基本数据类型，所以在传递对象时需要序列化转换成可存储或可传输的本
//质状态（字节流）。序列化后的对象可以在网络、IPC（比如启动另一个进程的Activity、Service和
//Reciver）之间进行传输，也可以存储到本地。
//Serializable（Java自带）：
//Serializable 是序列化的意思，表示将一个对象转换成存储或可传输的状态。序列化后的对象可以在网
//络上进传输，也可以存储到本地。
//Parcelable（android专用）：
//除了Serializable之外，使用Parcelable也可以实现相同的效果，不过不同于将对象进行序列化，
//Parcelable方式的实现原理是将一个完整的对象进行分解，而分解后的每一部分都是Intent所支持的数
//据类型，这也就实现传递对象的功能了。
//区别总结如下图所示：8、动画
//tween 补间动画。通过指定View的初末状态和变化方式，对View的内容完成一系列的图形变换来
//实现动画效果。 Alpha, Scale ,Translate, Rotate。
//frame 帧动画。AnimationDrawable控制animation-list.xml布局
//PropertyAnimation 属性动画3.0引入，属性动画核心思想是对值的变化。
//Property Animation 动画有两个步聚：
//1.计算属性值
//2.为目标对象的属性设置属性值，即应用和刷新动画
//计算属性分为3个过程：
//过程一：
//计算已完成动画分数 elapsed fraction。为了执行一个动画，你需要创建一个ValueAnimator，并且指
//定目标对象属性的开始、结束和持续时间。在调用 start 后的整个动画过程中，ValueAnimator 会根据
//已经完成的动画时间计算得到一个0 到 1 之间的分数，代表该动画的已完成动画百分比。0表示 0%，1
//表示 100%。
//过程二：计算插值（动画变化率）interpolated fraction 。当 ValueAnimator计算完已完成的动画分数后，它会
//调用当前设置的TimeInterpolator，去计算得到一个interpolated（插值）分数，在计算过程中，已完
//成动画百分比会被加入到新的插值计算中。
//过程三：
//计算属性值当插值分数计算完成后，ValueAnimator会根据插值分数调用合适的 TypeEvaluator去计算
//运动中的属性值。
//以上分析引入了两个概念：已完成动画分数（elapsed fraction）、插值分数( interpolated fraction )。
//原理及特点：
//1.属性动画：
//插值器：作用是根据时间流逝的百分比来计算属性变化的百分比
//估值器：在1的基础上由这个东西来计算出属性到底变化了多少数值的类
//其实就是利用插值器和估值器，来计出各个时刻View的属性，然后通过改变View的属性来实现View的
//动画效果。
//2.View动画:
//只是影像变化，view的实际位置还在原来地方。
//3.帧动画：
//是在xml中定义好一系列图片之后，使用AnimatonDrawable来播放的动画。
//它们的区别：
//属性动画才是真正的实现了 view 的移动，补间动画对view 的移动更像是在不同地方绘制了一个影子，
//实际对象还是处于原来的地方。
//当动画的 repeatCount 设置为无限循环时，如果在Activity退出时没有及时将动画停止，属性动画会导
//致Activity无法释放而导致内存泄漏，而补间动画却没问题。
//xml 文件实现的补间动画，复用率极高。在 Activity切换，窗口弹出时等情景中有着很好的效果。
//使用帧动画时需要注意，不要使用过多特别大的图，容导致内存不足。
//为什么属性动画移动后仍可点击？
//播放补间动画的时候，我们所看到的变化，都只是临时的。而属性动画呢，它所改变的东西，却会更新
//到这个View所对应的矩阵中，所以当ViewGroup分派事件的时候，会正确的将当前触摸坐标，转换成矩
//阵变化后的坐标，这就是为什么播放补间动画不会改变触摸区域的原因了。
//9、Context相关
//1、Activity和Service以及Application的Context是不一样的,Activity继承自
//ContextThemeWraper.其他的继承自ContextWrapper。
//2、每一个Activity和Service以及Application的Context是一个新的ContextImpl对象。
//3、getApplication()用来获取Application实例的，但是这个方法只有在Activity和Service中才能调
//用的到。那也许在绝大多数情况下我们都是在Activity或者Servic中使用Application的，但是如果
//在一些其它的场景，比如BroadcastReceiver中也想获得Application的实例，这时就可以借助
//getApplicationContext()方法，getApplicationContext()比getApplication()方法的作用域会更广
//一些，任何一个Context的实例，只要调用getApplicationContext()方法都可以拿到我们的
//Application对象。
//4、创建对话框时不可以用Application的context，只能用Activity的context。
//5、Context的数量等于Activity的个数 + Service的个数 +1，这个1为Application。
//10、Android各版本新特性Android5.0新特性
//MaterialDesign设计风格
//支持64位ART虚拟机（5.0推出的ART虚拟机，在5.0之前都是Dalvik。他们的区别是：
//Dalvik,每次运行,字节码都需要通过即时编译器转换成机器码(JIT)。
//ART,第一次安装应用的时候,字节码就会预先编译成机器码(AOT)）
//通知详情可以用户自己设计
//Android6.0新特性
//动态权限管理
//支持快速充电的切换
//支持文件夹拖拽应用
//相机新增专业模式
//Android7.0新特性
//多窗口支持
//V2签名
//增强的Java8语言模式
//夜间模式
//Android8.0（O）新特性
//优化通知
//画中画模式：清单中Activity设置android:supportsPictureInPicture
//后台限制
//自动填充框架
//系统优化
//等等优化很多
//Android9.0（P）新特性
//室内WIFI定位
//“刘海”屏幕支持
//安全增强
//等等优化很多
//Android10.0（Q）目前曝光的新特性
//夜间模式：包括手机上的所有应用都可以为其设置暗黑模式。
//桌面模式：提供类似于PC的体验，但是远远不能代替PC。
//屏幕录制：通过长按“电源”菜单中的"屏幕快照"来开启。
//11、Json
//通知渠道 (Notification Channel)
//通知标志
//休眠
//通知超时
//通知设置
//通知清除JSON的全称是JavaScript Object Notation，也就是JavaScript 对象表示法
//JSON是存储和交换文本信息的语法，类似XML，但是比XML更小、更快，更易解析
//JSON是轻量级的文本数据交换格式，独立于语言，具有可描述性，更易理解，对象可以包含多个名称/
//值对，比如：
//使用谷歌的GSON包进行解析，在 Android Studio 里引入依赖：
//值得注意的是实体类中变量名称必须和json中的值名字相同。
//使用示例：
//1、解析成实体类：
//2、解析成int数组：
//3、直接解析成List.
//优点：
//轻量级的数据交换格式
//读写更加容易
//易于机器的解析和生成
//缺点：
//语义性较差，不如 xml 直观
//12、android中有哪几种解析xml的类,官方推荐哪种？以及它们的原理和区别？
//DOM解析
//优点:
//1.XML树在内存中完整存储,因此可以直接修改其数据结构.
//2.可以通过该解析器随时访问XML树中的任何一个节点.
//{"name":"zhangsan" , "age":25}
//compile 'com.google.code.gson:gson:2.7'
//Gson gson = new Gson();
//Student student = gson.fromJson(json1, Student.class);
//Gson gson = new Gson();
//int[] ages = gson.fromJson(json2, int[].class);
//Gson gson = new Gson();
//List<Integer> ages = gson.fromJson(json2, newTypeToken<List<Integer>>()
//{}.getType);
//Gson gson = new Gson();
//List<Student> students = gson.fromJson(json3, newTypeToke<List<Student>>()
//{}.getType);3.DOM解析器的API在使用上也相对比较简单.
//缺点:
//如果XML文档体积比较大时,将文档读入内存是非消耗系统资源的.
//使用场景:
//DOM 是与平台和语言无关的方式表示 XML文档的官方 W3C 标准.
//DOM 是以层次结构组织的节点的集合.这个层次结构允许开人员在树中寻找特定信息.分析该结构
//通常需要加载整个文档和构造层次结构,然后才能进行任何工作.
//DOM 是基于对象层次结构的.
//SAX解析
//优点:
//SAX 对内存的要求比较低,因为它让开发人员自己来决定所要处理的标签.特别是当开发人员只需要处理
//文档中包含的部分数据时,SAX 这种扩展能力得到了更好的体现.
//缺点:
//用SAX方式进行XML解析时,需要顺序执行,所以很难访问同一文档中的不同数据.此外,在基于该方式的解
//析编码程序也相对复杂.
//使用场景:
//对于含有数据量十分巨大,而又不用对文档的所有数据行遍历或者分析的时候,使用该方法十分有效.该方
//法不将整个文档读入内存,而只需读取到程序所需的文档标记处即可.
//Xmlpull解析
//android SDK提供了xmlpullapi,xmlpull和sax类似,是基于流（stream）操作文件,后者根据节点事件回
//调开发者编写的处理程序.因为是基于流的处理,因此xmlpull和sax都比较节约内存资源,不会像dom那样
//要把所有节点以对象树的形式展现在内存中.xmpull比sax更简明,而且不需要扫描完整个流.
//13、Jar和Aar的区别
//Jar包里面只有代码，aar里面不光有代码还包括资源文件，比如 drawable 文件，xml资源文件。对于一
//些不常变动的 Android Library，我们可以直接引用 aar，加快编译速度。
//14、Android为每个应用程序分配的内存大小是多少
//android程序内存一般限制在16M，也有的是24M。近几年手机发展较快，一般都会分配两百兆左右，
//和具体机型有关。
//15、更新UI方式
//Activity.runOnUiThread(Runnable)
//View.post(Runnable)，View.postDelay(Runnable, long)（可以理解为在当前操作视图UI线程添
//加队列）
//Handler
//AsyncTask
//Rxjava
//LiveData16、ContentProvider使用方法。
//进行跨进程通信，实现进程间的数据交互和共享。通过Context 中 getContentResolver() 获得实例，通
//过 Uri匹配进行数据的增删改查。ContentProvider使用表的形式来组织数据，无论数据的来源是什么，
//ConentProvider 都会认为是一种表，然后把数据组织成表格。
//17、Thread、AsyncTask、IntentService的使用场景与特点。
//1. Thread线程，独立运行与于 Activity 的，当Activity 被 finish 后，如果没有主动停止 Thread或者
//run 方法没有执行完，其会一直执行下去。
//2. AsyncTask 封装了两个线程池和一个Handler（SerialExecutor用于排队，
//THREAD_POOL_EXECUTOR为真正的执行任务，Handler将工作线程切换到主线程），其必须在
//UI线程中创建，execute 方法必须在 UI线程中执行，一个任务实例只允许执行一次，执行多次抛
//出异常，用于网络请求或者简单数据处理。
//3. IntentService：处理异步请求，实现多线程，在onHandleIntent中处理耗时操作，多个耗时任务
//会依次执行，执行完毕自动结束。
//18、Merge、ViewStub 的作用。
//Merge: 减少视图层级，可以删除多余的层级。
//ViewStub: 按需加载，减少内存使用量、加快渲染速度、不支持 merge 标签。
//19、activity的startActivity和context的startActivity区别？
//(1)、从Activity中启动新的Activity时可以直接mContext.startActivity(intent)就好
//(2)、如果从其他Context中启动Activity则必须给intent设置Flag:
//20、怎么在Service中创建Dialog对话框？
//1.在我们取得Dialog对象后，需给它设置类型，即：
//2.在Manifest中加上权限:
//21、Asset目录与res目录的区别？
//assets：不会在 R 文件中生成相应标记，存放到这里的资源在打包时会打包到程序安装包中。（通过
//AssetManager 类访问这些文件）
//res：会在 R 文件中生成 id 标记，资源在打包时如果使用到则打包到安装包中，未用到不会打入安装包
//中。
//res/anim：存放动画资源。
//res/raw：和 asset 下文件一样，打包时直接打入程序安装包中（会映射到 R 文件中）。
//22、Android怎么加速启动Activity？
//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
//mContext.startActivity(intent);
//dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
//<uses-permission android:name="android.permission.SYSTEM_ALERT_WINOW" />onCreate() 中不执行耗时操作
//把页面显示的 View 细分一下，放在 AsyncTask 里逐步显示，用 Handler 更好。这样用户的看到
//的就是有层次有步骤的一个个的 View 的展示，不会是先看到一个黑屏，然后一下显示所有
//View。最好做成动画，效果更自然。
//利用多线程的目的就是尽可能的减少 onCreate() 和 onReume() 的时间，使得用户能尽快看到页
//面，操作页面。
//减少主线程阻塞时间。
//提高 Adapter 和 AdapterView 的效率。
//优化布局文件。
//23、Handler机制
//Android消息循环流程图如下所示：
//主要涉及的角色如下所示：
//message：消息。
//MessageQueue：消息队列，负责消息的存储与管理，负责管理由 Handler 发送过来的
//Message。读取会自动删除消息，单链表维护，插入和删除上有优势。在其next()方法中会无限循
//环，不断判断是否有消息，有就返回这条消息并移除。
//Looper：消息循环器，负责关联线程以及消息的分发，在该线程下从 MessageQueue获取
//Message，分发给Handler，Looper创建的时候会创建一个
//MessageQueue，调用loop()方法的时候消息循环开始，其中会不断调用messageQueue的next()
//方法，当有消息就处理，否则阻塞在messageQueue的next()方法中。当Looper的quit()被调用的
//时候会调用messageQueue的quit()，此时next()会返回null，然后loop()方法也就跟着退出。
//Handler：消息处理器，负责发送并处理消息，面向开发者，提供 API，并隐藏背后实现的细节。
//整个消息的循环流程还是比较清晰的，具体说来：
//1、Handler通过sendMessage()发送消息Message到消息队列MessageQueue。
//2、Looper通过loop()不断提取触发条件的Message，并将Message交给对应的target handler来
//处理。3、target handler调用自身的handleMessage()方法来处理Message。
//事实上，在整个消息循环的流程中，并不只有Java层参与，很多重要的工作都是在C++层来完成的。我
//们来看下这些类的调用关系。
//注：虚线表示关联关系，实线表示调用关系。
//在这些类中MessageQueue是Java层与C++层维系的桥梁，MessageQueue与Looper相关功能都通过
//MessageQueue的Native方法来完成，而其他虚线连接的类只有关联关系，并没有直接调用的关系，它
//们发生关联的桥梁是MessageQueue。
//总结
//Handler 发送的消息由 MessageQueue 存储管理，并由 Looper 负责回调消息到
//handleMessage()。
//线程的转换由 Looper 完成，handleMessage() 所在线程由 Looper.loop() 调用者所在线程决定。
//Handler 引起的内存泄露原因以及最佳解决方案
//Handler 允许我们发送延时消息，如果在延时期间用户关闭了 Activity，那么该 Activity 会泄露。
//这个泄露是因为 Message 会持有 Handler，而又因为 Java 的特性，内部类会持有外部类，使得
//Activity 会被 Handler 持有，这样最终就导致 Activity 泄露。
//解决：将 Handler 定义成静态的内部类，在内部持有 Activity 的弱引用，并在Acitivity的onDestroy()中
//调用handler.removeCallbacksAndMessages(null)及时移除所有消息。
//为什么我们能在主线程直接使用 Handler，而不需要创建 Looper ？
//通常我们认为 ActivityThread 就是主线程。事实上它并不是一个线程，而是主线程操作的管理者。在
//ActivityThread.main() 方法中调用了 Looper.prepareMainLooper() 方法创建了 主线程的 Looper ,并
//且调用了 loop() 方法，所以我们就可以直接使用 Handler 了。
//因此我们可以利用 Callback 这个拦截机制来拦截 Handler 的消息。如大部分插件化框架中Hook
//ActivityThread.mH 的处理。
//主线程的 Looper 不允许退出
//主线程不允许退出，退出就意味 APP 要挂。
//Handler 里藏着的 Callback 能干什么？
//Handler.Callback 有优先处理消息的权利 ，当一条消息被 Callback 处理并拦截（返回 true），那么
//Handler 的 handleMessage(msg) 方法就不会被调用了；如果 Callback 处理了消息，但是并没有拦
//截，那么就意味着一个消息可以同时被 Callback 以及 Handler 处理。
//创建 Message 实例的最佳方式
//为了节省开销，Android 给 Message 设计了回收机制，所以我们在使用的时候尽量复用 Message ，减
//少内存消耗：通过 Message 的静态方法 Message.obtain()；
//通过 Handler 的公有方法 handler.obtainMessage()。
//子线程里弹 Toast 的正确姿势
//本质上是因为 Toast 的实现依赖于 Handler，按子线程使用 Handler 的要求修改即可，同理的还有
//Dialog。
//妙用 Looper 机制
//将 Runnable post 到主线程执行；
//利用 Looper 判断当前线程是否是主线程。
//主线程的死循环一直运行是不是特别消耗CPU资源呢？
//并不是，这里就涉及到Linux pipe/epoll机制，简单说就是在主线程的MessageQueue没有消息时，便
//阻塞在loop的queue.next()中的nativePollOnce()方法里，此时主线程会释放CPU资源进入休眠状态，
//直到下个消息到达或者有事务发生，通过往pipe管道写端写入数据来唤醒主线程工作。这里采用的
//epoll机制，是一种IO多路复用机制，可以同时监控多个描述符，当某个描述符就绪(读或写就绪)，则立
//刻通知相应程序进行读或写操作，本质是同步I/O，即读写是阻塞的。所以说，主线程大多数时候都是
//处于休眠状态，并不会消耗大量CPU资源。
//handler postDelay这个延迟是怎么实现的？
//handler.postDelay并不是先等待一定的时间再放入到MessageQueue中，而是直接进入
//MessageQueue，以MessageQueue的时间顺序排列和唤醒的方式结合实现的。
//Handler 都没搞懂，拿什么去跳槽啊？
//24、程序A能否接收到程序B的广播？
//能，使用全局的BroadCastRecevier能进行跨进程通信，但是注意它只能被动接收广播。此外，
//LocalBroadCastRecevier只限于本进程的广播间通信。
//25、数据加载更多涉及到分页，你是怎么实现的？
//分页加载就是一页一页加载数据，当滑动到底部、没有更多数据加载的时候，我们可以手动调用接口，
//重新刷新RecyclerView。
//26、通过google提供的Gson解析json时，定义JavaBean的规则是什么？
//1). 实现序列化 Serializable
//2). 属性私有化，并提供get，set方法
//3). 提供无参构造
//4). 属性名必须与json串中属性名保持一致 （因为Gson解析json串底层用到了Java的反射原理）
//27、json解析方式的两种区别？
//1，SDK提供JSONArray，JSONObject
//2，google提供的 Gson
//通过fromJson()实现对象的反序列化（即将json串转换为对象类型）
//通过toJson()实现对象的序列化 （即将对象类型转换为json串）
//28、线程池的相关知识。Android中的线程池都是直接或间接通过配置ThreadPoolExecutor来实现不同特性的线程池.Android中
//最常见的类具有不同特性的线程池分别为FixThreadPool、CachedhreadPool、SingleThreadPool、
//ScheduleThreadExecutr.
//1).FixThreadPool
//只有核心线程,并且数量固定的,也不会被回收,所有线程都活动时,因为队列没有限制大小,新任务会等待执
//行.
//优点:更快的响应外界请求.
//2).SingleThreadPool
//只有一个核心线程,确保所有的任务都在同一线程中按序完成.因此不需要处理线程同步的问题.
//3).CachedThreadPool
//只有非核心线程,最大线程数非常大,所有线程都活动时会为新任务创建新线程,否则会利用空闲线程(60s
//空闲时间,过了就会被回收,所以线程池中有0个线程的可能)处理任务.
//优点:任何任务都会被立即执行(任务队列SynchronousQuue相当于一个空集合);比较适合执行大量的耗
//时较少的任务.
//4).ScheduledThreadPool
//核心线程数固定,非核心线程（闲着没活干会被立即回收数）没有限制.
//优点:执行定时任务以及有固定周期的重复任务
//29、内存泄露，怎样查找，怎么产生的内存泄露？
//1.资源对象没关闭造成的内存泄漏
//描述： 资源性对象比如(Cursor，File文件等)往往都用了一些缓冲，我们在不使用的时候，应该及时关
//闭它们，以便它们的缓冲及时回收内存。它们的缓冲不仅存在于 java虚拟机内，还存在于java虚拟机
//外。如果我们仅仅是把它的引用设置为null,而不关闭它们，往往会造成内存泄漏。因为有些资源性对
//象，比如SQLiteCursor(在析构函数finalize(),如果我们没有关闭它，它自己会调close()关闭)，如果我们
//没有关闭它，系统在回收它时也会关闭它，但是这样的效率太低了。因此对于资源性对象在不使用的时
//候，应该调用它的close()函数，将其关闭掉，然后才置为null.在我们的程序退出时一定要确保我们的资
//源性对象已经关闭。
//程序中经常会进行查询数据库的操作，但是经常会有使用完毕Cursor后没有关闭的情况。如果我们的查
//询结果集比较小，对内存的消耗不容易被发现，只有在常时间大量操作的情况下才会复现内存问题，这
//样就会给以后的测试和问题排查带来困难和风险。
//2.构造Adapter时，没有使用缓存的convertView
//描述： 以构造ListView的BaseAdapter为例，在BaseAdapter中提供了方法： public View getView(int
//position, ViewconvertView, ViewGroup parent) 来向ListView提供每一个item所需要的view对象。初
//始时ListView会从BaseAdapter中根据当前的屏幕布局实例化一定数量的 view对象，同时ListView会将
//这些view对象缓存起来。当向上滚动ListView时，原先位于最上面的list item的view对象会被回收，然
//后被用来构造新出现的最下面的list item。这个构造过程就是由getView()方法完成的，getView()的第二
//个形参View convertView就是被缓存起来的list item的view对象(初始化时缓存中没有view对象则
//convertView是null)。由此可以看出，如果我们不去使用 convertView，而是每次都在getView()中重新
//实例化一个View对象的话，即浪费资源也浪费时间，也会使得内存占用越来越大。 ListView回收list
//item的view对象的过程可以查看: android.widget.AbsListView.java --> voidaddScrapView(View
//scrap) 方法。 示例代码：修正示例代码：
//3.Bitmap对象不在使用时调用recycle()释放内存
//描述： 有时我们会手工的操作Bitmap对象，如果一个Bitmap对象比较占内存，当它不在被使用的时
//候，可以调用Bitmap.recycle()方法回收此对象的像素所占用的内存，但这不是必须的，视情况而定。
//可以看一下代码中的注释：
///* •Free up the memory associated with thisbitmap's pixels, and mark the •bitmap as "dead",
//meaning itwill throw an exception if getPixels() or •setPixels() is called, and will drawnothing. This
//operation cannot be •reversed, so it should only be called ifyou are sure there are no •further
//uses for the bitmap. This is anadvanced call, and normally need •not be called, since the normal
//GCprocess will free up this memory when •there are no more references to thisbitmap. /
//4.试着使用关于application的context来替代和activity相关的context
//这是一个很隐晦的内存泄漏的情况。有一种简单的方法来避免context相关的内存泄漏。最显著地一个
//是避免context逃出他自己的范围之外。使用Application context。这个context的生存周期和你的应用
//的生存周期一样长，而不是取决于activity的生存周期。如果你想保持一个长期生存的对象，并且这个对
//象需要一个context,记得使用application对象。你可以通过调用 Context.getApplicationContext() or
//Activity.getApplication()来获得。更多的请看这篇文章如何避免 Android内存泄漏。
//5.注册没取消造成的内存泄漏
//一些Android程序可能引用我们的Anroid程序的对象(比如注册机制)。即使我们的Android程序已经结束
//了，但是别的引用程序仍然还有对我们的Android程序的某个对象的引用，泄漏的内存依然不能被垃圾
//回收。调用registerReceiver后未调用unregisterReceiver。 比如:假设我们希望在锁屏界面
//(LockScreen)中，监听系统中的电话服务以获取一些信息(如信号强度等)，则可以在LockScreen中定义
//一个 PhoneStateListener的对象，同时将它注册到TelephonyManager服务中。对于LockScreen对
//象，当需要显示锁屏界面的时候就会创建一个LockScreen对象，而当锁屏界面消失的时候LockScreen
//对象就会被释放掉。 但是如果在释放 LockScreen对象的时候忘记取消我们之前注册的
//PhoneStateListener对象，则会导致LockScreen无法被垃圾回收。如果不断的使锁屏界面显示和消
//失，则最终会由于大量的LockScreen对象没有办法被回收而引起OutOfMemory,使得system_process
//进程挂掉。 虽然有些系统程序，它本身好像是可以自动取消注册的(当然不及时)，但是我们还是应该在
//我们的程序中明确的取消注册，程序结束时应该把所有的注册都取消掉。
//6.集合中对象没清理造成的内存泄漏
//public View getView(int position, ViewconvertView, ViewGroup parent) {
//View view = new Xxx(...);
//... ...
//return view;
//} p
//ublic View getView(int position, ViewconvertView, ViewGroup parent) {
//View view = null;
//if (convertView != null) {
//view = convertView;
//populate(view, getItem(position));
//...
//} else {
//view = new Xxx(...);
//...
//} r
//eturn view;
//}我们通常把一些对象的引用加入到了集合中，当我们不需要该对象时，并没有把它的引用从集合中清理
//掉，这样这个集合就会越来越大。如果这个集合是static的话，那情况就更严重了。
//查找内存泄漏可以使用Android Studio 自带的AndroidProfiler工具或MAT，也可以使用Square产品的
//LeakCanary.
//1、使用AndroidProfiler的MEMORY工具：
//运行程序，对每一个页面进行内存分析检查。首先，反复打开关闭页面5次，然后收到GC（点击Profile
//MEMORY左上角的垃圾桶图标），如果此时total内存还没有恢复到之前的数值，则可能发生了内存泄
//露。此时，再点击Profile MEMORY左上角的垃圾桶图标旁的heap dump按钮查看当前的内存堆栈情
//况，选择按包名查找，找到当前测试的Activity，如果引用了多个实例，则表明发生了内存泄露。
//2、使用MAT：
//1、运行程序，所有功能跑一遍，确保没有改出问题，完全退出程序，手动触发GC，然后使用adb shell
//dumpsys meminfo packagename -d命令查看退出界面后Objects下的Views和Activities数目是否为
//0，如果不是则通过Leakcanary检查可能存在内存泄露的地方，最后通过MAT分析，如此反复，改善满
//意为止。
//1、在使用MAT之前，先使用as的Profile中的Memory去获取要分析的堆内存快照文件.hprof，如果要
//测试某个页面是否产生内存泄漏，可以先dump出没进入该页面的内存快照文件.hprof，然后，通常执
//行5次进入/退出该页面，然后再dump出此刻的内存快照文件.hprof，最后，将两者比较，如果内存相
//除明显，则可能发生内存泄露。（注意:MAT需要标准的.hprof文件，因此在as的Profiler中GC后dump
//出的内存快照文件.hprof必须手动使用android sdk platform-tools下的hprof-conv程序进行转换才能
//被MAT打开）
//2、然后，使用MAT打开前面保存的2份.hprof文件，打开Overview界面，在Overview界面下面有4中
//action，其中最常用的就是Histogram和Dominator Tree。
//Dominator Tree：支配树，按对象大小降序列出对象和其所引用的对象，注重引用关系分析。选择
//Group by package，找到当前要检测的类（或者使用顶部的Regex直接搜索），查看它的Object数目是
//否正确，如果多了，则判断发生了内存泄露。然后，右击该类，选择Merge Shortest Paths to GC Root
//中的exclude all phantom/weak/soft etc.references选项来查看该类的GC强引用链。最后，通过引用
//链即可看到最终强引用该类的对象。
//Histogram：直方图注重量的分析。使用方式与Dominator Tree类似。
//3、对比hprof文件，检测出复杂情况下的内存泄露：
//通用对比方式：在Navigation History下面选择想要对比的dominator_tree/histogram，右击选择Add
//to Compare Basket，然后在Compare Basket一栏中点击红色感叹号（Compare the results）生成对
//比表格（Compared Tables），在顶部Regex输入要检测的类，查看引用关系或对象数量去进行分析即
//可。
//针对于Historam的快速对比方式：直接选择Histogram上方的Compare to another Heap Dump选择
//要比较的hprof文件的Historam即可。
//30、类的初始化顺序依次是？
//（静态变量、静态代码块）>（变量、代码块）>构造方法
//31、JSON的结构？
//json是一种轻量级的数据交换格式，
//json简单说就是对象和数组，所以这两种结构就是对象和数组两种结构，通过这两种结构可以表示各种
//复杂的结构1、对象：对象表示为“{}”扩起来的内容，数据结构为 {key：value,key：value,...}的键值对的结构，在
//面向对象的语言中，key为对象的属性，value为对应的属性值，所以很容易理解，取值方法为 对象.key
//获取属性值，这个属性值的类型可以是 数字、字符串、数组、对象几种。
//2、数组：数组在json中是中括号“[]”扩起来的内容，数据结构为 ["java","javascript","vb",...]，取值方式
//和所有语言中一样，使用索引获取，字段值的类型可以是 数字、字符串、数组、对象几种。
//经过对象、数组2种结构就可以组合成复杂的数据结构了。
//32、ViewPager使用细节，如何设置成每次只初始化当前的Fragment，其他的不
//初始化（提示：Fragment懒加载）？
//自定义一个 LazyLoadFragment 基类，利用 setUserVisibleHint 和 生命周期方法，通过对 Fragment
//状态判断，进行数据加载，并将数据加载的接口提供开放出去，供子类使用。然后在子类 Fragment 中
//实现 requestData 方法即可。这里添加了一个 isDataLoaded 变量，目的是避免重复加载数据。考虑到
//有时候需要刷新数据的问题，便提供了一个用于强制刷新的参数判断。
//35、Android为什么引入Parcelable？
//可以肯定的是，两者都是支持序列化和反序列化的操作。
//两者最大的区别在于 存储媒介的不同，Serializable 使用 I/O 读写存储在硬盘上，而 Parcelable 是直接
//在内存中读写。很明显，内存的读写速度通常大于 IO 读写，所以在 Android 中传递数据优先选择
//Parcelable。
//public abstract class LazyLoadFragment extends BaseFragment {
//protected boolean isViewInitiated;
//protected boolean isDataLoaded;
//@Override
//public void onCreate(Bundle savedInstanceState) {
//super.onCreate(savedInstanceState);
//} @
//Override
//public void onActivityCreated(Bundle savedInstanceState) {
//super.onActivityCreated(savedInstanceState);
//isViewInitiated = true;
//prepareRequestData();
//} @
//Override
//public void setUserVisibleHint(boolean isVisibleToUser) {
//super.setUserVisibleHint(isVisibleToUser);
//prepareRequestData();
//} p
//ublic abstract void requestData();
//public boolean prepareRequestData() {
//return prepareRequestData(false);
//} p
//ublic boolean prepareRequestData(boolean forceUpdate) {
//if (getUserVisibleHint() && isViewInitiated && (!isDataLoaded ||
//forceUpdate)) {
//requestData();
//isDataLoaded = true;
//return true;
//} r
//eturn false;
//}
//}Serializable 会使用反射，序列化和反序列化过程需要大量 I/O 操作， Parcelable 自已实现封送和解封
//（marshalled &unmarshalled）操作不需要用反射，数据也存放在 Native 内存中，效率要快很多。
//36、有没有尝试简化Parcelable的使用？
//使用Parcelable插件（Android Parcelable code generator）进行实体类的序列化的实现。
//37、Bitmap 使用时候注意什么？
//1、要选择合适的图片规格（bitmap类型）：
//2、降低采样率。BitmapFactory.Options 参数inSampleSize的使用，先把
//options.inJustDecodeBounds设为true，只是去读取图片的大小，在拿到图片的大小之后和要显示的
//大小做比较通过calculateInSampleSize()函数计算inSampleSize的具体值，得到值之后。
//options.inJustDecodeBounds设为false读图片资源。
//3、复用内存。即，通过软引用(内存不够的时候才会回收掉)，复用内存块，不需要再重新给这个
//bitmap申请一块新的内存，避免了一次内存的分配和回收，从而改善了运行效率。
//4、使用recycle()方法及时回收内存。
//5、压缩图片。
//38、Oom 是否可以try catch ？
//只有在一种情况下，这样做是可行的：
//在try语句中声明了很大的对象，导致OOM，并且可以确认OOM是由try语句中的对象声明导致的，那
//么在catch语句中，可以释放掉这些对象，解决OOM的问题，继续执行剩余语句。
//但是这通常不是合适的做法。
//Java中管理内存除了显式地catch OOM之外还有更多有效的方法：比如SoftReference,
//WeakReference, 硬盘缓存等。
//在JVM用光内存之前，会多次触发GC，这些GC会降低程序运行的效率。
//如果OOM的原因不是try语句中的对象（比如内存泄漏），那么在catch语句中会继续抛出OOM。
//39、多进程场景遇见过么？
//1、在新的进程中，启动前台Service，播放音乐。
//2、一个成熟的应用一定是多模块化的。首先多进程开发能为应用解决了OOM问题，因为Android对内
//存的限制是针对于进程的，所以，当我们需要加载大图之类的操作，可以在新的进程中去执行，避免主
//进程OOM。而且假如图片浏览进程打开了一个过大的图片，java heap 申请内存失败，该进程崩溃并不
//影响我主进程的使用。
//40、Canvas.save()跟Canvas.restore()的调用时机
//save：用来保存Canvas的状态。save之后，可以调用Canvas的平移、放缩、旋转、错切、裁剪等操
//作。
//restore：用来恢复Canvas之前保存的状态。防止save后对Canvas执行的操作对后续的绘制有影响。
//save和restore要配对使用（restore可以比save少，但不能多），如果restore调用次数比save多，会引
//发Error。save和restore操作执行的时机不同，就能造成绘制的图形不同。
//ALPHA_8 每个像素占用1byte内存
//ARGB_4444 每个像素占用2byte内存
//ARGB_8888 每个像素占用4byte内存（默认）
//RGB_565 每个像素占用2byte内存41、数据库升级增加表和删除表都不涉及数据迁移，但是修改表涉及到对原有数据
//进行迁移。升级的方法如下所示：
//将现有表命名为临时表。
//创建新表。
//将临时表的数据导入新表。
//删除临时表。
//如果是跨版本数据库升级，可以有两种方式，如下所示：
//逐级升级，确定相邻版本与现在版本的差别，V1升级到V2,V2升级到V3，依次类推。
//跨级升级，确定每个版本与现在数据库的差别，为每个case编写专门升级大代码。
//42、编译期注解跟运行时注解
//运行期注解(RunTime)利用反射去获取信息还是比较损耗性能的，对应
//@Retention（RetentionPolicy.RUNTIME）。
//编译期(Compile time)注解，以及处理编译期注解的手段APT和Javapoet，对应
//@Retention(RetentionPolicy.CLASS)。
//其中apt+javaPoet目前也是应用比较广泛，在一些大的开源库，如EventBus3.0+,页面路由 ARout、
//Dagger、Retrofit等均有使用的身影，注解不仅仅是通过反射一种方式来使用，也可以使用APT在编译
//期处理
//43、bitmap recycler 相关
//public class DBservice extends SQLiteOpenHelper{
//private String CREATE_BOOK = "create table book(bookId integer
//primarykey,bookName text);";
//private String CREATE_TEMP_BOOK = "alter table book rename to _temp_book";
//private String INSERT_DATA = "insert into book select *,'' from _temp_book";
//private String DROP_BOOK = "drop table _temp_book";
//public DBservice(Context context, String name, CursorFactory factory,int
//version) {
//super(context, name, factory, version);
//} @
//Override
//public void onCreate(SQLiteDatabase db) {
//db.execSQL(CREATE_BOOK);
//} @
//Override
//public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//switch (newVersion) {
//case 2:
//db.beginTransaction();
//db.execSQL(CREATE_TEMP_BOOK);
//db.execSQL(CREATE_BOOK);
//db.execSQL(INSERT_DATA);
//db.execSQL(DROP_BOOK);
//db.setTransactionSuccessful();
//db.endTransaction();
//break;
//}
//}在Android中，Bitmap的存储分为两部分，一部分是Bitmap的数据，一部分是Bitmap的引用。
//在Android2.3时代，Bitmap的引用是放在堆中的，而Bitmap的数据部分是放在栈中的，需要用户调用
//recycle方法手动进行内存回收，而在Android2.3之后，整个Bitmap，包括数据和引用，都放在了堆
//中，这样，整个Bitmap的回收就全部交给GC了，这个recycle方法就再也不需要使用了。
//bitmap recycler引发的问题：当图像的旋转角度小余两个像素点之间的夹角时，图像即使旋转也无法显
//示，因此，系统完全可以认为图像没有发生变化。这时系统就直接引用同一个对象来进行操作，避免内
//存浪费。
//44、强引用置为null，会不会被回收？
//不会立即释放对象占用的内存。 如果对象的引用被置为null，只是断开了当前线程栈帧中对该对象的引
//用关系，而 垃圾收集器是运行在后台的线程，只有当用户线程运行到安全点(safe point)或者安全区域
//才会扫描对象引用关系，扫描到对象没有被引用则会标记对象，这时候仍然不会立即释放该对象内存，
//因为有些对象是可恢复的（在 finalize方法中恢复引用 ）。只有确定了对象无法恢复引用的时候才会清
//除对象内存。
//45、Bundle传递数据为什么需要序列化？
//序列化，表示将一个对象转换成可存储或可传输的状态。序列化的原因基本三种情况：
//1.永久性保存对象，保存对象的字节序列到本地文件中；
//2.对象在网络中传递；
//3.对象在IPC间传递。
//46、广播传输的数据是否有限制，是多少，为什么要限制？
//Intent在传递数据时是有大小限制的，大约限制在1MB之内，你用Intent传递数据，实际上走的是跨进
//程通信（IPC），跨进程通信需要把数据从内核copy到进程中，每一个进程有一个接收内核数据的缓冲
//区，默认是1M；如果一次传递的数据超过限制，就会出现异常。
//不同厂商表现不一样有可能是厂商修改了此限制的大小，也可能同样的对象在不同的机器上大小不一
//样。
//传递大数据，不应该用Intent；考虑使用ContentProvider或者直接匿名共享内存。简单情况下可以考
//虑分段传输。
//47、是否了解硬件加速？
//硬件加速就是运用GPU优秀的运算能力来加快渲染的速度，而通常的基于软件的绘制渲染模式是完全利
//用CPU来完成渲染。
//1.硬件加速是从API 11引入，API 14之后才默认开启。对于标准的绘制操作和控件都是支持的，但是对
//于自定义View的时候或者一些特殊的绘制函数就需要考虑是否需要关闭硬件加速。
//2.我们面对不支持硬件加速的情况，就需要限制硬件加速，这个兼容性的问题是因为硬件加速是把View
//的绘制函数转化为使用OpenGL的函数来进完成实际的绘制的，那么必然会存在OpenGL中不支持原始
//回执函数的情况，对于这些绘制函数，就会失效。
//3.硬件加速的消耗问题，因为是使用OpenGL，需要把系统中OpenGL加载到内存中，OpenGL API调用
//就会占用8MB，而实际上会占用更多内存，并且使用了硬件必然增加耗电量了。
//4.硬件加速的优势还有display list的设计，使用这个我们不需要每次重绘都执行大量的代码，基于软件
//的绘制模式会重绘脏区域内的所有控件，而display只会更新列表，然后绘制列表内的控件。
//5. CPU更擅长复杂逻辑控制，而GPU得益于大量ALU和并行结构设计，更擅长数学运算。48、ContentProvider的权限管理(读写分离，权限控制-精确到表级，URL控
//制)。
//对于ContentProvider暴露出来的数据，应该是存储在自己应用内存中的数据，对于一些存储在外部
//存储器上的数据，并不能限制访问权限，使用ContentProvider就没有意义了。对于ContentProvider而
//言，有很多权限控制，可以在AndroidManifest.xml文件中对节点的属性进行配置，一般使用如下一些
//属性设置：
//android:grantUriPermssions:临时许可标志。
//android:permission:Provider读写权限。
//android:readPermission:Provider的读权限。
//android:writePermission:Provider的写权限。
//android:enabled:标记允许系统启动Provider。
//android:exported:标记允许其他应用程序使用这个Provider。
//android:multiProcess:标记允许系统启动Provider相同的进程中调用客户端。
//49、Fragment状态保存
//Fragment状态保存入口:
//1、Activity的状态保存, 在Activity的onSaveInstanceState()里, 调用了FragmentManger的
//saveAllState()方法, 其中会对mActive中各个Fragment的实例状态和View状态分别进行保存.
//2、FragmentManager还提供了public方法: saveFragmentInstanceState(), 可以对单个Fragment进行
//状态保存, 这是提供给我们用的。
//3、FragmentManager的moveToState()方法中, 当状态回退到ACTIVITY_CREATED, 会调用
//saveFragmentViewState()方法, 保存View的状态.
//50、直接在Activity中创建一个thread跟在service中创建一个thread之间的区
//别？
//在Activity中被创建：该Thread的就是为这个Activity服务的，完成这个特定的Activity交代的任务，主动
//通知该Activity一些消息和事件，Activity销毁后，该Thread也没有存活的意义了。
//在Service中被创建：这是保证最长生命周期的Thread的唯一方式，只要整个Service不退出，Thread就
//可以一直在后台执行，一般在Service的onCreate()中创建，在onDestroy()中销毁。所以，在Service中
//创建的Thread，适合长期执行一些独立于APP的后台任务，比较常见的就是：在Service中保持与服务
//器端的长连接。
//51、如何计算一个Bitmap占用内存的大小，怎么保证加载Bitmap不产生内存溢
//出？
//注：这里inDensity表示目标图片的dpi（放在哪个资源文件夹下），inTargetDensity表示目标屏幕的
//dpi，所以你可以发现inDensity和inTargetDensity会对Bitmap的宽高进行拉伸，进而改变Bitmap占用
//内存的大小。
//在Bitmap里有两个获取内存占用大小的方法。
//getByteCount()：API12 加入，代表存储 Bitmap 的像素需要的最少内存。
//getAllocationByteCount()：API19 加入，代表在内存中为 Bitmap 分配的内存大小，代替了
//getByteCount() 方法。
//在不复用 Bitmap 时，getByteCount() 和 getAllocationByteCount 返回的结果是一样的。在通过复用
//Bitamp 占用内存大小 = 宽度像素 x （inTargetDensity / inDensity） x 高度像素 x
//（inTargetDensity / inDensity）x 一个像素所占的内存Bitmap 来解码图片时，那么 getByteCount() 表示新解码图片占用内存的大 小，
//getAllocationByteCount() 表示被复用 Bitmap 真实占用的内存大小（即 mBuffer 的长度）。
//为了保证在加载Bitmap的时候不产生内存溢出，可以使用BitmapFactory进行图片压缩，主要有以下几
//个参数：
//BitmapFactory.Options.inPreferredConfig：将ARGB_8888改为RGB_565，改变编码方式，节约内
//存。
//BitmapFactory.Options.inSampleSize：缩放比例，可以参考Luban那个库，根据图片宽高计算出合适
//的缩放比例。
//BitmapFactory.Options.inPurgeable：让系统可以内存不足时回收内存。
//52、对于应用更新这块是如何做的？(灰度，强制更新，分区域更新)
//1、通过接口获取线上版本号，versionCode
//2、比较线上的versionCode 和本地的versionCode，弹出更新窗口
//3、下载APK文件（文件下载）
//4、安装APK
//灰度：
//(1)找单一渠道投放特别版本。
//(2)做升级平台的改造，允许针对部分用户推送升级通知甚至版本强制升级。
//(3)开放单独的下载入口。
//(4)是两个版本的代码都打到app包里，然后在app端植入测试框架，用来控制显示哪个版本。测试框架
//负责与服务器端api通信，由服务器端控制app上A/B版本的分布，可以实现指定的一组用户看到A版
//本，其它用户看到B版本。服务端会有相应的报表来显示A/B版本的数量和效果对比。最后可以由服务端
//的后台来控制，全部用户在线切换到A或者B版本~
//无论哪种方法都需要做好版本管理工作，分配特别的版本号以示区别。
//当然，既然是做灰度，数据监控（常规数据、新特性数据、主要业务数据）还是要做到位，该打的数据
//桩要打。
//还有，灰度版最好有收回的能力，一般就是强制升级下一个正式版。
//强制更新:一般的处理就是进入应用就弹窗通知用户有版本更新，弹窗可以没有取消按钮并不能取消。这
//样用户就只能选择更新或者关闭应用了，当然也可以添加取消按钮，但是如果用户选择取消则直接退出
//应用。
//增量更新：bsdiff：二进制差分工具bsdiff是相应的补丁合成工具,根据两个不同版本的二进制文件，生
//成补丁文件.patch文件。通过bspatch使旧的apk文件与不定文件合成新的apk。 注意通过apk文件的
//md5值进行区分版本。
//53、请解释安卓为啥要加签名机制。
//1、发送者的身份认证
//由于开发商可能通过使用相同的 Package Name 来混淆替换已经安装的程序，以此保证签名不同的包
//不被替换。
//2、保证信息传输的完整性
//签名对于包中的每个文件进行处理，以此确保包中内容不被替换。
//3、防止交易中的抵赖发生， Market 对软件的要求。
//54、为什么bindService可以跟Activity生命周期联动？
//1、bindService 方法执行时，LoadedApk 会记录 ServiceConnection 信息。2、Activity 执行 finish 方法时，会通过 LoadedApk 检查 Activity 是否存在未注销/解绑的
//BroadcastReceiver 和 ServiceConnection，如果有，那么会通知 AMS 注销/解绑对应的
//BroadcastReceiver 和 Service，并打印异常信息，告诉用户应该主动执行注销/解绑的操作。
//55、如何通过Gradle配置多渠道包？
//用于生成不同渠道的包
//执行./gradlew assembleRelease ，将会打出所有渠道的release包；
//执行./gradlew assembleWandoujia，将会打出豌豆荚渠道的release和debug版的包；
//执行./gradlew assembleWandoujiaRelease将生成豌豆荚的release包。
//因此，可以结合buildType和productFlavor生成不同的Build Variants，即类型与渠道不同的组合。
//56、activty和Fragmengt之间怎么通信，Fragmengt和Fragmengt怎么通信？
//（一）Handler
//（二）广播
//（三）事件总线：EventBus、RxBus、Otto
//（四）接口回调
//（五）Bundle和setArguments(bundle)
//57、自定义view效率高于xml定义吗？说明理由。
//自定义view效率高于xml定义：
//1、少了解析xml。
//2.、自定义View 减少了ViewGroup与View之间的测量,包括父量子,子量自身,子在父中位置摆放,当子
//view变化时,父的某些属性都会跟着变化。
//58、广播注册一般有几种，各有什么优缺点？
//第一种是常驻型(静态注册)：当应用程序关闭后如果有信息广播来，程序也会被系统调用，自己运行。
//第二种不常驻(动态注册)：广播会跟随程序的生命周期。
//动态注册
//优点：
//在android的广播机制中，动态注册优先级高于静态注册优先级，因此在必要情况下，是需要动态注册
//广播接收者的。
//缺点：
//当用来注册的 Activity 关掉后，广播也就失效了。
//android {
//productFlavors {
//xiaomi {}
//baidu {}
//wandoujia {}
//_360 {} // 或“"360"{}”，数字需下划线开头或加上双引号
//}
//}静态注册
//优点：
//无需担忧广播接收器是否被关闭，只要设备是开启状态，广播接收器就是打开着的。
//59、服务启动一般有几种，服务和activty之间怎么通信，服务和服务之间怎么通
//信
//方式：
//1、startService：
//onCreate()--->onStartCommand() ---> onDestory()
//如果服务已经开启，不会重复的执行onCreate()， 而是会调用onStartCommand()。一旦服务开启跟调
//用者(开启者)就没有任何关系了。
//开启者退出了，开启者挂了，服务还在后台长期的运行。
//开启者不能调用服务里面的方法。
//2、bindService：
//onCreate() --->onBind()--->onunbind()--->onDestory()
//bind的方式开启服务，绑定服务，调用者挂了，服务也会跟着挂掉。
//绑定者可以调用服务里面的方法。
//通信：
//1、通过Binder对象。
//2、通过broadcast(广播)。
//60、ddms 和 traceView 的区别？
//ddms 原意是：davik debug monitor service。简单的说 ddms 是一个程序执行查看器，在里面可以看
//见线程和堆栈等信息，traceView 是程序性能分析器。traceview 是 ddms 中的一部分内容。
//Traceview 是 Android 平台特有的数据采集和分析工具，它主要用于分析 Android 中应用程序的
//hotspot（瓶颈）。Traceview 本身只是一个数据分析工具，而数据的采集则需要使用 Android SDK 中
//的 Debug 类或者利用DDMS 工具。二者的用法如下：开发者在一些关键代码段开始前调用 Android
//SDK 中 Debug 类的 startMethodTracing 函数，并在关键代码段结束前调用 stopMethodTracing 函
//数。这两个函数运行过程中将采集运行时间内该应用所有线程（注意，只能是 Java线程） 的函数执行
//情况， 并将采集数据保存到/mnt/sdcard/下的一个文件中。 开发者然后需要利用 SDK 中的 Traceview
//工具来分析这些数据。
//61、ListView卡顿原因
//Adapter的getView方法里面convertView没有使用setTag和getTag方式；
//在getView方法里面ViewHolder初始化后的赋值或者是多个控件的显示状态和背景的显示没有优化好，
//抑或是里面含有复杂的计算和耗时操作；
//在getView方法里面 inflate的row 嵌套太深（布局过于复杂）或者是布局里面有大图片或者背景所致；
//Adapter多余或者不合理的notifySetDataChanged；
//listview 被多层嵌套，多次的onMessure导致卡顿，如果多层嵌套无法避免，建议把listview的高和宽设
//置为match_parent. 如果是代码继承的listview，那么也请你别忘记为你的继承类添加上
//LayoutPrams，注意高和宽都mactch_parent的；
//62、AndroidManifest的作用与理解AndroidManifest.xml文件，也叫清单文件，来获知应用中是否包含该组件，如果有会直接启动该组
//件。可以理解是一个应用的配置文件。
//作用：
//为应用的 Java 软件包命名。软件包名称充当应用的唯一标识符。
//描述应用的各个组件，包括构成应用的 Activity、服务、广播接收器和内容提供程序。它还为实现
//每个组件的类命名并发布其功能，例如它们可以处理的 Intent - 消息。这些声明向 Android 系统
//告知有关组件以及可以启动这些组件的条件的信息。
//确定托管应用组件的进程。
//声明应用必须具备哪些权限才能访问 API 中受保护的部分并与其他应用交互。还声明其他应用与
//该应用组件交互所需具备的权限
//列出 Instrumentation类，这些类可在应用运行时提供分析和其他信息。这些声明只会在应用处于
//开发阶段时出现在清单中，在应用发布之前将移除。
//声明应用所需的最低 Android API 级别
//列出应用必须链接到的库
//63、LaunchMode应用场景
//standard，创建一个新的Activity。
//singleTop，栈顶不是该类型的Activity，创建一个新的Activity。否则，onNewIntent。
//singleTask，回退栈中没有该类型的Activity，创建Activity，否则，onNewIntent+ClearTop。
//注意:
//设置了"singleTask"启动模式的Activity，它在启动的时候，会先在系统中查找属性值affinity等于它的属
//性值taskAffinity的Task存在；如果存在这样的Task，它就会在这个Task中启动，否则就会在新的任务栈
//中启动。因此， 如果我们想要设置了"singleTask"启动模式的Activity在新的任务中启动，就要为它设置
//一个独立的taskAffinity属性值。
//如果设置了"singleTask"启动模式的Activity不是在新的任务中启动时，它会在已有的任务中查看是否已
//经存在相应的Activity实例， 如果存在，就会把位于这个Activity实例上面的Activity全部结束掉，即最终
//这个Activity 实例会位于任务的Stack顶端中。
//在一个任务栈中只有一个”singleTask”启动模式的Activity存在。他的上面可以有其他的Activity。这点与
//singleInstance是有区别的。
//singleInstance，回退栈中，只有这一个Activity，没有其他Activity。
//singleTop适合接收通知启动的内容显示页面。
//例如，某个新闻客户端的新闻内容页面，如果收到10个新闻推送，每次都打开一个新闻内容页面是很烦
//人的。
//singleTask适合作为程序入口点。
//例如浏览器的主界面。不管从多少个应用启动浏览器，只会启动主界面一次，其余情况都会走
//onNewIntent，并且会清空主界面上面的其他页面。
//singleInstance应用场景：
//闹铃的响铃界面。 你以前设置了一个闹铃：上午6点。在上午5点58分，你启动了闹铃设置界面，并按
//Home 键回桌面；在上午5点59分时，你在微信和朋友聊天；在6点时，闹铃响了，并且弹出了一个对
//话框形式的 Activity(名为 AlarmAlertActivity) 提示你到6点了(这个 Activity 就是以 SingleInstance 加载
//模式打开的)，你按返回键，回到的是微信的聊天界面，这是因为 AlarmAlertActivity 所在的 Task 的栈
//只有他一个元素， 因此退出之后这个 Task 的栈空了。如果是以 SingleTask 打开 AlarmAlertActivity，
//那么当闹铃响了的时候，按返回键应该进入闹铃设置界面。64、说说Activity、Intent、Service 是什么关系
//他们都是 Android 开发中使用频率最高的类。其中 Activity 和 Service 都是 Android 四大组件之一。他
//俩都是
//Context 类的子类 ContextWrapper 的子类，因此他俩可以算是兄弟关系吧。不过兄弟俩各有各自的本
//领，Activity
//负责用户界面的显示和交互，Service 负责后台任务的处理。Activity 和 Service 之间可以通过 Intent 传
//递数据，因此
//可以把 Intent 看作是通信使者。
//65、ApplicationContext和ActivityContext的区别
//这是两种不同的context，也是最常见的两种.第一种中context的生命周期与Application的生命周期相
//关的，context随着Application的销毁而销毁，伴随application的一生，与activity的生命周期无关.第
//二种中的context跟Activity的生命周期是相关的，但是对一个Application来说，Activity可以销毁几
//次，那么属于Activity的context就会销毁多次.至于用哪种context，得看应用场景。还有就是，在使用
//context的时候，小心内存泄露，防止内存泄露，注意一下几个方面：
//不要让生命周期长的对象引用activity context，即保证引用activity的对象要与activity本身生命周
//期是一样的。
//对于生命周期长的对象，可以使用application context。
//避免非静态的内部类，尽量使用静态类，避免生命周期问题，注意内部类对外部对象引用导致的生
//命周期变化。
//66、Handler、Thread和HandlerThread的差别
//1、Handler：在android中负责发送和处理消息，通过它可以实现其他支线线程与主线程之间的消息通
//讯。
//2、Thread：Java进程中执行运算的最小单位，亦即执行处理机调度的基本单位。某一进程中一路单独
//运行的程序。
//3、HandlerThread：一个继承自Thread的类HandlerThread，Android中没有对Java中的Thread进行
//任何封装，而是提供了一个继承自Thread的类HandlerThread类，这个类对Java的Thread做了很多便
//利的封装。HandlerThread继承于Thread，所以它本质就是个Thread。与普通Thread的差别就在于，
//它在内部直接实现了Looper的实现，这是Handler消息机制必不可少的。有了自己的looper，可以让我
//们在自己的线程中分发和处理消息。如果不用HandlerThread的话，需要手动去调用Looper.prepare()
//和Looper.loop()这些方法。
//67、ThreadLocal的原理
//ThreadLocal是一个关于创建线程局部变量的类。使用场景如下所示：
//实现单个线程单例以及单个线程上下文信息存储，比如交易id等。
//实现线程安全，非线程安全的对象使用ThreadLocal之后就会变得线程安全，因为每个线程都会有
//一个对应的实例。
//承载一些线程相关的数据，避免在方法中来回传递参数。
//当需要使用多线程时，有个变量恰巧不需要共享，此时就不必使用synchronized这么麻烦的关键字来锁
//住，每个线程都相当于在堆内存中开辟一个空间，线程中带有对共享变量的缓冲区，通过缓冲区将堆内
//存中的共享变量进行读取和操作，ThreadLocal相当于线程内的内存，一个局部变量。每次可以对线程
//自身的数据读取和操作，并不需要通过缓冲区与 主内存中的变量进行交互。并不会像synchronized那
//样修改主内存的数据，再将主内存的数据复制到线程内的工作内存。ThreadLocal可以让线程独占资
//源，存储于线程内部，避免线程堵塞造成CPU吞吐下降。
//在每个Thread中包含一个ThreadLocalMap，ThreadLocalMap的key是ThreadLocal的对象，value是
//独享数据。68、计算一个view的嵌套层级
//69、MVP，MVVM，MVC解释和实践
//MVC:
//视图层(View)
//对应于xml布局文件和java代码动态view部分
//控制层(Controller)
//MVC中Android的控制层是由Activity来承担的，Activity本来主要是作为初始化页面，展示数据的
//操作，但是因为XML视图功能太弱，所以Activity既要负责视图的显示又要加入控制逻辑，承担的
//功能过多。
//模型层(Model)
//针对业务模型，建立数据结构和相关的类，它主要负责网络请求，数据库处理，I/O的操作。
//总结
//具有一定的分层，model彻底解耦，controller和view并没有解耦
//层与层之间的交互尽量使用回调或者去使用消息机制去完成，尽量避免直接持有
//controller和view在android中无法做到彻底分离，但在代码逻辑层面一定要分清
//业务逻辑被放置在model层，能够更好的复用和修改增加业务。
//MVP
//通过引入接口BaseView，让相应的视图组件如Activity，Fragment去实现BaseView，实现了视图层的
//独立，通过中间层Preseter实现了Model和View的完全解耦。MVP彻底解决了MVC中View和Controller
//傻傻分不清楚的问题，但是随着业务逻辑的增加，一个页面可能会非常复杂，UI的改变是非常多，会有
//非常多的case，这样就会造成View的接口会很庞大。
//MVVM
//MVP中我们说过随着业务逻辑的增加，UI的改变多的情况下，会有非常多的跟UI相关的case，这样就会
//造成View的接口会很庞大。而MVVM就解决了这个问题，通过双向绑定的机制，实现数据和UI内容，只
//要想改其中一方，另一方都能够及时更新的一种设计理念，这样就省去了很多在View层中写很多case的
//情况，只需要改变数据就行。
//MVVM与DataBinding的关系？
//MVVM是一种思想，DataBinding是谷歌推出的方便实现MVVM的工具。
//看起来MVVM很好的解决了MVC和MVP的不足，但是由于数据和视图的双向绑定，导致出现问题时不太
//好定位来源，有可能数据问题导致，也有可能业务逻辑中对视图属性的修改导致。如果项目中打算用
//MVVM的话可以考虑使用官方的架构组件ViewModel、LiveData、DataBinding去实现MVVM。
//三者如何选择？
//如果项目简单，没什么复杂性，未来改动也不大的话，那就不要用设计模式或者架构方法，只需要
//将每个模块封装好，方便调用即可，不要为了使用设计模式或架构方法而使用。
//private int getParents(ViewParents view){
//if(view.getParents() == null)
//return 0;
//} else {
//return (1 + getParents(view.getParents));
//}
//}对于偏向展示型的app，绝大多数业务逻辑都在后端，app主要功能就是展示数据，交互等，建议
//使用mvvm。
//对于工具类或者需要写很多业务逻辑app，使用mvp或者mvvm都可。
//70、SharedPrefrences的apply和commit有什么区别？
//这两个方法的区别在于：
//1. apply没有返回值而commit返回boolean表明修改是否提交成功。
//2. apply是将修改数据原子提交到内存, 而后异步真正提交到硬件磁盘, 而commit是同步的提交到硬
//件磁盘，因此，在多个并发的提交commit的时候，他们会等待正在处理的commit保存到磁盘后
//在操作，从而降低了效率。而apply只是原子的提交到内容，后面有调用apply的函数的将会直接
//覆盖前面的内存数据，这样从一定程度上提高了很多效率。
//3. apply方法不会提示任何失败的提示。
//由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，如果对提交的结果不
//关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。
//71、Base64、MD5是加密方法么？
//Base64是什么？
//Base64是用文本表示二进制的编码方式，它使用4个字节的文本来表示3个字节的原始二进制数据。
//它将二进制数据转换成一个由64个可打印的字符组成的序列：A-Za-z0-9+/
//MD5是什么？
//MD5是哈希算法的一种，可以将任意数据产生出一个128位（16字节）的散列值，用于确保信息传输完
//整一致。我们常在注册登录模块使用MD5，用户密码可以使用MD5加密的方式进行存储。如：
//md5(hello world,32) = 5eb63bbbe01eeed093cb22bb8f5acdc3
//加密，指的是对数据进行转换以后，数据变成了另一种格式，并且除了拿到解密方法的人，没人能把数
//据转换回来。
//MD5是一种信息摘要算法，它是不可逆的，不可以解密。所以它只能算的上是一种单向加密算法。
//Base64也不是加密算法，它是一种数据编码方式，虽然是可逆的，但是它的编码方式是公开的，无所
//谓加密。
//72、HttpClient和HttpConnection的区别？
//Http Client适用于web浏览器，拥有大量灵活的API，实现起来比较稳定，且其功能比较丰富，提供了
//很多工具，封装了http的请求头，参数，内容体，响应，还有一些高级功能，代理、COOKIE、鉴权、
//压缩、连接池的处理。
//但是，正因此，在不破坏兼容性的前提下，其庞大的API也使人难以改进，因此Android团队对于修
//改优化Apache Http Client并不积极。(并在Android 6.0中抛弃了Http Client，替换成OkHttp)
//HttpURLConnection对于大部分功能都进行了包装，Http Client的高级功能代码会较复杂，另外，
//HttpURLConnection在Android 2.3中增加了一些Https方面的改进(包括Http Client，两者都支持
//https)。且在Android 4.0中增加了response cache。当缓存被安装后(调用HttpResponseCache的
//install()方法)，所有的HTTP请求都会满足以下三种情况：
//所有的缓存响应都由本地存储来提供。因为没有必要去发起任务的网络连接请求，所有的响应都可
//以立刻获取到。
//视情况而定的缓存响应必须要有服务器来进行更新检查。比如说客户端发起了一条类似于 “如
//果/foo.png这张图片发生了改变，就将它发送给我” 这样的请求，服务器需要将更新后的数据进行
//返回，或者返回一个304 Not Modified状态。如果请求的内容没有发生，客户端就不会下载任何
//数据。
//没有缓存的响应都是由服务器直接提供的。这部分响应会在稍后存储到响应缓存中。在Android 2.2版本之前，HttpClient拥有较少的bug，因此使用它是最好的选择。
//而在Android 2.3版本及以后，HttpURLConnection则是最佳的选择。它的API简单，体积较小，因
//而非常适用于Android项目。压缩和缓存机制可以有效地减少网络访问的流量，在提升速度和省电方面
//也起到了较大的作用。对于新的应用程序应该更加偏向于使用HttpURLConnection，因为在以后的工作
//当中Android官方也会将更多的时间放在优化HttpURLConnection上面。
//73、ActivityA跳转ActivityB然后B按back返回A，各自的生命周期顺序，A与B均
//不透明。
//ActivityA跳转到ActivityB：
//ActivityB返回ActivityA：
//74、如何通过广播拦截和abort一条短信？
//可以监听这条信号，在传递给真正的接收程序时，我们将自定义的广播接收程序的优先级大于它，并且
//取消广播的传播，这样就可以实现拦截短信的功能了。
//75、BroadcastReceiver，LocalBroadcastReceiver 区别？
//1、应用场景
//1、BroadcastReceiver用于应用之间的传递消息；
//2、而LocalBroadcastManager用于应用内部传递消息，比broadcastReceiver更加高效。
//2、安全
//1、BroadcastReceiver使用的Content API，所以本质上它是跨应用的，所以在使用它时必须要考虑
//到不要被别的应用滥用；
//2、LocalBroadcastManager不需要考虑安全问题，因为它只在应用内部有效。
//3、原理方面
//(1) 与BroadcastReceiver是以 Binder 通讯方式为底层实现的机制不同，LocalBroadcastManager 的核
//心实现实际还是 Handler，只是利用到了 IntentFilter 的 match 功能，至于 BroadcastReceiver 换成其
//他接口也无所谓，顺便利用了现成的类和概念而已。
//(2) LocalBroadcastManager因为是 Handler 实现的应用内的通信，自然安全性更好，效率更高。
//76、如何选择第三方，从那些方面考虑？
//大方向：从软件环境做判断
//Activity A：onPause
//Activity B：onCreate
//Activity B：onStart
//Activity B：onResume
//Activity A：onStop
//Activity B：onPause
//Activity A：onRestart
//Activity A：onStart
//Activity A：onResume
//Activity B：onStop
//Activity B：onDestroy性能是开源软件第一解决的问题。
//一个好的生态，是一个优秀的开源库必备的，取决标准就是观察它是否一直在持续更新迭代，是否能及
//时处理github上用户提出来的问题。大家在社区针对这个开源库是否有比较活跃的探讨。
//背景，该开源库由谁推出，由哪个公司推出来的。
//用户数和有哪些知名的企业落地使用
//小方向：从软件开发者的角度做判断
//是否解决了我们现有问题或长期来看带来的维护成本。
//公司有多少人会。
//学习成本。
//77、简单说下接入支付的流程，是否自己接入过支付功能？
//Alipay支付功能：
//1.首先登录支付宝开放平台创建应用，并给应用添加App支付功能，
//由于App支付功能需要签约，因此需要上传公司信息和证件等资料进行签约。
//2.签约成功后，需要配置秘钥。使用支付宝提供的工具生成RSA公钥和私钥，公钥需要设置到管理后
//台。
//3.android studio集成
//78、单例实现线程的同步的要求：
//1.单例类确保自己只有一个实例(构造函数私有:不被外部实例化,也不被继承)。
//2.单例类必须自己创建自己的实例。
//3.单例类必须为其他对象提供唯一的实例。
//79、如何保证Service不被杀死？
//Android 进程不死从3个层面入手：
//A.提供进程优先级，降低进程被杀死的概率
//方法一：监控手机锁屏解锁事件，在屏幕锁屏时启动1个像素的 Activity，在用户解锁时将 Activity 销毁
//掉。
//方法二：启动前台service。
//方法三：提升service优先级：
//在AndroidManifest.xml文件中对于intent-filter可以通过android:priority = "1000"这个属性设置最高
//优先级，1000是最高值，如果数字越小则优先级越低，同时适用于广播。
//B. 在进程被杀死后，进行拉活
//方法一：注册高频率广播接收器，唤起进程。如网络变化，解锁屏幕，开机等
//方法二：双进程相互唤起。
//方法三：依靠系统唤起。
//（1）copy jar包；
//（2）发起支付请求，处理支付请求。方法四：onDestroy方法里重启service：service + broadcast 方式，就是当service走ondestory的时
//候，发送一个自定义的广播，当收到广播的时候，重新启动service；
//C. 依靠第三方
//根据终端不同，在小米手机（包括 MIUI）接入小米推送、华为手机接入华为推送；其他手机可以考虑
//接入腾讯信鸽或极光推送与小米推送做 A/B Test。
//80、说说ContentProvider、ContentResolver、ContentObserver 之间的关
//系？
//ContentProvider：管理数据，提供数据的增删改查操作，数据源可以是数据库、文件、XML、网络
//等，ContentProvider为这些数据的访问提供了统一的接口，可以用来做进程间数据共享。
//ContentResolver：ContentResolver可以为不同URI操作不同的ContentProvider中的数据，外部进程
//可以通过ContentResolver与ContentProvider进行交互。
//ContentObserver：观察ContentProvider中的数据变化，并将变化通知给外界。
//81、如何导入外部数据库?
//把原数据库包括在项目源码的 res/raw。
//android系统下数据库应该存放在 /data/data/com.（package name）/ 目录下，所以我们需要做的是
//把已有的数据库传入那个目录下。操作方法是用FileInputStream读取原数据库，再用
//FileOutputStream把读取到的东西写入到那个目录。
//82、LinearLayout、FrameLayout、RelativeLayout性能对比，为什么？
//RelativeLayout会让子View调用2次onMeasure，LinearLayout 在有weight时，也会调用子 View 2次
//onMeasure
//RelativeLayout的子View如果高度和RelativeLayout不同，则会引发效率问题，当子View很复杂时，这
//个问题会更加严重。如果可以，尽量使用padding代替margin。
//在不影响层级深度的情况下,使用LinearLayout和FrameLayout而不是RelativeLayout。
//为什么Google给开发者默认新建了个RelativeLayout，而自己却在DecorView中
//用了个LinearLayout？
//因为DecorView的层级深度是已知而且固定的，上面一个标题栏，下面一个内容栏。采用
//RelativeLayout并不会降低层级深度，所以此时在根节点上用LinearLayout是效率最高的。而之所以给
//开发者默认新建了个RelativeLayout是希望开发者能采用尽量少的View层级来表达布局以实现性能最
//优，因为复杂的View嵌套对性能的影响会更大一些。
//83、scheme跳转协议
//Android中的scheme是一种页面内跳转协议，通过定义自己的scheme协议，可以跳转到app中的各个
//页面
//服务器可以定制化告诉app跳转哪个页面
//App可以通过跳转到另一个App页面
//可以通过H5页面跳转页面
//84、HandlerThread
//1、HandlerThread原理当系统有多个耗时任务需要执行时，每个任务都会开启个新线程去执行耗时任务，这样会导致系统多次
//创建和销毁线程，从而影响性能。为了解决这一问题，Google提出了HandlerThread，
//HandlerThread本质上是一个线程类，它继承了Thread。HandlerThread有自己的内部Looper对象，
//可以进行loopr循环。通过获取HandlerThread的looper对象传递给Handler对象，可以在
//handleMessage()方法中执行异步任务。创建HandlerThread后必须先调用HandlerThread.start()方
//法，Thread会先调用run方法，创建Looper对象。当有耗时任务进入队列时，则不需要开启新线程，在
//原有的线程中执行耗时任务即可，否则线程阻塞。它在Android中的一个具体的使用场景是
//IntentService。由于HanlderThread的run()方法是一个无限循环，因此当明确不需要再使用
//HandlerThread时，可以通过它的quit或者quitSafely方法来终止线程的执行。
//2、HanlderThread的优缺点
//HandlerThread优点是异步不会堵塞，减少对性能的消耗。
//HandlerThread缺点是不能同时继续进行多任务处理，要等待进行处理，处理效率较低。
//HandlerThread与线程池不同，HandlerThread是一个串队列，背后只有一个线程。
//85、IntentService
//IntentService是一种特殊的Service，它继承了Service并且它是一个抽象类，因此必须创建它的子类才
//能使用IntentService。
//原理
//在实现上，IntentService封装了HandlerThread和Handler。当IntentService被第一次启动时，它的
//onCreate()方法会被调用，onCreat()方法会创建一个HandlerThread，然后使用它的Looper来构造一
//个Handler对象mServiceHandler，这样通过mServiceHandler发送的消息最终都会在HandlerThread
//中执行。
//生成一个默认的且与主线程互相独立的工作者线程来执行所有传送至onStartCommand()方法的
//Intetnt。
//生成一个工作队列来传送Intent对象给onHandleIntent()方法，同一时刻只传送一个Intent对象，这样
//一来，你就不必担心多线程的问题。在所有的请求(Intent)都被执行完以后会自动停止服务，所以，你
//不需要自己去调用stopSelf()方法来停止。
//该服务提供了一个onBind()方法的默认实现，它返回null。
//提供了一个onStartCommand()方法的默认实现，它将Intent先传送至工作队列，然后从工作队列中每
//次取出一个传送至onHandleIntent()方法，在该方法中对Intent做相应的处理。
//为什么在mServiceHandler的handleMessage()回调方法中执行完onHandlerIntent()方法后要使用
//带参数的stopSelf()方法？
//因为stopSel()方法会立即停止服务，而stopSelf（int startId）会等待所有的消息都处理完毕后才终止服
//务，一般来说，stopSelf(int startId)在尝试停止服务之前会判断最近启动服务的次数是否和startId相
//等，如果相等就立刻停止服务，不相等则不停止服务。
//86、如何将一个Activity设置成窗口的样式。
//中配置：
//另外
//android:theme="@android:style/Theme.Dialog"
//android:theme="@android:style/Theme.Translucnt"是设置透明。
//87、Android中跨进程通讯的几种方式
//1：访问其他应用程序的Activity
//如调用系统通话应用
//2：Content Provider
//如访问系统相册
//3：广播（Broadcast）
//如显示系统时间
//4：AIDL服务
//88、显示Intent与隐式Intent的区别
//对明确指出了目标组件名称的Intent，我们称之为“显式Intent”。
//对于没有明确指出目标组件名称的Intent，则称之为“隐式 Intent”。
//对于隐式意图，在定义Activity时，指定一个intent-filter，当一个隐式意图对象被一个意图过滤器进行
//匹配时，将有三个方面会被参考到：
//动作(Action)
//类别(Category ['kætɪg(ə)rɪ] )
//数据(Data )
//89、Android Holo主题与MD主题的理念，以及你的看法
//Holo Theme
//Holo Theme 是 Android Design 的最基础的呈现方式。因为是最为基础的 Android Design 呈现形式，
//每一台 Android 4.X 的手机系统内部都有集成 Holo Theme 需要的控件，即开发者不需要自己设计控
//件，而是直接从系统里调用相应的控件。在 UI 方面没有任何的亮点，和 Android4.X 的设置/电话的视
//觉效果极度统一。由此带来的好处显而易见，这个应用作为 Android 应用的辨识度极高，且完全不可能
//与系统风格产生冲突。
//Material Design
//Material design其实是单纯的一种设计语言，它包含了系统界面风格、交互、UI,更加专注拟真,更加大
//胆丰富的用色,更加丰富的交互形式,更加灵活的布局形式
//1.鲜明、形象的界面风格，
//2.色彩搭配使得应用看起来非常的大胆、充满色彩感，凸显内容
//Intent callIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:12345678");
//startActivity(callIntent);
//<activity android:name=".MainActivity" android:label="@string/app_name">
//<intent-filter>
//<action android:name="com.wpc.test" />
//<category android:name="android.intent.category.DEFAULT" />
//<data android:mimeType="image/gif"/>
//</intent-filter>
//</activity>3.Material design对于界面的排版非常的重视
//4.Material design的交互设计上采用的是响应式交互，这样的交互设计能把一个应用从简单展现用户所
//请求的信息，提升至能与用户产生更强烈、更具体化交互的工具。
//90、如何让程序自动启动？
//定义一个Braodcastreceiver，action为BOOT——COMPLETE，接受到广播后启动程序。
//91、Fragment 在 ViewPager 里面的生命周期，滑动 ViewPager 的页面时
//Fragment 的生命周期的变化。
//92、如何查看模拟器中的SP与SQList文件。如何可视化查看布局嵌套层数与加载
//时间。
//93、各大平台打包上线的流程与审核时间，常见问题(主流的应用市场说出3-4个)
//94、屏幕适配的处理技巧都有哪些?
//一、为什么要适配
//为了保证用户获得一致的用户体验效果,使得某一元素在Android不同尺寸、不同分辨率的、不同系统的
//手机上具备相同的显示效果，能够保持界面上的效果一致,我们需要对各种手机屏幕进行适配！
//Android系统碎片化：基于Google原生系统，小米定制的MIUI、魅族定制的flyme、华为定制的
//EMUI等等；
//Android机型屏幕尺寸碎片化：5寸、5.5寸、6寸等等；
//Android屏幕分辨率碎片化：320x480、480x800、720x1280、1080x1920等。
//二、基本概念
//像素（px）：像素就是手机屏幕的最小构成单元，px = 1像素点 一般情况下UI设计师的设计图会
//以px作为统一的计量单位。
//分辨率：手机在横向、纵向上的像素点数总和 一般描述成 宽*高 ，即横向像素点个数 * 纵向像素
//点个数（如1080 x 1920），单位：px。
//屏幕尺寸：手机对角线的物理尺寸。单位 英寸（inch），一英寸大约2.54cm 常见的尺寸有4.7
//寸、5寸、5.5寸、6寸。
//屏幕像素密度（dpi）：每英寸的像素点数，例如每英寸内有160个像素点，则其像素密度为
//160dpi，单位：dpi（dots per inch）。
//标准屏幕像素密度（mdpi）： 每英寸长度上还有160个像素点（160dpi），即称为标准屏幕像素
//密度（mdpi）。
//密度无关像素（dp）：与终端上的实际物理像素点无关，可以保证在不同屏幕像素密度的设备上
//显示相同的效果，是安卓特有的长度单位，dp与px的转换：1dp = （dpi / 160 ） * 1px。
//独立比例像素（sp）：字体大小专用单位 Android开发时用此单位设置文字大小，推荐使用
//12sp、14sp、18sp、22sp作为字体大小。
//三、适配方案
//适配的最多的3个分辨率：1280720,19201080,800*480。
//解决方案：
//对于Android的屏幕适配，我认为可以从以下4个方面来做：
//1、布局组件适配
//请务必使用密度无关像素 dp 或独立比例像素 sp 单位指定尺寸。
//使用相对布局或线性布局，不要使用绝对布局使用wrap_content、match_parent、权重
//使用minWidth、minHeight、lines等属性
//dimens使用：
//不同的屏幕尺寸可以定义不同的数值，或者是不同的语言显示我们也可以定义不同的数值，因为翻译后
//的长度一般都不会跟中文的一致。此外，也可以使用百分比布局或者AndroidStudio2.2的新特性约束布
//局。
//2、布局适配
//使用限定符（屏幕密度限定符、尺寸限定符、最小宽度限定符、布局别名、屏幕方向限定符)根据屏幕的
//配置来加载相应的UI布局。
//3、图片资源适配
//使用自动拉伸图.9png图片格式使图片资源自适应屏幕尺寸。
//普通图片和图标：
//建议按照官方的密度类型进行切图即可，但一般我们只需xxhdpi或xxxhdpi的切图即可满足我们的需
//求；
//4、代码适配：
//在代码中使用Google提供的API对设备的屏幕宽度进行测量，然后按照需求进行设置。
//5、接口配合：
//本地加载图片前判断手机分辨率或像素密度，向服务器请求对应级别图片。
//95、动态布局的理解
//96、怎么去除重复代码？
//97、Recycleview和ListView的区别
//98、动态权限适配方案，权限组的概念
//99、Android系统为什么会设计ContentProvider？
//100、下拉状态栏是不是影响activity的生命周期
//101、如果在onStop的时候做了网络请求，onResume的时候怎么恢复？
//102、Debug和Release状态的不同
//103、dp是什么，sp呢，有什么区别
//103、自定义View，ViewGroup注意那些回调？
//104、android中的存储类型
//105、Activity的生命周期，finish调用后其他生命周期还会走么？
//106、有遇到过哪些屏幕和资源适配问题？
//107、项目中遇到哪些难题，最终你是如何解决的？
//108、listview图片加载错乱的原理和解决方案。
//109、invalidate和requestLayout的区别及使用。110、如何反编译，对代码逆向分析。
//111、RemoteViews实现和使用场景
//112、对服务器众多错误码的处理（错误码有好几万个）
//113、adb常用命令行
//114、Android中如何查看一个对象的回收情况？
//115、Activity正常和异常情况下的生命周期
//116、关于< include >< merge >< stub >三者的使用场景
//117、Android对HashMap做了优化后推出的新的容器类是什么？
//118、说下你对服务的理解，如何杀死一个服务。
//119、断点续传实现？
//在本地下载过程中要使用数据库实时存储到底存储到文件的哪个位置了，这样点击开始继续传递时，才
//能通过HTTP的GET请求中的setRequestProperty("Range","bytes=startIndex-endIndex");方法可以告
//诉服务器，数据从哪里开始，到哪里结束。同时在本地的文件写入时，RandomAccessFile的seek()方
//法也支持在文件中的任意位置进行写入操作。最后通过广播或事件总线机制将子线程的进度告诉
//Activity的进度条。关于断线续传的HTTP状态码是206，即HttpStatus.SC_PARTIAL_CONTENT。
        return "";
    }

    private static String decodeString(byte[] bytes) {
        //a4  64  de  56  88  63  43  67
        try {
            return Charset.forName("UTF-16LE").newDecoder().decode(
                    ByteBuffer.wrap(bytes)).toString();
        } catch (CharacterCodingException ex) {
            return null;
        }
    }

    public static ExtMXSerializer getResXmlSerializer() {
        ExtMXSerializer serial = new ExtMXSerializer();
        serial.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "    ");
        serial.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", System.getProperty("line.separator"));
        serial.setProperty("DEFAULT_ENCODING", "utf-8");
        serial.setDisabledAttrEscape(true);
        return serial;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            if (i > 3) {
                break;
            }
            int shift = i * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    private static byte[] intToByte(int in) {
        byte[] b = new byte[4];
        b[3] = (byte) (in & 0xff);
        b[2] = (byte) (in >> 8 & 0xff);
        b[1] = (byte) (in >> 16 & 0xff);
        b[0] = (byte) (in >> 24 & 0xff);
        return b;
    }


    /**
     * 随机汉字
     */
    private static void testRandomStr() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(getRandomWord());
        }

        System.out.println(builder.toString());
        char[] chars = builder.toString().toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            System.out.println(chars[i]);
        }
    }


    /**
     * 获取随机汉字
     *
     * @return
     */
    private static String getRandomWord() {
        String str = "";
        int heightPos;
        int lowPos;
        Random rd = new Random();
        heightPos = 176 + Math.abs(rd.nextInt(39));
        lowPos = 161 + Math.abs(rd.nextInt(93));
        byte[] bt = new byte[2];
        bt[0] = Integer.valueOf(heightPos).byteValue();
        bt[1] = Integer.valueOf(lowPos).byteValue();
        try {
            str = new String(bt, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
