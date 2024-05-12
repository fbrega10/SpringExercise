## Spring Boot Exercise
<img src="https://cdn.dribbble.com/users/2084726/screenshots/11897843/media/f8a268a9e9a4cc3ab7b0c104a0301210.gif" width="200" height="140"/>

The scope of this repository is to expose the api's of a financial product.

The requirements for the project are :

- JDK version 17 (at least)
- Maven (3.6.3)
- Curl 7.81.0 (required for the scripts)
- Jq 1.6
- Psql (PostgreSQL) v 14.11

The application.properties file must contain these configurations :


```shell
$ cat application.properties

spring.application.name=exercise
spring.datasource.url=jdbc:postgresql://$SERVERURL:$SERVERPORT/$DATABASENAME
spring.datasource.username=$USERNAME
spring.datasource.password=$PASSWORD
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port = 443
server.ssl.key-store = $KEYSTORENAME.p12
server.ssl.key-store-password = $KEYSTORE.PASSWORD
server.ssl.keyStoreType = PKCS12
server.ssl.keyAlias = $ALIAS
```

#### Where the declared variables mean: 

- $SERVERURL is the current server url in which we find Postgres installed 
- $SERVERPORT is the current port in which postgres is running (usually the port is 5432)
- $KEYSTORENAME.p12 is the keystore assigned name
- $DATABASENAME is the database name
- $USERNAME is the database username
- $KEYSTORENAME is the Java Keystore name
- $KEYSTORE.PASSWORD is the user selected Keystore password
- $ALIAS is the alias to the key

Once you've got everything set and done, it's time to build the application :
```shell
mvn clean install
```
and run it :
```shell
sudo mvn spring:boot-run
```
Under <b>/scripts</b> you can find some curl examples to inquiry the server:

```shell
sh src/main/bash/getAllProducts.sh
```
