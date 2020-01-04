# D8gerAutoCode
##### IDEA代码自动生成插件•**`For Java Web Developer`**版本

##### 约定
- 通用问题插件提供规范解法, 个性化问题插件只提供示例解法

##### 设计初衷
- 移除开发中的固定的重复编码过程, 提升开发效率
- 提供可用的代码模板, 基本不用修改就可使用的代码
- 代码自动生成后, 用户需自行`ctrl` + `C`/`V`到项目相关目录, 自行导包
- 支持用户自定义配置, 可指定需要生成的文件


##### 功能特性
- 一键生成数据表定义SQL/Mapper/Service/Controller, 单表增删改查+分页一键搞定
- 结合SwaggerApi, 一键生成带文档定义Vo及Controller, 支持Vo字段自动排序
- 一键生成文件, 默认快捷键`cmd`+`alt`+`shift`+`D`
- 支持用户自定义配置, 指定生成哪些文件及文件author等
- 预留彩蛋, 在IDEA中编辑空白文件输入'd8ger'即可得到配置文件模板, 后期会增加其他代码彩蛋
- SwaggerVo字段排序, 默认快捷键`cmd`+`alt`+`shift`+`O`


##### 使用截图
1.创建Model类  
<img width="25%" height="40%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCode-1.png" />  
2.选中类名, 按下(默认)快捷键`cmd` + `alt` + `shift` + `D`  
<img width="40%" height="30%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCode-2.png" />  
3.默认配置下, 会生成`D8gerAutoCode`目录及数据表SQL定义和Web代码, 共10个  
<img width="70%" height="40%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCode-3.png" />  
4.你需要做的:
- 移动文件到项目对应的目录下, 例如**xxMo.java**放到`model`目录
- 根据需求对**xx.sql**增加非空约束
- 如果你生成了**xxController.java**文件, ***请注意接口返回对象类型***, 应修改为项目统一的返回类型
- 在上述步骤中, 代码文件会飘红报错, 这需要你自行导包解决  
<img width="70%" height="50%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCode-4.png" />  
5.默认配置文件模板, 代码彩蛋: 
    - 在`Model类`所在模块的***resource***根目录下, 创建`d8ger.properties`属性配置文件
    - 输入`d8ger`, 点击`Tools`-->`D8ger`-->`D8gerMore`  
<img width="40%" height="50%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCode-5.png" />  
6.你就得到如下默认配置信息
    - 推荐你更改的配置项
        - `author`(你挖的坑可不许署我的名!)
        - `apiUrlPrefix`(接口url前缀应配置为项目接口统一Url前缀)
    - 其他`boolean`配置项, 是让你选择需要生成哪些文件  
<img width="40%" height="50%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCode-6.png" />  

##### 如何安装
- IDEA官方插件库安装  
<img width="50%" height="50%" align="center" src="http://file.debuggerpowerzcy.top/power/2019-D8gerAutoCodeIDEA.jpeg" />  
- [下载插件包](http://file.debuggerpowerzcy.top/power/D8gerAutoCode-1.22.zip)  
- 源码安装, 这需要你熟悉Gradle, 后期我会详细补充如何使用Gradle开发一个插件  

##### Bug&问题
- 已知问题
>修改`d8ger.properties`文件后再生成代码, 配置文件不生效
```text
解决方法
1.由于IDEA文件采用VFS机制, 作为一个插件去采用监听文件我认为是不合算的
2.此外, `D8gerAutoCode`目录是辅助, 当我们生成及修改好代码后, 这个目录更应该来去无痕
3.所以, 删除`D8gerAutoCode`目录, 即可解决
```

[其他问题, 欢迎前来查房](https://github.com/caofanCPU/D8gerAutoCode/issues)

