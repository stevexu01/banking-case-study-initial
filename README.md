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
 
## Microservice Details

#### Auto Loan Microservice
Using the patterns and standards you learned in this class, design and build a microservice to manage auto loan accounts.

The microservice needs to have the following items implemented:

* Needs to be a REST service.
* All CRUD endpoints need to be implemented (5 in total).
  * There need to be 2 READ endpoints:
    * getLoanById
    * getAllLoans
* Endpoints are as follows:

    * /autoloan/createLoan
        * Accepts loan information from request body.
        * Returns loan created in response body.
        * Returns a 200 status code if successful.
        * Example of request/response body:
            ```json
            {
              "id": "1",      
              "name": "Homer Simpson",
              "balance": "15000.0"
            }     
            ```
    * /autoloan/getLoanById/{id}
        * Returns loan queried in response body.
        * Returns a 200 status code if successful.
        * Example of request:
            ```text
              http://localhost:9091/autoloan/getLoanById/1
            ```
        * Example of response:
            ```json
            {
              "id": "1",      
              "name": "Homer Simpson",
              "balance": "15000.0"
            }     
            ```
    * /autoloan/getAllLoans
        * Returns a list of all loan information in the system.
        * Returns a 200 status code if successful.
        * Example of response:
            ```json
            [
             {
                "id": "1",      
                "name": "Homer Simpson",
                "balance": "15000.0"
             },
             {
                "id": "2",      
                "name": "Marge Simpson",
                "balance": "10000.0"
             } 
            ] 
            ```
    * /autoloan/updateLoan/{id}
        * Accepts loan information from request body.
        * Returns loan updated in response body.
        * Returns a 200 status code if successful.
        * Example of request:
            ```text
              http://localhost:9091/autoloan/updateLoan/1
            ```
        * Example of request/response body:
            ```json
            {
              "id": "1",      
              "name": "Homer J Simpson",
              "balance": "15000.0"
            }     
            ```
    * /autoloan/deleteLoan/{id}
        * Returns a 204 status code if successful.
        * Returns a 404 status code if unsuccessful.
        * Example of unsuccessful message:
        ```text
        Record not deleted.
        ```

* Use Cassandra to store *AutoLoan* objects.
* Use a circuit breaker to return a message if the data or database is unavailable.
    ```text
    Data is unavailable at this time.
    ```

#### Credit Card Microservice
#### Deposit Microservice
#### Orchestrator Microservice

 
###### Response
Aggregated response should look like (for example)

* When all 3 microservices respond in-time
```
{ “accountSummary”: [ {“depositAccounts”:[“account1”:……, “account2”:….]}, {“creditAccounts”:[“account1”:……, “account2”:….]}, {“autoLoanAccounts”:[“account1”:……, “account2”:….]}}
```

* When one of the microservice doesn’t respond in-time
```
{ “accountSummary”: [ {“depositAccounts”:[“account1”:……, “account2”:….]}, {“creditAccounts”:[“defaultMessage”:”No accounts available to show currently”]}, {“autoLoanAccounts”:[“account1”:……, “account2”:….]}}
```
 
###### Technology Stack
* Build Tool - Maven
* Framework - Springboot
* Cassandra DB - for microservice CRUD
* Java Executor Service and Stream APIs for Accounts aggregation
* Use Hystrix framework to implement Circuit Breaker
* GitHub for code repository
* Using AWS Code build, Code Deploy and Code Pipelines create CICD pipeline
* Push the artifacts to AWS ECR
* Deploy the microservices on AWS Fargate using AWS ECS load balanced services.
