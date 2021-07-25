# SpringCloud

## Netfix

- 服务发现和注册——Eureka
- API网关——Zuul
- 断路器——Hystrix
- 客服端负载均衡——Ribbon
- 分布式配置——Spring Cloud Config

### 项目构建

#### 版本对应

- 网址：https://start.spring.io/actuator/info

  

  ![image-20210721131955709](http://qwkrktldg.hn-bkt.clouddn.com/image-20210721131955709.png)

#### maven构建（父项目）

```xml
<!--统一管理jar包版本-->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.targer>1.8</maven.compiler.targer>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>8.0.19</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>
<!--版本管理 实际并未导入-->

  <dependencyManagement>
    <dependencies>
      <!--springboot依赖-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.3.4.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--springboot启动器依赖-->
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>

      <!--spring-cloud依赖-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring-cloud-alibaba依赖-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--mysql数据库依赖-->
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>
      <!--单元测试依赖-->
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <!--日志依赖-->
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>
      <!--lombok依赖-->
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
      </dependency>
      <!--数据源依赖-->
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>

    </dependencies>
  </dependencyManagement>


```

#### 实体类（子模块）

- 独立成模块  生产者 消费者都需引入

- 依赖

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>springcloud</artifactId>
          <groupId>com.whitestorm.springcloud</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
      <artifactId>spring-cloud-api</artifactId>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  <!--当前model自己需要的依赖-->
      <dependencies>
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
          </dependency>
  
      </dependencies>
  
  </project>
  ```

- Class

  ```java
  @Data
  @NoArgsConstructor
  @Accessors(chain = true)//链式写法
  public class Dept implements Serializable {
     private Long id;
     private String dname;
     private String db_source;
  
     public Dept(String dname){
        this.dname = dname;
     }
  }
  ```

#### 生产者（子模块）

- maven

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>springcloud</artifactId>
          <groupId>com.whitestorm.springcloud</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>spring-cloud-producer-dept-8001</artifactId>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
      <dependencies>
          <dependency>
              <groupId>com.whitestorm.springcloud</groupId>
              <artifactId>spring-cloud-api</artifactId>
              <version>1.0-SNAPSHOT</version>
          </dependency>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
          </dependency>
          <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>druid</artifactId>
          </dependency>
          <dependency>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
          </dependency>
          <dependency>
              <groupId>org.mybatis.spring.boot</groupId>
              <artifactId>mybatis-spring-boot-starter</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
          </dependency>
          <!--热部署-->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-devtools</artifactId>
          </dependency>
      </dependencies>
  
  </project>
  ```

- application.yaml

  ```yaml
  server:
    port: 8001
  
  mybatis:
  # 指定POJO扫描包
    type-aliases-package: com.whitestorm.springcloud.entities
  # mapper地址
    mapper-locations: classpath:mybatis/mapper/mapper/*.xml
  # mybatis-config地址
    config-location: classpath:mybatis/mybatis-config.xml
  
  spring:
    application:
      name: spring-cloud-producer-dept
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      type: com.alibaba.druid.pool.DruidDataSource
      url: jdbc:mysql://localhost:3306/db01?useUnicode=true&characherEncoding=utf-8
      username: root
      password: root
  ```

- 统一返回体**（消费者中也有）**

  - HttpStatus

    ```java
    package com.whitestorm.springcloud.common;
    
    public interface HttpStatus
    {
        /**
         * 操作成功
         */
        public static final int SUCCESS = 200;
    
        /**
         * 对象创建成功
         */
        public static final int CREATED = 201;
    
        /**
         * 请求已经被接受
         */
        public static final int ACCEPTED = 202;
    
        /**
         * 操作已经执行成功，但是没有返回数据
         */
        public static final int NO_CONTENT = 204;
    
        /**
         * 资源已被移除
         */
        public static final int MOVED_PERM = 301;
    
        /**
         * 重定向
         */
        public static final int SEE_OTHER = 303;
    
        /**
         * 资源没有被修改
         */
        public static final int NOT_MODIFIED = 304;
    
        /**
         * 参数列表错误（缺少，格式不匹配）
         */
        public static final int BAD_REQUEST = 400;
    
        /**
         * 未授权
         */
        public static final int UNAUTHORIZED = 401;
    
        /**
         * 访问受限，授权过期
         */
        public static final int FORBIDDEN = 403;
    
        /**
         * 资源，服务未找到
         */
        public static final int NOT_FOUND = 404;
    
        /**
         * 不允许的http方法
         */
        public static final int BAD_METHOD = 405;
        /**
         * 请求超时
         */
        public static final int TIME_OUT = 408;
    
        /**
         * 资源冲突，或者资源被锁
         */
        public static final int CONFLICT = 409;
    
        /**
         * 不支持的数据，媒体类型
         */
        public static final int UNSUPPORTED_TYPE = 415;
    
        /**
         * 系统内部错误
         */
        public static final int ERROR = 500;
    
        /**
         * 接口未实现
         */
        public static final int NOT_IMPLEMENTED = 501;
    }
    ```

  - CommonResultCode

    ```java
    package com.whitestorm.springcloud.common;
    
    /**
     * @author admin
     */
    
    public enum CommonResultCode {
        SUCCESS(HttpStatus.SUCCESS, "SUCCESS"),
        BAD_REQUEST(HttpStatus.BAD_REQUEST, "参数或者语法不对"),
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "认证失败"),
        LOGIN_ERROR(HttpStatus.UNAUTHORIZED, "登陆失败,用户名或密码无效"),
        FORBIDDEN(HttpStatus.FORBIDDEN, "权限不住,禁止访问"),
        NOT_FOUND(HttpStatus.NOT_FOUND, "请求的资源不存在"),
        OPERATE_ERROR(HttpStatus.BAD_METHOD, "操作失败,请求操作的资源不存在"),
        TIME_OUT(HttpStatus.TIME_OUT, "请求超时"),
        SERVER_ERROR(HttpStatus.ERROR, "服务器内部错误"),
        REGISTER_SUCCESS(HttpStatus.SUCCESS, "帐号注册成功"),
        REGISTER_FAIL(HttpStatus.ERROR, "帐号注册失败");
    
        private final int code;
    
        private final String msg;
    
        CommonResultCode(int code, String msg)
        {
            this.code = code;
            this.msg = msg;
        }
    
        public int getCode()
        {
            return code;
        }
    
        public String getMsg()
        {
            return msg;
        }
    }
    ```

  - CommonResult

    ```java
    package com.whitestorm.springcloud.common;
    
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    import java.io.Serializable;
    
    @Data
    @NoArgsConstructor
    public class CommonResult implements Serializable
    {
        private static final long serialVersionUID = 1L;
    
        /**
         * 返回代码
         */
        private Integer code;
    
        /**
         * 失败消息
         */
        private String msg;
    
        /**
         * 时间戳
         */
    
    
        /**
         * 返回数据
         */
        private Object data;
    
        public CommonResult(String msg, int code)
        {
            this.setMsg(msg);
            this.setCode(code);
    
        }
    
        public CommonResult(String msg, int code, Object data)
        {
            this.setMsg(msg);
            this.setCode(code);
            this.setData(data);
    
        }
    
        public CommonResult(Integer code, String msg, Object data)
        {
            this.code = code;
            this.msg = msg;
            this.data = data;
    
        }
    
        public static CommonResult success()
        {
            return new CommonResult(CommonResultCode.SUCCESS.getMsg(), CommonResultCode.SUCCESS.getCode());
        }
    
        public static CommonResult success(Object data)
        {
            return new CommonResult(CommonResultCode.SUCCESS.getMsg(), CommonResultCode.SUCCESS.getCode(), data);
        }
    
        public static CommonResult success(String msg, Object data)
        {
            return new CommonResult(msg, HttpStatus.SUCCESS, data);
        }
    
        public static CommonResult success(CommonResultCode CommonResultCode, Object data)
        {
            return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode(), data);
        }
    
        public static CommonResult success(CommonResultCode CommonResultCode)
        {
            return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode());
        }
    
        public static CommonResult fail()
        {
            return new CommonResult(CommonResultCode.SERVER_ERROR.getMsg(), CommonResultCode.SERVER_ERROR.getCode());
        }
    
        public static CommonResult fail(String msg)
        {
            return new CommonResult(msg, HttpStatus.ERROR);
        }
    
        public static CommonResult fail(CommonResultCode CommonResultCode)
        {
            return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode());
        }
    
        public static CommonResult fail(CommonResultCode CommonResultCode, Object data)
        {
            return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode(), data);
        }
    
        @Override
        public String toString()
        {
            return "{" + "\"code\":" + code + ", \"msg\":\"" + msg + '\"' + ", \"data\":\"" + data + '\"' + ", \"time\":\""  + '}';
        }
    }
    ```

  - mybatis-config

    ```java
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <settings>
    <!--开启二级缓存-->
            <setting name="cacheEnabled" value="true" />
            <setting name="useGeneratedKeys" value="true" />
            <setting name="defaultExecutorType" value="REUSE" />
            <setting name="logImpl" value="STDOUT_LOGGING" />
        </settings>
    
    </configuration>
    ```

  - DeptDaoMapper

    ```java
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <settings>
    <!--开启二级缓存-->
            <setting name="cacheEnabled" value="true" />
            <setting name="useGeneratedKeys" value="true" />
            <setting name="defaultExecutorType" value="REUSE" />
            <setting name="logImpl" value="STDOUT_LOGGING" />
        </settings>
    
    </configuration>
    ```

  - DeptDao

    ```java
    package com.whitestorm.dao;
    
    
    import org.apache.ibatis.annotations.Mapper;
    import org.springframework.stereotype.Repository;
    
    import java.util.List;
    
    @Mapper
    @Repository
    
    public interface DeptDao {
        public boolean addDept(Dept dept);
        public Dept queryById(Long id);
        public List<Dept> queryAll();
    }
    ```

  - DeptService

    ```java
    public interface DeptServicee {
        public boolean addDept(Dept dept);
        public Dept queryById(Long id);
        public List<Dept> queryAll();
    }
    ```

  - DeptServiceImpl

    ```java
    package com.whitestorm.service;
    
    import com.whitestorm.dao.DeptDao;
    import com.whitestorm.springcloud.entities.Dept;
    import lombok.extern.log4j.Log4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    import java.util.List;
    
    @Service
    @Log4j
    public class DeptServiceeImpl implements DeptServicee {
    
        @Autowired
        private DeptDao deptDao;
        @Override
        public boolean addDept(Dept dept) {
            return deptDao.addDept(dept);
        }
    
        @Override
        public Dept queryById(Long id) {
            return deptDao.queryById(id);
        }
    
        @Override
        public List<Dept> queryAll()  {
            return deptDao.queryAll();
        }
    }
    ```

  

  - DeptController

    ```java
    package com.whitestorm.controller;
    
    import com.whitestorm.common.CommonResult;
    import com.whitestorm.service.DeptServiceeImpl;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @Log4j
    public class DeptController {
    
        @Autowired
        private DeptServiceeImpl deptServicee;
    
    
        @PostMapping("/dept/add")
        public CommonResult addDept(@RequestBody Dept dept){
            boolean flag = deptServicee.addDept(dept);
            if(flag){
                return CommonResult.success(dept);
            }else{
                return CommonResult.fail();
            }
        }
        @GetMapping("/dept/get/{id}")
        public CommonResult getDeptById(@PathVariable("id") Long id){
            Dept dept = deptServicee.queryById(id);
            return CommonResult.success(dept);
        }
        @GetMapping("/dept/getall")
        public CommonResult getAll(){
            List<Dept> deptList = deptServicee.queryAll();
            return  CommonResult.success(deptList);
        }
    
    }
    ```

  

#### 消费者（子模块）

- config

  ```java
  @Configuration
  public class ConfigBean {
  
      @Bean
      public RestTemplate getRestTemplate(){
  
          return new RestTemplate();
      }
  }
  ```

- DeptConsumer

  ```java
  @RestController
  public class DeptConsumerController {
  
      @Autowired
          //  提供远程访问http服务的方法
      private RestTemplate restTemplate;
  
      private static final  String REST_URL_PREFIX = "http://localhost:8001";
      @RequestMapping("/consumer/dept/get/{id}")
      public CommonResult get(@PathVariable("id") Long id){
          return restTemplate.getForObject(REST_URL_PREFIX+"dept/get/"+id,CommonResult.class);
      }
      @RequestMapping("/consumer/dept/getall")
      public CommonResult getall(){
          return restTemplate.getForObject(REST_URL_PREFIX+"dept/getall",CommonResult.class);
      }
      @RequestMapping("/consumer/dept/add")
      public CommonResult getall(@RequestBody Dept dept){
          return restTemplate.postForObject(REST_URL_PREFIX+"dept/add",dept, CommonResult.class);
      }
  }
  ```



### Eureka

#### 架构图

 ![image-20210721132223835](http://qwkrktldg.hn-bkt.clouddn.com/image-20210721132223835.png)

- Register(服务注册):把自己的IP和端口注册给Eureka.
  Renew(服务续约):发送心跳包，每30秒发送一次。告诉Eureka自己还活着。
  Cancel(服务下线):当provider关闭时会向Eureka发送消息,把自己从服务列表中删除。防
  止consumer调用到不存在的服务。
  Get Regitry(获取服务注册列表):获取其他服务列表。
  Replicate(集群中数据同步): eureka 集群中的数据复制与同步。
  Make Remote call(远程调用):完成服务的远程调用。

  

#### 启动（子模块）

##### 导入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        <version>2.2.1.RELEASE</version>
    </dependency>

    <!--热部署-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```

##### 配置

```yaml
server:
  port: 7001

#Eureka配置
eureka:
  instance:
    hostname: localhost #eureka服务端名字
  client:
    register-with-eureka: false #表示是否想eureka注册中心注册自己
    service-url:  # 监控页面
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

##### 设置启动项

```java
@SpringBootApplication
@EnableEurekaServer
public class EurakeServer_7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurakeServer_7001.class,args);
    }
}
```

#### 生产者

##### 添加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
<!--完善监控信息-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##### 添加配置

```yaml
#eureka配置
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
#      监控信息
info:
  app.name: whitestorm-springcloud
  company.name: gitwhitestorm.github.io
```

##### 开启主启动类注解

```java
@EnableEurekaClient
```

##### info信息展示

![image-20210722125102032](http://qwkrktldg.hn-bkt.clouddn.com/image-20210722125102032.png)

![image-20210722125116658](http://qwkrktldg.hn-bkt.clouddn.com/image-20210722125116658.png)

##### 获取注册微服务信息

```java
//注册进来的微服务，获取一些信息
@GetMapping("/dept/discovery")
public Object discovery(){
    //获取微服务列表的清单
    List<String> services = client.getServices();
    for (String service : services) {
        System.out.println(service);
    }
    List<ServiceInstance> instances = client.getInstances("SPRING-CLOUD-PRODUCER-DEPT");
    for (ServiceInstance instance : instances) {
        System.out.println(
                instance.getHost()+"\t"+
                        instance.getPort()+"\t"+
                        instance.getUri()+"\t"+
                        instance.getServiceId()
        );
    }
    return this.client;
}
```

##### 添加服务发现注解

```java
//服务发现
@EnableDiscoveryClient
```

![image-20210722125537152](http://qwkrktldg.hn-bkt.clouddn.com/image-20210722125537152.png)

#### 集群

##### 复制多个Eureka服务，修改配置

例：

```yaml
server:
  port: 7001

#Eureka配置
eureka:
  instance:
    hostname: eureka7001.com #eureka服务端名字
  client:
    register-with-eureka: false #表示是否想eureka注册中心注册自己
    service-url:  # 监控页面
      defaultZone: http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
```

![image-20210722135417373](http://qwkrktldg.hn-bkt.clouddn.com/image-20210722135417373.png)

##### 修改生产者配置

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka/
```

### 负载均衡（消费者设置）



##### 添加客户端依赖

```xml
<!--        ribbon依赖        -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
<!--        eureka依赖        -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

##### 添加配置

```yaml
eureka:
  client:
    register-with-eureka: false # 不想eureka注册自己
    service-url:
      defaultZone:  http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka/
```

##### 开启eureka注解

```java
@EnableEurekaClient
```

##### 修改RestTemplate的config

```java
@Configuration
public class ConfigBean {

//  配置负载均衡实现RestTemplate
    @LoadBalanced // Ribbon
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
```

##### 修改访问前缀

```java
private static final  String REST_URL_PREFIX = "http://SPRING-CLOUD-PRODUCER-DEPT/";
```

##### 添加服务

- 复制多个生产者
- 修改启动项
- 修改配置

![image-20210722152655758](http://qwkrktldg.hn-bkt.clouddn.com/image-20210722152655758.png)

##### 修改负载均衡策略

- 默认为轮询

- ```java
  @Configuration
  public class MyRibbonRule {
      @Bean
      public IRule iRule(){
          return new RandomRule();
      }
  }
  ```

- 在主启动类中添加注解

```java
//在微服务启动的时候就能去加载我们自定义的Ribbon类
//MyRibbonRule最好不要放在项目包中，否则会被扫描到 覆盖所有ribbon负载均衡规则
@RibbonClient(name = "SPRING-CLOUD-PRODUCER-DEPT",configuration = MyRibbonRule.class)
```



