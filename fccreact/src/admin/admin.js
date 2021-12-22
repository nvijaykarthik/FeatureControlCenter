import React, { Component } from "react"
import { Button, Card, Col, Form, Row } from "react-bootstrap"

export default class Admin extends Component {
    render() {
        return (
            <div>
                <h2>Admin</h2>
                <Row>
                    <Col md={3}>
                <Card className="border-1">
                    
                    <Card.Body>
                        <Card.Title> <h4>Configure the Action types</h4></Card.Title>
                    <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Always" />
                    <Form.Check type="checkbox" label="Period" />
                    <Form.Check type="checkbox" label="Scheduled" />
                    <Form.Check type="checkbox" label="On Demand Priority" />
                    <Form.Check type="checkbox" label="On Demand Traffic" />
                </Form.Group>
                    </Card.Body>
                    <Card.Footer>
                    <Button variant="primary" type="submit">
                        Save
                    </Button>
                    </Card.Footer>      
                    </Card>
               </Col>
               </Row>
               
            </div>
        )
    }
}