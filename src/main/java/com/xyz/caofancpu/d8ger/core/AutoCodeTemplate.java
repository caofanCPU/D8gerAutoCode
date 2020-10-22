package com.xyz.caofancpu.d8ger.core;

/**
 * Code template string constants
 *
 * @author caofanCPU
 */
public class AutoCodeTemplate {

    public static String TEMPLATE_REGEX = "0.About details, please refer https://github.com/caofanCPU/JavaVerbalExpressions\n" +
            "Usage, static pattern can improve performance in regex searching:\n" +
            "    public static final Pattern XXX_REGEX = Pattern.compile(\"...Regex string...\");\n" +
            "\n" +
            "1.Whitespace Regex\n" +
            "((?:\\s)+)\n" +
            "\n" +
            "2.One or more newlines Regex\n" +
            "(?:\\\\n|(?:\\\\r\\\\n))+\n" +
            "\n" +
            "3.Phone validate Regex\n" +
            "^1[0-9]{10}$\n" +
            "\n" +
            "4.Email validate Regex\n" +
            "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$\n" +
            "\n" +
            "5.Password validate, rules: digital, uppercase, lowercase, special character >= 3 species\n" +
            "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\\\W_]+$)(?![a-z0-9]+$)(?![a-z\\\\W_]+$)(?![0-9\\\\W_]+$)[a-zA-Z0-9\\\\W_]{8,30}$\n" +
            "\n" +
            "6.Keyword detect Regex, for example, position[may be have space]=[may be have space][may be have digital]\n" +
            "(?:position)(?:\\\\s)*(?:\\\\=)(?:\\\\s)*(?:\\\\d)*\n" +
            "\n" +
            "7.No '_' and begin with [A-Z] in word Regex\n" +
            "^(?!_)(?:[A-Z])[a-zA-Z0-9\\\\W]+$\n" +
            "\n" +
            "8.No upper case in word Regex\n" +
            "^(?![A-Z])[a-z0-9\\\\W_]+$\n" +
            "\n" +
            "9.No lower case in word Regex\n" +
            "^(?![a-z])[A-Z0-9\\\\W_]+$\n" +
            "\n" +
            "10.'www' Url detect Regex\n" +
            "^(?:http)(?:s)?(?:\\:\\/\\/)(?:www\\.)?(?:[^\\ ]*)$\n" +
            "\n" +
            "11.IDEA Skills\n" +
            "- 11.1 For multi-lines, how to add some same fix characters?\n" +
            " ResearchRegex@   (?:\\n|(?:\\r\\n))+\n" +
            " ReplaceString@   ,\\r\\n\n" +
            "\n" +
            "- 11.2 For multi-lines, especially for SQL batch replacing, such as:\n" +
            "    - zcy_cf,  --> WE'RE(zcy_cf) AS zcy_cf,\n" +
            "    - cf_zcy   --> WE'RE(cf_zcy) AS cf_zcy\n" +
            "    - note: WE'RE is just a function\n" +
            " I.Clear the character ','\n" +
            "  ResearchRegex@   (?:,)+\n" +
            "  ReplaceString@   [EMPTY]\n" +
            " II.Batch replace\n" +
            "  ResearchRegex@   ((?:\\w+)+)\n" +
            "  ReplaceString@   WE'RE($0) AS $0,\n" +
            " III.Clear the rare character ',' at the last line\n" +
            "\n" +
            "- 11.3 For multi-lines, especially for SQL batch reduce-replacing, such as:\n" +
            "    - WE'RE(zcy_cf) AS zcy_cf,  --> zcy_cf,\n" +
            "    - WE'RE(cf_zcy) AS cf_zcy   --> cf_zcy\n" +
            "    - note: WE'RE is just a function\n" +
            " I.Handle prefix\n" +
            "  ResearchRegex@   (?:WE'RE\\()+\n" +
            "  ReplaceString@   [EMPTY]\n" +
            " II.Handle suffix\n" +
            "  ResearchRegex@   (?:\\))(?:[^\\,]*)\n" +
            "  ReplaceString@   [EMPTY]\n" +
            " III.Watch out whether need to clear the rare character ',' at the last line\n" +
            "\n" +
            "12. Cron Expression\n" +
            "每隔5秒执行一次:                  */5 * * * * ?\n" +
            "每隔5分钟执行一次:                0 */5 * * * ?\n" +
            "每天23点执行一次:                 0 0 23 * * ?\n" +
            "每天凌晨2点执行一次:               0 0 2 * * ?\n" +
            "每月2号凌晨4点执行一次：            0 0 2 4 * ?\n" +
            "每月最后一天23点执行一次：          0 0 23 L * ?\n" +
            "每周星期天凌晨2点实行一次：          0 0 2 ? * L\n" +
            "在26分、29分、33分执行一次：        0 26,29,33 * * * ?\n" +
            "每天的0点、22点、23点都执行一次：    0 0 0 0,22,23 * * ?\n" +
            "\n";

