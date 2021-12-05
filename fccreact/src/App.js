import React, { Component } from 'react';

import './App.css';
import { Card, Col, Container, Row, Button, InputGroup, FormControl, ButtonGroup, Table, Form } from 'react-bootstrap';
import FeatureSearch from './FeatureSearch';
import FeatureDetails from './FeatureDetails';
import ImpactedServices from './ImpactedService';
import Action from './Action';

class App extends Component {
  state ={
    selectedFeatureName:"", 
    selectedFeatureDesc:""   
  }

  setSelectedFeature(fname,fdesc){
    console.log(fname,fdesc)
    this.setState({selectedFeatureName : fname,selectedFeatureDesc:fdesc})
  }

  render() {
    return (
      <Container fluid>
        <Row>
          <Col><h1>Features</h1></Col>
        </Row>
        <Row>
          <Col md={3}>
            <FeatureSearch selectedFeature={(fname,fdesc)=>this.setSelectedFeature(fname,fdesc)}/>
          </Col>
          <Col md={9}>
            <div className="card">
              <FeatureDetails selectedFeatureName={this.state.selectedFeatureName} selectedFeatureDesc={this.state.selectedFeatureDesc}/>
              <br />
              <Row>
                <Col md={4}>
                 <ImpactedServices selectedFeatureName={this.state.selectedFeatureName}/>
                </Col>
                <Col md={8}>
                  <Action selectedFeatureName={this.state.selectedFeatureName}/>
                </Col>
              </Row>
            </div>
          </Col>

        </Row>
      </Container>
    );
  }
}

export default App;