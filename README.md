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

docker 构建：
gradle :acloud-simple-ui:buildDocker -info
docker tag acloud-istio-example/acloud-simple-ui acloud-istio-example/acloud-simple-ui:1.0

gradle :acloud-simple-service:buildDocker -info
docker tag acloud-istio-example/acloud-simple-service acloud-istio-example/acloud-simple-service:1.0

（测试：）
docker run -d  --name acloud-simple-ui -p 8701:8701 -t acloud-istio-example/acloud-simple-ui:1.0

