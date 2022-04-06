runTests: runFrontendTests runDataWranglerTests runBackendTests

clean: 
	rm *.class
runFrontendTests: compileFrontend
	java -jar junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests
compileFrontend: Holiday.class 
	javac -cp .:junit5.jar FrontendDeveloperTests.java -Xlint
	javac HolidayCalendarFront.java
	javac HolidayCalendarBackend.java
runBackendTests: compileBackendTests
	java -jar junit5.jar -cp . --scan-classpath -n BackendDeveloperTests

compileBackendTests: HolidayBackendTester.java
	javac -cp .:junit5.jar HolidayBackendTester.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java Holiday.class HolidayLoader.class
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

Holiday.class: Holiday.java IHoliday.class
	javac Holiday.java

runAlgorithmEngineerTests:  AlgorithmEngineerTests.class junit5.jar
	java -jar junit5.jar --class-path . --scan-classpath

RedBlackTree.class: RedBlackTree.java SortedCollectionInterface.class
	javac RedBlackTree.java

RedBlackCalendar.class: RedBlackCalendar.java IHoliday.class HolidayAE.class ComparableList.class
	javac RedBlackCalendar.java


IHoliday.class: IHoliday.java
	javac IHoliday.java

HolidayLoader.class: HolidayLoader.java IHolidayLoader.class
	javac HolidayLoader.java

IHolidayLoader.class: IHolidayLoader.java
	javac IHolidayLoader.java

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java RedBlackTree.class RedBlackCalendar.class IHoliday.class HolidayAE.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java -Xlint

HolidayAE.class: HolidayAE.java IHoliday.class
	javac HolidayAE.java

ComparableList.class: ComparableList.java
	javac ComparableList.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac SortedCollectionInterface.java


