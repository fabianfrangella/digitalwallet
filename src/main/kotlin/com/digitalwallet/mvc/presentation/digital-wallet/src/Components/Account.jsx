import React, { Component } from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import Axios from 'axios';


export default class Account extends Component {
    constructor(props) {
        super(props)
        this.state =  {
            username: "",
            email: "",
            idCard: "",
            cvu: "",
            balance: "",
            userId: ""
        }
    }

    componentDidMount() {
        Axios.get(`http://localhost:8080/account/${this.props.location.state.userId}`)
        .then((response) => {
            this.setState({
                username: response.data.username,
                email: response.data.email,
                idCard: response.data.idCard,
                cvu: response.data.cvu,
                balance: response.data.balance,
                userId: this.props.location.state.userId
            })
        })
    }

    render() {
        return (
            <div>
                <Navigation id={this.state.userId}/>
                <header className="App-header">
                    <div className="container col-8">
                        <div className="row justify-content-center">
                            <div className="col-md-6 row justify-content-center">
                                <h3 className="text-primary"> Account </h3>
                            </div>
                        </div>
                        <hr style={{color: '#0275d8', backgroundColor: '#0275d8', height: 1}}/>
                       
                            <div className="row">
                                <div className="form-group col-md-6">
                                    <label>Username</label>
                                    <input type="text"
                                           className='form-control'
                                           placeholder= "Enter your First Name"
                                           value={this.state.username}
                                            >
                                    </input>
                                    <div className="invalid-feedback">
                                        Username cannot be empty.
                                    </div>
                                </div>
                                <div className="form-group col-md-6">
                                    <label>ID Card</label>
                                    <input type="number"
                                           className='form-control'
                                           disabled={true}
                                           value={this.state.idCard}
                                           >
                                    </input>
                                </div>
                            </div>
                            <div className="row">
                                <div className="form-group col-md-6">
                                    <label>Email</label>
                                    <input type="text"
                                           className='form-control'
                                           placeholder= "Enter your E-Mail"
                                           value={this.state.email}></input>
                                    <div className="invalid-feedback">
                                        Please enter a valid email address.
                                    </div>
                                </div>
                                <div className="form-group col-md-3">
                                    <label>CVU</label>
                                    <input type="number"
                                           className="form-control"
                                           disabled={true}
                                           value={this.state.cvu}>
                                    </input>
                                </div>
                                <div className="form-group col-md-3">
                                    <label>Balance</label>
                                    <input type="number"
                                           className="form-control"
                                           disabled={true}
                                           value={this.state.balance}>
                                    </input>
                                </div>
                            </div>
                            <button type="submit"
                                    className="btn btn-primary">Change
                                    <i className="far fa-paper-plane"></i>
                            </button>
                        <br/>
                    </div>
                    <Footer/>
                </header>
            </div>
        )
    }
}