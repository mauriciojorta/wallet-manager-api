# Wallet Manager API

A simple demo of a digital currency wallet manager API thought out for an hypothetical admin panel to monitor customer users and make transfers between their wallets.

## Used technologies

The API is developed with **Java 11** and the **Spring Boot** framework as a **Maven** project. The dependency **MapStruct** is used to allow automatic mapping between database entities and DTOs (Data Transfer Objects), and **Spring Fox** to generate Swagger HTML documentation available on the endpoint /api/v1/swagger-ui/index.html. The app uses a **MongoDB 4.0** database in its data layer.

## Installation and configuration steps

First make sure to install Java 11 and Maven in your system and verify that the latter uses Java 11 with *mvn --version*.

Then, create a MongoDB 4.0 database named wallet-manager (for example, on your local environment or with a Cloud service such as [**Cloud Atlas**](https://www.mongodb.com/cloud)) with the following collections: customers and wallets. You should create documents for each one of them like those from these examples:

* **customers**
   
```json
[{
  "_id": {
    "$oid": "609abc30188d7b0279203187"
  },
  "name": "Mike",
  "surname": "Mays",
  "email": "mikem@test.com"
  },{
  "_id": {
    "$oid": "609abd8f188d7b0279203188"
  },
  "name": "Lucas",
  "surname": "Holmes",
  "email": "lucash@test.com"
}]
```

* **wallets**
  
```json
[{
  "_id": {
    "$oid": "609ac3b0188d7b027920318a"
  },
  "customerId": {
    "$oid": "609abc30188d7b0279203187"
  },
  "hash": "2d4a40efd1cb8b68e2e9ba054365add6961c71a0f3196c919327358c9b91e6fe",
  "name": "Mike's Wallet",
  "balance": "352.00",
  "_class": "com.wallet.manager.model.Wallet"
  },{
  "_id": {
    "$oid": "609ac48f188d7b027920318b"
  },
  "customerId": {
    "$oid": "609abd8f188d7b0279203188"
  },
  "hash": "36f2840d0cdb6b0eb32e5612de3578397e8dea8c71476d5b17b32f8be9bf8253",
  "name": "Lucas's Wallet",
  "balance": "200.50",
  "_class": "com.wallet.manager.model.Wallet"
  },{
  "_id": {
    "$oid": "609c3a7c367ff13b6aa9d008"
  },
  "customerId": {
    "$oid": "609abc30188d7b0279203187"
  },
  "hash": "f2fafd5d3416386813499072d8ff3c01cb5ab9a7c41aac2620f4dadc6ad54493",
  "name": "Mike's Wallet 2",
  "balance": "150.65",
  "_class": "com.wallet.manager.model.Wallet"
}]
```
Once you have created enough documents, modify the MongoDB properties from the **application.properties** file of both src/main/resources and src/test/resources to use your custom connection. Then, you can safely compile and package the app as a jar with the Maven command *mvn clean package* and run it by default on [http://localhost:8080/api/v1](http://localhost:8080/api/v1). 

To test it, you can use the auto-generated Swagger documentation available on [http://localhost:8080/api/v1/swagger-ui/index.html](http://localhost:8080/api/v1/swagger-ui/index.html) or a tool such as **Postman**.


