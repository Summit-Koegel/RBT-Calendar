runTests: runFrontendTests runDataWranglerTests runBackendTests

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

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java Holiday.class HolidayLoader.class
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

Holiday.class: Holiday.java IHoliday.class
	javac Holiday.java

IHoliday.class: IHoliday.java
	javac IHoliday.java

HolidayLoader.class: HolidayLoader.java IHolidayLoader.class
	javac HolidayLoader.java

IHolidayLoader.class: IHolidayLoader.java
	javac IHolidayLoader.java

clean:
	rm *.class

