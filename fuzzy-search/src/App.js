import React, { Component } from 'react';

function Items(props) {
  const values = props.value;
  console.log(values);
  const items = values.map((value) =>
    <li key={value}>{value}</li>
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
  }
  
  handleChange(event) {
    this.setState({value: event.target.value});
    this.getSuggestions(event.target.value);
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
	return Promise.all([this.fetchData(data)]).then(([data]) => {
        let s = data.length === 0 ? [] : data.map(item => item.amount+ ' '+ item.cardLastFour);
		console.log(s);
        this.setState({suggestions: s});
	});
  }

  onSuggestionsFetchRequested = ({ value }) => {
	this.suggestions = this.getSuggestions(value);
  };

  render() {
	const { value, suggestions } = this.state;

	// Autosuggest will pass through all these props to the input.
	const inputProps = {
	  placeholder: 'Type something to search',
	  value,
	  onChange: this.onChange
	};

    return (
      <div>
        <h1>Fuzzy search</h1>
        <div className="content">
            <h2>Search:</h2>
			<input type="text" onChange={this.handleChange} className="search-input"/>
            <div className="autocomplete">
				<Items value={this.state.suggestions}/>
            </div>
        </div>
      </div>
    );
  }
}

export default App;
