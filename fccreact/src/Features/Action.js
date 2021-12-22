import React, { Component } from 'react';
import { Card, Button, FormControl, Form,  } from 'react-bootstrap';
import axios from "axios";
import { SERVICE_DOMAIN, Spinner } from '../Constants';

export default class Action extends Component {

  state = {
    id: "",
    featureName: "",
    actionType: "",
    config: "",
    action: "",
    alyStatus: "",
    endD:"",
    startDt:"",
    periodStatus: "",
    scheduleStatus: "",
    cron: "",
    cronDesc:"",
    showSpinner:false
  }

  componentDidUpdate(prevProps) {
    if (this.props.selectedFeatureName !== prevProps.selectedFeatureName) {
      let fname = "";
      if (this.props.selectedFeatureName !== "NEW") {
        fname = this.props.selectedFeatureName;
      }
      this.loadAction(fname);
    }
  }
  reset(){
    this.setState({
      id: "",
      featureName: "",
      actionType: "",
      config: "",
      action: "",
      alyStatus: "",
      endD:"",
      startDt:"",
      periodStatus: "",
      scheduleStatus: "",
      cron: ""
    })
  }
  loadAction(fname) {
    let self = this
    self.setState({showSpinner:true})
    axios.get(SERVICE_DOMAIN + "/api/getActivationConfig?featureName=" + fname).then(
      resp => {
        console.log(resp);
        if (resp.data.action === 'SCHEDULED') {
          let json = JSON.parse(resp.data.configuration)
          let status = json["status"];
          let cron=json['cron'];
          self.setState({
            id: resp.data.id,
            featureName: fname,
            showSpinner:false,
            actionType: resp.data.action,
            config: resp.data.configuration,
            scheduleStatus: status,
            cron:cron
          })
        } else if (resp.data.action === 'PERIOD') {
         // conf = '{"startdatetime":"' + this.state.startDt + '","enddatetime":"' + this.state.endDt + '","status":"' + this.state.periodStatus + '"}';
         let json = JSON.parse(resp.data.configuration)
          let status = json["status"];
          let stdt=json['startdatetime'];
          let eddt=json['enddatetime'];
          self.setState({
            id: resp.data.id,
            featureName: fname,
            actionType: resp.data.action,
            config: resp.data.configuration,
            periodStatus: status,
            startDt:stdt,
            endDt:eddt,
            showSpinner:false,
          })

        } else if (resp.data.action === 'ALWAYS') {
          let json = JSON.parse(resp.data.configuration)
          let status = json["status"];
          self.setState({
            id: resp.data.id,
            featureName: fname,
            actionType: resp.data.action,
            config: resp.data.configuration,
            alyStatus: status,
            showSpinner:false,
          })
        }else{
          self.setState({
            id: "",
            featureName: fname,
            actionType: "",
            config: "",
            action: "",
            alyStatus: "",
            endD:"",
            startDt:"",
            periodStatus: "",
            scheduleStatus: "",
            cron: "",
            showSpinner:false,
          })
        }
      },
      err => { console.error(err);self.setState({showSpinner:false}) })
  }

  actionChange(e) {
    this.setState({
      actionType: e.target.value,
      config: ""
    })
  }

  saveAction() {
   
    if (this.state.featureName === "") {
      alert("Please save/select the feature and add the configuration")
      return
    }
    let conf = this.state.config
    if (this.state.actionType === 'ALWAYS') {
      conf = '{"status":"' + this.state.alyStatus + '"}';
    } else if (this.state.actionType === 'PERIOD') {
      conf = '{"startdatetime":"' + this.state.startDt + '","enddatetime":"' + this.state.endDt + '","status":"' + this.state.periodStatus + '"}';
    } else if (this.state.actionType === 'SCHEDULED') {
      conf = '{"cron":"' + this.state.cron + '","status":"' + this.state.scheduleStatus + '"}';
    }
    let self = this;
    self.setState({showSpinner:true})
    let data = {
      id: this.state.id,
      featureName: this.state.featureName,
      action: this.state.actionType,
      configuration: conf
    }
    axios.post(SERVICE_DOMAIN + "/api/saveActivationConfig", data).then(
      resp => {
        self.setState({
          id: resp.data.id,
          featureName: resp.data.featureName,
          actionType: resp.data.action,
          config: resp.data.configuration,
          showSpinner:false
        })
        alert("Data saved Successfully")
      },
      err => { console.error(err); self.setState({showSpinner:false})}
    )
  }

