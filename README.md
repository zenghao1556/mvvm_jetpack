#### 由于经常要开一些新的项目，但是每次都需要现搭框架或者从别的项目中拷贝实在是太麻烦了，所以我打算做一个基于kotlin+组件化+mvvm（databinding+viewmode+livedata）的框架模板以供自己在以后开新项目时能迅速拿来使用，项目本身不写任何业务功能，框架也会不断的完善，本人能力有限希望大家不喜勿喷，但是欢迎提出宝贵意见。

- **1、先画一个简单的架构图**
![Image text](https://raw.githubusercontent.com/zenghao1556/img-folder/master/1639236698615.jpg)


- **2、简单接单介绍下项目中使用到的一些框架**
	- ##### 1. mvvm使用的是jiepack里的常用的几个组件dadabinding、viewmodel、livedata后面会把room也加上
	- ##### 2. 图片加载框架这里使用的glide
	- ##### 3. 本地缓存数据使用的MMKV
	- ##### 4. 路由框架使用阿里的ARouter
	- ##### 5. 事件总栈自己用livedata封装的livedatabus

- **3、通用功能点**
	- ##### 1. APP进程拉活，互联网应用都会关注日活、月活这种数据，所以这里使用了账户同步的方式进行进程拉活，如果有业务需求可以在拉活的service里进行业务逻辑的添加
	- ##### 2. APP性能监测，内存泄漏问题是android应用的常见问题，所以这里集成了leackCanry，方便我们在开发和测试阶段对我们的app内存使用情况进行一个大概的分析
	- ##### 3.热修复，这个地方还没弄，但是最终会选择美团的Robust或腾讯的Tinker
	