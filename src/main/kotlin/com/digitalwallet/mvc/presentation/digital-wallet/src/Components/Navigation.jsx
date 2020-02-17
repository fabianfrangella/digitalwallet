import React, { Component } from 'react'
import Navbar from 'react-bootstrap/Navbar';
import Image from 'react-bootstrap/Image';
import logo from '../dist/img/DigitalWalletLogo.png';
import '../dist/css/Navigation.css';
import { Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom'

class Navigation extends Component {
  render() {
    return (
      <Navbar bg="dark" expand="lg" variant="dark">
        <Navbar.Brand>          
        <Link to="">
          <Image className="logo" src={logo} fluid ></Image>
          </Link>
          </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Navbar.Brand>
              <Link className="link" to="/home">Account</Link>
            </Navbar.Brand>
            <Navbar.Brand>
              <Link className="link" to="">Transactions</Link>
            </Navbar.Brand>
            <Navbar.Brand>
              <Link className="link" to="">Cash In</Link>
            </Navbar.Brand>
            <Navbar.Brand>
              <Link className="link" to="/transfer">Transfer</Link>
            </Navbar.Brand>
            </Nav>
            <Nav>
            <Navbar.Brand>
            <Link className="link" to= "logout">Logout</Link>
            </Navbar.Brand>
            </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
  }
}

export default Navigation