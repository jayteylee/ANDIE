echo 'Attempting to run unit tests...'
javac -d bin src/cosc202/andie/*.java
javac -d bin -classpath bin:lib/junit-platform-console-standalone-1.8.2.jar src/cosc202/andie/unitTests/*.java
java -jar lib/junit-platform-console-standalone-1.8.2.jar -classpath bin --select-package cosc202.andie
