# D8gerAutoCode
##### IDEA代码自动生成插件•`For Java Web Developer`版本

##### 约定
- 通用问题提供规范解法, 个性化问题只提供示例解法

##### 设计初衷
- 移除开发中的固定的重复编码过程, 提升开发效率
- 提供可用的代码模板, 基本不用修改就可使用的代码
- 代码自动生成后, 用户需自行`cmd`+`C` | `V`到项目相关目录, 自行导包
- 支持用户自定义配置, 可指定需要生成的文件


##### 功能特性
- 一键生成`数据表定义SQL` | `Mo` | `Mapper` | `Service`, 单表增删改查+分页一键搞定
- 结合SwaggerApi, 一键生成带文档定义`Vo`及`Controller`, 支持Vo字段自动排序
- 一键生成文件, 默认快捷键 `alt`+`shift`+`cmd`+`D`
- 支持用户自定义配置, 指定生成哪些文件及文件author等
- 预留彩蛋, 在IDEA中编辑空白文件输入'd8ger'即可得到配置文件模板, 后期会增加其他代码彩蛋
- SwaggerVo字段排序, 默认快捷键 `alt`+`shift`+`cmd`+`O`
- JSON格式化与单行文本转换, 默认快捷键 `alt`+`shift`+`cmd`+`J` | `S`


##### 使用截图
1.创建Model类  
2.选中类名, 按下(默认)快捷键 `alt`+`shift`+`cmd`+`D`  
3.默认配置下, 会生成`D8gerAutoCode`目录及数据表SQL定义和Web代码, 共10个  
4.你需要做的:
- 移动文件到项目对应的目录下, 例如**`Mo.java`**放到`model`目录  
- 根据需求对**`xx.sql`**增加非空约束  
- 如果你生成了**`Controller.java`**文件, ***请注意接口返回对象类型***, 应修改为项目统一的返回类型  
- 在上述步骤中, 代码文件会飘红报错, 别慌, 这需要你自行导包解决  
5.默认配置文件模板, 代码彩蛋:  
    - 在`Model类`所在模块的***`resource`***根目录下, 创建`d8ger.properties`属性配置文件  
    - 输入`d8ger`, 点击`Tools`-->`D8ger`-->`D8gerMore`  
6.你就得到如下默认配置信息  
    - 推荐你更改的配置项  
        - `author`(你挖的坑可不许署我的名!)  
        - `apiUrlPrefix`(接口url前缀应配置为项目接口统一Url前缀)  
    - 其他`boolean`配置项, 是让你选择需要生成哪些文件  

>以上默认快捷键针对MacOS系统, 对于Windows用户将`cmd`看做`ctrl`即可

### 使用操作截图
<div align="center">
	<img width="100%" height="100%" src="http://file.debuggerpowerzcy.top/power/2020-Tina-D8gerAutoCode.jpg" /> 
</div>

##### 如何安装
- IDEA官方插件库安装  
- [下载插件包](http://file.debuggerpowerzcy.top/power/D8gerAutoCode-1.23.zip)  
- 源码安装, 这需要你熟悉Gradle, 后期我会详细补充如何使用Gradle开发一个插件  

##### Bug&问题
- 已知问题
>修改`d8ger.properties`文件后再生成代码, 配置文件不生效

```
解决方法
1.由于IDEA文件采用VFS机制, 作为一个插件去采用监听文件我认为是不合算的
2.此外, `D8gerAutoCode`目录是辅助, 当我们生成及修改好代码后, 这个目录更应该来去无痕
3.所以, 删除`D8gerAutoCode`目录, 即可解决
```

[其他问题, 欢迎前来查房](https://github.com/caofanCPU/D8gerAutoCode/issues)

