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
    console.log('id ', id)
    const result = await axios.get(`http://localhost:8081/api/employees/${id}`);
    setUser(result.data);
  };

  const loadAsset = async () => {
    const result1 = await axios.get(`http://localhost:8081/api/assets/${id}`);
    console.log('result 1 :: ', result1);
    setAssets(result1.data);
  };

  return (
    <div className="container">
      <div className="py-4">
        <strong><u>Employee</u></strong>
        <table className="table border shadow">
          <thead>
            <tr>
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

        <strong><u>Assets</u></strong>
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.No</th>
              <th scope="col">AssetName</th>
              <th scope="col">AssetId</th>
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
              </tr>
            ))}

          </tbody>
        </table>
      </div>
    </div>
  );
}