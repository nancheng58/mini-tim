# 山东大学软件学院2018级JAVA课程设计
## [English](https://github.com/nancheng58/JAVA-Course-Design-of-Software-College/blob/master/README.md)

# 如果不用代理的话，下面的图应该会挂掉，只能下载项目文件在本地查看。

# 搭建方法

## 依赖环境

## Java JDK+Mysql

## 步骤：
### 将源码下载到本地。

### 本项目采用了mysql作为数据库，要将目录中的 **info.sql** 导入到本地的数据库中

### 将src文件夹中bean包中的Constant 文件中的资源地址改为自定义地址

### 将src文件夹中DB包中的DBConnection文件中的数据库配置改为自定义配置

### 将lib文件夹里的相关jar包解析到项目中

### 完成后，依次点击StartServer 和StartClient 文件即可

### 注：本项目开发环境为Intellij Idea，所以建议使用其作为debug环境。注册功能可能有点问题，建议使用数据库中已存在的账号登录。支持多进程，可以同时打开多个客户端交♂流

实验要求设计一个班级事务管理系统，班内成员可进行查看公告，投票等操作。

最终系统实现的功能

1.权限管理：设管理员与用户两种使用权限，管理员操作过程有日志记录，管理员有权限发布公告和投票。

2.传阅投票：列出同意与不同意选项，每个收到的同学能够看到稿子的当前状态，可加建议栏用于补充意见。投票时显示投票状态和详情。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo2.png)



3.公告传阅：公告发送给班级的每个成员，并给与提醒。公告信息显示在班级的公告面板里，配有带下划线的标题。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo3.png)

4.文件共享：在服务器端每个班级设置共享空间，允许班级成员上传下载（离线）。实现了文件属性的预览，包括文件名，大小，上传时间，上传者。文件下载后可以直接打开。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo4.png)


5.即时通信：独立弹出小窗，基本功能：一对一对话（私聊），发言至班级（群聊），可发送图片。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo5.png)

6.表情发送：实现选择一个表情并发送功能。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo6.png)


7.截图功能：选择截图并发送，监听键盘实现组合键Ctrl+Alt+x快速截图功能。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo7.png)

8.抖动功能：自身抖动并发送抖动消息，实现了双方一起抖动的功能，私聊和群聊都可使用。

9.系统托盘： 
实现了如下功能：左键弹出窗口；右键弹出选项菜单，可以添加班级，创建班级，打开，退出；收到消息时托盘闪烁，直到用户点击托盘为止；
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo91.png)
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo91.png)

10.消息提示音：收到消息时发出消息提示音

11.发送消息功能，支持图片发送。监听消息文本编辑器，监听键盘实现按下Enter键发送消息的功能。


12.绘图板：实现了实时手绘的功能，并发送给当前聊天的用户或班级。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo12.png)

13.头像功能：用户和班级都可以添加头像。

14.登录注册功能：检验密码是否正确。加入了防止重复登录机制。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo14.png)


15.加入班级，创建班级功能： 
可以创建和加入班级，创建班级后自动变为管理员身份。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo151.png)
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo152.png)

16.消息提醒
收到消息后会由图一变为图二。管理员发布文稿，公告，投票时也都会有提示。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo161.png)
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo162.png)

17.聊天气泡功能：绘制了聊天气泡，区分消息本用户与其他用户。聊天气泡可以放置文字和图片。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo17.png)


18.在班群里单击头像即可发起私聊。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo18.png)


19.私聊时点击头像或者用户名可以显示用户信息。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo19.png)


20.添加了小游戏功能，并加入了分数统计功能，查看用户信息时可以查看对方的最高记录。

21.实现了日志功能。用户每次进行请求时都会把结果写入到日志中。
![avatar](https://raw.githubusercontent.com/nancheng58/JAVA-Course-Design-of-Software-College/master/projectdemo/demo21.png)


![image](https://github.com/nancheng58/JAVA-Course-Design-of-Software-College-of-Shandong-University/blob/master/image.png)

一．系统模块架构
系统架构：
C/S 架构，实现客户端与服务器分离，通常客户端运行应用程序，服务器端运行服务程序，应用程序向服务程序提出申请，服务程序分析该申请是否合理，来决定返回数据信息还是返回错误指令。实现了聊天，文件，信息服务的分离，互不干扰。
客户端：客户端包括聊天，文件，信息服务三个客户端及系统的GUI部分。




服务端：包括服务器和数据库。其中服务器包括聊天，文件，信息服务三个服务器，分别监听三个端口。数据库部分包括数据库连接和数据库查询API。










系统模块

系统一共有5个包。分别是bean , DB，GUI，net, util。
一．Bean包是系统附加功能及系统静态资源调用包。
里面有以下五个类：

1.User类：内含用户信息调用方法。

2.Constant类:系统静态工具类，含有存放了缓存文件夹（文件，聊天记录，截屏图片等）的路径和获取文件大小的工具静态方法。

3.Draw类：绘图板功能。通过监听鼠标绘图，调用屏幕截图实现。

4.Emoticon类：表情功能，绘制一个JLabel数组，将表情加入数组中，通过监听每个表情图片实现。

5.Game2048类：2048小游戏，从网上下载的素材，加入了分数统计功能，查看用户信息时可以查看对方的最高记录。

6.ScreenShot类：截图功能，调用系统的Toolkit.getDefaultToolkit().getScreenSize()方法获取全屏截图，监听鼠标确定位置并保存图片以实现此功能。

7.TipMusic类：消息提示音功能，收到消息播放指定声音。


二．GUI包是系统界面和组件包。
包内部还有一个base包，是重写的swing组件和重写的字体类，实现界面美化。除此之外还有8个用户界面类，分别是登录，注册，主页面，发布公告，发布投票，用户详细信息，创建班级，加入班级。及添加消息列表组件charbar和绘制气泡组件chatbubble。


三．net包是系统通信包，里面有4个包base,Client,Json,Server。
1. Base包是客户端和服务器共用的通信基础包，包括Csocket类和Message类。Csocket类是重写的socket工具,Message类是传输的基本协议。
2. Json包是json转换相关的包。数据传输时，系统使用了json转换的形式，可以实现object<->json string。包内部的类实现了将传输消息时用到的object转换为json和json转为object的方法。
3. Client包是客户端相关的包。包内部含有聊天，文件，信息服务三个客户端及监听聊天和文件客户端的接口。聊天客户端实现了聊天信息的收发功能，文件客户端实现了文件的上传和下载功能，信息服务客户端实现了信息的查询和反馈功能（如登录，注册，公告的发布等）。还有一个客户端启动类，负责启动客户端。
4. Server包是服务器相关包。包内部有两个包handler和Server。
1）其中Server包中含有三个server（聊天，文件，信息服务）和一个ServerManager（负责启动三个server）。三个服务器均实现了通信连接的功能。
2）Handler包中配有每个服务器配有一个clienthandler类（消息收发器）和一个消息处理器（messagehandler）。每次有客户端连接，服务器都开一个新的clienthandler线程接受消息，并用messagehandler处理并返回结果给客户端。
3）还有一个服务器启动类，负责启动服务器。

四．DB包是数据库相关包。里面有数据库连接和使用数据库的API，负责处理所有数据的添加，查找，修改，删除命令，并提供数据获取的方法。

五．Util包是系统素材及图片美化包，内部有三个类。
PictureUtil类简化了调用图片路径的方式。PictureUtil.class.getClassLoader().getResource("util/resource/image/" + name))
ImageUtil类是image处理类，可以将图片压缩，创建一个圆形遮罩。
RendererUtil类是渲染类，可以创建图像阴影效果。


