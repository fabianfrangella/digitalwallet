import React, { Component } from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import {Alert, Modal, Button} from 'react-bootstrap';
import Axios from 'axios';
import Card from './Card';

export default class Cards extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: [],
            setShowModal: false,
            records: false,
            alert: {
                show: false,
                variant: "danger",
                message: ""
            }
        }
    }

    renderCards = () => {
        return this.state.cards.map(card =>
            <div>
                <Card 
                    cardNumber={card.cardNumber}
                    firstname={card.firstname}
                    lastname={card.lastname}
                    cardId={card.card_id}
                    dueDate={card.dueDate}
                />
                <br/>
            </div>
        )
    }

    componentDidMount() {
        Axios.get(`http://localhost:8080/card/user-cards?userId=${this.props.location.state.userId}`)
        .then((response) => {
            this.setState({
                cards: response.data,
                records: response.data.length > 0
            })
            this.renderCards()
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
                                <br/>
                                <h3 className="text-primary"> My Cards </h3>
                            </div>
                        </div>
                        <hr style={{color: '#0275d8', backgroundColor: '#0275d8', height: 1}}/>
                        <div className="container">
                            <div hidden={this.state.records}>
                                <i className="fas fa-exclamation-triangle fa-10x"></i>
                                <h1 className="display-4">No Cards</h1>
                            </div>
                            <div className="container col-md-10">
                                <div className="row">
                                    {this.renderCards()}
                                </div>
                            </div>
                            <div className="row justify-content-center">
                                <div className="col-md-3">
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