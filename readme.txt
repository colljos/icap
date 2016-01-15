
Specification
=============
Write a RESTfull service that calculates prime numbers.

Try and implement more than 1 algorithm
Try and use multiple threads.
It must be written Java 8.
You can use your favourite open source libraries and frameworks e.g drop wizard, spring.
You must use maven.
You must have unit tests.
The project must be available from a github account.

I am looking for good design, use of appropriate libraries and attention to detail, especially around unhappy paths.

And it should go without saying that I should be able to clone, compile and test the service. Instructions are welcome in the form of a readme in the repository.

Solution
========
Completed using Spring (Boot, REST) and a Strategy Pattern to support multiple algorithm implementations

- Spring Boot for RESTful container hosting
  To run from the command line execute:
   mvn spring-boot:run
   
- Invoke RESTful URL available as follows:
  http://localhost:8080/primes[?numPrimes=<MAX>&algo=<simple|concurrent>]
  
  Default execution without parameters: 
  http://localhost:8080/primes
  defaults to creating 10000 primes using a PreJava8Algorithm
  
TODO
- RESTful API documentation (would use Swagger [http://swagger.io/] or RAML [http://raml.org/])
- Add ERROR code handling for RESTful service
- Consider alternative RESTful API representation (http://martinfowler.com/articles/richardsonMaturityModel.html)
- Improve Concurrent ForkJoin implementation + further benchmark
- Complete Executor implementation + further benchmark