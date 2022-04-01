runTests: compileTests 
	java -jar junit5.jar -cp . --scan-classpath 

compileTests: HolidayBackendTester.java
	javac -cp .:junit5.jar HolidayBackendTester.java

clean:
	rm -f *.class
