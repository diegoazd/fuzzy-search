import React, { Component } from 'react';

function Items(props) {
  const values = props.value;
  const items = values.map((value, index) =>
    <li key={index}>amount: <b>{value.amount}</b> card last four: <b>{value.cardLastFour}</b> date: <b>{value.dateTime}</b> </li>
  )	

  return(
	<ul>{items}</ul>
  )
}

class App extends Component {
  
  constructor(props){
	super();
	this.state = {value: '',
    suggestions: []};
    this.handleChange = this.handleChange.bind(this);
    this.handleKeyPress = this.handleKeyPress.bind(this);
  }
  
  handleChange(event) {
    this.setState({value: event.target.value});
    this.getSuggestions(event.target.value);
  }
  
  handleKeyPress(event){
	if(event.key === 'Enter') {
		this.getSuggestions(this.state.value);
    }
  }

  fetchData(data) {
	var url = 'http://localhost:8080/search';
	if(data !== '') {
		url+='/'+data+'/transaction'
	}
	var sugestions = [];
	return fetch(url) 
	  .then(response => response.json());
  }
  
  getSuggestions(data) {
	Promise.all([this.fetchData(data)]).then(([data]) => {
        this.setState({suggestions: data});
	});
  }

  onSuggestionsFetchRequested = ({ value }) => {
	this.suggestions = this.getSuggestions(value);
  };

  render() {
    return (
      <div>
        <h1>Fuzzy search</h1>
        <div className="content">
            <h2>Search:</h2>
			<input type="text" onChange={this.handleChange} onKeyPress={this.handleKeyPress} className="search-input"/>
            <div className="autocomplete">
				<Items value={this.state.suggestions}/>
            </div>
        </div>
      </div>
    );
  }
}

export default App;
