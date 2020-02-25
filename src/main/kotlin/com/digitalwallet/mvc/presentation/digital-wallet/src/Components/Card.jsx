import React, { Component } from 'react';
import '../dist/css/Card.css';

export default class Cards extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cardNumber: '',
            firstname: '',
            lastname: '',
            cardId:'',
            dueDate: '10/25'
            }
    }

    componentDidMount() {
        this.setState({
            cardNumber: this.props.cardNumber,
            firstname: this.props.firstname,
            lastname: this.props.lastname,
            cardId: this.props.cardId
        })
    }

    render() {
        return(
            <div className="Card">
                <div className="row">
                    <div className="col-md-6">
                        <br/><br/>
                        <h6>Digital Wallet Card</h6>
                        <br/><br/><br/>
                        <div className="col-md-6">
                            <h4>{this.state.cardNumber}</h4>
                        </div>                      
                        <div className="container">     
                            <label>Due Date: {this.state.dueDate}</label>
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="row">
                            <h6>{this.state.firstname} {this.state.lastname}</h6>
                        </div>
                    </div>
                </div> 
             </div>
        )
    }
}
