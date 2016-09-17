
## Using an IDE

You can run the system in your IDE by running the three servers in order: _RegistrationService_, _phoneService_ and _WebService_.

Eureka dashboard [http://localhost:1111](http://localhost:1111) in your browser to see that the `PHONE-SERVICE` and `WEB-SERVICE` applications have registered.  Next open the Demo Home Page [http://localhost:3333](http://localhost:3333)

The `localhost:3333` web-site is being handled by a Spring MVC Controller in the _WebService_ application, but you should also see logging output from _PhonesService_ showing requests for Account data.

You should see servers being registered in the log output of the first (registration) window.
As you interact with the web-application ([http://localhost:3333](http://localhost:3333)) you should logging appear
in the second and third windows.
 1. Allow it to register itself
 1. Kill the first account-server and see the web-server switch to using the new account-server - no loss of service.

