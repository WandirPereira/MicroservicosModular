# MicroservicosModular

Container banco de dados:
  docker run --nmae mysql_ac -d -p 3306:3306 -v /c/mysql_datadir:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=12345 mysql:latest

Container Rabbitmq:
  docker rum --nmae rabbitma_ac -p 5672:5672 -p 15672:15672 rabbit:3.10-management
  Configurar fila emissao-cartoes
  
Container KeyCloak:
  docker run --name keycloak_ac -p 8084:8080 -e KEYCLOAK_ADMIN_PASSWORD=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start dev
  
  
