javac image.java
javah image
gcc -shared -fPIC main.c -I"/usr/lib/jvm/java-8-openjdk-amd64/include" -I"/usr/lib/jvm/java-8-openjdk-amd64/include/linux" -o libmaskimg.so
java -Djava.library.path=. image