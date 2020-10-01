# D8gerAutoCode

<a href="https://github.com/caofanCPU/D8gerAutoCode"><img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/m/caofanCPU/D8gerAutoCode"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode/stargazers"><img src="https://badgen.net/github/stars/caofanCPU/D8gerAutoCode" alt="stars"></a>
<a href="https://plugins.jetbrains.com/plugin/13576-d8gerautocode"><img src="https://img.shields.io/jetbrains/plugin/d/13576"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode"><img src="https://img.shields.io/github/v/release/caofanCPU/D8gerAutoCode"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode/issues"><img src="https://badgen.net/github/open-issues/caofanCPU/D8gerAutoCode" alt="issues"></a>
<a href="http://file.debuggerpowerzcy.top/power/html/IntelliJBestAction.html"><img src="https://img.shields.io/github/search/caofanCPU/D8gerAutoCode/D8ger"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode/pulls"><img src="https://badgen.net/badge/PRs/welcome/cyan" alt="PRs Welcome"></a>
<a href="https://github.com/D8ger"><img src="https://badgen.net/badge/organization/join%20us/cyan" alt="open-source-organization"></a>
<a href="https://github.com/caofanCPU/D8gerAutoCode/blob/master/LICENCE"><img src="https://badgen.net/github/license/caofanCPU/D8gerAutoCode?color=green" alt="license"></a>


## IDEA代码自动生成插件•`Java版本`
<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/D8ger-V5.jpg" /> 
</div>

