import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import {WelcomeLoginPage} from '../pages/WelcomeLoginPage';


interface LoginProps {
onLogin: () => void;
}
export const Login: React.FC<LoginProps> = ({ onLogin }) => {
const [loggedIn, setLoggedIn] = useState(false);
const history = useHistory();

const getCredentials = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    let getUsername = (document.getElementById("username") as HTMLInputElement).value;
    let getPassword = (document.getElementById("password") as HTMLInputElement).value;

    try {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username: getUsername, password: getPassword })
        });

        if (response.ok) {
            const data = await response.text();
            console.log("Login successful: ", data);
            setLoggedIn(true);
            onLogin(); // Call the onLogin function passed as prop
            history.push('/welcome');
        } else {
            console.error("Login failed: ", response.statusText);
        }
    } catch (error) {
        console.error("Error: ", error);
    }

    console.log(getUsername);
    console.log(getPassword);
};

return (
    loggedIn ? (
        <WelcomeLoginPage />
    ) : (
        <form onSubmit={getCredentials}>
            <div>
                <label>Username: </label><input type="text" name="username" placeholder="Enter username" id="username" />
            </div>
            <div>
                <label>Password: </label><input type="password" name="password" placeholder="Enter password" id="password" />
            </div>
            <button type="submit">Login</button>
        </form>
    )
);
};