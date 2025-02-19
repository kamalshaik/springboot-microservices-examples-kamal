
Start servers in below order: 
- 1. EurekaServerPhotoAppDiscoveryService [9000]
- 2. api-gateway-sp-cloud [9050]
- 3. PhotoAppMgtService [9001]
- 4. PhotoAppUserService [9002]
------------------------------------------------------------------------------------------------------------
Open postman: 
- send request: 
	- 1. http://localhost:9050/mmt/display       ==> GET request
	- 2. [optional] http://localhost:9050/users/display       ==> GET request
------------------------------------------------------------------------------------------------------------
Flow: 
		Request sending to --> api-gateway-sp-cloud <--> PhotoAppMgtService <--> PhotoAppUserService
------------------------------------------------------------------------------------------------------------
Points:
- We are sending request to 'api-gateway-sp-cloud' it navigate request based on end point we provided "mmt" or "users" in the URL.
- Please check the  "PhotoAppMgtService" more as it is configured with Eureka URL.
	Here to connect with other micro service we are NOT using http://localhost/users/display we are using resttemplate with 
	
	restTemplate.getForObject("http://USER-APP/users/display", String.class)
- Please read more and understand api-gateway-sp-cloud application.yml file as it is having more gateway configurations.
------------------------------------------------------------------------------------------------------------
Swagger: 
- In side "PhotoAppUserService [9002]" we have latest Swagger implementation also.
- We can access Swagger using below URL: http://localhost:9002/swagger-ui.html
	
------------------------------------------------------------------------------------------------------------
Service internal details:

>EurekaServerPhotoAppDiscoveryService:  		
	- @EnableEurekaServer  
	- application.yml:  
		- server:   
			  port: 9000  
			spring:   
			  application:   
				name: discoveryservice  
			eureka:   
			  instance:   
				hostname: localhost  
			  client:   
				register-with-eureka: false  
				service-url:   
				  defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka  
				fetch-registry: false  
------------------------------------------------------------------------------------------------------------










------------------------------------------------------------------------------------------------------------				