    public static String TEMPLATE_OH_MY_ZSH = "export ZSH=\"$HOME/.oh-my-zsh\"\n" +
            "export HOMEBREW_NO_AUTO_UPDATE=true\n" +
            "\n" +
            "ZSH_THEME=\"powerlevel9k/powerlevel9k\"\n" +
            "POWERLEVEL9K_MODE=\"nerdfont-complete\"\n" +
            "POWERLEVEL9K_PROMPT_ON_NEWLINE=true\n" +
            "POWERLEVEL9K_RPROMPT_ON_NEWLINE=false\n" +
            "POWERLEVEL9K_LEFT_PROMPT_ELEMENTS=(os_icon user dir_writable dir vcs)\n" +
            "POWERLEVEL9K_RIGHT_PROMPT_ELEMENTS=(status command_execution_time root_indicator background_jobs time disk_usage ram)\n" +
            "#POWERLEVEL9K_MULTILINE_LAST_PROMPT_PREFIX=\"%(?:%{$fg_bold[green]%}➜ :%{$fg_bold[red]%}➜ )\"\n" +
            "#POWERLEVEL9K_MULTILINE_FIRST_PROMPT_PREFIX=\"\"\n" +
            "#POWERLEVEL9K_USER_ICON=\"\\uF415\" # \uF415\n" +
            "POWERLEVEL9K_ROOT_ICON=\"\\uF09C\"\n" +
            "#POWERLEVEL9K_SUDO_ICON=$'\\uF09C' # \uF09C\n" +
            "POWERLEVEL9K_TIME_FORMAT=\"%D{%H:%M}\"\n" +
            "#POWERLEVEL9K_VCS_GIT_ICON='\\uF408 '\n" +
            "#POWERLEVEL9K_VCS_GIT_GITHUB_ICON='\\uF408 '\n" +
            "\n" +
            "ZSH_DISABLE_COMPFIX=true\n" +
            "#ENABLE_CORRECTION=\"true\"\n" +
            "DISABLE_CORRECTION=true\n" +
            "COMPLETION_WAITING_DOTS=true\n" +
            "\n" +
            "# 左侧栏目显示的要素（指定的关键字参考官网）\n" +
            "#POWERLEVEL9K_LEFT_PROMPT_ELEMENTS=(os_icon context dir vcs)\n" +
            "# 右侧栏目显示的要素\n" +
            "#POWERLEVEL9K_RIGHT_PROMPT_ELEMENTS=(status root_indicator background_jobs time virtualenv)\n" +
            "#新起一行显示命令 (推荐！极其方便）\n" +
            "#POWERLEVEL9K_PROMPT_ON_NEWLINE=true\n" +
            "#右侧状态栏与命令在同一行\n" +
            "#POWERLEVEL9K_RPROMPT_ON_NEWLINE=false\n" +
            "#缩短目录层级\n" +
            "#POWERLEVEL9K_SHORTEN_DIR_LENGTH=1\n" +
            "#缩短目录策略：隐藏上层目录中间的字\n" +
            "#POWERLEVEL9K_SHORTEN_STRATEGY=\"truncate_middle\"\n" +
            "#添加连接上下连接箭头更方便查看\n" +
            "#POWERLEVEL9K_MULTILINE_FIRST_PROMPT_PREFIX=\"↱\"\n" +
            "#POWERLEVEL9K_MULTILINE_LAST_PROMPT_PREFIX=\"↳ \"\n" +
            "# 新的命令与上面的命令隔开一行\n" +
            "#POWERLEVEL9K_PROMPT_ADD_NEWLINE=true\n" +
            "# Git仓库状态的色彩指定\n" +
            "#POWERLEVEL9K_VCS_CLEAN_FOREGROUND='blue'\n" +
            "#POWERLEVEL9K_VCS_CLEAN_BACKGROUND='black'\n" +
            "#POWERLEVEL9K_VCS_UNTRACKED_FOREGROUND='yellow'\n" +
            "#POWERLEVEL9K_VCS_UNTRACKED_BACKGROUND='black'\n" +
            "#POWERLEVEL9K_VCS_MODIFIED_FOREGROUND='red'\n" +
            "#POWERLEVEL9K_VCS_MODIFIED_BACKGROUND='black'\n" +
            "\n" +
            "\n" +
            "DISABLE_AUTO_UPDATE=\"true\"\n" +
            "# autojump   : 'j'历史目录\n" +
            "# cp         : 'cpv'带进度条的复制\n" +
            "# zsh_reload : 'src'快速重载.zshrc\n" +
            "# 快速编辑.zshrc: alias 'vrc=vim ~/.zshrc'\n" +
            "# 快速展示.zshrc: alias 'crc=cat ~/.zshrc'\n" +
            "# extract    : 'x'解压任何文件\n" +
            "plugins=(git autojump cp zsh_reload extract zsh-syntax-highlighting)\n" +
            "\n" +
            "export LC_ALL=en_US.UTF-8  \n" +
            "export LANG=en_US.UTF-8\n" +
            "\n" +
            "# Maven家目录\n" +
            "export M2_HOME=/usr/local/maven-3.6.1/apache-maven-3.6.1\n" +
            "alias mcf=\"/usr/local/maven-3.6.1/caofanCPU-apache-maven-3.6.1/bin/mvn\"\n" +
            "\n" +
            "# 查看java安装位置命令 /usr/libexec/java_home -V\n" +
            "export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home\n" +
            "export JRE_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre\n" +
            "export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH\n" +
            "export PATH=$M2_HOME/bin:$JAVA_HOME/bin:$JRE_HOME/bin:$JAVA_HOME:$PATH\n" +
            "\n" +
            "# Python项目打包工具包\n" +
            "export TWINE_HOME=/Users/D8GER/Library/Python/3.7\n" +
            "export PATH=$PATH:$TWINE_HOME/bin\n" +
            "\n" +
            "export PATH=$PATH:$HOME/bin\n" +
            "export PATH=\"$PATH:/usr/local/python3/bin\"\n" +
            "\n" +
            "# thefuck插件\n" +
            "eval \"$(thefuck --alias)\"\n" +
            "\n" +
            "# 服务器\n" +
            "alias caofanCPU='sshpass -f PWD_DIR  ssh  xxxx@xx.xx.xx.xx'\n" +
            "alias caofanJumpAli='sshpass -f PWD_DIR  ssh  xx@xx.xx.xx.xx'\n" +
            "\n" +
            "function execute() {\n" +
            "    http --session=~/Desktop/ssoLogin/session-${1}.json --verify=no -v ${2} POST ${@:3}\n" +
            "}\n" +
            "\n" +
            "function downLoadResponse() {\n" +
            "    echo \"执行命令内容: \\n    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -d >>~/Desktop/ssoLogin/ResponseBody.json ${@:2}\\n\"\n" +
            "    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -d >>~/Desktop/ssoLogin/ResponseBody.json ${@:2}\n" +
            "}\n" +
            "\n" +
            "function downLoadExcel() {\n" +
            "    echo \"执行命令内容: \\n    http --verify=no -v --download  --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -o ${2}\"\n" +
            "    http --verify=no -v --download  --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -o ${2}\n" +
            "}\n" +
            "\n" +
            "function downloadD8ger() {\n" +
            "    echo \"执行命令内容: 并发下载文件\"\n" +
            "    for i in {1..30}\n" +
            "        do \n" +
            "            http --verify=no -v --download  GET  ${1} -o D8.zip\n" +
            "    done\n" +
            "}\n" +
            "\n" +
            "function show() {\n" +
            "    echo \"执行命令内容: \\n    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json ${@:2}\\n\"\n" +
            "    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json ${@:2}\n" +
            "}\n" +
            "\n" +
            "function searchPortOccupy(){\n" +
            "    lsof -i :${1}\n" +
            "}\n" +
            "\n" +
            "function searchPID(){\n" +
            "    ps aux | grep ${1} | grep -v grep | awk '{print $2}'\n" +
            "}\n" +
            "\n" +
            "function killPID(){\n" +
            "    pid=`ps aux | grep ${1} | grep -v grep | awk '{print $2}'`\n" +
            "    if [ -n \"$pid\" ]; then\n" +
            "        kill -9 $pid\n" +
            "        sleep 1\n" +
            "    fi\n" +
            "}\n" +
            "\n" +
            "\n" +
            "# 环境\n" +
            "function xDev() {\n" +
            "    sshpass -f ENV_PIR.txt  ssh xx@xx.xx.xx.${1}  -t  'cd /work/www/d8ger.com/logs/; exec $SHELL'\n" +
            "}\n" +
            "\n" +
            "# 环境普通模式\n" +
            "function xDevNormal(){\n" +
            "    sshpass -f ENV_PIR.txt  ssh xx@xx.xx.xx.${1}\n" +
            "}\n" +
            "\n" +
            "# 不同环境\n" +
            "function backgroundENV() {\n" +
            "    env=${1}\n" +
            "    if [ -z \"$env\" ]; then\n" +
            "        echo \"请选择可用环境: 1,2,3,4,5,6,7,8,9,10\"\n" +
            "        return\n" +
            "    fi\n" +
            "    domain=''\n" +
            "    if [ \"$env\" = '1' ]; then\n" +
            "        domain='https://1.d8ger.com'\n" +
            "    elif [ \"$env\" = '2' ]; then\n" +
            "        domain='https://2.d8ger.com'\n" +
            "    elif [ \"$env\" = '3' ]; then\n" +
            "        domain='https://3.d8ger.com'\n" +
            "    elif [ \"$env\" = '4' ]; then\n" +
            "        domain='https://4.d8ger.com'\n" +
            "    elif [ \"$env\" = '5' ]; then\n" +
            "        domain='https://5.d8ger.com'\n" +
            "    elif [ \"$env\" = '6' ]; then\n" +
            "        domain='https://6.d8ger.com'\n" +
            "    elif [ \"$env\" = '7' ]; then\n" +
            "        domain='https://7.d8ger.com'\n" +
            "    elif [ \"$env\" = '8' ]; then\n" +
            "        domain='https://8.d8ger.com'\n" +
            "    elif [ \"$env\" = '9' ]; then\n" +
            "        domain='https://9.d8ger.com'\n" +
            "    elif [ \"$env\" = '10' ]; then\n" +
            "        domain='https://ok.d8ger.com'\n" +
            "    fi\n" +
            "    # 判断\n" +
            "    if [ -z \"$domain\" ]; then\n" +
            "        echo \"请选择可用环境: 1,2,3,4,5,6,7,8,9,10\"\n" +
            "        return\n" +
            "    fi\n" +
            "    echo \"德玛西亚, http -v --verify=no POST  ${domain}/ok/xx\"\n" +
            "    echo \"查询任务是否启动及是否完成: grep \\\"德玛西亚\\\" all.log | grep \\\"end\\\"\"\n" +
            "    http -v --verify=no POST  ${domain}/ok/xx\n" +
            "}\n" +
            "\n" +
            "function justDoIT(){\n" +
            "    SID=${1}\n" +
            "    if [ -z \"$SID\" ]; then\n" +
            "        echo \"非法的SID, 请检查\"\n" +
            "        return\n" +
            "    fi    \n" +
            "    url=\"https://1.d8ger.com/xx/yy/zz\"\n" +
            "    # 登录\n" +
            "    login-cookie -f ~/Desktop/ssoLogin/sso-on-admin.json -a 6 \n" +
            "    # 执行\n" +
            "    echo \"执行:\\n http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${url} sId:=${SID} bId:=50220671 cIds:='[]' areUok:=false healthy:=true\\n\"\n" +
            "    # http多个参数不能放在一个字符串中, 用多个变量来区分解决\n" +
            "    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${url} sId:=${SID} bId:=50220671 cIds:='[]' areUok:=false healthy:=true\n" +
            "}\n" +
            "\n" +
            "function cph(){\n" +
            "    echo \",------.   ,---.  ,----.   ,------.,------.\"\n" +
            "    echo \"|  .-.  \\\\ |  o  |'  .-./   |  .---'|  .--. '\"\n" +
            "    echo \"|  |  \\\\  :.'   '.|  | .---.|  \\`--, |  '--'.'\"\n" +
            "    echo \"|  '--'  /|  o  |'  '--'  ||  \\`---.|  |\\\\  \\\\\"\n" +
            "    echo \"\\`-------'  \\`---'  \\`------' \\`------'\\`--' '--'\"\n" +
            "    echo \"##### SCP命令 #####\"\n" +
            "    echo \"# -r 支持复制目录及其子文件\"\n" +
            "    echo \"- 本地文件传到远程服务器\"\n" +
            "    echo \"scp /Users/D8GER/Desktop/ssoLogin/LEARN-SH.sh  caofan@172.16.10.59:~/\"\n" +
            "    echo \"- 从远程服务器拉取文件\"\n" +
            "    echo \"scp caofan@172.16.10.59:~/HAHA.tmp /Users/D8GER/Desktop/ssoLogin/ZZ.xls\"\n" +
            "    echo \"- 无痕登录\"\n" +
            "    echo \"xD8scp || d8scp\"\n" +
            "    echo \"sshpass -f /Users/D8GER/Desktop/CAOFAN/sshpass/caofan-ssh-dev.txt scp /Users/D8GER/Desktop/ssoLogin/LEARN-SH.sh  caofan@172.16.10.59:~/\"\n" +
            "    echo \"sshpass -f /Users/D8GER/Desktop/CAOFAN/sshpass/caofan-ssh-dev.txt scp /Users/D8GER/Desktop/ssoLogin/LEARN-SH.sh  caofan@172.16.10.59:~/\"\n" +
            "    echo \"##### cpv #####, zsh的一个插件cp, 文件复制时展示进度条\"\n" +
            "    echo \"##### sudo cp #####, 普通复制\"\n" +
            "}\n" +
            "\n" +
            "function fkgrep(){\n" +
            "    echo \",------.,--. ,--. ,----.   ,------. ,------.,------.\"\n" +
            "    echo \"|  .---'|  .'   /'  .-./   |  .--. '|  .---'|  .--. '\"\n" +
            "    echo \"|  \\`--, |  .   ' |  | .---.|  '--'.'|  \\`--, |  '--' |\"\n" +
            "    echo \"|  |\\`   |  |\\\\   \\'  '--'  ||  |\\\\  \\\\ |  \\`---.|  | --'\"\n" +
            "    echo \"\\`--'    \\`--' '--' \\`------' \\`--' '--'\\`------'\\`--'\"\n" +
            "    echo \"\"\n" +
            "    echo \"grep -n '[a-zA-Z0-9]D8' X.txt\"\n" +
            "    echo \"grep -n '[^a-zA-Z0-9]D9' X.txt\"\n" +
            "    echo \"grep -n '^[a-z]' X.txt\"\n" +
            "    echo \"grep -n '^[^a-z]' X.txt\"\n" +
            "    echo \"grep -n '^$' X.txt\"\n" +
            "    echo \"grep -n '\\.$' X.txt\"\n" +
            "    echo \"grep -n 'g.*d' X.txt\"\n" +
            "    echo \"grep -n 'go*d' X.txt\"\n" +
            "    echo \"grep -n 'o\\{2,3\\}' X.txt\"\n" +
            "    echo \"grep -En 'God|The'  X.txt     grep -n 'god\\|The' X.txt\"\n" +
            "    echo \"grep -En 'o+' X.txt           grep -n 'o\\+' X.txt\"\n" +
            "    echo \"grep -n '\\.' X.txt            grep -En '\\.' X.txt\"\n" +
            "    echo \"grep -En '(oo)+' X.txt        grep -n '\\(oo\\)\\+' X.txt\"\n" +
            "    echo \"Search Today's log: ll -ah | grep \\\"[a-z_A-Z]\\+\\.log\\\"\"\n" +
            "}\n" +
            "\n" +
            "function arthasHelp(){\n" +
            "    echo \"  ,---.  ,------. ,--------.,--.  ,--.  ,---.   ,---.\"\n" +
            "    echo \" /  O  \\\\ |  .--. ''--.  .--'|  '--'  | /  O  \\\\ '   .-'\"\n" +
            "    echo \"|  .-.  ||  '--'.'   |  |   |  .--.  ||  .-.  |\\`.  \\`-.\"\n" +
            "    echo \"|  | |  ||  |\\\\  \\\\    |  |   |  |  |  ||  | |  |.-'    |\"\n" +
            "    echo \"\\`--' \\`--'\\`--' '--'   \\`--'   \\`--'  \\`--'\\`--' \\`--'\\`-----'\"\n" +
            "    # `和\\ 为特殊字符, 必须使用\\转义\n" +
            "    echo \"\\n# 观察方法返回值\"\n" +
            "    echo \"watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage \\\"{params,returnObj}\\\" -x 2\"\n" +
            "    echo \"watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage \\\"{params,returnObj}\\\"\"\n" +
            "    echo \"# 观察方法入参, 对象层次限制2级\"\n" +
            "    echo \"watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage \\\"{params,returnObj}\\\" -x 2 -b\"\n" +
            "    echo \"# 持续记录3次接口调用\"\n" +
            "    echo \"tt -t -n 3 com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage\"\n" +
            "    echo \"# 展示记录接口调用的列表\"\n" +
            "    echo \"tt -l\"\n" +
            "    echo \"# 展示某个具体调用过程\"\n" +
            "    echo \"tt -i 1002\"\n" +
            "    echo \"# 重复某个具体调用, 重复3次, 重复间隔2秒\"\n" +
            "    echo \"tt -i 1002 -p --replay-times 3  --replay-interval 2000\"\n" +
            "    echo \"# 日志器\"\n" +
            "    echo \"logger\"\n" +
            "    echo \"# 类加载器列表\"\n" +
            "    echo \"classloader -t\"\n" +
            "    echo \"history\"\n" +
            "    echo \"help\"\n" +
            "    echo \"keymap\"\n" +
            "    echo \"dashboard\"\n" +
            "    echo \"# 清屏\"\n" +
            "    echo \"cls\"\n" +
            "    echo \"# 线程\"\n" +
            "    echo \"thread\"\n" +
            "    echo \"thread --state WAITING\"\n" +
            "    echo \"thread --state TIMED_WAITING\"\n" +
            "    echo \"thread --state RUNNABLE\"\n" +
            "    echo \"\\n# 退出、关闭等命令, 禁止ctrl + C\"\n" +
            "    echo \"# 退出某个命令\"\n" +
            "    echo \"Q\"\n" +
            "    echo \"# 退出当前arthas-client\"\n" +
            "    echo \"quit\"\n" +
            "    echo \"# 关闭arthas-server\"\n" +
            "    echo \"shutdown\"\n" +
            "}\n" +
            "\n" +
            "alias searchPID='searchPID'\n" +
            "alias killPID='killPID'\n" +
            "alias searchPortOccupy='searchPortOccupy'\n" +
            "alias https-downLoadResponse='downLoadResponse'\n" +
            "alias https-show='show'\n" +
            "alias https-downLoadExcel='downLoadExcel'\n" +
            "alias https-downloadD8ger='downloadD8ger'\n" +
            "\n" +
            "alias 'xDev=xDev'\n" +
            "alias 'xDevNormal=xDevNormal'\n" +
            "alias 'backgroundENV=backgroundENV'\n" +
            "\n" +
            "alias 'showssh=ps -ef | grep ssh'\n" +
            "alias 'tsm=justDoIT'\n" +
            "# 开隧道\n" +
            "alias 'iphone4j=nohup sshpass -f TUNNEL_DIR ssh xx@xx.xx.xx.xx -L 11186:xx.xx.xx.xx:1186 -N &'\n" +
            "\n" +
            "alias 'ip=ifconfig | grep xxx'\n" +
            "\n" +
            "alias 'cph=cph'\n" +
            "alias 'fkgrep=fkgrep'\n" +
            "alias 'arthasHelp=arthasHelp'\n" +
            "# 快速编辑.zshrc\n" +
            "alias 'vrc=vim ~/.zshrc'\n" +
            "# 快速展示.zshrc\n" +
            "alias 'crc=cat ~/.zshrc'\n" +
            "\n" +
            "# source ~/.bash_profile\n" +
            "if [ -f ~/.bash_profile ]; then\n" +
            "    . ~/.bash_profile;\n" +
            "fi\n" +
            "\n" +
            "\n" +
            "source $ZSH/oh-my-zsh.sh\n";

