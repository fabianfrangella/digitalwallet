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
        let modifiedCardNumber = this.props.cardNumber.toString();
        while (modifiedCardNumber.length > 0){
            this.setState({
                cardNumberWSpaces: this.state.cardNumberWSpaces + " " + modifiedCardNumber.substring(0,4)
            })
            modifiedCardNumber = modifiedCardNumber.substring(4);// Trim String
        }
    }

    render() {
        return(
            <div className="Card">
                <label>{this.state.cardNumberWSpaces}</label>
             </div>
        )
    }
}
