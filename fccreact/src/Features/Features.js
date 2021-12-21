import React, { Component } from "react";
import {  Col, Row } from 'react-bootstrap';
import FeatureSearch from './FeatureSearch';
import FeatureDetails from './FeatureDetails';
import ImpactedServices from './ImpactedService';
import Action from './Action';

export default class Features extends Component {
    state = {
        selectedFeatureName: "",
        selectedFeatureDesc: ""
    }

    setSelectedFeature(fname, fdesc) {
        console.log(fname, fdesc)
        this.setState({ selectedFeatureName: fname, selectedFeatureDesc: fdesc })
    }
    render() {
        return (
            <div>
                <Row>
                    <Col><h2>Features</h2></Col>
                </Row>
                <Row>
                    <Col md={3}>
                        <FeatureSearch selectedFeature={(fname, fdesc) => this.setSelectedFeature(fname, fdesc)} />
                    </Col>
                    <Col md={9}>
                        <div className="card">
                            <FeatureDetails selectedFeatureName={this.state.selectedFeatureName} selectedFeatureDesc={this.state.selectedFeatureDesc} />
                            <br />
                            <Row>
                                <Col md={4}>
                                    <ImpactedServices selectedFeatureName={this.state.selectedFeatureName} />
                                </Col>
                                <Col md={8}>
                                    <Action selectedFeatureName={this.state.selectedFeatureName} />
                                </Col>
                            </Row>
                        </div>
                    </Col>
                </Row>
            </div>
        )
    }
}