clean: 
	rm *.class
runAllTests: compile
	java -jar junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests
compile:
	javac -cp .:junit5.jar FrontendDeveloperTests.java -Xlint
	javac HolidayCalendarBackendPL.java
	javac HolidayCalendarFront.java
	javac HolidayPL.java
