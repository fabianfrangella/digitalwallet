import React, { Component } from 'react';
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import './dist/css/App.css';
import Login from './Components/Login';
import NotFound from './Components/NotFound';

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
          <Route path="*" component={NotFound} />
      </Switch>
  </BrowserRouter>
    );
  }
}

export default App;