    public static String TEMPLATE_NASA = "=========================================NASA=========================================\n" +
            "Note: 1.D8ger-ALIGN(included by character '@') is the config keyword of this text,\n" +
            "      which these context included will be ignored;\n" +
            "      2.Multi-lines to be handled must include ','\n" +
            "      as the split keyword\n" +
            "      3.config example:\n" +
            "      - @<prefix=D8(>@         , add 'D8(' before the start of each line\n" +
            "      - @<suffix=)>@           , add ')' after the end of each line\n" +
            "      - @<alignStyle=LEFT>@    , you can config CENTER, RIGHT too\n" +
            "      - @<formatSQL=false>@    , if config SQL then it will append 'AS' alias name\n" +
            "      - @<formatAsCamel=false>@ , special for SQL column alias camel name\n" +
            "      4.As example below, one handled what you will find like this:\n" +
            "      first_name,       --> D8(first_name)        AS  firstName,\n" +
            "      current_age,      --> D8(current_age)       AS  currentAge,\n" +
            "      blog_url,         --> D8(blog_url)          AS  blogUrl,\n" +
            "      graduated_school, --> D8(graduated_school)  AS  graduatedSchool,\n" +
            "      total_assets      --> D8(total_assets)      AS  totalAssets\n" +
            "=========================================NASA=========================================\n" +
            "The next line is very import below, do not modify anything or you'll get nothing\n" +
            "@D8ger-ALIGN@\n" +
            "\n" +
            "\n" +
            "first_name,\n" +
            "current_age,\n" +
            "blog_url,\n" +
            "graduated_school,\n" +
            "total_assets\n";

