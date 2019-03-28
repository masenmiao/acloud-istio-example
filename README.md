istio官方示例
https://istio.io/docs/examples/bookinfo/
代码
https://github.com/istio/istio/tree/release-0.1/samples/apps/bookinfo/src

SpringBoot2  istio简单实例：
acloud-simple-ui
acloud-simple-service
Istio替换了注册中心，服务网关，
SpringBoot->gradle 生成docker 镜像(或上传)->kubernetes部署->istio路由规则配置
配置中心替换，调用链使用Istio的Jaeger