runBDTests: BackendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests

runMain: BackendImplementation.class
	java BackendImplementation


BackendDeveloperTests.class: BackendInterface.class Car.class BackendImplementation.class DummyBackendImplementation.class BackendDeveloperTests.java TextUITester.class 
	javac -cp .:../junit5.jar BackendDeveloperTests.java

BackendInterface.class: Car.class BackendInterface.java
	javac -cp .:../junit5.jar BackendInterface.java

CarImplementation.class: Car.class CarImplementation.java
	javac -cp .:../junit5.jar CarImplementation.java

DummyBackendImplementation.class: BackendInterface.class
	javac -cp .:../junit5.jar DummyBackendImplementation.java

Car.class: Car.java
	javac -cp .:../junit5.jar Car.java

BackendImplementation.class: BackendImplementation.java BackendInterface.class CarImplementation.class
	javac -cp .:../junit5.jar BackendImplementation.java

BinarySeachTree.class: BinarySearchTree.java SortedCollectionInterface.class
	javac -cp .:../junit5.jar BinarySearchTree.java

IterableMultiKeyRBT.class: IterableMultiKeyRBT.java RedBlackTree.class KeyListInterface.class IterableMultiKeySortedCollectionInterface.class
	javac -cp .:../junit5.jar IterableMultiKeyRBT.java

KeyList.class: KeyList.java KeyListInterface.class
	javac -cp .:../junit5.jar KeyList.java

RedBlackTree.class: RedBlackTree.java BinarySeachTree.class
	javac -cp .:../junit5.jar RedBlackTree.java

CarOrganizerFrontend.class: FrontendInterface.class CarOrganizerFrontend.java BackendInterface.class
	javac -cp .:../junit5.jar CarOrganizerFrontend.java

FrontendInterface.class: FrontendInterface.java
	javac -cp .:../junit5.jar FrontendInterface.java
	
IterableMultiKeySortedCollectionInterface.class: IterableMultiKeySortedCollectionInterface.java SortedCollectionInterface.class KeyList.class
	javac -cp .:../junit5.jar IterableMultiKeySortedCollectionInterface.java

KeyListInterface.class: KeyListInterface.java 
	javac -cp .:../junit5.jar KeyListInterface.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac -cp .:../junit5.jar SortedCollectionInterface.java

TextUITester.class: TextUITester.java
	javac -cp .:../junit5.jar TextUITester.java



clean:
	rm *.class


runFDTests:
	javac TextUITester.java
	javac SortedCollectionInterface.java
	javac KeyListInterface.java
	javac IterableMultiKeySortedCollectionInterface.java
	javac Car.java
	javac BackendInterface.java
	javac BackendPlaceholder.java
	javac FrontendInterface.java
	javac -cp .:../junit5.jar FrontendDeveloperTests.java
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

