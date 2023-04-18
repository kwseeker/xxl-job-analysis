# 部署

为模拟线上环境，这里部署两个调度中心（通过 nginx 负载均衡）、两个执行器。



## 配置

+ 调度中心

  ```properties
  ### web
  # 两个节点端口分别是8080、8081
  server.port=8080
  #server.port=8081
  server.servlet.context-path=/xxl-job-admin
  
  ### actuator
  management.server.servlet.context-path=/actuator
  management.health.mail.enabled=true
  
  ### resources
  spring.mvc.servlet.load-on-startup=0
  spring.mvc.static-path-pattern=/static/**
  spring.resources.static-locations=classpath:/static/
  
  ### freemarker
  spring.freemarker.templateLoaderPath=classpath:/templates/
  spring.freemarker.suffix=.ftl
  spring.freemarker.charset=UTF-8
  spring.freemarker.request-context-attribute=request
  spring.freemarker.settings.number_format=0.##########
  
  ### mybatis
  mybatis.mapper-locations=classpath:/mybatis-mapper/*Mapper.xml
  #mybatis.type-aliases-package=com.xxl.job.admin.core.model
  
  ### xxl-job, datasource
  spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
  spring.datasource.username=root
  spring.datasource.password=123456
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  
  ### datasource-pool
  spring.datasource.type=com.zaxxer.hikari.HikariDataSource
  spring.datasource.hikari.minimum-idle=10
  spring.datasource.hikari.maximum-pool-size=30
  spring.datasource.hikari.auto-commit=true
  spring.datasource.hikari.idle-timeout=30000
  spring.datasource.hikari.pool-name=HikariCP
  spring.datasource.hikari.max-lifetime=900000
  spring.datasource.hikari.connection-timeout=10000
  spring.datasource.hikari.connection-test-query=SELECT 1
  spring.datasource.hikari.validation-timeout=1000
  
  ### xxl-job, email
  spring.mail.host=smtp.qq.com
  spring.mail.port=25
  spring.mail.username=345945094@qq.com
  spring.mail.from=345945094@qq.com
  spring.mail.password=xxx
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true
  spring.mail.properties.mail.smtp.starttls.required=true
  spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
  
  ### xxl-job, access token
  xxl.job.accessToken=default_token
  
  ### xxl-job, i18n (default is zh_CN, and you can choose "zh_CN", "zh_TC" and "en")
  xxl.job.i18n=zh_CN
  
  ## xxl-job, triggerpool max size
  xxl.job.triggerpool.fast.max=200
  xxl.job.triggerpool.slow.max=100
  
  ### xxl-job, log retention days
  xxl.job.logretentiondays=30
  ```

  

+ nginx

+ 执行器


