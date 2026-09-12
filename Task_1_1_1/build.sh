rm -rf my_compile
mkdir my_compile
mkdir ./my_compile/app
mkdir ./my_compile/doc

javac -d ./my_compile/app ./app/src/main/java/func/*.java
javadoc -d ./my_compile/doc ./app/src/main/java/func/*.java
jar -c -f ./my_compile/sort.jar -e func.Main -C ./my_compile/app .

java -jar ./my_compile/sort.jar     