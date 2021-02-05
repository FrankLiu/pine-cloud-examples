## SpringCloud practice project

本项目是为了演示Springcloud集成nodejs的微服务而开发的，主要功能包括
1. java微服务注册到Eureka服务器
2. nodejs微服务注册到Eureka服务器
3. zuul网关路由到java微服务（pine-cloud-user-service)
4. zuul网关路由nodejs微服务（pine-cloud-nodejs-service)
5. nodejs实现bff层，并且依赖了java微服务和nodejs微服务（nodejs-bff)

### 启动步骤

#### 启动zipkin server
```
docker run -d -p 9411:9411 openzipkin/zipkin
```

#### 启动pine-cloud-discovery-server
运行子工程下的DiscoveryServerApplication类

#### 启动pine-cloud-user-service(compute-service)
运行子工程下的UserServiceApplication类

#### 启动pine-cloud-nodejs-service(book-service)
```shell
cd pine-cloud-nodejs-service/src
npm install --save express eureka-js-client
node server.js
```

#### 启动nodejs-bff(nodejs-bff-service)
```shell
cd nodejs-bff
npm install --save express eureka-js-client request-promise
node server.js
```

5. 启动pine-cloud-api-gateway
运行子工程下的ApiGatewayApplication类

### 测试功能
所有服务启动后，可通过网关访问所有服务
```
|--/compute/**  -> 访问java提供的微服务
|--/book/**     -> 访问nodejs提供的微服务
|--/bff/**      -> 访问nodejs提供的BFF层聚合服务
```


e.g. 直接访问java提供的/compute服务
```shell
$ curl -X GET -s http://localhost:5555/compute/add?access_token=123\&a=1\&b=23
```

e.g. 直接访问nodejs提供的/books服务
```shell
$ curl -X GET -s http://localhost:5555/book/books?access_token=123
[{"bookname":"Nodejs Web Development","author":"David Herron"},{"bookname":"Mastering Web Application Development with Express ","author":"Alexandru Vlăduțu"}]
```

e.g. 通过bff访问/compute服务
```shell
$ curl -X GET -s http://localhost:5555/bff/compute/add?access_token=123\&a=1\&b=23
```

e.g. 通过bff访问/books服务
```shell
$ curl -X GET -s http://localhost:5555/bff/books?access_token=123
"[{\"bookname\":\"Nodejs Web Development\",\"author\":\"David Herron\"},{\"bookname\":\"Mastering Web Application Development with Express \",\"author\":\"Alexandru Vlăduțu\"}]"
```

