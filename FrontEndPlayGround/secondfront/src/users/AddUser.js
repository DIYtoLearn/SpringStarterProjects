import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddUser() {

    let navigation=useNavigate()

  const [user, setUser] = useState({
    userName: "",
    emailAddress: ""
  });

  const { userName, emailAddress } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  // This function intercepts the form submission
  const onSubmit = async (e) => {
    e.preventDefault(); // Prevents the default page reload
    
    // You will put your axios.post() request here soon!
    console.log("Form data ready to be sent to Spring Boot:", user);
    await axios.post("http://localhost:8080/v1/AddUser", user)
    navigation("/")
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h4 className="text-center m-2">Add user data to Central DB</h4>
          
          {/* We wrap the inputs and buttons inside the form element */}
          <form onSubmit={onSubmit}>
            
            <div className='mb-3'>
              <label htmlFor='Name' className='form-label'>
                Name
              </label>
              <input
                type={"text"}
                className='form-control'
                placeholder='Enter Name'
                name='userName'
                value={userName}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <div className='mb-3'>
              <label htmlFor='E-mail' className='form-label'>
                Email Address
              </label>
              <input
                type={"text"}
                className='form-control'
                placeholder='Enter Email Address'
                name='emailAddress'
                value={emailAddress}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <button type="submit" className='btn btn-outline-primary'>Submit</button>
            
            {/* The button is replaced with a Link component for routing */}
            <Link className='btn btn-outline-danger mx-2' to="/">Go Back</Link>
            
          </form>
          
        </div>
      </div>
    </div>
  );
}