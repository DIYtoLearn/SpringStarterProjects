import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import ChromeDinoGame from 'react-chrome-dino';

export default function Home() {

    const [users, setUsers] = useState([]);
    const { id } = useParams();

    
    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const result = await axios.get("/UserData");
        setUsers(result.data);
    };

    const deleteuser = async (id) => {
        await axios.delete(`/Delete/${id}`);
        loadUsers();
    };

  return (
    <div style={{ backgroundColor: '#d3dcb5', minHeight: '100vh' }} className="pb-5">
        {/* 1. Full-screen background wrapper with modern light-slate color */}
        <div className='container pt-4'>
            
            <div className='py-4'>
                {/* 2. Wrapped the table in a white "card" to make it pop off the background */}
                <div className="bg-white p-4 rounded shadow-sm border">
                    <table className="table table-hover mb-0">
                      <thead className="table-light">
                        <tr>
                          <th scope="col">ID</th>
                          <th scope="col">Email</th>
                          <th scope="col">UserName</th>
                          <th scope="col">Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {
                            users.map((user, index) => (
                        <tr key={index}>
                          <th scope="row">{index + 1}</th>
                          <td>{user.emailAddress}</td>
                          <td>{user.userName}</td>
                          <td>
                            <Link className='btn btn-primary mx-2' to={`/edituser/${user.id}`}>
                                Edit
                            </Link>
                            <button className='btn btn-danger mx-2' onClick={() => deleteuser(user.id)}>
                                Delete
                            </button>
                          </td>
                        </tr>
                            ))
                        }
                      </tbody>
                    </table>
                </div>
            </div>

          {/* 3. The Dino Game section, also given a pure white background */}
          <div className="d-flex flex-column align-items-center justify-content-center mt-4 p-4 border rounded shadow-sm bg-white">
            <h5 className="text-muted mb-3">Take a break from the database! Press Space to start.</h5>
            
            <div style={{ maxWidth: '600px', width: '100%' }}>
                <ChromeDinoGame />
            </div>
          </div>
          
        </div>
    </div>
  )
}