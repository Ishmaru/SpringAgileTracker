class Main extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props
  }
  render(){
    return (
      <div className="container">
        <nav>{UserInfo(this.state.user[0])}</nav>
        <div className="row">
          <div className="col xs3">
            <BacklogList backlog={this.state.stories}/>
          </div>          
          <div className="col xs3">
            <SprintList backlog={this.state.stories}/>
          </div>          
          <div className="col xs3">
            <TestingList backlog={this.state.stories}/>
          </div>          
          <div className="col xs3">
            <CompleteList backlog={this.state.stories}/>
          </div>
        </div>
      </div>
    );
  }
}

const UserInfo = (props) => {
  console.log(props)
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
    this.state = {backlog:this.props}
  }
  render() {
      return (listItems(this.state.backlog));
  }
}

class SprintList extends React.Component {
  constructor(props){
    super(props);
    this.state = {backlog:this.props}
  }
  render() {
      return (listItems(this.state.backlog));
  }
}

class TestingList extends React.Component {
  constructor(props){
    super(props);
    this.state = {backlog:this.props}
  }
  render() {
      return (listItems(this.state.backlog));
  }
}

class CompleteList extends React.Component {
  constructor(props){
    super(props);
    this.state = {backlog:this.props}
  }
  render() {
      return (listItems(this.state.backlog));
  }
}

const listItems = (props) => {
  return(
  <p>{props}</p>
  );
}


//fetch("/api/login", {method:"POST", header: {'Content-Type': 'application/json'}, body: JSON.stringify({email:"ishmaru@123abc.com",password:"123abc"})})
fetch("/api/login", {method:"POST", header: {'Content-Type': 'application/x-www-form-urlencoded'}, body: ({id: null, userName: null, email:"ishmaru@123abc.com",password:"123abc"})})
//fetch("/api/user")
.then(function(response) {
  return response.json();
})
.then(function(myJson) {
  let user = myJson;
  ReactDOM.render(<Main user={user}/>, document.getElementById('root'));
});
