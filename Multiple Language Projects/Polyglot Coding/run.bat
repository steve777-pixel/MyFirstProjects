@echo off
:: 1. Compile Java (Compiles ALL files inside app and models)
javac src\models\*.java src\app\*.java src\utils\*.java

:: 2. Compile C++
g++ -shared -o src\MathEngine.dll -I"C:\Users\Asus\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.3.9-hotspot\include" -I"C:\Users\Asus\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.3.9-hotspot\include\win32" mathEngine\math.cpp mathEngine\core_algorithms.cpp

:: 3. Run the App safely without warnings
cd src
java --enable-native-access=ALL-UNNAMED app.Main
cd ..
