# 架构图

![](https://images.cnblogs.com/cnblogs_com/ironspirit/2257402/o_221223133824_%E4%B8%9A%E5%8A%A1%E6%9E%B6%E6%9E%84%E5%9B%BE.png)
项目主要功能模块
![](https://images.cnblogs.com/cnblogs_com/ironspirit/2257402/o_221223134104_SikaService.png)

# 已完成模块

- Sika-Common-Swagger
- Sika-Security
- Sika-Api
- Sika-QuestionSika模块

## Sika-Common-Swagger

`Sika-Common-Swagger` 模块作为一个 `SpringBoot-Starter` 依赖存在, 并且是基于 `Springfox-boot-starter3.0.0` 开发,
旨在完成对 `Swagger-UI` 的自适应自动化配置.

模块提供了 `@EnableCustomSwagger2` 注解来对存在 `Sika-Common-Swagger` 的模块进行接口扫描并自动生成基于 Swagger-UI 的接口文档

如果导入了该依赖并且开启 `@EnableCustomSwagger2`, 将会在 `项目url:端口/swagger-ui/index.html` 生成一个接口文档

模块内部默认对 SpringMVC 的所有请求进行扫描

### 可配置选项

|配置名称|配置方式|值|作用|
|:---:|:---:|:---:|:---:|
|enabled|swagger.enabled|`true\|false`|开启或关闭接口文档生成功能|
|basePackage|swagger.basePackage|Java包名字符串|解析的包路径|
|basePath|swagger.basePath|antMatch字符串, 默认为 `\**`, 即全路径扫描, 可以配置多个|扫描的请求路径|
|excludePath|swagger.excludePath|antMatch字符串, 默认为不排除, 即不排除扫描, 可以配置多个|排除扫描的请求路径|
|title|swagger.title|字符串|接口文档的生成标题|
|description|swagger.description|字符串|接口文档的描述|
|version|swagger.version|字符串, 正常情况下为版本数值|文档版本|
|license|swagger.license|字符串|文档许可证信息|
|licenseUrl|swagger.licenseUrl|url字符串|文档许可证地址|
|termsOfServiceUrl|swagger.termsOfServiceUrl|url字符串|服务条款URL|
|host|swagger.host|字符串|主机信息|
|contact|swagger.contact|对象信息|联系人信息|

contact配置信息:
|配置名称|配置方式|值|作用|
|:---:|:---:|:---:|:---:|
|name|swagger.contact.name|字符串|联系人|
|url|swagger.contact.url|字符串|联系人url|
|email|swagger.contact.email|字符串|联系人email|

> 默认情况下, Sika-Common-Swagger 会扫描导入类的 `SpringBootApplication` 类所在的包以及子包

## Sika-Security

`Sika-Security` 模块基于 `spring-boot-starter-security2.7.3` 开发, 模块使用 MySQL 存储用户认证信息和授权的权限存储,使用
Redis 来充当缓存, 用户认证获得授权信息后会将信息存储到 Redis 中以作缓存, 避免了每次请求都进行数据库查询的耗时操作.

### 流程分析

#### 登录

`Sika-Security` 模块使用 `io.jsonwebtoken`.`jjwt`.`0.9.1` 处理 Token, 在用户登录时,
会使用 `UsernamePasswordAuthenticationToken` 封装用户名和密码进行数据库查询(密码使用强散列哈希存储),
如果 `BCryptPasswordEncoder` 比对后的用户名密码正确, 则会通过认证流程, 并且通过 `CustomUserDetailsService` 进一步获取权限信息生成
包含用户登录状态和权限信息的 Token. 将 Token 放入 Redis 中以做缓存

#### 鉴权

在登录之后访问需要鉴权的接口时, `Sika-Security` 会基于 `SecurityFilterChain` 从请求的 `header` 域中获取 Token
信息于Redis缓存中的信息进行权限校验, 如果权限符合已配置的权限, 则会放行, 对所有不符合权限的请求进行拦截.
> 这也就决定了权限接口操作必须在header域中携带登录生成的token

#### 插拔式安全认证

`Sika-Security` 提供了 `@EnableAuthentication` 注解来控制是否启用 安全认证的功能

### 可配置选项

#### Token 配置

|配置名称|配置方式|值|作用|
|:---:|:---:|:---:|:---:|
|secretKey|cc.token.secret-key|字符串|token生成和解析的密钥|
|jwtTTL|cc.token.jwt-TTL|数值|Token有效期|
|issuer|cc.token.issuer|字符串|签发人签名|

#### Security 配置

|配置名称|配置方式|值|作用|
|:---:|:---:|:---:|:---:|
|enable|cc.security.enable|`true\|false`|是否开启认证功能|
|matches|cc.security.matches|对象|匹配的路径和权限信息|

#### 路径和权限信息配置

|配置名称|配置方式|值|作用|
|:---:|:---:|:---:|:---:|
|path|cc.security.matches.path|antMatcher路径字符串|匹配的路径名称|
|permission|cc.security.matches.permission|字符串|响应路径所需的权限信息|

> 以上两个配置选项都是列表类型, 支持多个值的配置


### 待更新

- 模块目前使用的是 `SpringBoot-Starter` 的形式存在, 将认证授权模块修改成独立的项目存在后可以通过 SpringCloud 的刷新域结合
  Nacos 实现配置热更新来实现动态权限配置
- 不改变项目接口, 通过修改数据库的权限信息来修改用户的权限

# Sika-QuestionBank

`Sika-QuestionBank` 是业务功能题库模块, 提供对问题和答案的 `CRUD` 操作.

# Sika-Api

`Sika-Api` 模块是基于 `OpenFeign` 的客户端远程调用模块, 将项目中的远程调用 API 、其他模块通用的常量、JavaBean、枚举类进行抽取,
作为基础包被其他模块所引用

# Sika-Upload

`Sika-Upload` 模块负责文件上传的相关处理, 模块依赖于 `Sika-Api`.

提供上传 `Excel` 文件, 并对 Excel 解析后生成对应的题目和答案,
通过 `Feign` 客户端将数据传输到 `Sika-QuestionBank` 服务进行落盘持久.

提供 题库导出的接口, 通过 Feign 客户端从题库服务拉取题目答案信息, 封装到 Excel 中并响应

导出的 Excel 可直接作为题库备份.

### 待更新

- 该模块将作为整个系统的文件上传处理中心, 对资料共享模块提供支撑.

