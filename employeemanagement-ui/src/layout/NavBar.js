import React from 'react';
import {
  MDBContainer,
  MDBNavbar,
  MDBBtn,
  MDBInputGroup
} from 'mdb-react-ui-kit';
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <MDBNavbar dark bgColor='black'>
    <MDBContainer fluid>
      <MDBInputGroup tag="form" className='d-flex w-auto mb-3'>
          <div >
          <Link  style={{ color: "white", textAlign: "start", fontSize:"40px"}} to="/">
            Employees Management Application
          </Link>  
          <Link style={{ color: "white",textAlign: "start", fontSize:"10px", marginTop:"20px", marginBottom:"14px", marginLeft:"966px"}} className="btn btn-outline-light" to="/add-employee">
            Add Employee
          </Link>
          </div>
      </MDBInputGroup>
    </MDBContainer>
  </MDBNavbar>

  );
}