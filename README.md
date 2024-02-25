 ## Forex API
 ## 使用的技術
- **VPS** vultr廠商(vCPU*2, RAM 2G)
- **Java** : 11.0.22+7
- **GC** : 雲端使用ZGC，本地使用G1，JDK11在windows不支持ZGC
- **mongodb** : 7.0.5
- **mongodb lib** : spring-boot-starter-data-mongodb 2.7.18(已包含mongodb-driver-sync 4.6.1)
- **spring boot** : 2.7.18
- **Kubernetes** : v1.29.1+1
- **istioctl** : 1.20
- **spring doc** : 1.6.15
- **docker image** : eclipse-temurin:11-jdk
 ## 測試方式
- 因為unit test有做mock所以除了unit test還直接使用vps架設swagger網頁版可以直接測試API或是直接到src/java/test有寫好兩隻針對API跟DB的Integratin Test
- **swagger url** : http://64.176.36.65/swagger-ui/index.html
- **API Integration Test** : tw.com.cathaybk.digital.taifex.infrastructure.rest.controller.ForexControllerTest
- **DB Integration Test** : tw.com.cathaybk.digital.taifex.infrastructure.repository.ForexRepositoryImplTest
