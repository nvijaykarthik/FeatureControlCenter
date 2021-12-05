import React, { Component, useState } from 'react';
import { Card, Col, Row, Button, InputGroup, FormControl, Table, Form, Alert, Modal } from 'react-bootstrap';
import axios from "axios";



export default class ImpactedServices extends Component {

    state = {
        featureName: "",
        show: false,
        impactedServices: [],
        servicename: "",
    }


    handleClose = () => {
        this.setState({ show: false });
    }
    handleShow = () => {
        if(this.state.featureName===""){
            alert("Please save/select the feature add impacted Service")
            return 
        }
        this.setState({ servicename: "", show: true });
    }

    getImpactedService(fname){
        axios.get("http://localhost:9000/api/getImpactedServices?featureName=" + fname).then(
            resp => {
                this.setState({
                    impactedServices: resp.data,
                    featureName:fname
                })
            },
            err => { console.error(err) }
        )
    }

    componentDidUpdate(prevProps) {
        if (this.props.selectedFeatureName !== prevProps.selectedFeatureName) {
            let fname = "";

            if (this.props.selectedFeatureName !== "NEW") {
                fname = this.props.selectedFeatureName;
            }
            this.getImpactedService(fname)
        }
    }
    onServiceNameChange(e) {
        this.setState({ servicename: e.target.value })
    }
    saveImpactedService() {

        let fname = this.state.featureName;
        let serviceName = this.state.servicename;
        this.setState({
            show: false
        })
        let data = {
            featureName: fname,
            serviceName: serviceName
        }
        axios.post("http://localhost:9000/api/addImpactedService", data).then(
            resp => {
                console.log(resp.data)
                this.setState({
                    show: false
                })
                this.getImpactedService(fname)
            },
            err => {
                console.error(err)
            }
        )
    }

    render() {
        let rowdt = null;
        if (this.state.impactedServices != null) {
            rowdt = this.state.impactedServices.map((dt, i) => {
                return (<tr key={i}>
                    <td>{i + 1}</td>
                    <td>{dt.serviceName}</td>
                    <td align="right">
                        <Button variant="danger" id="button-addon2">
                            <i class="fas fa-trash"></i>
                        </Button>
                    </td>
                </tr>);
            });
        }

        return (
            <div>
                <Card className="border-0">
                    <Card.Body>
                        <Card.Title><h5>Impacted Service</h5></Card.Title>
                        <InputGroup className="mb-3">
                            <FormControl
                                placeholder="Filter"
                            />
                            <Button variant="info" id="button-addon2">
                                <i class="fas fa-search"></i>
                            </Button>
                            <Button variant="primary" id="button-addon2" onClick={this.handleShow}>
                                <i class="fas fa-plus-circle"></i>
                            </Button>
                        </InputGroup>
                        <Table striped hover size="sm">
                            <tbody>
                                {rowdt}
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>

                <Modal show={this.state.show} onHide={this.handleClose} centered>

                    <Modal.Body>
                        <h5>Impacted Serice</h5>
                        <Form.Label>Service name</Form.Label>
                        <InputGroup className="mb-3">
                            <FormControl
                                placeholder="enter the service name" onChange={(e) => this.onServiceNameChange(e)}
                                value={this.state.servicename}
                            />
                            <Button variant="info" id="button-addon2" onClick={()=>this.saveImpactedService()}>
                                <i class="fas fa-save"></i>
                            </Button>
                        </InputGroup>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" size="sm" onClick={this.handleClose}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Modal>

            </div>
        )
    }

}