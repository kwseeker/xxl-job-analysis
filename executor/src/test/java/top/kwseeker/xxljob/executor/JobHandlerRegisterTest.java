package top.kwseeker.xxljob.executor;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobHandlerRegisterTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testRegisterJobHandler() {
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);

            Map<Method, XxlJob> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        new MethodIntrospector.MetadataLookup<XxlJob>() {
                            public XxlJob inspect(Method method) {
                                return AnnotatedElementUtils.findMergedAnnotation(method, XxlJob.class);
                            }
                        });
            } catch (Throwable ex) {
                System.out.println("scan xxl job handler exception: " + ex);
            }

            if (annotatedMethods == null || annotatedMethods.isEmpty()) {
                continue;
            }

            for (Map.Entry<Method, XxlJob> methodXxlJobEntry : annotatedMethods.entrySet()) {
                Method executeMethod = methodXxlJobEntry.getKey();
                System.out.println("xxl-job handler method: " + executeMethod.getName());
                XxlJob xxlJob = methodXxlJobEntry.getValue();
                System.out.println("xxl-job handler name: " + xxlJob.value());
            }
        }
    }
}
