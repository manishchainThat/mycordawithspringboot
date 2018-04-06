# mycordawithspringboot

This project illustrate the integration of the spring boot with corda .

**** prerequisite ******
1.Get the RPC connection according to the your settings. change application.properties file under resource  folder.
2.clean build the project.
3. Run the FacilityServer.java
4. spring  boot is being used with jersey JerseyConfig.

Issue or weird behaviour :
Response that is coming for the
http://localhost:8080/rest/market/peers

  "peers": [
    {
      "commonName": null,
      "organisationUnit": null,
      "organisation": "PartyC",
      "locality": "Paris",
      "state": null,
      "country": "FR",
      "x500Principal": {
        "name": "O=PartyC,L=Paris,C=FR",
        "encoded": "MC4xCzAJBgNVBAYTAkZSMQ4wDAYDVQQHDAVQYXJpczEPMA0GA1UECgwGUGFydHlD"
      }
    }

is different  from the response that we used to get while using the same endpoint with corda webserver.

with Corda webserver response is

["peers":"O=PartyC,L=Paris,C=FR"] something like this.


Please look into the MarketApi for the implementation.
Note : I used the same same version of code for the two servers but response is different.





