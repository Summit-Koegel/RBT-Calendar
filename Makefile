runTests:  AlgorithmEngineerTests.class junit5.jar
	java -jar junit5.jar --class-path . --scan-classpath

RedBlackTree.class: RedBlackTree.java SortedCollectionInterface.class
	javac RedBlackTree.java

RedBlackCalendar.class: RedBlackCalendar.java IHoliday.class HolidayAE.class ComparableList.class
	javac RedBlackCalendar.java

IHoliday.class: IHoliday.java
	javac IHoliday.java

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java RedBlackTree.class RedBlackCalendar.class IHoliday.class HolidayAE.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java -Xlint

HolidayAE.class: HolidayAE.java IHoliday.class
	javac HolidayAE.java

ComparableList.class: ComparableList.java
	javac ComparableList.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac SortedCollectionInterface.java




