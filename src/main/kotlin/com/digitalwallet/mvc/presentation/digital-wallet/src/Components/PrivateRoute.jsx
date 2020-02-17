import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import {isLogin} from "../Utils";

/**
 * @author Fabian Frangella
 * Route component used for routing components that should not be accessible without login
 */
const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={props => (
        isLogin()
            ? <Component {...props} />
            : <Redirect to={{ pathname: '/', state: { from: props.location } }} />
    )} />
)

export default PrivateRoute