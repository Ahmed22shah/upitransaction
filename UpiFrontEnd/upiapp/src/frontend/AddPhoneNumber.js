import axios from "axios";
import { useState } from "react";

function AddPhoneNumber(){ // AddPhoneNumber functional component

     // React state hook 
    // phoneNumber holds the value amd setPhoneNumber is used to update the value.
    const [phoneNumber, setPhoneNumber] = useState('');

     // Event handler, when the button clicked this will call.
    const addPhoneNumber = () => {
         // axios for spring boot api - Endpoint
    // Second parameter for request body, so there is no request body 
    // Params - axios convert this into query string
    // And spring boot controller will call.
        axios.post('http://localhost:8080/upitransaction/addPhoneNumber',null, { params : {phoneNumber}})
            .then(response => {  // for exception handling 
                alert(response.data);
            })
            .catch(error =>{
                console.error(error);
                alert("Not added!.")
                
            })
    }

    return(
        <div>
            <h2>Add Phone Number</h2>
            <input type="text"  // Input text box
               placeholder="phoneNumber" 
               value={phoneNumber} // the entered value store here
               // through frontend value will update here.
               onChange={(e) => setPhoneNumber(e.target.value)}>  
            
            </input>
            {/* By clicking this button, addPhoneNumber event handler will trigger. */}
            <button onClick={addPhoneNumber}>Add Phone Number</button>
        </div>
    );
}

export default AddPhoneNumber; // for importing this into another file