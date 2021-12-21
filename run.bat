call javac -d out -cp lib/jade.jar src/com/jade/book/*.java
cd out/ && call jar uf ../lib/jade.jar com/jade/book/*.class && cd ../
call java -classpath lib/jade.jar jade.Boot -gui
