# Spring Security框架的一个Demo
一个练手Demo
以下都是一些个人笔记

## FilterInvocationSecurityMetadataSource 
- 用于存储请求与权限的对应关系
- 用于获取当前请求需要的权限
- 用于判断当前请求是否在权限表中
- 用于返回权限表中所有权限的集合

## AccessDecisionManager
- 用于判断当前用户是否具有当前请求所需要的权限
- 判断的依据是当前用户的所有权限和当前请求所需要的权限进行对比
- 如果权限足够则放行，否则抛出异常
- 有三种实现类
    - AffirmativeBased
        - 只要有一个权限足够就放行
    - ConsensusBased
        - 通过一个选举算法判断是否放行
    - UnanimousBased
        - 需要所有的权限都足够才能放行
- 通过`<security:access-decision-manager>`标签配置