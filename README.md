istio官方示例
https://istio.io/docs/examples/bookinfo/
代码
https://github.com/istio/istio/tree/release-0.1/samples/apps/bookinfo/src

SpringBoot2  istio简单实例：
acloud-simple-ui 服务调用方
acloud-simple-service 服务提供方

<!-- Istio承担了注册中心，服务网关，配置中心替换，调用链使用Istio的Jaeger -->
SpringBoot->gradle 生成docker 镜像(或上传)->kubernetes部署->istio流量规则配置

docker 构建：
gradle :acloud-simple-ui:buildDocker -info
docker tag acloud-istio-example/acloud-simple-ui acloud-istio-example/acloud-simple-ui:1.0

gradle :acloud-simple-service:buildDocker -info
docker tag acloud-istio-example/acloud-simple-service acloud-istio-example/acloud-simple-service:1.0

（测试：）
docker run -d  --name acloud-simple-ui -p 8701:8701 -t acloud-istio-example/acloud-simple-ui:1.0

ISTIO配置：
istioctl create -f istio-rules\gateway.yaml
istioctl create -f .\istio-rules\acloud-simple-ui-vs.yaml
kubectl get svc -n istio-system		--查看istio-ingressgateway端口
istioctl get all
访问：
http://192.168.1.102:31380/users
404
istioctl create -f .\istio-rules\acloud-simple-service-vs.yaml
503 （增加acloud-simple-service dest配置,v1,v2）
istioctl create -f .\istio-rules\acloud-simple-service-dest.yaml
访问成功
部署 V2版本
kubectl apply -f .\kubernetes\simplesrv-deployment-v2.yaml
调整流量配置，如:
istioctl replace -f .\istio-rules\acloud-simple-service-vs-v1.yaml

