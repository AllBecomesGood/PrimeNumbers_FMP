- how to run it
> I compiled and ran it via Windows Command Prompt using the JDK command "javac filename.java" and then run it with "java filename".
Java Development Kit has to be installed and its path added to the PATH System Environment variable.
> Tests can be run in the same manner except using these commands:
compile test
javac -cp .;junit-4.12.jar PrimeTest.java
run test
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore PrimeTest


- what you’re pleased with
> I developed two algorithms of my own for Prime generation by reading maths articles first (found out about Sieve of Eratosthenes).
The first was crap, but finding out why was fun. The second takes ~30secs to create a million primes.
> I never ran into a case where my variables were overflowing and it took me a little to figure it out. Java int only made it to about 6000 primes before breaking at the multiplication. Was interesting to deal with the data types.
> I realized I'd like to do more coding and get better at it, I suck a little at the moment.

- what you would do with it if you had more time
> Unit Testing, as I haven't actually done any, but from exercism.io I have an idea what it's about and we'll also have to do it at University this coming week.
> Try in Elixir. I originally started learning Elixir for this, but it proved a difficult (but still satisfying) road. I plan on doing more exercises for it.
