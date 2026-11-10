# java-growth

简易 Spring Boot 示例程序（需要 JDK 22+ 与 Maven 3.9+）。

## 启动

```powershell
mvn spring-boot:run
```

或先打包再运行：

```powershell
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

启动后访问 http://localhost:8080/hello?name=Spring ，应返回 `Hello, Spring!`。
如需更换端口：`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081`。
