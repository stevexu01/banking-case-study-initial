# Banking Case Study

###### Overview
You're going to create a banking application.  The banking application has the following lines of business (LoBs):

* Deposit Accounts
* Credit Card
* Auto Loans

Bank decided to consolidate and display on the dashboard, all the accounts belonging to a particular customer once he/she logs in to the Bank application.
 
###### Expectation
* Design 4 microservices
* Develop 3 microservices one for each LoBs to provide Accounts CRUD operations.
* Develop a microservice, that makes 3 parallel calls to each respective LoB Microservices and aggregates them within stipulated time say max 5 seconds.
* Implement a circuit breaker pattern for parallel calls, to break the wait and respond with the default response for only that particular call which didn’t respond in time (5 sec). Total wait time for all the 3 parallel calls should be 5 seconds (but not 5+5+5=15 seconds)
* Microservices should have CICD for build and deploy, must be deployed in AWS cloud and are scalable
 
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
* Use Resilience4j framework to implement Circuit Breaker
* GitHub for code repository
* Using AWS Code build, Code Deploy and Code Pipelines create CICD pipeline
* Push the artifacts to AWS ECR
* Deploy the microservices on AWS Fargate using AWS ECS load balanced services.
