# Banking Case Study

You're going to create a banking application.  The banking application has the following lines of business (LoBs):

* Deposit Accounts
* Credit Card Accounts
* Auto Loan Accounts

The bank has decided to consolidate and display on the dashboard (a non-existent UI), all the accounts belonging to a particular customer once they log into the banking application.
 
#### Overall Deliverables
* Design and build a REST service for deposit accounts.
* Design and build a REST service for credit card accounts.
* Design and build a REST service for auto loan accounts.
* Design and build a REST service for aggregating data from the 3 previous microservices.
* Use patterns discussed in class (MVC, 3-tier, Builder).
 
## Microservice Details
Details for each microservice are below.

## Auto Loan Microservice
Using the patterns and standards you learned in this class, design and build a microservice to manage auto loan accounts.

The microservice needs to have the following items implemented:

* Needs to be a REST service.  For more information, see the tutorial [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* All CRUD endpoints need to be implemented (5 in total).
    * createLoan
    * updateLoan
    * deleteLoan
    * There need to be 2 READ endpoints:
        * getLoansByClientId
        * getAllLoans
    
#### Endpoint Detail
##### /autoloan/createLoan
* Accepts loan information from request body.
* Returns loan created in response body.
* Returns a 200 status code if successful.
* Use Cassandra to store *AutoLoan* objects.
* Use a circuit breaker to return a message if the data or database is unavailable.
    ```text
    Data is unavailable at this time.
    ```
* The model class needs to implement an inner builder.  (Builder pattern implemented as an inner class.)
    
###### Request Body

```json
{           
  "clientId" : "001",    
  "name" : "Homer Simpson",
  "balance" : "15000.0"
}     
```
    
###### Response Body

```json
{           
  "id" : "1",  
  "clientId" : "001",    
  "name" : "Homer Simpson",
  "balance" : "15000.0"
}     
```

##### /autoloan/getLoansByClientId/{id}
* Returns loans queried in response body.
* Returns a 200 status code if successful.

###### Request

```text
  http://localhost:9091/autoloan/getLoanById/1
```

###### Response Body

```json
[
  {
    "id": "1",
    "clientId" : "001",          
    "name": "Homer Simpson",
    "balance": "15000.0"
  }
]     
```

##### /autoloan/getAllLoans
* Returns a list of all loan information in the system.
* Returns a 200 status code if successful.

###### Request

```text
  http://localhost:9091/autoloan/getAllLoans
```

###### Response Body

```json
[
  {
    "id": "1",
    "clientId" : "001",          
    "name": "Homer Simpson",
    "balance": "15000.0"
  },
  {
    "id": "2",
    "clientId" : "004",          
    "name": "Marge Simpson",
    "balance": "10000.0"
  } 
] 
```

##### /autoloan/updateLoan/{id}
* Accepts loan information from request body.
* Returns loan updated in response body.
* Returns a 200 status code if successful.

###### Request

```text
    http://localhost:9091/autoloan/updateLoan/1
```
        
###### Request Body

```json
{    
  "clientId" : "001",      
  "name": "Homer J Simpson",
  "balance": "15000.0"
}     
```

###### Response Body

```json
{
  "id": "1",
  "clientId" : "001",          
  "name": "Homer J Simpson",
  "balance": "15000.0"
}     
```
        
##### /autoloan/deleteLoan/{id}
* Returns a 204 status code if successful.
* Returns a 404 status code if unsuccessful.

###### Request
```text
    http://localhost:9091/autoloan/deleteLoan/1
```

###### Example of unsuccessful message:
```text
    Record not deleted.
```



## Credit Card Microservice
Using the patterns and standards you learned in this class, design and build a microservice to manage credit card accounts.

The microservice needs to have the following items implemented:

* Needs to be a REST service.  For more information, see the tutorial [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* Use Cassandra to store *CreditCard* objects.
* Use a circuit breaker to return a message if the data or database is unavailable.
    ```text
    Data is unavailable at this time.
    ```
* The model class needs to implement an inner builder.  (Builder pattern implemented as an inner class.)
* All CRUD endpoints need to be implemented (5 in total).
    * createCreditCard
    * updateCreditCard
    * deleteCreditCard
    * There need to be 2 READ endpoints:
        * getCreditCardById
        * getAllCreditCards
* Credit card numbers are all randomly generated, unique, 16 digit numbers.  The first four numbers of the account number are always 1234.
    
###### Endpoint Detail
* /creditcard/createCreditCard
    * Accepts credit card information from request body.
    * Returns credit card created in response body.
    * Returns a 200 status code if successful.
    * Example of request/response body:
        ```json
        {        
          "number": "1234 5678 9012 3456",      
          "name": "Homer Simpson",
          "balance": "500.0"
        }     
        ```
* /creditcard/getCreditCardById/{id}
    * Returns credit card queried in response body.
    * Returns a 200 status code if successful.
    * Example of request:
        ```text
          http://localhost:9092/creditcard/getCreditCardById/1
        ```
    * Example of response:
        ```json
        {
          "id": "1",
          "number": "1234 5678 9012 3456",      
          "name": "Homer Simpson",
          "balance": "500.0"
        }     
        ```
* /creditcard/getAllCreditCards
    * Returns a list of all loan information in the system.
    * Returns a 200 status code if successful.
    * Example of response:
        ```json
        [
         {
            "id": "1",
            "number": "1234 5678 9012 3456",      
            "name": "Homer Simpson",
            "balance": "500.0"
         },
         {
            "id": "2",
            "number": "9876 5432 1098 7654",      
            "name": "Marge Simpson",
            "balance": "100.0"
         } 
        ] 
        ```
* /creditcard/updateCreditCard/{id}
    * Accepts credit card information from request body.
    * Returns credit card updated in response body.
    * Returns a 200 status code if successful.
    * Example of request:
        ```text
          http://localhost:9092/creditcard/updateCreditCard/1
        ```
    * Example of request/response body:
        ```json
        {
          "id": "1",
          "number": "1234 5678 9012 3456",      
          "name": "Homer J Simpson",
          "balance": "100.0"
        }     
        ```
* /creditcard/deleteCreditCard/{id}
    * Returns a 204 status code if successful.
    * Returns a 404 status code if unsuccessful.
    * Example of unsuccessful message:
        ```text
        Record not deleted.
        ```

## Deposit Microservice
Using the patterns and standards you learned in this class, design and build a microservice to manage deposit accounts.

The microservice needs to have the following items implemented:

* Needs to be a REST service.  For more information, see the tutorial [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* Use JPA and MySQL to store *DepositAccount* objects.
* Use a circuit breaker to return a message if the data or database is unavailable.
    ```text
    Data is unavailable at this time.
    ```
* The model class needs to implement an inner builder.  (Builder pattern implemented as an inner class.)
* All CRUD endpoints need to be implemented (5 in total).
    * createDepositAccount
    * updateDepositAccount
    * deleteDepositAccount
    * There need to be 2 READ endpoints:
        * getDepositAccountById
        * getAllDepositAccounts
* The deposit account number is a randomly generated, unique, 9-digit numeric value which is assigned to the account when it is created.
    
###### Endpoint Detail
* /deposit/createDepositAccount
    * Accepts user information from request body.
    * Returns deposit account information created in response body.
    * Returns a 200 status code if successful.
    * Example of request body:
        ```json
        {            
          "name": "Homer Simpson",
          "initialBalance": "500.0"
        }     
        ```
    * Example of response body:
      ```json
      {
        "accountNumber": "123456789",     
        "name": "Homer Simpson",
        "balance": "500.0"
      }     
      ```
* /deposit/getDepositAccountById/{id}
    * Returns deposit account queried in response body.
    * Returns a 200 status code if successful.
    * Example of request:
        ```text
          http://localhost:9093/deposit/getDepositAccountById/123456789
        ```
    * Example of response:
        ```json
        {
          "accountNumber": "123456789",      
          "name": "Homer Simpson",
          "balance": "500.0"
        }     
        ```
* /deposit/getAllDepositAccounts
    * Returns a list of all loan information in the system.
    * Returns a 200 status code if successful.
    * Example of response:
        ```json
        [
         {
            "accountNumber": "123456789",      
            "name": "Homer Simpson",
            "balance": "500.0"
         },
         {
            "accountNumber": "987654321",      
            "name": "Marge Simpson",
            "balance": "1000.0"
         } 
        ] 
        ```
* /deposit/updateDepositAccount/{id}
    * Accepts deposit account information from request body.
    * Returns deposit account updated in response body.
    * Returns a 200 status code if successful.
    * Example of request:
        ```text
          http://localhost:9093/deposit/updateDepositAccount/123456789
        ```
    * Example of request/response body:
        ```json
        {
          "accountNumber": "123456789",      
          "name": "Homer J Simpson",
          "balance": "100.0"
        }     
        ```
* /deposit/deleteDepositAccount/{id}
    * Returns a 204 status code if successful.
    * Returns a 404 status code if unsuccessful.
    * Example of unsuccessful message:
        ```text
        Record not deleted.
        ```
      
## Orchestrator Microservice
Using the patterns and standards you learned in this class, design and build an orchestrator microservice.  The whole purpose of the orchestrator is to aggregate information from all the other 3 microservices and serve it via a REST endpoint.

The microservice needs to have the following items implemented:

* Needs to be a REST service.  For more information, see the tutorial [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* Needs to be a REST client.  For more information, see the tutorial [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)
    * The REST client will consume endpoint data from the 3 other microservices to be able to return the data below.
    * *Some* of the endpoints from the other microservices will be used.  Don't expect to utilize all endpoints from all the other microservices.
    * Create a single service (class) that will invoke methods from 3 other services (classes) that make calls to their respective microservices. (i.e. a DepositService, CreditCardService and an AutoLoanService), and then aggregate the results into a single object that can be passed back to the REST controller.
* The model class(es) need to implement an inner builder.  (Builder pattern implemented as an inner class.)
* The orchestrator service needs to aggregate information from all 3 microservices and serve it via a REST endpoint.
    
###### Endpoint Detail
The orchestrator has a single REST endpoint:
* /getAccountSummaryByClientId/{clientId}
###### Request
    ```text
      http://localhost:9090/getAccountSummaryByClientId/001
    ```
###### Response
Aggregated response should look like (for example)

* When all 3 microservices respond in-time:
```json
{
  "accountSummary" : {
    "depositAccounts" : [ {
      "clientId" : "001",
      "accountNumber" : "1234566",
      "name" : "Homer",
      "balance" : 10000.0
    } ],
    "creditAccounts" : [ {
      "clientId" : "1",
      "accountNumber" : "12345",
      "name" : "Homer",
      "balance" : 1000.0
    } ],
    "autoLoanAccounts" : [ {
      "id" : "1",
      "clientId" : "001",
      "name" : "Homer",
      "balance" : 500.0
    } ]
  }
}
```

* When one of the microservices does not respond in-time:
```json
{
  "accountSummary" : {
    "depositAccounts" : [ {
      "clientId" : "001",
      "accountNumber" : "1234566",
      "name" : "Homer",
      "balance" : 10000.0
    } ],
    "creditAccounts" : [ {
      "defaultMessage" : "No accounts available to show currently"
    } ],
    "autoLoanAccounts" : [ {
      "id" : "1",
      "clientId" : "001",
      "name" : "Homer",
      "balance" : 500.0
    } ]
  }
}
```
 
## Technology Stack
* Maven
* Java
* Spring
* Spring Boot
* Cassandra
* JPA
* MySQL
* REST Service
* REST Client
* Hystrix Circuit Breaker
* GitHub

