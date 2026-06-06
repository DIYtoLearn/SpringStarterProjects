import React, { useState } from 'react'

export default function AddUser() {

  const [user, setUser] = useState({
    userName: "",
    emailAddress: ""
  })

  const { userName, emailAddress } = user;

  // FIX 1: Dynamically update the state using the input's 'name' attribute
  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h4 className="text-center m-2">Add user data to Central DB</h4>
          
          <div className='mb-3'>
            <label htmlFor='Name' className='form-label'>
              Name
            </label>
            <input
              type={"text"}
              className='form-control'
              placeholder='Enter Name'
              name='userName' /* FIX 2: Matches state key 'userName' exactly */
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
              name='emailAddress' /* FIX 3: Matches state key 'emailAddress' exactly */
              value={emailAddress}
              onChange={(e) => onInputChange(e)}
            />
          </div>

          <button type="submit" className='btn btn-outline-primary'>Submit</button>
          <button type="button" className='btn btn-outline-danger mx-2'>Go Back</button>
          
        </div>
      </div>
    </div>
  );
}