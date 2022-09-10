# MicroservicosModular

***EXECUTANDO TODOS OS SERVIÇOS EM LOCALHOST:***

* Container banco de dados:
   * docker run --name mysql_ac -d -p 3306:3306 -v /c/mysql_datadir:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=12345 mysql:latest

* Container Rabbitmq:
    * docker rum --name rabbitma_ac -p 5672:5672 -p 15672:15672 rabbit:3.10-management
    * configurar fila "emissao-cartoes"
  
* Container KeyCloak:
    * docker run --name keycloak_ac -p 8084:8080 -e KEYCLOAK_ADMIN_PASSWORD=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start dev
  
  
***CRIANDO CONTAINERS PARA CADA SERVIÇO:***

  * Criando a rede: 
    * docker network create ac-network
  
  * Container banco de dados:
    * docker run --name mysql_ac -d -p 3306:3306 -v /c/mysql_datadir:/var/lib/mysql --network ac-network -e MYSQL_ROOT_PASSWORD=12345 mysql:latest

  * Container Rabbitmq:
    * docker run --name rabbitmq_ac -p 5672:5672 -p 15672:15672  --network ac-network rabbit:3.10-management
    * Configurar fila emissao-cartoes
  
  * Container KeyCloak:
    * docker run --name keycloak_ac -p 8084:8080 --network ac-network -e KEYCLOAK_ADMIN_PASSWORD=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start dev
    
  * Container eurekaserver:
    * docker build --tag wandir/eureka_ac .
    * docker run --name eureka_ac -p 8761:8761 --network ac-network wandir/eureka_ac
    
  * Container mscloudgateway:
    * docker build --tag wandir/mscloudgateway .
    * docker run --name mscloudgateway_ac --network ac-network -e EUREKA_SERVER=eureka_ac  -e KEYCLOAK_SERVER=keycloak_ac -d wandir/mscloudgateway  
  
  * Container msclientes:
    * docker build --tag wandir/msclientes_ac .
    * docker run --name msclientes_ac --network ac-network -e EUREKA_SERVER=eureka_ac  -e MYSQL_SERVER=mysql_ac -d wandir/msclientes_ac   

  * Container mscartoes:
    * docker build --tag wandir/mscartoes_ac .
    * docker run --name mscartoes_ac --network ac-network -e EUREKA_SERVER=eureka_ac  -e MYSQL_SERVER=mysql_ac -e RABBITMQ_SERVER=rabbitmq_ac -d wandir/mscartoes_ac 

  * Container msavaliadorcredito:
    * docker build --tag wandir/msavaliadorcredito_ac .
    * docker run --name msavaliadorcredito_ac --network ac-network -e EUREKA_SERVER=eureka_ac -e RABBITMQ_SERVER=rabbitmq_ac -d wandir/msavaliadorcredito_ac    
  
