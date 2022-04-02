# Library Management
## This application handle library management
###&nbsp;By this application you can manage the storage in the library like: add, order, loan, return books.<br/>&nbsp;This application also handle user management like: Sign up, Sign in, user authentication.<br/>&nbsp;The app also contains jobs that run in the background, and it sends messages to notify th users. 

##Install and run:
###Install DB locally - you can do it with docker:
- You need MYSQL and Docker to be in your machine, then run in your terminal:
```
docker run -p 7306:3306 --name library-db-local -eMYSQL_ROOT_PASSWORD=root -d mysql:5.7
```
- Create Library DB.
###Run java:
- This project based on Java 11, so you need in your machine JDK 11 or latest.

###Now you can run the application,duo this project use hibernate and JPA, it wil creat hte tables at DB automatically.

###Run the UI side:  
- Install node version 12 or latest in your machine.
- Goto client folder.
- Run nvm use 12 (or the latest version).
- Run npm install.
- Run npm start.