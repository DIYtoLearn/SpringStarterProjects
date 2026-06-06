import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddUser() {

  let navigation = useNavigate();

  const [user, setUser] = useState({
    userName: "",
    emailAddress: ""
  });

  // 1. Add an error state to track validation failures
  const [error, setError] = useState("");

  const { userName, emailAddress } = user;

  const onInputChange = (e) => {
    // 2. Clear the error message as soon as the user starts typing again
    setError("");
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault(); 
    
    // 3. The Validation Check: Check if either field is empty or just whitespace
    if (!userName.trim() || !emailAddress.trim()) {
      setError("Fields cannot be empty. Please provide both a Name and an Email.");
      return; // This completely stops the function, preventing the axios request
    }

    console.log("Form data ready to be sent to Spring Boot:", user);
    await axios.post(`/AddUser`, user);
    navigation("/");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h4 className="text-center m-2">Add user data to Central DB</h4>
          
          {/* 4. Conditionally render a red Bootstrap alert if an error exists */}
          {error && (
            <div className="alert alert-danger" role="alert">
              {error}
            </div>
          )}

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
            <Link className='btn btn-outline-danger mx-2' to="/">Go Back</Link>
            
          </form>
          
        </div>
      </div>
    </div>
  );
}