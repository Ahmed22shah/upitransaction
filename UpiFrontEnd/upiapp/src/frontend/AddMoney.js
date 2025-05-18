import { useState } from 'react';
import axios from 'axios';

function AddMoney() { // AddMoney functional component
    // React state hook 
    // phoneNumber holds the value amd setPhoneNumber is used to update the value.
  const [phoneNumber, setPhoneNumber] = useState(''); 
  // amount holds the value amd setAmount is used to update the value.
  const [amount, setAmount] = useState('');

  // Event handler, when the button clicked this will call.
  const addMoney = () => {
    // axios for spring boot api - Endpoint
    // Seconf parameter for request body, so there is no request body 
    // Params - axios convert this into query string
    // And spring boot controller will call.
    axios.post('http://localhost:8080/upitransaction/add-money', null, { params: { phoneNumber, amount } })
      .then(response => { // for exception handling 
        alert(response.data);
      })
      .catch(error => {
        console.error(error);
        alert('Error adding money');
      });
  };

  return (
    <div>
      <h2>Add Money</h2>
      <input // Input text box
        type="text"
        placeholder="Phone Number"
        value={phoneNumber} // the entered value store here
        onChange={(e) => setPhoneNumber(e.target.value)} // through frontend value will update here.
      />
      <input // Input text box
        type="number" 
        placeholder="Amount"
        value={amount} // the entered value store here
        onChange={(e) => setAmount(e.target.value)} // through frontend value will update here.
      />
      {/* By clicking this button, addMoney event handler will trigger. */}
      <button onClick={addMoney}>Add Money</button> 
    </div>
  );
}

export default AddMoney; // for importing this into another file
