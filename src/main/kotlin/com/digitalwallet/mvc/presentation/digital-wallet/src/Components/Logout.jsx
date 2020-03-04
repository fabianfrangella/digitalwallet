 
import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

/**
 * @author Fabian Frangella
 * Component for logout
 */
class Logout extends Component {
    render(){
        return(
            <Redirect push to="/"/>
        )
    }
}

export default Logout