  alwStatus(e) {
    let status = e.target.value
    this.setState({
      alyStatus: status
    })
  }

  startDtChange(e) {
    let stdt = e.target.value
    this.setState({
      startDt: stdt
    })
  }

  endDtChange(e) {
    let eddt = e.target.value
    this.setState({
      endDt: eddt
    })
  }
  periodStatusChange(e) {
    let status = e.target.value
    this.setState({
      periodStatus: status
    })
  }
  scheduleStatusChange(e) {
    let status = e.target.value
    this.setState({
      scheduleStatus: status
    })
  }
  
  cronChange(e) {
    let cron = e.target.value
    this.setState({
      cron: cron
    })
  }

  evaluateCron(){
    this.setState({showSpinner:true})
    axios.get(SERVICE_DOMAIN + "/api/evaluateCron?cron="+this.state.cron).then(
      resp => {
        this.setState({
          cronDesc:resp.data,
          showSpinner:false
        })
      },
      err => { 
        this.setState({
          cronDesc:"Invalid cron, Please verify(read details in via link)",
          showSpinner:false
        })
        console.error(err) }
    )
  }

  render() {
    let content = () => {
      if (this.state.actionType === "ALWAYS") {
        return (
          <div>
            <Form.Label>Active</Form.Label>
            <Form.Select onChange={(e) => this.alwStatus(e)} value={this.state.alyStatus}>
              <option></option>
              <option value="Y">Y</option>
              <option value="N">N</option>
            </Form.Select>
          </div>
        )
      } else if (this.state.actionType === "PERIOD") {
        return (<div>
          <Form.Label >Start Date Time</Form.Label>
          <FormControl
            placeholder="start date time"
            type="datetime-local"
            onChange={(e) => this.startDtChange(e)} value={this.state.startDt}
          />
          <Form.Label>End Date Time</Form.Label>
          <FormControl
            placeholder="end date time"
            type="datetime-local"
            onChange={(e) => this.endDtChange(e)} value={this.state.endDt}
          />
          <Form.Label>Active</Form.Label>
          <Form.Select onChange={(e) => this.periodStatusChange(e)} value={this.state.periodStatus}>
            <option></option>
            <option value="Y">Y</option>
            <option value="N">N</option>
          </Form.Select>
        </div>
        )
      } else if (this.state.actionType === "SCHEDULED") {
        return (<div>
          <Form.Label>Cron Schedule (<a target="_blank" rel="noreferrer" href="https://github.com/nvijaykarthik/FeatureControlCenter/wiki/CRON">click for Details</a>)</Form.Label>
          <FormControl
            placeholder="Cron Configuration"
            onChange={(e) => this.cronChange(e)} value={this.state.cron}
            onBlur={()=>this.evaluateCron()}
          />
          <p style={{color:"#eb6404"}}>{this.state.cronDesc}</p>
          <Form.Label>Active</Form.Label>
          <Form.Select onChange={(e) => this.scheduleStatusChange(e)} value={this.state.scheduleStatus}>
            <option></option>
            <option value="Y">Y</option>
            <option value="N">N</option>
          </Form.Select>
        </div>
        )
      }
    }

    return (
      <div>
        <Card className="border-0">
          <Card.Body>
          {this.state.showSpinner ? <Spinner />:<div></div>}
            <Card.Title><h5>Action Configuration</h5></Card.Title>
            <Form.Label>Action Type</Form.Label>
            <Form.Select onChange={(e) => this.actionChange(e)} value={this.state.actionType}>
              <option value="">Select action...</option>
              <option value="ALWAYS">Always</option>
              <option value="PERIOD">Period</option>
              <option value="SCHEDULED">Scheduled</option>
            </Form.Select>

            {content()}
            <br />
            <Button variant="success" type="button" onClick={() => this.saveAction()}>
              Save
            </Button>
          </Card.Body>
        </Card>


      </div>
    )
  }

}