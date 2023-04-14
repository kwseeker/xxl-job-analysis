# XXL-Job 源码分析

是一个分布式任务调度中间件，轻量（不算空行Java代码量仅有9k多行），代码也很容易理解。



## 分析文档

+ [1_启动&主流程调试.md](doc/1_启动&主流程调试.md)
+ [2_主流程源码分析.md](doc/2_主流程源码分析.md)

+ [3_一些重要功能实现原理.md](3_一些重要功能实现原理.md)



## 主流程图





## 细节实现分析

+ **表单请求参数映射到对象**

  ```java
  //InvocableHandlerMethod.java
  @Nullable
  public Object invokeForRequest(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer, Object... providedArgs) throws Exception {
      //转换发生在这一步 TODO
      Object[] args = this.getMethodArgumentValues(request, mavContainer, providedArgs);
      if (logger.isTraceEnabled()) {
          logger.trace("Arguments: " + Arrays.toString(args));
      }
      return this.doInvoke(args);
  }
  ```

  

