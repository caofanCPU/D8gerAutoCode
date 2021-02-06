package com.xyz.caofancpu.d8ger.core;

/**
 * Fixed link url
 *
 * @author D8GER
 */
public enum LinkUrlEnum {
    QR_CODE("@=@", "形式: key=url", "https://github.com/caofanCPU"),
    HELP("help", "帮助文档", "https://github.com/caofanCPU"),

    THREAD_POOL("thread_pool", "线程池", "https://mp.weixin.qq.com/s/dTMH1TdxiCKy5yotQ7u7cA"),
    THREAD_BASIC("thread_basic", "线程的基本认知", "https://mp.weixin.qq.com/s/p0c6DCTcuA7bXtSFDYDoPQ"),
    THREAD_INTERVIEW("thread_interview", "线程高频面试", "https://mp.weixin.qq.com/s/qvK9XA5JRZBBlm6qBlKGRw"),
    THREAD_LOCK_AQS("thread_lock_aqs", "并发JUC元祖AQS", "https://mp.weixin.qq.com/s/bxWgo9IuggDpE1l37JqEhQ"),
    THREAD_LOCK_CAS("thread_lock_cas", "并发JUC-CAS", "https://mp.weixin.qq.com/s/kvuPxn-vc8dke093XSE5IQ"),
    THREAD_LOCK_CONTAINER("thread_lock_container", "并发JUC-容器", "https://mp.weixin.qq.com/s/TlnAeajB8hAfImvuB6yGag"),
    THREAD_LOCK_SYNCHRONIZED("thread_lock_synchronized", "并发JUC-Synchronized", "https://mp.weixin.qq.com/s/e_fYFWK5Qnxjmz6Abi7uqw"),

    JAVA_INTERVIEW("java_interview", "Java预热面试", "https://mp.weixin.qq.com/s/d3x7anAT1AN8GKBOE6KO-A"),
    JVM_INTERVIEW("jvm_interview", "JVM高频面试", "https://mp.weixin.qq.com/s/Okkz4_J92yJdl-QCNnG_1g"),
    JVM_END("jvm_interview", "JVM原理", "https://mp.weixin.qq.com/s/0qN8d__7yiKxGNU7Eae08g"),
    JVM_END_MAP("jvm_end_map", "JVM原理高清大图", "https://www.processon.com/view/5fd72a125653bb06f33d2296?fromnew=1"),

    MYSQL_INTERVIEW("mysql_interview", "MySQL高频面试", "https://mp.weixin.qq.com/s/sIKQP3VJeSCU8jkegxyS_w"),
    MYSQL_BASIC("mysql", "MySQL基础图", "https://mp.weixin.qq.com/s/YXH47C4P2Sc1OQblyZlZzg"),
    MYSQL_EXPLAIN("mysql_explain", "MySQL执行计划", "https://mp.weixin.qq.com/s/PaHGeKOzW_HePzHjmpHArA"),
    MYSQL_END("mysql_end", "MySQL终极原理图", "https://mp.weixin.qq.com/s/uyu0lKz2_N5BYgITKz71CQ"),

    REDIS_INTERVIEW("redis_end", "Redis高频面试", "https://mp.weixin.qq.com/s/syECAfZai9HvCs02EQcvrw"),
    REDIS_BASIC("redis_basic", "Redis基础", "https://mp.weixin.qq.com/s/3giipjKU668jPqta6zAH7Q"),
    REDIS_CLUSTER("redis_cluster", "Redis集群", "https://mp.weixin.qq.com/s/k7Ps-pkwuNtZOw1CtENI3w"),
    REDIS_END("redis_end", "Redis应用设计", "https://mp.weixin.qq.com/s/pgYBiG7UyPIe2qX5fWWYfA"),

    KAFKA("kafka", "Kafka原理", "https://mp.weixin.qq.com/s/st-7k7WH5pvLZwA_o9jPpw"),
    RABBIT_MQ("rabbit_mq", "RabbitMQ原理", "https://mp.weixin.qq.com/s/FPylHiJ9PtWLv6zd1al23A"),

    HBASE("hbase", "HBase超然旅行", "https://mp.weixin.qq.com/s/Cc7O7GNLsR3po7juNw590A"),
    HADOOP("hadoop", "Hadoop俯视图", "https://mp.weixin.qq.com/s/Wq5kijx2DbObm3BMMB_Nig"),
    FLINK("flink", "Flink真经", "https://blog.jrwang.me/tags/flink/"),
    ZOOKEEPER("zookeeper", "掀开Zookeeper面纱", "https://mp.weixin.qq.com/s/W6QgmFTpXQ8EL-dVvLWsyg"),
    BG_PROTOCOL("bg_protocol", "浅析大数据协议", "https://mp.weixin.qq.com/s/b5mGEbn-FLb9vhOh1OpwIg"),

    OS_FILE("file", "揭秘读取文件内幕", "https://mp.weixin.qq.com/s/maTzszO4Y2dyxgm7g2YdXw"),
    OS_IO_REUSE("io_reuse", "起底IO多路复用", "https://mp.weixin.qq.com/s/tXEfsLqdePjcPS6FKa-qzg"),
    OS_RAM_POOL("io_ram_pool", "聊聊OS内存池技术", "https://mp.weixin.qq.com/s/-E7xhoqIkF_BCAF8q-Nk8w"),

    SPRING("spring", "Spring老生常谈", "https://mp.weixin.qq.com/s/-gLXHd_mylv_86sTMOgCBg"),
    ;

    private String topic;
    private String url;
    private String desc;


    LinkUrlEnum(String key, String desc, String url) {
        this.topic = key;
        this.url = url;
        this.desc = desc;
    }

    public static String loadHelpInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("链接使用指南").append("\n").append("<输入关键字>").append(" ---> ").append("获取目标内容").append("\n");
        for (LinkUrlEnum item : LinkUrlEnum.values()) {
            sb.append("[").append(item.getTopic()).append("]").append(" ---> ").append(item.getDesc()).append("\n");
        }
        return sb.toString();
    }

    public String getTopic() {
        return topic;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

}
