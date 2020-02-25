import React, { Component } from 'react';
import '../dist/css/Card.css';

export default class Cards extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cardNumber: 0,
            firstname: '',
            lastname: '',
            cardId:'',
            dueDate: '10/25',
            cardNumberWSpaces:''
            }
    }

    componentDidMount() {
        this.setState({
            cardNumber: this.props.cardNumber,
            firstname: this.props.firstname,
            lastname: this.props.lastname,
            cardId: this.props.cardId
        })
        let cardNumber = this.props.cardNumber.toString();
        while (cardNumber.length > 0){
            this.state.cardNumberWSpaces = this.state.cardNumberWSpaces + " " + cardNumber.substring(0,4)
            cardNumber = cardNumber.substring(4);// Trim String
        }
    }

    render() {
        return(
            <div className="Card">
                <div className="col-md-9 card-number">
                    <h4>{this.state.cardNumberWSpaces}</h4>
                </div>
                <div className="card-due-date">
                    <label>{this.state.dueDate}</label>
                </div>
                <div className="card-name">
                    <h5>{this.state.firstname} {this.state.lastname}</h5>
                </div>
             </div>
        )
    }
}
