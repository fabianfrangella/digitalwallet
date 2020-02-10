import React, { Component } from 'react';
import { withRouter } from 'react-router-dom'
import axios from 'axios';
import { Button, Form, Alert } from "react-bootstrap";
import '../dist/css/Login.css';
import Footer from './Footer'
import logo from '../dist/img/DigitalWalletLogo.png';

/**
 * @author Fabian Frangella
 * Component for login
 */
class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            idCard: '',
            validate: false,
            alert: {
                show: false,
                variant: "danger",
                message: ""
            }
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleRegister = this.handleRegister.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    /**
     * function used to request a login to the API and access the app
     * @param {Event} ev
     */
    handleLogin = (ev) => {
        ev.preventDefault();
        axios.post(`http://localhost:8080/login`, {
            email: this.state.email,
            password: this.state.password,
        }).then((response) => {
            console.log("login successful")
        })
    }

    /**
     * function used to bind the values of inputs to the component state
     * @param {*} value 
     * @param {prop} prop 
     */
    handleChange(value, prop) {
        this.setState(prevState => ({ ...prevState, [prop]: value }));
    }
    /**
     * function used to redirect the user to the register page
     */
    handleRegister() {
        this.props.history.push('/register');
    }

    /**
     * function used to validate the login form and send the login request to the API
     * @param {Event} event 
     */
    handleSubmit(event) {
        this.handleLogin(event)
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        this.setState({ validated: true });

    };

    /**
     * render the login page
     */
    render() {
        return (
            <div className="Login">
                <div className="row justify-content-center">
                    <img className="Login-logo" src={logo} alt="DigitalWallet Logo"></img>
                </div>
                    <form noValidate validated={this.state.validated} onSubmit={this.handleSubmit}>
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" 
                                class="form-control"  
                                aria-describedby="emailHelp"
                                required
                                autoFocus
                                value={this.state.email}
                                onChange={event => this.handleChange(event.target.value, 'email')}
                                placeholder="Enter email"></input>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                     </div>
                     <div class="form-group">
                        <label>Password</label>
                        <input required
                            class="form-control"
                            type="password"
                            value={this.state.password}
                            onChange={event => this.handleChange(event.target.value, 'password')}
                            placeholder="Enter your password">
                            </input>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <a> </a>
                    <button class="btn btn-secondary">Register</button>
                    </form>
                <div className="App">
                    <Footer />
                </div>
            </div>
        );
    }
}

export default withRouter(Login)
