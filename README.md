# D8gerAutoCode
## IDEA Code Automatically Generate Plug-ins • **`For Java Web Developer`** Version

> [中文说明](./README_CN.md)

### Convention
- General problem provides standard solutions, personalized problem only provides example solutions

### Original design intention
- Remove the fixed duplication coding for improving efficiency in development  
- Provide available code templates that can be used without modification  
- After the code is automatically generated, you just need `cmd`+`C` | `V` for moving these files to related directory in the project and importing package  
- Support user-defined configuration, you can specify which file to be generated  

### Features
- Generating `data table definition SQL` | `Mo` | `Mapper` | `Service`, single table addition, deletion, modification and search with paging in one click  
- Under the help of SwaggerApi generating `Vo` | `Controller` with friendly document description, in addition to this, supporting `Vo` field automatic sorting  
- For automatic generating files, you just need one shortcut and the default is `alt`+`shift`+`cmd`+`D`  
- Supporting customer configuration, for example, specify the author who you are  
- Reserving easter eggs, you can try to edit the blank file in IDEA and type 'd8ger' to get the default configuration template, other eggs will be added later  
- For automatic sorting `Vo` field , you just need one shortcut and the default is `alt`+`shift`+`cmd`+`O`  
- JSON formatting and single line text conversion, default shortcut `alt`+`shift`+`cmd`+`J` | `S`

### Using screenshots
1. Create Model Class  
2. Select the class name and press the (default) shortcut `alt`+`shift`+`cmd`+`D`  
3. Under the default configuration, `D8gerAutoCode` directory and `data table SQL definitions` and **Web codes** will be generated  
4. So, What you need to do:
- Move files to directory corresponding to the project, for example, **`Mo.java`** should be moved into the `model` directory  
- Add non-null constraints to **`xx.sql`** as required  
- If you generated the **Controller.java** file, please note the interface returns type of **`Object`**, it should be modified to the project's uniform return type  
- In the above steps the `xx.java` file will report some errors, ***don't worry***, these just require you to import packages  
5.Default configuration file template for easter egg:  
    - In the ***`resource`*** root directory of the module where the `Model class` is located, create `d8ger.properties` property configuration file  
    - Type `d8ger` and click on` Tools`-> `D8ger`->` D8gerMore`, and yo will find some surprise  
6.You will get the following default configuration information  
    - It is recommended that you change the configuration items  
        - `author` (you can't sign my name for the pits you dug!)  
        - `apiUrlPrefix` (the interface url prefix should be configured as the project interface unified URL prefix)  
    - As for `boolean` configuration items, let you choose which files need to be generated  

> By the way, these default shortcuts are used in Mac OS, if you use Windows just take `cmd` into `ctrl`.

### User Operate Snapshot
<div align="center">
	<img width="100%" height="100%" src="http://file.debuggerpowerzcy.top/power/2020-Tina-D8gerAutoCode.jpg" /> 
</div>

### How to install
- IDEA official plugin library installation  
- [Download plugin package](http://file.debuggerpowerzcy.top/power/D8gerAutoCode-1.23.zip)  
- As for `Source installation` requires you're familiar with Gradle, I will add more details about how to develop a plugin by ***Gradle*** later

### Bug & Question
- Known issues
> Modify the `d8ger.properties` file before generating code, the configuration file does not take effect

```
Solution
1. Because the IDEA file uses the VFS mechanism, as a plug-in to use the monitoring file I think it is not cost-effective  
2. In addition, the `D8gerAutoCode` directory is an auxiliary. When we generate and modify the code, this directory should come and go.  
3. So, delete the `D8gerAutoCode` directory and you can solve it.
```

[Other issues, please come to join room](https://github.com/caofanCPU/D8gerAutoCode/issues)
