package com.by.config;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
@Configuration
@EnableScheduling
public class ScheduledTask {
    /*
     * name属性：锁名称，必须指定，每次只能执行一个具有相同名字的任务，锁名称应该是全局唯一的;
     * lockAtMostFor属性：设置锁的最大持有时间,为了解决如果持有锁的节点挂了,无法释放锁,其他节点无法进行下一次任务;
     * lockAtMostForString属性：成功执行任务的节点所能拥有的独占锁的最长时间的字符串表达，例如“PT14M”表示为14分钟
     * lockAtLeastFor属性：指定保留锁的最短时间。主要目的是在任务非常短的且节点之间存在时钟差异的情况下防止多个节点执行。
     * 这个属性是锁的持有时间。设置了多少就一定会持有多长时间，再此期间，下一次任务执行时，其他节点包括它本身是不会执行任务的
     * */
    private static final int TWENTY_NINE_MIN = 5 * 1000;

    @Scheduled(cron = "*/5 * * * * ?")
    @SchedulerLock(name = "testCp", lockAtMostFor = TWENTY_NINE_MIN, lockAtLeastFor = TWENTY_NINE_MIN)
    public void timedTask() {
        System.out.println("====================================");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh mm ss").format(Calendar.getInstance().getTime()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh mm ss").format(Calendar.getInstance().getTime()));
        System.out.println("====================================");
    }
}
