package top.kwseeker.xxljob.executor.service.job.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * JobHandler 定义任务处理逻辑
 * 任务处理方法通过 @XxlJob 注释，在 XxlJobSpringExecutor 实例化后通过扫描所有 Bean @XxlJob 注释的方法，
 * 创建 MethodJobHandler 并注册到名为 jobHandlerRepository 的 ConcurrentHashMap 中。
 */
@Component
public class MyJobHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyJobHandler.class);

    @XxlJob(value = "lifecycleJobHandler", init = "init", destroy = "destroy")
    public void handleLifecycleJob() {
        XxlJobHelper.log("handle LifecycleJob");
    }

    public void init(){
        logger.info("Job handler init");
    }

    public void destroy(){
        logger.info("Job handler destroy");
    }
}
