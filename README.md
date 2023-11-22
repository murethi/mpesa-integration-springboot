# mpesa-integration-springboot
This is a demo project on how to integrate to mpesa using springboot. 

# Environment Requirements
* Java 17
* Docker

# How to run
* Spin up your docker-engine
* run the following command on the project root <code>mvn spring-boot:run</code>

> This project makes use of the springboot docker-compose dependancy to automatically configure database settings.

# This repo covers the following M-Pesa APIs
* C2B Register URLs
* C2B Validate Payments
* C2B Confirm Payment
* M-Pesa Express (STK PUSH / NI PUSH)
* M-PESA Express Callback
* Mpesa Express Query Transaction
* Generate QR Code

  
# Resources
* [Safaricom Developer Portal (Daraja)](https://developer.safaricom.co.ke/Documentation)
* [Postman collection - Sample payload you can use to test APIS] (https://github.com/murethi/mpesa-integration-springboot/blob/07-qr-code-generation/mpesa%20integration.postman_collection.json)
