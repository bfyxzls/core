## 大叔类库
大叔通用底层库，基于springboot框架，使用maven进行构建，可以方便在用在maven和gradle为框架的spring项目里。
## 框架库项目注意的把这个==注释==
```
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
 </build>
```
## 框架包括
1. 通用实体
2. 通用工具
3. 全局异常拦截，要求具体项目继承ErrorHandler
4. 自定义注释
5. 序列化相关
6. 加密相关
