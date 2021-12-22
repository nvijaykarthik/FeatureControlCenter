import React, { Component } from 'react';
import { Card,Button, InputGroup, FormControl, Table, Form,  Modal } from 'react-bootstrap';
import axios from "axios";
import { SERVICE_DOMAIN, Spinner } from '../Constants';



export default class ImpactedServices extends Component {

    state = {
        featureName: "",
        show: false,
        impactedServices: [],
        servicename: "",
        impsearch:"",
        filteredImpactedService:[],
        showSpinner:false
    }


    handleClose = () => {
        this.setState({ show: false });
    }
    handleShow = () => {
        if(this.state.featureName===""){
            alert("Please save/select the feature and add impacted Service")
            return 
        }
        this.setState({ servicename: "", show: true });
    }

    getImpactedService(fname){
        this.setState({showSpinner:true})
        axios.get(SERVICE_DOMAIN+"/api/getImpactedServices?featureName=" + fname).then(
            resp => {
                this.setState({
                    impactedServices: resp.data,
                    filteredImpactedService:resp.data,
                    featureName:fname,
                    showSpinner:false
                })
            },
            err => { console.error(err);this.setState({showSpinner:false}) }
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

    searchOnchange(e) {
        this.setState({ impsearch: e.target.value })
    }
    saveImpactedService() {
        let fname = this.state.featureName;
        let serviceName = this.state.servicename;
        this.setState({
            show: false,
            showSpinner:true
        })
        let data = {
            featureName: fname,
            serviceName: serviceName
        }
        axios.post(SERVICE_DOMAIN+"/api/addImpactedService", data).then(
            resp => {
                console.log(resp.data)
                this.setState({
                    show: false,
                    showSpinner:false
                })
                this.getImpactedService(fname)
            },
            err => {
                console.error(err)
                this.setState({showSpinner:false})
            }
        )
    }
    onSearchClick(){
      let lst=this.state.filteredImpactedService
      if(this.state.impsearch!==""){
        lst=this.state.impactedServices.filter(data=>data.serviceName.includes(this.state.impsearch));
      }
      this.setState({impactedServices:lst})
    }
    remove(dt){
        console.log(dt)
        this.setState({showSpinner:true})
        axios.post(SERVICE_DOMAIN+"/api/removeImpactedService", dt).then(
            resp => {
                console.log(resp.data)
                this.getImpactedService(this.state.featureName)

            },
            err => {
                console.error(err)
                this.setState({showSpinner:false})
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
                        <Button variant="danger" id="button-addon2" onClick={()=>this.remove(dt)}>
                            <i className="fas fa-trash"></i>
                        </Button>
                    </td>
                </tr>);
            });
        }

        return (
            <div>
                <Card className="border-0">
                    <Card.Body>
                    {this.state.showSpinner ? <Spinner />:<div></div>}
                        <Card.Title><h5>Impacted Service</h5></Card.Title>
                        <InputGroup className="mb-3">
                            <FormControl
                                placeholder="Filter" onChange= {(e)=>this.searchOnchange(e)} value={this.state.impsearch}
                            />
                            <Button variant="info" id="button-addon2" onClick={()=>this.onSearchClick()}>
                                <i className="fas fa-search"></i>
                            </Button>
                            <Button variant="nav" id="button-addon2" onClick={this.handleShow}>
                                <i className="fas fa-plus-circle"></i>
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
                                <i className="fas fa-save"></i>
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