import React, { Component } from 'react';
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import './dist/css/App.css';
import Login from './Components/Login';
import Register from './Components/Register';
import NotFound from './Components/NotFound';
import Logout from './Components/Logout';
import Account from './Components/Account';

/**
 * @author Fabian Frangella
 * Component that contains the app routing
 */
class App extends Component {
  
  render() {
    return (    
    <BrowserRouter>
      <Switch>
          <Route exact path="/" component={Login} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/logout" component={Logout}/>
          <Route exact path="/home" component={Account}/>
          <Route path="*" component={NotFound} />
      </Switch>
  </BrowserRouter>
    );
  }
}

export default App;
