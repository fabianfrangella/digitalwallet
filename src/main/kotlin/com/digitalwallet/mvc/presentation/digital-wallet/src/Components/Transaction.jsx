import React, {Component} from 'react';
import Axios from 'axios';
import Footer from './Footer'
import Navigation from './Navigation'


export default class Transaction extends Component {
    constructor(props) {
        super(props);
        this.state = {
            transactions: [],
            records: false
        };

    }

    renderTransactions = () => {
        return this.state.transactions.map(e => <tr>
            <td>{e.date.substring(0, 10)}</td>
            <td>{e.date.substring(11, 19)}</td>
            <td> {Math.abs(e.amount)} </td>
        </tr>)
    }

    componentDidMount = () => {
        Axios.get(`http://localhost:8080/transaction/transaction-list?accountId=4`)
            .then(response => {
                this.setState({
                    transactions: response.data.reverse(),
                    records: response.data.length > 0
                })
            })

    }

    render() {
        return (
            <div className="App">
                <Navigation/>
                <header className="App-header">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-md-6">
                                <h3 className="text-primary"> Transactions </h3>
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
                                    <th scope="col">Date</th>
                                    <th scope="col">Time</th>
                                    <th scope="col">Amount</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.renderTransactions()}
                                </tbody>
                            </table>

                        </div>
                    </div>
                </header>
                <Footer/>
            </div>

        );
    }
}