    public static String TEMPLATE_END = "=========================================END=========================================\n" +
            "Note: 1.D8ger-ALIGN(included by character '@') is the config keyword of this text,\n" +
            "      which these context included will be ignored;\n" +
            "      2.Considering compatibility separator, Multi-lines to be handled\n" +
            "      must include ',' or line break or '，' as the split keyword.\n" +
            "      3.config example:\n" +
            "      - @<alignStyle=LEFT>@    , LEFT(default) and you can config CENTER, RIGHT too\n" +
            "      - @<algorithmType=1>@    , 1(default) as 'AES' and 2 as 'PinYin'\n" +
            "      - @<operateType=0>@      , 1 as encrypt, 2 as decrypt\n" +
            "                                 and 0(default) is encrypt + decrypt for complete\n" +
            "      4.As example below, one handled what you will find like this:\n" +
            "      When algorithmType=1 && operateType = 0\n" +
            "        MyName -->(first AES encryption) d8gerX==\n" +
            "               -->(then AES decryption)  MyName\n" +
            "      When algorithmType=2 && operateType = 0\n" +
            "        帝八哥  -->(first Fetch Chinese PinYin) dibage\n" +
            "               -->(then PinYin encryption)     d8gerY==\n" +
            "               -->(last PinYin decryption)     dibage\n" +
            "      Other case, just refer the two above.\n" +
            "=========================================END=========================================\n" +
            "The next line is very import below, do not modify anything or you'll get nothing\n" +
            "@D8ger-END@\n" +
            "\n" +
            "\n" +
            "AName";

}
