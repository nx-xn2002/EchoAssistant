package com.bupt.echoassistantbackend.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * thread pool config
 *
 * @author Ni Xiang
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
	@Value("${thread-pool-config.core-pool-size}")
	private int corePoolSize;
	@Value("${thread-pool-config.max-pool-size}")
	private int maxPoolSize;
	@Value("${thread-pool-config.queue-capacity}")
	private int queueCapacity;
	@Value("${thread-pool-config.keep-alive-seconds}")
	private int keepAliveSeconds;
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		executor.setCorePoolSize(corePoolSize);
		// 设置最大线程数
		executor.setMaxPoolSize(maxPoolSize);
		// 设置队列容量
		executor.setQueueCapacity(queueCapacity);
		// 设置线程活跃时间,单位秒
		executor.setKeepAliveSeconds(keepAliveSeconds);
		// 设置核心线程超时回收
		executor.setAllowCoreThreadTimeOut(true);
		// 设置默认线程名称
		executor.setThreadNamePrefix("IThread-");
		// 设置拒绝策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		return executor;

	}
}