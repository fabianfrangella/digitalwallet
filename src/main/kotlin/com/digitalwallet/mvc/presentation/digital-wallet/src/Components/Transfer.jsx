import React, {Component} from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import {Alert, Modal, Button} from 'react-bootstrap';
import Axios from 'axios'

export default class Transfer extends Component {
    constructor(props) {
        super(props)
        this.state = {
            accountFrom: '',
            accountTo: '',
            amount: '',
            setShowModal: false,
            alert: {
                show: false,
                variant: "danger",
                message: ""
            }
        }
        this.handleChange = this.handleChange.bind(this);
        this.makeTransfer = this.makeTransfer.bind(this);
    }

    /**
     * function used to bind the values of inputs to the component state
     * @param {*} value
     * @param {prop} prop
     */
    handleChange(value, prop) {
        this.setState(prevState => ({...prevState, [prop]: value}));
    }

    makeTransfer() {
        Axios.post(`http://localhost:8080/transaction/transfer`, {
            accountFrom: 7,
            accountTo: this.state.accountTo,
            amount: this.state.amount
        })
            .then((response) => {
                this.setState({
                    setShowModal: false,
                    alert: {
                        show: true,
                        variant: "success",
                        message: response.data,
                    }
                })
            }).catch((error) => {
            this.setState({
                setShowModal: false,
                alert: {
                    show: true,
                    variant: "danger",
                    message: error.response.data.message
                }
            })
        })
    }

    render() {
        return (
            <div className="App">
                <>
                    <Modal show={this.state.setShowModal} onHide={this.handleClose} animation={false}>
                        <Modal.Header closeButton>
                            <Modal.Title>Are you sure?</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            Are you sure you want to cash in {this.state.amount} USD
                            to your account? <b>This decition cannot be undone.</b>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={this.handleClose}>
                                Close
                            </Button>
                            <Button variant="primary" onClick={(ev) => this.handleCashIn(ev)}>
                                Yes, transfer!
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </>
                <Navigation/>
                <div className="App-header">
                    <div className="container col-8">
                        <div className="row justify-content-center">
                            <div className="col-md-6 row justify-content-center">
                                <h3 className="text-primary"> Make a Transfer! </h3>
                            </div>
                        </div>
                        <hr style={{color: '#0275d8', backgroundColor: '#0275d8', height: 1}}/>
                        <div className="row justify-content-center">
                            <div className="col-md-4">
                                <label> Account Number </label>
                                <input type="number"
                                       required
                                       className='form-control'
                                       placeholder="Account Number"
                                       value={this.state.accountTo}
                                       onChange={event => this.handleChange(event.target.value, "accountTo")}>
                                </input>
                            </div>
                        </div>
                        <div className="row justify-content-center">
                            <div className="col-md-4">
                                <label> Amount </label>
                                <input type="number"
                                       required
                                       className='form-control'
                                       placeholder="Amount to transfer"
                                       value={this.state.amount}
                                       onChange={event => this.handleChange(event.target.value, "amount")}>
                                </input>
                            </div>
                        </div>
                        <div className="row justify-content-center">
                            <div className="col-md-3">
                                <br></br>
                                <button type="submit"
                                        className="btn btn-primary">
                                    Make Transfer!
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-xs-12">
                        <Alert variant={this.state.alert.variant} show={this.state.alert.show}>
                            {this.state.alert.message}
                        </Alert>
                    </div>
                </div>
                <Footer/>
            </div>
        )
    }
}