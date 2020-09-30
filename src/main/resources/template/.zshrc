export ZSH="$HOME/.oh-my-zsh"
export HOMEBREW_NO_AUTO_UPDATE=true

ZSH_THEME="powerlevel9k/powerlevel9k"
POWERLEVEL9K_MODE="nerdfont-complete"
POWERLEVEL9K_PROMPT_ON_NEWLINE=true
POWERLEVEL9K_RPROMPT_ON_NEWLINE=false
POWERLEVEL9K_LEFT_PROMPT_ELEMENTS=(os_icon user dir_writable dir vcs)
POWERLEVEL9K_RIGHT_PROMPT_ELEMENTS=(status command_execution_time root_indicator background_jobs time disk_usage ram)
#POWERLEVEL9K_MULTILINE_LAST_PROMPT_PREFIX="%(?:%{$fg_bold[green]%}➜ :%{$fg_bold[red]%}➜ )"
#POWERLEVEL9K_MULTILINE_FIRST_PROMPT_PREFIX=""
#POWERLEVEL9K_USER_ICON="\uF415" # 
POWERLEVEL9K_ROOT_ICON="\uF09C"
#POWERLEVEL9K_SUDO_ICON=$'\uF09C' # 
POWERLEVEL9K_TIME_FORMAT="%D{%H:%M}"
#POWERLEVEL9K_VCS_GIT_ICON='\uF408 '
#POWERLEVEL9K_VCS_GIT_GITHUB_ICON='\uF408 '

ZSH_DISABLE_COMPFIX=true
#ENABLE_CORRECTION="true"
DISABLE_CORRECTION=true
COMPLETION_WAITING_DOTS=true

# 左侧栏目显示的要素（指定的关键字参考官网）
#POWERLEVEL9K_LEFT_PROMPT_ELEMENTS=(os_icon context dir vcs)
# 右侧栏目显示的要素
#POWERLEVEL9K_RIGHT_PROMPT_ELEMENTS=(status root_indicator background_jobs time virtualenv)
#新起一行显示命令 (推荐！极其方便）
#POWERLEVEL9K_PROMPT_ON_NEWLINE=true
#右侧状态栏与命令在同一行
#POWERLEVEL9K_RPROMPT_ON_NEWLINE=false
#缩短目录层级
#POWERLEVEL9K_SHORTEN_DIR_LENGTH=1
#缩短目录策略：隐藏上层目录中间的字
#POWERLEVEL9K_SHORTEN_STRATEGY="truncate_middle"
#添加连接上下连接箭头更方便查看
#POWERLEVEL9K_MULTILINE_FIRST_PROMPT_PREFIX="↱"
#POWERLEVEL9K_MULTILINE_LAST_PROMPT_PREFIX="↳ "
# 新的命令与上面的命令隔开一行
#POWERLEVEL9K_PROMPT_ADD_NEWLINE=true
# Git仓库状态的色彩指定
#POWERLEVEL9K_VCS_CLEAN_FOREGROUND='blue'
#POWERLEVEL9K_VCS_CLEAN_BACKGROUND='black'
#POWERLEVEL9K_VCS_UNTRACKED_FOREGROUND='yellow'
#POWERLEVEL9K_VCS_UNTRACKED_BACKGROUND='black'
#POWERLEVEL9K_VCS_MODIFIED_FOREGROUND='red'
#POWERLEVEL9K_VCS_MODIFIED_BACKGROUND='black'


DISABLE_AUTO_UPDATE="true"
# autojump   : 'j'历史目录
# cp         : 'cpv'带进度条的复制
# zsh_reload : 'src'快速重载.zshrc
# 快速编辑.zshrc: alias 'vrc=vim ~/.zshrc'
# 快速展示.zshrc: alias 'crc=cat ~/.zshrc'
# extract    : 'x'解压任何文件
plugins=(git autojump cp zsh_reload extract zsh-syntax-highlighting)

export LC_ALL=en_US.UTF-8  
export LANG=en_US.UTF-8

# Maven家目录
export M2_HOME=/usr/local/maven-3.6.1/apache-maven-3.6.1
alias mcf="/usr/local/maven-3.6.1/caofanCPU-apache-maven-3.6.1/bin/mvn"

# 查看java安装位置命令 /usr/libexec/java_home -V
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home
export JRE_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$M2_HOME/bin:$JAVA_HOME/bin:$JRE_HOME/bin:$JAVA_HOME:$PATH

# Python项目打包工具包
export TWINE_HOME=/Users/D8GER/Library/Python/3.7
export PATH=$PATH:$TWINE_HOME/bin

export PATH=$PATH:$HOME/bin
export PATH="$PATH:/usr/local/python3/bin"

# thefuck插件
eval "$(thefuck --alias)"

# 服务器
alias caofanCPU='sshpass -f PWD_DIR  ssh  xxxx@xx.xx.xx.xx'
alias caofanJumpAli='sshpass -f PWD_DIR  ssh  xx@xx.xx.xx.xx'

