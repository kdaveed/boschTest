javac -d bin src/*.java
cd bin
jar cfm BoschTest.jar ../src/META-INF/MANIFEST.MF .
cd ..
mv bin/BoschTest.jar .

