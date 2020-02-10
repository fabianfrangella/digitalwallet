import '../dist/css/NotFound.css';
import React, {Component} from 'react';
import Footer from './Footer'
import logo from '../dist/img/DigitalWalletLogo.png';

/**
 * @author Fabian Frangella
 * Component for non existing routes
 */
export default class NotFound extends Component {
    render(){
        return (
            <div className="NotFound">
                <img className="App-logo" src={logo} alt="DigitalWallet Logo"></img>
                <h1>The page you're trying to visit doesn't exist</h1>
                <Footer/>
            </div>
        );
    }
}