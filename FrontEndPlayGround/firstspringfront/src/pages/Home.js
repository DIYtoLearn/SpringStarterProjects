import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function Home() {

    const[users,setUsers]=useState([])
    
    useEffect(()=>{
        console.log("First Spring Loading ..");
        loadUsers();
    },[])

    const loadUsers=async()=>{
        const result=await axios.get("http://localhost:8080/v1/UserData")
        setUsers(result.data);
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
    </tr>
  </thead>
  <tbody>
    {
        users.map((users,index)=>(
    <tr>
      <th scope="row" key={index}>{index+1}</th>
      <td>{users.emailAddress}</td>
      <td>{users.userName}</td>
    </tr>
        ))
    }
  </tbody>
</table>

        </div>
    </div>
  )
}
