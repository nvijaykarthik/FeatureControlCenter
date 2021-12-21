import React, { Component } from 'react';

import './App.css';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Route, Routes } from 'react-router-dom';
import Features from './Features/Features';
import Admin from './admin/admin';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar bg="nav" variant="dark">
          <Container fluid>
            <Navbar.Brand href="/">Feature Control center</Navbar.Brand>
            <Nav className="me-auto">
              <Nav.Link href="/"><i class="fas fa-home"></i> &nbsp;Home</Nav.Link>
              <Nav.Link href="/admin"><i class="fas fa-user-shield"></i>&nbsp;Admin</Nav.Link>
            </Nav>
          </Container>
        </Navbar>
        <Container fluid>
          <Routes>
            <Route exact path='/' element={<Features />} />
            <Route path='/admin' element={<Admin />} />
          </Routes>
        </Container>
      </div>
    );
  }
}

export default App;