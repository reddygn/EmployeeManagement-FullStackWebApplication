import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function AddAsset(event) {
  let navigate = useNavigate();

  const { id } = useParams();

  const aId = parseInt(id);

  const [asset, setAsset] = useState({
    employeeId: 0,
    assetName: "",
    assetId: ""
  });
  const {employeeId, assetName, assetId } = asset;

  const onInputChange = (e) => {
    setAsset({ ...asset, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadAsset();
  }, []);



  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8081/api/assets/${aId}`, asset);
    console.log('Successfully updated the asset');
    navigate(`/view-employee/${employeeId}`);
  };


  const loadAsset = async () => {
    const result = await axios.get(`http://localhost:8081/api/assets/${aId}`);
    setAsset(result.data);
  };


  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit the asset</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Assetname
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter Asset Name"
                name="assetName"
                value={assetName}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                assetId
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter assetId"
                name="assetId"
                value={assetId}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}