import React, { Component } from 'react';
import { Card, Button, Form, Alert} from 'react-bootstrap';
import axios from "axios";
import { SERVICE_DOMAIN, Spinner } from '../Constants';

export default class FeatureDetails extends Component{
    state={
        featureName:"",
        featureDesc:"",
        show:false,
        showSpinner:false
    }

    featureNameCng(e){
        this.setState({
            featureName:e.target.value
        })
    }

    featureDescCng(e){
        this.setState({
            featureDesc:e.target.value
        })
    }


    componentDidUpdate(prevProps) {
      if(this.props.selectedFeatureName!==prevProps.selectedFeatureName) 
      {
        let fname="";
        let fdesc="";
        if(this.props.selectedFeatureName!=="NEW"){
          fname=this.props.selectedFeatureName;
          fdesc=this.props.selectedFeatureDesc;
        }
        this.setState({
          featureName:fname,
          featureDesc:fdesc,
          show:false
       })
      }
    } 

    saveFeature(){
      if (this.state.featureName === "") {
        alert("Please Enter the feature name")
        return
      }
      let fname=this.state.featureName;
      let fdesc=this.state.featureDesc;
      this.setState({
        show:false,
        showSpinner:true
     })
     
      let data={
        featureName:fname,
        featureDescription:fdesc
      }
      axios.post(SERVICE_DOMAIN+"/api/saveFeatures",data).then(
        resp=>{
          fname=resp.data.featureName;
          fdesc=resp.data.featureDescription;
          this.setState({
            featureName:fname,
            featureDesc:fdesc,
            show:true,
            showSpinner:false
         })
        },
        err=>{
          console.error(err)
          this.setState({showSpinner:false})
        }
      )
    }
    
    render(){
      let alert=()=>{
        if(this.state.show){
          return(
            <Alert variant="success" >
                    Data saved successfully please click refresh to view the new data
            </Alert>
          )
        }
      }
        return(
            <Card className="border-0">
                <Card.Body>
                {this.state.showSpinner ? <Spinner />:<div></div>}
                  <Card.Title><h4>Feature Details</h4></Card.Title>
                  {alert()}
                  <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                      <Form.Label>Feature Name</Form.Label>
                      <Form.Control type="text" onChange={(e)=>this.featureNameCng(e)} value={this.state.featureName} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                      <Form.Label>Feature Description</Form.Label>
                      <Form.Control as="textarea" rows={3} onChange={(e)=>this.featureDescCng(e)} value={this.state.featureDesc}/>
                    </Form.Group>
                    <Button variant="primary" type="button" onClick={()=>this.saveFeature()}>
                    <i className="far fa-save"></i>&nbsp;Save
                    </Button>
                  </Form>
                </Card.Body>
              </Card>
        )
    }
}