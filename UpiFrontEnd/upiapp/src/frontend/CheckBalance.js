import { useState } from 'react';
import axios from 'axios';

function CheckBalance() { // CheckBalance functional component

  // React state hook 
  // phoneNumber holds the value amd setPhoneNumber is used to update the value.
  const [phoneNumber, setPhoneNumber] = useState('');
  // balance holds the value amd setBalance is used to update the value.
  const [balance, setBalance] = useState(null);

  // Event handler, when the button clicked this will call.
  const checkBalance = () => {
      // axios for spring boot api - Endpoint
    // Second parameter for request body, so there is no request body 
    // Params - axios convert this into query string
    // And spring boot controller will call.
    axios.get('http://localhost:8080/upitransaction/balance',null, { params: { phoneNumber } })
      .then(response => { // for exception handling 
        setBalance(response.data);
      })
      .catch(error => {
        console.error(error);
        alert('Error fetching balance');
      });
  };

  return (
    <div>
      <h2>Check Balance</h2>
      <input  // Input text box
        type="text"
        placeholder="Phone Number"
        value={phoneNumber} // the entered value store here
        onChange={(e) => setPhoneNumber(e.target.value)}  // through frontend value will update here.
      />
      {/* By clicking this button, checkBalance event handler will trigger. */}
      <button onClick={checkBalance}>Check Balance</button>

      {/* The balance will update in the frontend */}
      {balance !== null && <p>Balance: ₹{balance}</p>}
    </div>
  );
}

export default CheckBalance; // for importing this into another file