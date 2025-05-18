import { useState } from 'react';
import axios from 'axios';

function TransferMoney() { // TransferMoney functional component

  // React state hook 
  // senderPhone holds the value amd setSenderPhone is used to update the value.
  const [senderPhone, setSenderPhone] = useState('');
  // receiverPhone holds the value amd setReceiverPhone is used to update the value.
  const [receiverPhone, setReceiverPhone] = useState('');
  // amount holds the value amd setAmount is used to update the value.
  const [amount, setAmount] = useState('');

  // Event handler, when the button clicked this will call.
  const transferMoney = () => {
    // axios for spring boot api - Endpoint
    // Second parameter for request body, so there is no request body 
    // Params - axios convert this into query string
    // And spring boot controller will call.
    axios.post('http://localhost:8080/upitransaction/transfer', null, {
      params: { senderPhone, receiverPhone, amount }
    })
      .then(response => {  // for exception handling 
        alert(response.data);
      })
      .catch(error => {
        console.error(error);
        alert('Error transferring money');
      });
  };

  return (
    <div>
      <h2>Transfer Money</h2>
      <input // Input text box
        type="text"
        placeholder="Sender Phone Number"
        value={senderPhone} // the entered value store here
        onChange={(e) => setSenderPhone(e.target.value)} // through frontend value will update here.
      />
      <input // Input text box
        type="text"
        placeholder="Receiver Phone Number"
        value={receiverPhone} // the entered value store here
        onChange={(e) => setReceiverPhone(e.target.value)} // through frontend value will update here.
      />
      <input // Input text box
        type="number"
        placeholder="Amount"
        value={amount} // the entered value store here
        onChange={(e) => setAmount(e.target.value)} // through frontend value will update here.
      />
      {/* By clicking this button, transferMoney event handler will trigger. */}
      <button onClick={transferMoney}>Transfer</button>
    </div>
  );
}

export default TransferMoney;  // for importing this into another file
