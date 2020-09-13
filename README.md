# CryptoComputing

## How to run 
The project uses Maven so that's a prerequisite to run the application. Navigate into the CryptoComputing folder before run any of the following commands.

```
#compile the project and generate target folder
mvn compile
```

Run the compiled project. This setup (without any parameter) will run all of the tests.
```
mvn exec:java -Dexec.mainClass=com.attila.toth.crypto.Main
```

There are 3 switch so far:
* -lookup
* -bool
* -ottt

Pass the switch and the two blood types as string arguments:
```
mvn exec:java -Dexec.mainClass=com.attila.toth.crypto.Main -Dexec.args="-bool 0+ 0+"
```
