import React, { Component } from 'react';
import Footer from './Footer'
import Navigation from './Navigation'
import {Alert} from 'react-bootstrap';
import Axios from 'axios';

export default class Cards extends Component {

    render(){
        return(
            <div className="App">
                <Navigation id={this.props.location.state.userId}/>

                <Footer/>
            </div>
        )
    }
}