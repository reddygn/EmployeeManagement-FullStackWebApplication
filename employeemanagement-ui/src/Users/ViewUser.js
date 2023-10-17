import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewUser() {
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });

  const [assets, setAssets] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadUser();
    loadAsset();
  }, []);

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8081/api/employees/${id}`);
    setUser(result.data);
  };

  const loadAsset = async () => {
    const result = await axios.get(`http://localhost:8081/api/assets/${id}/all`);
    setAssets(result.data);
  };
  const deleteAsset = async (id) => {
    await axios.delete(`http://localhost:8081/api/assets/${id}`);
    window.location.reload();
  };

  return (
    <div className="container">
      <div className="py-4">
        <strong><u>Employee</u></strong>
        <table className="table border shadow">
          <thead>
            <tr  >
              <th scope="col">FirstName</th>
              <th scope="col">LastName</th>
              <th scope="col">Email</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{user.firstName}</td>
              <td>{user.lastName}</td>
              <td>{user.emailId}</td>
            </tr>
          </tbody>
        </table>
        {/* <strong><u>Assets</u></strong> */}
        <Link style={{ color: "white", backgroundColor:"green", textAlign: "start", fontSize: "10px", marginTop: "20px", marginBottom: "14px", marginLeft: "966px" }} 
            className="btn btn-outline-light" to={`/add-asset/${id}`} >
              Add Asset
            </Link>        
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.No</th>
              <th scope="col">AssetName</th>
              <th scope="col">AssetId</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {assets.map((asset, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{asset.assetName}</td>
                <td>{asset.assetId}</td>
                <td>
                  <Link
                    className="btn btn-outline-primary mx-2" to={`/edit-asset/${asset.id}`}
                  >
                    Edit
                  </Link>
                  <Link
                    className="btn btn-danger mx-2"
                    onClick={() => deleteAsset(asset.id)}
                  >
                    Delete
                  </Link>
                </td>
              </tr>
            ))}

          </tbody>
        </table>
      </div>
    </div>
  );
}