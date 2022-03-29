runTests: DataWranglerTests.class
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
