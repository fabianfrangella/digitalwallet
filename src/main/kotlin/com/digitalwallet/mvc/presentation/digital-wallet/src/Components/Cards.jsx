import React, { Component } from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import {Alert, Modal, Button} from 'react-bootstrap';
import Axios from 'axios';

export default class Cards extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: [],
            setShowModal: false,
            alert: {
                show: false,
                variant: "danger",
                message: ""
            }
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

    createCard = (ev) => {
        ev.preventDefault()
        Axios.post(`http://localhost:8080/card/create-card?userId=${this.props.location.state.userId}`)
        .then((response) => {
            this.setState({
                setShowModal: false
            })
        this.componentDidMount()
        })
    }

    handleOpen = (ev) => {
        ev.preventDefault();
        this.setState({
            setShowModal: true,
            alert: {
                show: false
            }
        })
    }


    handleClose = () => {
        this.setState({
            setShowModal: false
        })
    }

    render(){
        return(
            <div className="App">
                <>
                    <Modal show={this.state.setShowModal} onHide={this.handleClose} animation={false}>
                        <Modal.Header closeButton>
                            <Modal.Title>Are you sure?</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                           Are you sure you want to create a new Card?
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={this.handleClose}>
                                Close
                            </Button>
                            <Button variant="primary" onClick={(ev) => this.createCard(ev)}>
                                Yes!
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </>
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
                            <div className="row justify-content-center">
                            <div className="col-md-3">
                                <br></br>
                                <button type="submit"
                                        className="btn btn-primary"
                                        onClick={ev=>this.handleOpen(ev)}
                                        >
                                            Create a New Card!
                                </button>
                            </div>
                        </div>
                        </div>
                    </div>
                </header>
                <Footer/>
            </div>
        )
    }
}