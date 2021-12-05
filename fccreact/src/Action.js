import React, { Component, useState } from 'react';
import { Card, Col, Row, Button, InputGroup, FormControl, Table, Form, Alert, Modal } from 'react-bootstrap';
import axios from "axios";



export default class Action extends Component {

  state = {
    featureName: "",
    show: false,
    actionType: ""
  }

  componentDidUpdate(prevProps) {
    if (this.props.selectedFeatureName !== prevProps.selectedFeatureName) {
      let fname = "";
      if (this.props.selectedFeatureName !== "NEW") {
        fname = this.props.selectedFeatureName;
      }
      this.setState({
        featureName: fname,
        show: false
      })
    }
  }
  actionChange(e) {
    this.setState({
      actionType: e.target.value
    })
  }
  render() {

    let content = () => {
      if (this.state.actionType === "ALWAYS") {
        return (
          <div>
            <Form.Label>Active</Form.Label>
            <Form.Select>
              <option>Y</option>
              <option>N</option>
            </Form.Select>
          </div>
        )
      } else if (this.state.actionType === "PERIOD") {
        return (<div>
          <Form.Label>Start Date Time</Form.Label>
          <FormControl
            placeholder="Filter"
            type="datetime-local"
          />
          <Form.Label>End Date Time</Form.Label>
          <FormControl
            placeholder="Filter"
            type="datetime-local"
          />
          <Form.Label>Active</Form.Label>
          <Form.Select>
            <option>Y</option>
            <option>N</option>
          </Form.Select>
        </div>
        )
      } else if (this.state.actionType === "SCHEDULED") {
        return (<div>
          <Form.Label>Cron Schedule</Form.Label>
          <FormControl
            placeholder="Filter"
          />
          <Form.Label>Active</Form.Label>
          <Form.Select>
            <option>Y</option>
            <option>N</option>
          </Form.Select>
        </div>
        )
      }
    }

    return (
      <div>
        <Card className="border-0">
          <Card.Body>
            <Card.Title><h5>Action Configuration</h5></Card.Title>
            <Form.Label>Action Type</Form.Label>
            <Form.Select onChange={(e) => this.actionChange(e)}>
              <option value="">Select action...</option>
              <option value="ALWAYS">Always</option>
              <option value="PERIOD">Period</option>
              <option value="SCHEDULED">Scheduled</option>
            </Form.Select>

            {content()}
            <br />
            <Button variant="success" type="button">
              Save
            </Button>
          </Card.Body>
        </Card>

       
      </div>
    )
  }

}