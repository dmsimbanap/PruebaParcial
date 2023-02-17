@echo off
git clone https://github.com/dmsimbanap/PruebaParcial
cd .\cliente-app\
call .\gradlew clean
call .\gradlew build
call .\gradlew installDist
docker build . -t danymsimbanap/app-web:test
cd .\..\app-author\
call .\gradlew clean
call .\gradlew build
call .\gradlew installDist
docker build . -t danymsimbanap/app-author:test
cd ..
cd .\app-books\
call .\gradlew clean
call .\gradlew build
call .\gradlew installDist
docker build . -t danymsimbanap/app-book:test
docker push danymsimbanap/app-web:test
docker push danymsimbanap/app-author:test
docker push danymsimbanap/app-book:test
cd ..
docker compose up --scale app-books=3 --scale app-authors=2