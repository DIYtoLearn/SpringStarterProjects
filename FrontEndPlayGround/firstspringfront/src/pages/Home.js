import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function Home() {

    const[users,setUsers]=useState([])
    const {id}=useParams
    
    useEffect(()=>{
        console.log("First Spring Loading ..");
        loadUsers();
    },[])

    const loadUsers=async()=>{
        const result=await axios.get("http://localhost:8080/v1/UserData")
        setUsers(result.data);
    }

    const deleteuser= async (id)=>{
        await axios.delete(`http://localhost:8080/v1/Delete/${id}`)
        loadUsers()
    }


  return (
    <div className='container'>
        <div className='py-4'>
            <table className="table border shadow">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Email</th>
      <th scope="col">UserName</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    {
        users.map((users,index)=>(
    <tr>
      <th scope="row" key={index}>{index+1}</th>
      <td>{users.emailAddress}</td>
      <td>{users.userName}</td>
      <td>
        <Link className='btn btn-primary mx-2'
        to={`/edituser/${users.id}`}>
            Edit
        </Link>
        <button className='btn btn-danger mx-2'
        onClick={()=>deleteuser(users.id)}
        >Delete</button>
      </td>
    </tr>
        ))
    }
  </tbody>
</table>

        </div>
    </div>
  )
}