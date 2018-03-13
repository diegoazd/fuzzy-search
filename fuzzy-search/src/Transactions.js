import React, { Component } from 'react';

function Element(props) {
  return(
    <li>{props}</li>
  );
}

class Transactions extends Component {

  render() {
   console.log(this.props);
   let elements =this.props.value.length > 0 ? '' :  this.props.value.map( transaction =>
      (
       <div>
         <ul>
         </ul>
       </div>
      )    
    );
    return(
      {elements}
    );
  }

}

export default Transactions;