## 说明
最新版本为**3.4**, 在 _**`10月1号`**_ 已通过官方审核发布  
如果无法获取, [戳此](https://github.com/caofanCPU/D8gerAutoCode/releases/tag/3.4) 下载~~.zip~~压缩包  
如果用的不错, 给我点个🌟呗, thx!

### 序言
正如JetBrains所说, 我相信你在繁忙编码工作中时应该善用工具以节省宝贵的时间。

我时常在思考, "如果其他人已经解决了这些重复工作, 为什么我还需要重复解决?"

这个问题一直驱动着我, 在日常工作中探寻重复部分并开发此插件以节省时间。 

### [快速开始](http://www.debuggerpowerzcy.top/home/2020/03/14/D8gerAutoCode%E6%8F%92%E4%BB%B6%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97/)
<a href="http://file.debuggerpowerzcy.top/power/html/IntelliJBestAction.html" target="_blank">ZN程序员福利, IntelliJ IDEA最佳实践</a>

### 约定
- 通用问题提供规范解法
- 个性化问题只提供示例解法

### 设计初衷
- 移除开发中的固定的重复编码过程, 提升开发效率
- 提供可用的代码模板, 基本不用修改就可使用的代码
- 支持代码目录配置, 一键自动生成代码, 仅需自行导包即可完成智能编程
- 支持用户自定义配置, 可指定需要生成的文件

### 功能特性
- 驼峰-下划线-大写-首字母小写-小写, 时间戳与标准时间字符串(香!), 一键转换, 默认快捷键`alt` + `shift` + `cmd` + `U`  
- 生成代码的注释支持英语|汉语配置
- JSON格式化与单行文本转换, 默认快捷键 `alt`+`shift`+`cmd`+`J` | `S`
- 一键生成`数据表定义SQL` | `Mo` | `Mapper` | `Handler`, 单表增删改查+分页一键搞定
- 结合SwaggerApi, 一键生成带文档定义`Vo`及`Controller`, 支持Vo字段自动排序
- 一键智能编程, 默认快捷键 `alt`+`shift`+`cmd`+`D`
- 支持用户自定义配置, 指定生成哪些文件及文件author等
- 预留彩蛋, 在IDEA中编辑空白文件输入`d8ger`即可得到配置文件模板
- 预留彩蛋, 在IDEA中编辑空白文件输入`nasa`即可得到NASA模板, 针对多行字符串批量处理, 尤其是SQL字段命名处理   
- 预留彩蛋, 在IDEA中编辑空白文件输入`regex`即可得到一些常用的有趣正则表达式, 后期会增加其他代码彩蛋
- SwaggerVo字段排序, 默认快捷键 `alt`+`shift`+`cmd`+`O`

### 使用手册(v3.0+)
1.打开配置框设置生成参数 `Preference` --> `Other Settings` --> `D8gerAutoCode`

2.勾选要生成的文件及生成路径, 指明 `author`、`apiUrlPrefix`、`locale`, 保存即可

<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/D8King.jpg" /> 
</div>

### 使用手册(v3.0以下)
1. 创建Model类  

2. 选中类名, 按下(默认)快捷键 `alt`+`shift`+`cmd`+`D`  

3. 默认配置下, 会生成`D8gerAutoCode`目录及数据表SQL定义和Web代码, 共9个  

4. 你需要做的:
    - 多模块工程下, 配置生成文件输出目录, 例如 **`Mo.java`** 配置`model`目录  
    - 根据需求对 **`xx.sql`** 增加非空约束  
    - 如果你生成了 **`Controller.java`** 文件, ***请注意接口返回对象类型***, 应修改为项目统一的返回类型  
    - 在上述步骤中, 代码文件会飘红报错, 别慌, 这需要你自行导包解决  

5. 默认配置文件模板, 代码彩蛋:  
    - 在`Model类`所在模块的 ***`resource`*** 根目录下, 创建`d8ger.properties`属性配置文件  
    - 输入`d8ger`并点击`Tools`-->`D8ger`-->`D8gerMore`

6. 正则表达式模板, 代码彩蛋:
    - 任意空文本, 输入`regex`并点击`Tools`->`D8ger`->`D8gerMore`, 你会得到一点小惊喜

7. NASA模板(多行文本批处理), 代码彩蛋:
    - 任意空文本, 输入`nasa`并点击`Tools`->`D8ger`->`D8gerMore`, 你会得到NASA操作手册
    - 按照NASA手册提示, 自定义配置并输入需要处理的多行文本, 然后你会得到一点小惊喜

8. 驼峰-下划线-大写-首字母小写-小写, 一键循环转换
    - 选中任意编辑状态下文件里的单词, 按下快捷键`alt` + `shift` + `cmd` + `U`, 你会发现选中单词转换了
    - 如果不是你想要的结果那就继续重复按下快捷键, 直到是你想要的为止

9. 你就得到如下默认配置信息  
    - 推荐你更改的配置项  
        - `author`(你挖的坑可不许署我的名!)  
        - `apiUrlPrefix`(接口url前缀应配置为项目接口统一Url前缀)  
        - 多模块工程下, 生成文件输出目录
    - 其他`boolean`配置项, 是让你选择需要生成哪些文件, 可选指定输出目录  

>以上默认快捷键针对MacOS系统, 对于Windows用户将`cmd`看做`ctrl`即可

### 如何安装
- `Preference` --> `Plugins` --> `Marketplace` --> type `D8` --> install
- [IDEA官方插件库安装](https://plugins.jetbrains.com/plugin/13576-d8gerautocode)  
- 源码安装, 这需要你熟悉Gradle, 后期我会详细补充如何使用Gradle开发一个插件  

### Bug&问题
- 已知问题(最新版本3.0中已修复)
>修改`d8ger.properties`文件后再生成代码, 配置文件不生效

```
解决方法
0.升级到3.0及以上版本, 该问题不复存在
1.由于IDEA文件采用VFS机制, 作为一个插件去采用监听文件我认为是不合算的
2.此外, 编辑.properties文件时, 内容变更有时会消耗1-2分钟才会更新生效
3.所以, 执行快捷键`cmd` + `s`强制刷新.properties文件, 即可解决
```

> 插件图标显示异常, 图标内只出现相交的红叉
<div align="center">
    <img src="http://file.debuggerpowerzcy.top/power/W-D8ger.png" /> 
</div>

```
1.这是Intelij IDEA新版本特性遗留的兼容性小BUG
2.升级Intelij IDEA版本, 至少为2019.3.*(2019.2.*及以下都会显示异常)
```

[其他问题, 欢迎前来查房](https://github.com/caofanCPU/D8gerAutoCode/issues)

### 版本演进历史
- 3.4
    - 新增功能: 'OhMyZSH' 彩蛋, 空白文件中输入 'ohmyzsh' 将获得一些有用的linux命令工具及使用技巧. 这将节省你不少时间.
    - Bug修复: 修复donate链接(铁子, 等着你🌟该项目, thx!).
    - 体验优化: 'BeautifulJSON', 'SimplifyOneLine' 和 'BeautifulMySQL' 现在同时支持部分和整个文件内容转换.
- 3.2
    - Bug修复: 修复'BeautifulJSON'在解析复杂JSON字符串时的问题
    - 体验优化: 'CamelUnderlineConvert' 支持时间戳与标准时间字符串相互转换

- 3.1
    - Bug修复:
        - 修复配置项`locale`为中文时不生效的问题, 在之前的3.0版本只能生成英文注释.
        - 修复x.sql中在某些情况下因缺失`,`导致报错的问题.
        - 批量插入记录, 字段`id`, `createTime`, `updateTime`将被忽略强制采用数据库的默认值.
    - 体验优化: 配置项`autoDetectSQLTimeColumn`现在更加智能. 当你勾选改配置项时, `createTime` and `updateTime`字段将会自动填充到合适的位置.

- 3.0
    - 新增功能:
        - 很高兴宣布插件`D8gerAutoCode`终于有脸(面)了! 现在你将通过设置界面配置生码参数.
        - 第一步, 打开设置界面, `Preference` --> `Other Settings` --> `D8gerAutoCode`
        - 第二步, 勾选你要生成的文件并指明文件生成后所在目录, 填写`author`、`apiUrlPrefix` and `locale`, 保存就完事了.
    - 体验优化: 使用项目级别的界面配置, 对用户的代码无任何侵入性.
    - 功能调整: 移除`d8ger.properties`配置文件, 一键生码配置参数将通过D8gerAutoCode设置GUI完成.

- 2.5
    - 新增功能:
        - 新增配置项`autoDetectSQLTimeColumn`用以支持`create_time`和`update_time`字段的自动探测定义.
        - 新增配置项`mapperBatterThenRepository`用以支持`SpringMVC`和`SpringBoot`下的`mapper`注解.
        - 在Mapper.java文件中新增`selectOneByExample`方法用以支持根据条件查询单条记录, 慢走不送: `xList.get(0)`.
        - 在Mapper.java文件中新增`insertSelectiveWithId`用以支持只插入非NULL字段, NULL字段将采用数据库默认值.
    - 体验优化: 考虑到`id`是约定的主键字段, 所有插入操作都应该排除该字段.
    - Bug修复:
        - 在x.sql文件定义中, `update_time`在某些情况下会出现多余的`,`.
        - 颇费周折地抓获冗余目录`D8gerAutoCode`莫名出现的现场, 现在只有在你未指明生成目录的情况下才能遇到他, 版本不停步, 且用且珍惜.
    - 功能调整: 去除`ServiceInterface`和`ServiceImpl`, 因为针对自动生码的通用性而言`Handler`将更合适.

- 2.4
    - 新增功能: 一键美化SQL, 不香吗?
    - 体验优化: 优化字符串解析性能.
    - TheShy提示: 选中MySQL文本, 快捷键ctrl + alt + shift + `B`将奉上惊喜.

- 2.3
    - Bug修复: Json字符串格式化的问题.
    - 新增功能:
        - NASA彩蛋, 空白文件中输入`nasa`获取模板.
        - NASA彩蛋, 支持字符串批处理, 例如多行文本对齐, 提供SQL格式化及自动生成别名功能, 搞数据分析的小伙伴值得一试.
    - 体验优化: 
        - 优化正则处理性能.
        - 支持换行符作为分割符.
        - 针对Windows系统, 字符串批处理结果渲染时使用Windows换行符.
    - TheShy提示: 
        - 运行自动生码前, 请留意IDE项目默认换行符配置.
    - 功能调整: 
        - 插件功能图标调整对齐.
        - 插件使用指南搬迁到我的<a href="http://www.debuggerpowerzcy.top/home/2020/03/14/D8gerAutoCode%E6%8F%92%E4%BB%B6%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97/">博客</a>.

- 2.1
    - Bug修复: 补充一键首字母小写转换功能, 完成字段命名闭环: 驼峰-下划线-全大写-首字母小写-全小写.
    - 体验优化: 优化一些正则处理的性能.
    - TheShy提示: 正则神器彩蛋中增加IDEA自带的高效查找与替换功能, 花点时间瞄一瞄, 之后工作快到飞起.

- 2.0
    - Bug修复: 修改Controller.java文件中方法名.
    - 新增功能:
        - 通过配置指定文件生成目录, 不用再手动拖入目录了, 就应该是这样的, 重复工作应该被剔除.
        - 驼峰-下划线-大写-小写一键转化: `alt` + `shift` + `cmd` + `U`.
        - 空白文件中输入`regex` 获取正则神器菜单.
    - 体验优化: 代码优化与性能优化.
    - TheShy提示: 如果插件图标出现红叉, 这是IDEA版本不兼容引起的, 将IDEA版本升级至2019.3.*(至少是2019.2.*以后).

- 1.24
    - Bug修复: mapper.xml文件中 `updateBatchByPrimaryKeySelective` SQL生成修复.
    - 新增功能: 空白文件中输入`d8ger`以获取最新模板参数配置, 该版本支持代码注释中英文, 默认英文.
    - TheShy提示: **对于批量更新功能, 数据库连接参数必须开启该功能: `allowMultiQueries=true`**.

- 1.23
    - Bug修复: 修复Intellij版本间的依赖包兼容问题.
    - 新增功能: 新增一键JSON美化和一键剔除空白字符.
    - TheShy提示: 老铁, 点亮一颗🌟可好?

- 1.22
    - Bug修复: 修正图片及文档格式.
    - 体验优化: 使用正则匹配取缔原有循环处理以提升性能
    - TheShy提示: 小伙伴, 点亮一颗🌟可好?

- 1.21
    - Bug修复: 修正图片及文档格式.
    - 新增功能: 新增MoExample查询条件对象以支持简单查询.
    - TheShy提示: 同学, 点亮一颗🌟可好?

- 1.20
    - 首发上阵: 一键自动生成Mo/Example/Mapper/Xml/SQL/Service 完成, wow!
    - TheShy提示: 少年, 点亮一颗🌟可好?

### 参与贡献
- 帮我点亮一颗🌟
- 提需求, 在[issue](https://github.com/caofanCPU/D8gerAutoCode/issues)描述工作中经常处理的重复问题
- 请我到🍦店坐坐
<table>
    <tr>
      <td align="center" style="width: 200px;">
        <a href="https://github.com/D8ger">
          <img src="http://file.debuggerpowerzcy.top/power/WX.png" style="width: 400px;"><br>
          <sub>微信</sub>
        </a><br>
      </td>
      <td align="center" style="width: 200px;">
        <a href="http://www.debuggerpowerzcy.top/">
          <img src="http://file.debuggerpowerzcy.top/power/ZFB.png" style="width: 400px;"><br>
          <sub>支付宝</sub>
        </a><br>
      </td>
      <td align="center" style="width: 200px;">
          <a href="https://github.com/caofanCPU">
            <img src="http://file.debuggerpowerzcy.top/power/MX.jpg" style="width: 400px;"><br>
            <sub>MiXin</sub>
          </a><br>
      </td>
    </tr>
</table>

### 致谢

<a href="https://www.jetbrains.com/idea">
    <img src="http://file.debuggerpowerzcy.top/power/jetbrains-variant-4.svg" style="width: 40px;"><br>
    <sub>感谢JetBrains团队的开源证书支持, 本项目将秉承开源精神持续开发</sub>
</a>