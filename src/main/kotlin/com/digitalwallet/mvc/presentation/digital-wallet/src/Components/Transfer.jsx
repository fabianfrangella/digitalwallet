import React, {Component} from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import Axios from 'axios'

export default class Transfer extends Component {
    constructor(props) {
        super(props)
        this.state = {
            accountFrom: '',
            accountTo: '',
            amount: ''
        }

    }

    render() {
        return (
            <div>
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
                                       className='form-control'
                                       placeholder="Account Number">
                                </input>
                            </div>
                        </div>
                        <div className="row justify-content-center">
                            <div className="col-md-4">
                                <label> Amount </label>
                                <input type="number"
                                       className='form-control'
                                       placeholder="Amount to transfer">
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
                <Footer/>
            </div>
        )
    }
}