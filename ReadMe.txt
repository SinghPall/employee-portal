1. logger for file and console output, using no other dependecy it comes with starter of spring dependency bydefault.
2. using logback.xml to provide confiuration
#using application.properties
logging.level.root=info
logging.pattern.console=%d{dd-MM-yyyy HH:mm:ss.SSS} %magenta([%thread]) %highlight(%-5level) %logger.%M - %msg%n

3. If just want to define logger level for individual class that should differ from root level then also has specification written.
4. after giving root level and class level configuration logs are printing twice, to avoid this can use 
additivity="false" in class specific configuration.

<logger name="com.employee.portal.controller.EmployeePortalController" additivity="false" level="ERROR">
    	<appender-ref ref="STDOUT" />
</logger>
5. If dont want to use logback.xml for loging config then can application.properties works fine

#for class level config
logging.level.com.lankydan.service.MyServiceImpl=debug