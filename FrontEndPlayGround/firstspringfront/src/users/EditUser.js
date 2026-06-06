import axios from 'axios';
import React, { useState, useEffect } from 'react'; // FIX 1: Imported useEffect
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditUser() {

  let navigate = useNavigate(); // Standard convention is to call this 'navigate'

  const { id } = useParams();

  const [user, setUser] = useState({
    userName: "",
    emailAddress: ""
  });

  const { userName, emailAddress } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  // FIX 2: Added the empty dependency array [] to prevent infinite loops
  useEffect(() => {
    loadUsers();
  }, []); 

  const onSubmit = async (e) => {
    e.preventDefault(); 
    
    console.log("Form data ready to be sent to Spring Boot:", user);
    await axios.put(`http://localhost:8080/v1/EditData/${id}`, user);
    navigate("/");
  };

  // FIX 3: Corrected the async arrow function syntax
const loadUsers = async () => {
    // Change this URL to your backend's GET endpoint for a single user.
    const result = await axios.get(`http://localhost:8080/v1/UserData/${id}`);
    setUser(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h4 className="text-center m-2">Edit User</h4>
          
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