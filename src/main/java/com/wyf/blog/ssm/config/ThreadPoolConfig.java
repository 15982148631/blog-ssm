package com.wyf.blog.ssm.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wyf
 * @ClassName ThreadPoolConfig
 * @Description 线程池类
 * @Date 2021/3/2 9:36
 * @Version 1.0.0
 */
@Component
public class ThreadPoolConfig implements SysTemInit{

    private static final Integer MAX_THREAD_POOL = 5;

    private static final Integer TIME_OUT = 30 * 60 ;

    public static ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD_POOL);

    static {
        Thread thread = getThread();
        thread.setName("tos-thred");
        String name = thread.getName();
        executor.execute(()-> System.out.println("new Thread````"+name));

    }


    public ThreadPoolConfig(){

    }

    public ThreadPoolConfig(Thread t){

    }

    public static Thread getThread(){
       return new Thread();
    }

    @Override
    public void init() {

    }


    @Override
    public void destory() {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }

}
