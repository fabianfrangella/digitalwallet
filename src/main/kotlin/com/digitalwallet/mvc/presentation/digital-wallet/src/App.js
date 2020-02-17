import React, {Component} from 'react';
import {Route, BrowserRouter, Switch} from 'react-router-dom'
import './dist/css/App.css';
import Login from './Components/Login';
import Register from './Components/Register';
import NotFound from './Components/NotFound';
import Logout from './Components/Logout';
import Account from './Components/Account';
import Transfer from './Components/Transfer';
import Transaction from './Components/Transaction';
import PrivateRoute from './Components/PrivateRoute';
import './Utils'
/**
 * @author Fabian Frangella
 * Component that contains the app routing
 */
class App extends Component {
    constructor(props) {
        super(props)
        this.state = {
            userId: ''
        }

    }

    render() {
        return (
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={Login}/>
                    <Route exact path="/register" component={Register}/>
                    <PrivateRoute exact path="/logout" component={Logout}/>
                    <PrivateRoute exact path="/home" component={Account}/>
                    <PrivateRoute exact path="/transfer" component={Transfer}/>
                    <PrivateRoute exact path="/transactions" component={Transaction}/>
                    <Route path="*" component={NotFound}/>
                </Switch>
            </BrowserRouter>
        );
    }
}

export default App;
