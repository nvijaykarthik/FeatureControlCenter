import React, { Component } from 'react';
import { Card, Col, Row, Button, InputGroup, FormControl,  Table} from 'react-bootstrap';
import axios from "axios";
import { SERVICE_DOMAIN, Spinner } from '../Constants';



export default class FeatureSearch extends Component{

    state={
        featureList:[],
        selectedFeatureNm:"",
        filterFeatureName:"",
        filteredFeatureList:[],
        searchData:"",
        showSpinner:false

    }

    componentDidMount(){
       this.getAllFeatures();
    }

    getAllFeatures(){
      let self=this
      self.setState({showSpinner:true})
      axios.get(SERVICE_DOMAIN+"/api/getFeatures").then(
          resp=>{
              console.log(resp.data)
              self.setState({featureList:resp.data,filteredFeatureList:resp.data,showSpinner:false})
          },
          err=>{
              console.error(err)
          }
      )
    }

    filterFeatureName(){

      let fList=this.state.filteredFeatureList
      if(this.state.searchData!==""){
        fList=this.state.featureList.filter(data=>data.featureName.includes(this.state.searchData));
      }
      this.setState({featureList:fList})
    }
    
    textChangeEvt(e){
      this.setState({searchData:e.target.value})
    }

    render(){

        let rowdt=null;
        if(this.state.featureList!=null) {
          rowdt= this.state.featureList.map((dt,i)=>{
           return( <tr key={i}>
                <td>{i+1}</td>
                <td>{dt.featureName}</td>
                <td align="right">
                    <Button onClick={()=>this.props.selectedFeature(dt.featureName,dt.featureDescription)} variant="warning" id="button-addon2">
                        <i className="fas fa-arrow-alt-circle-right"></i>
                    </Button>
                </td>
            </tr>);
        });
      }
      
        return(
            <Card>
              <Card.Body>
               {this.state.showSpinner ? <Spinner />:<div></div>}
                <Card.Title><h4>Feature List</h4></Card.Title>
                <Card.Subtitle className="mb-2 text-muted">List of Available features</Card.Subtitle>
                <Row>
                  <Col>
                    <InputGroup className="mb-3">
                      <FormControl  placeholder="Filter" value={this.state.searchData} onChange={(e)=>this.textChangeEvt(e)}/>
                      <Button variant="info" id="button-addon2" onClick={()=>this.filterFeatureName()}>
                        <i className="fas fa-search"></i>
                      </Button>
                      <Button variant="secondary" id="button-addon2" onClick={()=>this.getAllFeatures()}>
                        <i className="fas fa-sync"></i>
                      </Button>
                      <Button variant="primary" id="button-addon2"  onClick={()=>this.props.selectedFeature("NEW")}>
                        <i className="fas fa-plus-circle"></i>
                      </Button>
                    </InputGroup>
                  </Col>
                </Row>
                <br />
                <Row>
                  <Col>
                    <div className="featureList">
                      <Table striped hover size="sm">
                        <tbody>
                          {rowdt}
                        </tbody>
                      </Table>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
        )
    }
}