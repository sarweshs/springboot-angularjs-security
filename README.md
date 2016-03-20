# springboot-angularjs-security
A springboot project with spring security and angularjs.
Maven is used as a build tool and H2 as in memory database. An admin user is created during startup (admin/admin) which can be used to login.

User can create other new users and provide roles. Flyway is used for making DB migrations easy.

Some of the angularjs code has been reused from http://websystique.com/springmvc/spring-mvc-4-angularjs-example/