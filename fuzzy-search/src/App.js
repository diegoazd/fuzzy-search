import React, { Component } from 'react';

class App extends Component {
  
  constructor(props){
	super(props);
	this.state = {value: ''};
	
	this.handleChange = this.handleChange.bind(this);
	this.handleKeyPress = this.handleKeyPress.bind(this);
	//this.fetchData = this.fetchData.bind(this);
  }

  fetchData(data) {
	var url = 'http://localhost:8080/search';
	if(data !== '') {
		url+='/'+data+'/transaction'
	}
	fetch(url) 
	  .then(result => {
		result.json().then(data => {
			console.log(data);
		})
	});
  }

  handleChange(event) {
    console.log(event.target.value);
	this.setState({value: event.target.value});
	this.fetchData(event.target.value);

  }

  handleKeyPress(event) {
	if(event.key === 'Enter'){
     this.fetchData('');
	}
  }

  

  render() {
    return (
      <div>
        <h1>Fuzzy search</h1>
        <div className="content">
          <h2>Search:</h2>
          <input type="text" value={this.state.value} onKeyPress={this.handleKeyPress} onChange={this.handleChange} />
        </div>
      </div>
    );
  }
}

export default App;
