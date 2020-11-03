package io.github.chengzhe.gitway.outbound.httpclient4;

import java.security.SecureRandom;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/4 01:17
 * @Description:
 */
public class NameThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger();
    private final String namePrefix;
    private final boolean daemon;

    public NameThreadFactory(String namePrefix, boolean daemon) {
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    public NameThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        thread.setDaemon(daemon);
        return thread;
    }
}
