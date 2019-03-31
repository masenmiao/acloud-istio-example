istio官方示例<br/>
https://istio.io/docs/examples/bookinfo/<br/>
代码<br/>
https://github.com/istio/istio/tree/release-0.1/samples/apps/bookinfo/src<br/>

SpringBoot2  istio简单实例：<br/>
acloud-simple-ui 服务调用方<br/>
acloud-simple-service 服务提供方<br/>

<!-- Istio承担了注册中心，服务网关，配置中心替换，调用链使用Istio的Jaeger -->
SpringBoot->gradle 生成docker 镜像(或上传)->kubernetes部署->istio流量规则配置<br/>

docker 构建：<br/>
gradle :acloud-simple-ui:buildDocker -info<br/>
docker tag acloud-istio-example/acloud-simple-ui acloud-istio-example/acloud-simple-ui:1.0<br/>

gradle :acloud-simple-service:buildDocker -info<br/>
docker tag acloud-istio-example/acloud-simple-service acloud-istio-example/acloud-simple-service:1.0<br/>

（测试：）<br/>
docker run -d  --name acloud-simple-ui -p 8701:8701 -t acloud-istio-example/acloud-simple-ui:1.0<br/>

ISTIO配置：<br/>
istioctl create -f istio-rules\gateway.yaml     <br/>
istioctl create -f .\istio-rules\acloud-simple-ui-vs.yaml     <br/>
kubectl get svc -n istio-system		--查看istio-ingressgateway端口     <br/>
istioctl get all     <br/>
访问：     <br/>
http://192.168.1.102:31380/users     <br/>
404     <br/>
istioctl create -f .\istio-rules\acloud-simple-service-vs.yaml     <br/>
503 （增加acloud-simple-service dest配置,v1,v2）     <br/>
istioctl create -f .\istio-rules\acloud-simple-service-dest.yaml     <br/>
访问成功<br/>
部署 V2版本     <br/>
kubectl apply -f .\kubernetes\simplesrv-deployment-v2.yaml     <br/>
调整流量配置，如:     <br/>
istioctl replace -f .\istio-rules\acloud-simple-service-vs-v1.yaml      <br/>