function execute() {
    http --session=~/Desktop/ssoLogin/session-${1}.json --verify=no -v ${2} POST ${@:3}
}

function downLoadResponse() {
    echo "执行命令内容: \n    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -d >>~/Desktop/ssoLogin/ResponseBody.json ${@:2}\n"
    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -d >>~/Desktop/ssoLogin/ResponseBody.json ${@:2}
}

function downLoadExcel() {
    echo "执行命令内容: \n    http --verify=no -v --download  --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -o ${2}"
    http --verify=no -v --download  --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json -o ${2}
}

function downloadD8ger() {
    echo "执行命令内容: 并发下载文件"
    for i in {1..30}
        do 
            http --verify=no -v --download  GET  ${1} -o D8.zip
    done
}

function show() {
    echo "执行命令内容: \n    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json ${@:2}\n"
    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${1} < ~/Desktop/ssoLogin/requestBody.json ${@:2}
}

function searchPortOccupy(){
    lsof -i :${1}
}

function searchPID(){
    ps aux | grep ${1} | grep -v grep | awk '{print $2}'
}

function killPID(){
    pid=`ps aux | grep ${1} | grep -v grep | awk '{print $2}'`
    if [ -n "$pid" ]; then
        kill -9 $pid
        sleep 1
    fi
}


# 环境
function xDev() {
    sshpass -f ENV_PIR.txt  ssh xx@xx.xx.xx.${1}  -t  'cd /work/www/d8ger.com/logs/; exec $SHELL'
}

# 环境普通模式
function xDevNormal(){
    sshpass -f ENV_PIR.txt  ssh xx@xx.xx.xx.${1}
}

# 不同环境
function backgroundENV() {
    env=${1}
    if [ -z "$env" ]; then
        echo "请选择可用环境: 1,2,3,4,5,6,7,8,9,10"
        return
    fi
    domain=''
    if [ "$env" = '1' ]; then
        domain='https://1.d8ger.com'
    elif [ "$env" = '2' ]; then
        domain='https://2.d8ger.com'
    elif [ "$env" = '3' ]; then
        domain='https://3.d8ger.com'
    elif [ "$env" = '4' ]; then
        domain='https://4.d8ger.com'
    elif [ "$env" = '5' ]; then
        domain='https://5.d8ger.com'
    elif [ "$env" = '6' ]; then
        domain='https://6.d8ger.com'
    elif [ "$env" = '7' ]; then
        domain='https://7.d8ger.com'
    elif [ "$env" = '8' ]; then
        domain='https://8.d8ger.com'
    elif [ "$env" = '9' ]; then
        domain='https://9.d8ger.com'
    elif [ "$env" = '10' ]; then
        domain='https://ok.d8ger.com'
    fi
    # 判断
    if [ -z "$domain" ]; then
        echo "请选择可用环境: 1,2,3,4,5,6,7,8,9,10"
        return
    fi
    echo "德玛西亚, http -v --verify=no POST  ${domain}/ok/xx"
    echo "查询任务是否启动及是否完成: grep \"德玛西亚\" all.log | grep \"end\""
    http -v --verify=no POST  ${domain}/ok/xx
}

function justDoIT(){
    SID=${1}
    if [ -z "$SID" ]; then
        echo "非法的SID, 请检查"
        return
    fi    
    url="https://1.d8ger.com/xx/yy/zz"
    # 登录
    login-cookie -f ~/Desktop/ssoLogin/sso-on-admin.json -a 6 
    # 执行
    echo "执行:\n http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${url} sId:=${SID} bId:=50220671 cIds:='[]' areUok:=false healthy:=true\n"
    # http多个参数不能放在一个字符串中, 用多个变量来区分解决
    http --verify=no -v --session-read-only=~/session-cookie-read-only.json POST ${url} sId:=${SID} bId:=50220671 cIds:='[]' areUok:=false healthy:=true
}

function cph(){
    echo ",------.   ,---.  ,----.   ,------.,------."
    echo "|  .-.  \\ |  o  |'  .-./   |  .---'|  .--. '"
    echo "|  |  \\  :.'   '.|  | .---.|  \`--, |  '--'.'"
    echo "|  '--'  /|  o  |'  '--'  ||  \`---.|  |\\  \\"
    echo "\`-------'  \`---'  \`------' \`------'\`--' '--'"
    echo "##### SCP命令 #####"
    echo "# -r 支持复制目录及其子文件"
    echo "- 本地文件传到远程服务器"
    echo "scp /X_DIR/LEARN-SH.sh  xx@xx.xx.xx.xx:~/"
    echo "- 从远程服务器拉取文件"
    echo "scp xx@xx.xx.xx.xx:~/HAHA.tmp /X_DIR/ZZ.xls"
    echo "- 无痕登录"
    echo "xD8scp || d8scp"
    echo "sshpass -f /X_DIR/PWD-SSH.txt scp /X_DIR/LEARN-SH.sh  xx@xx.xx.xx.xx:~/"
    echo "sshpass -f /X_DIR/PWD-SSH.txt scp xx@xx.xx.xx.xx:~/HAHA.tmp /X_DIR/ZZ.xls"
    echo "##### cpv #####, zsh的一个插件cp, 文件复制时展示进度条"
    echo "##### sudo cp #####, 普通复制"
}

