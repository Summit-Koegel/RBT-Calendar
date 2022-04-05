clean: 
	rm *.class
runFrontendTests: compileFrontend
	java -jar junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests
compileFrontend:
	javac -cp .:junit5.jar FrontendDeveloperTests.java -Xlint
	javac HolidayCalendarBackendPL.java
	javac HolidayCalendarFront.java
	javac HolidayPL.java
runBackendTests: compileBackendTests 
	java -jar junit5.jar -cp . --scan-classpath 

compileBeckendTests: HolidayBackendTester.java
	javac -cp .:junit5.jar HolidayBackendTester.java
