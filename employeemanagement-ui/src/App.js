import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/NavBar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddUser from "./Users/AddUser";
import EditUser from "./Users/EditUser";
import ViewUser from "./Users/ViewUser";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/add-employee/" element={<AddUser />} />
          <Route exact path="/view-employee/:id" element={<ViewUser />} />
          <Route exact path="/update-employee/:id" element={<EditUser />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;