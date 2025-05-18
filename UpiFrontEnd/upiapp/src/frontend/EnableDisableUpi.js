import axios from "axios";
import { useState } from "react";

function EnableDisableUpi() { // EnableDisableUpi functional component

    // React state hook 
    // phoneNumber holds the value amd setPhoneNumber is used to update the value.
    const [phoneNumber, setPhoneNumber] =useState('');
    
    // Event handler, when the button clicked this will call.
    const enableUpi = () => {
        // axios for spring boot api - Endpoint
        // Second parameter for request body, so there is no request body 
        // Params - axios convert this into query string
        // And spring boot controller will call.
        axios.post('http://localhost:8080/upitransaction/enable', null, { params: { phoneNumber }})
            .then(response => { // for exception handling 
                alert(response.data);
            })
            .catch(error => {
                console.error(error);
                alert('Error enabling UPI');
            });
            
    }

     // Event handler, when the button clicked this will call.
     const disableUpi = () => {
    // axios for spring boot api - Endpoint
    // Second parameter for request body, so there is no request body 
    // Params - axios convert this into query string
    // And spring boot controller will call.
    axios.post('http://localhost:8080/upitransaction/disable', null, { params: { phoneNumber } })
      .then(response => { // for exception handling 
        alert(response.data);
      })
      .catch(error => {
        console.error(error);
        alert('Error disabling UPI');
      });
  };

  return (
    <div>
      <h2>Enable/Disable UPI</h2>
      <input // Input text box
        type="text"
        placeholder="Phone Number"
        value={phoneNumber} // the entered value store here
        onChange={(e) => setPhoneNumber(e.target.value)} // through frontend value will update here.
      />
       {/* By clicking this button, enableUpi event handler will trigger. */}
      <button onClick={enableUpi}>Enable UPI</button>

      {/* By clicking this button, disableUpi event handler will trigger. */}
      <button onClick={disableUpi}>Disable UPI</button>

    </div>
  );
}

export default EnableDisableUpi; // for importing this into another file