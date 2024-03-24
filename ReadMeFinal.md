1. open command prompt and install the following while in the Project folder
   - npm install react-router-dom@5.2.0
2. Start backend spring server file - Main.java
   - .\login_react_app\Login_Spring_Backend
3. Start frontend server
   - Open command prompt
   - enter 'npm start'
4. In postman go to localhost:8080/register and register a user. Pass this in the JSON:
   -
   {
      "username":"USERNAMEHERE",
      "password": "PASSWORDHERE"
}
5. Open up browser and go to http://localhost:3000/login (press F12 to see your login credentials and the session in the console.)
6. Login with credentials that you registered
7. This should take you to welcome page.
8. Login twice to see the welcome page (bug that I need to fix).