function fkgrep(){
    echo ",------.,--. ,--. ,----.   ,------. ,------.,------."
    echo "|  .---'|  .'   /'  .-./   |  .--. '|  .---'|  .--. '"
    echo "|  \`--, |  .   ' |  | .---.|  '--'.'|  \`--, |  '--' |"
    echo "|  |\`   |  |\\   \'  '--'  ||  |\\  \\ |  \`---.|  | --'"
    echo "\`--'    \`--' '--' \`------' \`--' '--'\`------'\`--'"
    echo ""
    echo "grep -n '[a-zA-Z0-9]D8' X.txt"
    echo "grep -n '[^a-zA-Z0-9]D9' X.txt"
    echo "grep -n '^[a-z]' X.txt"
    echo "grep -n '^[^a-z]' X.txt"
    echo "grep -n '^$' X.txt"
    echo "grep -n '\.$' X.txt"
    echo "grep -n 'g.*d' X.txt"
    echo "grep -n 'go*d' X.txt"
    echo "grep -n 'o\{2,3\}' X.txt"
    echo "grep -En 'God|The'  X.txt     grep -n 'god\|The' X.txt"
    echo "grep -En 'o+' X.txt           grep -n 'o\+' X.txt"
    echo "grep -n '\.' X.txt            grep -En '\.' X.txt"
    echo "grep -En '(oo)+' X.txt        grep -n '\(oo\)\+' X.txt"
    echo "Search Today's log: ll -ah | grep \"[a-z_A-Z]\+\.log\""
}

function arthasHelp(){
    echo "  ,---.  ,------. ,--------.,--.  ,--.  ,---.   ,---."
    echo " /  O  \\ |  .--. ''--.  .--'|  '--'  | /  O  \\ '   .-'"
    echo "|  .-.  ||  '--'.'   |  |   |  .--.  ||  .-.  |\`.  \`-."
    echo "|  | |  ||  |\\  \\    |  |   |  |  |  ||  | |  |.-'    |"
    echo "\`--' \`--'\`--' '--'   \`--'   \`--'  \`--'\`--' \`--'\`-----'"
    # `和\ 为特殊字符, 必须使用\转义
    echo "\n# 观察方法返回值"
    echo "watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage \"{params,returnObj}\" -x 2"
    echo "watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage \"{params,returnObj}\""
    echo "# 观察方法入参, 对象层次限制2级"
    echo "watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage \"{params,returnObj}\" -x 2 -b"
    echo "# 持续记录3次接口调用"
    echo "tt -t -n 3 com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage"
    echo "# 展示记录接口调用的列表"
    echo "tt -l"
    echo "# 展示某个具体调用过程"
    echo "tt -i 1002"
    echo "# 重复某个具体调用, 重复3次, 重复间隔2秒"
    echo "tt -i 1002 -p --replay-times 3  --replay-interval 2000"
    echo "# 日志器"
    echo "logger"
    echo "# 类加载器列表"
    echo "classloader -t"
    echo "history"
    echo "help"
    echo "keymap"
    echo "dashboard"
    echo "# 清屏"
    echo "cls"
    echo "# 线程"
    echo "thread"
    echo "thread --state WAITING"
    echo "thread --state TIMED_WAITING"
    echo "thread --state RUNNABLE"
    echo "\n# 退出、关闭等命令, 禁止ctrl + C"
    echo "# 退出某个命令"
    echo "Q"
    echo "# 退出当前arthas-client"
    echo "quit"
    echo "# 关闭arthas-server"
    echo "shutdown"
}

alias searchPID='searchPID'
alias killPID='killPID'
alias searchPortOccupy='searchPortOccupy'
alias https-downLoadResponse='downLoadResponse'
alias https-show='show'
alias https-downLoadExcel='downLoadExcel'
alias https-downloadD8ger='downloadD8ger'

alias 'xDev=xDev'
alias 'xDevNormal=xDevNormal'
alias 'backgroundENV=backgroundENV'

alias 'showssh=ps -ef | grep ssh'
alias 'tsm=justDoIT'
# 开隧道
alias 'iphone4j=nohup sshpass -f TUNNEL_DIR ssh xx@xx.xx.xx.xx -L 11186:xx.xx.xx.xx:1186 -N &'
alias 'ip=ifconfig | grep xxx'

alias 'cph=cph'
alias 'fkgrep=fkgrep'
alias 'arthasHelp=arthasHelp'
# 快速编辑.zshrc
alias 'vrc=vim ~/.zshrc'
# 快速展示.zshrc
alias 'crc=cat ~/.zshrc'

# source ~/.bash_profile
if [ -f ~/.bash_profile ]; then
    . ~/.bash_profile;
fi


source $ZSH/oh-my-zsh.sh
