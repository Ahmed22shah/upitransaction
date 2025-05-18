import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import EnableDisableUpi from './frontend/EnableDisableUpi';
import AddPhoneNumber from './frontend/AddPhoneNumber';
import CheckBalance from './frontend/CheckBalance';
import AddMoney from './frontend/AddMoney';
import TransferMoney from './frontend/TransferMoney';

function App() { // App function
  return (
    //It navigate another page without page reload.
    <Router> 
      {/* It renders the first matching route  */}
      <Routes>
        {/* Path - root URL adn it renders the element  */}
          <Route path="/" element={<EnableDisableUpi />} />
          {/* For all the below roots will navigate by there path and it renders the element  */}
          <Route path="addPhoneNumber" element={<AddPhoneNumber />}/>

          <Route path="checkBalance" element={<CheckBalance />}/>
        
           <Route path="addMoney" element={<AddMoney />}/>

           <Route path="transferMoney" element={<TransferMoney />}/>
      </Routes>
    </Router>
  );
}

export default App; // So it can be render in the index.js
