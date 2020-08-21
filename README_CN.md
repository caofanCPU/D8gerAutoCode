# D8gerAutoCode

<a href="https://github.com/caofanCPU/D8gerAutoCode/blob/master/LICENCE"><img src="https://badgen.net/github/license/caofanCPU/D8gerAutoCode?color=green" alt="license"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode/issues"><img src="https://badgen.net/github/open-issues/caofanCPU/D8gerAutoCode" alt="issues"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode/stargazers"><img src="https://badgen.net/github/stars/caofanCPU/D8gerAutoCode" alt="stars"></a>
<a href="http://makeapullrequest.com"><img src="https://badgen.net/badge/PRs/welcome/cyan" alt="PRs Welcome"></a>
<a href="http://www.debuggerpowerzcy.top/"><img src="https://badgen.net/badge/organization/join%20us/cyan" alt="open-source-organization"></a>

##### IDEA代码自动生成插件•`Java版本`
<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/D8-V3.png" /> 
</div>

### 序言
正如JetBrains所说，我相信你在繁忙编码工作中时应该善用工具以节省宝贵的时间。

我时常在思考, "如果其他人已经解决了这些重复工作，为什么我还需要重复解决?"

这个好问题一直驱动着我, 在日常工作中探寻重复部分并开发此插件以节省时间。 

##### 约定
- 通用问题提供规范解法
- 个性化问题只提供示例解法

##### 设计初衷
- 移除开发中的固定的重复编码过程, 提升开发效率
- 提供可用的代码模板, 基本不用修改就可使用的代码
- 支持代码目录配置, 一键自动生成代码, 仅需自行导包即可完成智能编程
- 支持用户自定义配置, 可指定需要生成的文件


##### 功能特性
- 驼峰-下划线-大写-首字母小写-小写, 一键转换, 默认快捷键`alt` + `shift` + `cmd` + `U`  
- 生成代码的注释支持英语|汉语配置
- JSON格式化与单行文本转换, 默认快捷键 `alt`+`shift`+`cmd`+`J` | `S`
- 一键生成`数据表定义SQL` | `Mo` | `Mapper` | `Handler`, 单表增删改查+分页一键搞定
- 结合SwaggerApi, 一键生成带文档定义`Vo`及`Controller`, 支持Vo字段自动排序
- 一键智能编程, 默认快捷键 `alt`+`shift`+`cmd`+`D`
- 支持用户自定义配置, 指定生成哪些文件及文件author等
- 预留彩蛋, 在IDEA中编辑空白文件输入`d8ger`即可得到配置文件模板
- 预留彩蛋, 在IDEA中编辑空白文件输入'nasa'即可得到NASA模板, 针对多行字符串批量处理, 尤其是SQL字段命名处理   
- 预留彩蛋, 在IDEA中编辑空白文件输入'regex'即可得到一些常用的有趣正则表达式, 后期会增加其他代码彩蛋
- SwaggerVo字段排序, 默认快捷键 `alt`+`shift`+`cmd`+`O`


##### 使用手册(v3.0+)
1.打开配置框设置生成参数 `Preference` --> `Other Settings` --> `D8gerAutoCode`

2.勾选要生成的文件及生成路径, 指明 `author`、`author`、`locale`, 保存即可

<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/D8King.jpg" /> 
</div>

##### 使用手册(v3.0以下)
1. 创建Model类  

2. 选中类名, 按下(默认)快捷键 `alt`+`shift`+`cmd`+`D`  

3. 默认配置下, 会生成`D8gerAutoCode`目录及数据表SQL定义和Web代码, 共9个  

4. 你需要做的:
- 多模块工程下, 配置生成文件输出目录, 例如**`Mo.java`**配置`model`目录  
- 根据需求对**`xx.sql`**增加非空约束  
- 如果你生成了**`Controller.java`**文件, ***请注意接口返回对象类型***, 应修改为项目统一的返回类型  
- 在上述步骤中, 代码文件会飘红报错, 别慌, 这需要你自行导包解决  

5.1 默认配置文件模板, 代码彩蛋:  
    - 在`Model类`所在模块的***`resource`***根目录下, 创建`d8ger.properties`属性配置文件  
    - 输入`d8ger`并点击`Tools`-->`D8ger`-->`D8gerMore`

5.2 正则表达式模板, 代码彩蛋:
    - 任意空文本, 输入`regex`并点击`Tools`->`D8ger`->`D8gerMore`, 你会得到一点小惊喜

5.3 NASA模板(多行文本批处理), 代码彩蛋:
    - 任意空文本, 输入`nasa`并点击`Tools`->`D8ger`->`D8gerMore`, 你会得到NASA操作手册
    - 按照NASA手册提示, 自定义配置并输入需要处理的多行文本, 然后你会得到一点小惊喜

5.4 驼峰-下划线-大写-首字母小写-小写, 一键循环转换
    - 选中任意编辑状态下文件里的单词, 按下快捷键`alt` + `shift` + `cmd` + `U`, 你会发现选中单词转换了
    - 如果不是你想要的结果那就继续重复按下快捷键, 直到是你想要的为止

6. 你就得到如下默认配置信息  
    - 推荐你更改的配置项  
        - `author`(你挖的坑可不许署我的名!)  
        - `apiUrlPrefix`(接口url前缀应配置为项目接口统一Url前缀)  
        - 多模块工程下, 生成文件输出目录
    - 其他`boolean`配置项, 是让你选择需要生成哪些文件, 可选指定输出目录  

>以上默认快捷键针对MacOS系统, 对于Windows用户将`cmd`看做`ctrl`即可

### 参与贡献
<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/2020-660X466-D8gerAutoCode.jpeg" /> 
</div>

### 致谢

- 感谢 [![JetBrains](http://file.debuggerpowerzcy.top/power/jetbrains-variant-4.svg)](https://www.jetbrains.com/idea) 团队的开源证书支持, 本项目将秉承开源精神持续开发

##### 如何安装
- IDEA官方插件库安装  
- 源码安装, 这需要你熟悉Gradle, 后期我会详细补充如何使用Gradle开发一个插件  

##### Bug&问题
- 已知问题(最新版本3.0中已修复)
>修改`d8ger.properties`文件后再生成代码, 配置文件不生效

```
解决方法
1.由于IDEA文件采用VFS机制, 作为一个插件去采用监听文件我认为是不合算的
2.此外, 编辑.properties文件时, 内容变更有时会消耗1-2分钟才会更新生效
3.所以, 执行快捷键'cmd' + 's'强制刷新.properties文件, 即可解决
```

> 插件图标显示异常，图标内只出现相交的红叉
<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/W-D8ger.png" /> 
</div>

```
1.这是Intelij IDEA新版本特性遗留的兼容性小BUG
2.升级Intelij IDEA版本, 至少为2019.3.*(2019.2.*及以下都会显示异常)
```

[其他问题, 欢迎前来查房](https://github.com/caofanCPU/D8gerAutoCode/issues)
