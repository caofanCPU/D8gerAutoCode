  ,---.  ,------. ,--------.,--.  ,--.  ,---.   ,---.
 /  O  \ |  .--. ''--.  .--'|  '--'  | /  O  \ '   .-'
|  .-.  ||  '--'.'   |  |   |  .--.  ||  .-.  |`.  `-.
|  | |  ||  |\  \    |  |   |  |  |  ||  | |  |.-'    |
`--' `--'`--' '--'   `--'   `--'  `--'`--' `--'`-----'
# 观察方法返回值
watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage "{params,returnObj}" -x 2 -n 5
watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage "{params,returnObj}"
# 观察方法入参, 对象层次限制2级
watch com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage "{params,returnObj}" -x 2 -b
# 持续记录3次接口调用
tt -t -n 3 com.xyz.caofancpu.trackingtime.controller.D8gerController queryD8gerMoPage
# 展示记录接口调用的列表
tt -l
# 展示某个具体调用过程
tt -i 1002
# 重复某个具体调用, 重复3次, 重复间隔2秒
tt -i 1002 -p --replay-times 3  --replay-interval 2000
# 日志器
logger
# 类加载器列表
classloader -t
history
help
keymap
dashboard
# 清屏
cls
# 线程
thread
thread --state WAITING
thread --state TIMED_WAITING
thread --state RUNNABLE

# 退出、关闭等命令, 禁止ctrl + C
# 退出某个命令
Q
# 退出当前arthas-client
quit
# 关闭arthas-server
shutdown
