class Main extends React.Component {
	constructor(props){
		super(props);
		this.state = this.props
	}
	render(){
		console.log(this.state)
		return (
			<div className="container">
			<div className="row">
			<div className="col xs3">
			<BacklogList backlog={this.state.stories}/>
			</div>          
			</div>
			</div>
		);
	}
}

//<nav>{UserInfo(this.state.user[0])}</nav>

const UserInfo = (props) => {
	return(
		<div>
		<h1>{props.userName}</h1>
		<p>{props.email}</p>
		</div>
	)
}

class BacklogList extends React.Component {
	constructor(props){
		super(props);
		this.state = this.props;
	}

	render() {
		return (<div>{listItems(this.state.backlog)}</div>);
	}
}


const listItems = (props) => {
	return(
		props.map((item, index) =>
		<li key={index}>
		<h3>{item.title}</h3>
		<p>{item.userStory}</p>
		<p>{item.actionCriteria}</p>
		<p>{item.points}</p>
		<p>{item.iteration}</p>
		</li>
		)
	);
}


fetch("/api/login", {method:"PUT",
	headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json'},
	body: "email=ishmaru@123abc.com&password=123abc"
})
.then(function(response) {
	return response.json();
})
.then(function(myJson) {
	let user = myJson;
	let stories = myJson;
	ReactDOM.render(<Main user={user} stories={stories}/>, document.getElementById('root'));
});
