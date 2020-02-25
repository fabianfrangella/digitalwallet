import React, { Component } from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import {Alert} from 'react-bootstrap';
import Axios from 'axios';

export default class Cards extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: []
        }
    }

    renderCards = () => {
        return this.state.cards.map(card => <tr>
            <td>{card.cardNumber}</td>
            <td>{card.amountLimit}</td>
            <td>{card.firstname}</td>
            <td>{card.lastname}</td>
            <td>{card.securityNumber}</td>
        </tr>)
    }

    componentDidMount() {
        Axios.get(`http://localhost:8080/card/user-cards?userId=${this.props.location.state.userId}`)
        .then((response) => {
            this.setState({
                cards: response.data,
                records: response.data.length > 0
            })
        })
    }

    render(){
        return(
            <div className="App">
                <Navigation id={this.props.location.state.userId}/>
                <header className="App-header">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-md-6">
                                <h3 className="text-primary"> My Cards </h3>
                            </div>
                        </div>
                        <hr style={{color: '#0275d8', backgroundColor: '#0275d8', height: 1}}/>
                        <div className="container">
                            <div hidden={this.state.records}>
                                <i className="fas fa-exclamation-triangle fa-10x"></i>
                                <h1 className="display-4">No records</h1>
                            </div>

                            <table hidden={!this.state.records} className="table-dark col">
                                <thead>
                                <tr>
                                    <th scope="col">Card Number</th>
                                    <th scope="col">Card Limit</th>
                                    <th scope="col">First Name</th>
                                    <th scope="col">Last Name</th>
                                    <th scope="col">Security Number</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.renderCards()}
                                </tbody>
                            </table>

                        </div>
                    </div>
                </header>
                <Footer/>
            </div>
        )
    }
}