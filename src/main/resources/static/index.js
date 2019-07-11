

class Main extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props
  }
  render(){
    return (
      <div>
        <div className="">
          <h5 className="" >Lets Add some Tasks:</h5>
          <button className="waves-effect waves-light btn" onClick={handleClick}>Add Task</button>
        </div>
        <div className="row">
          <div className="col m3 s6"><h5>Backlog</h5></div>
          <div className="col m3 s6"><h5>Sprint</h5></div>
          <div className="col m3 s6"><h5>Testing</h5></div>
          <div className="col m3 s6"><h5>Complete</h5></div>
        </div>
        <Form/>
        <div className="row">
          <div className="col m3 s6">
            <BacklogList backlog={this.state.stories}/>
          </div>
          <div className="col m3 s6">
            <SprintList sprint={this.state.stories}/>
          </div>
          <div className="col m3 s6">
            <TestingList testing={this.state.stories}/>
          </div>
          <div className="col m3 s6">
            <CompleteList complete={this.state.stories}/>
          </div>
        </div>
      </div>
    );
  }
}

const handleClick = (event) =>{
  document.getElementById("formfields").className = "container";
}

const Form = (props) => {
  return(
    <div id="formfields" className="hide">
      <div className="row">
        <form className="col s12" onSubmit={formSubmit}>
          <div className="row">
            <div className="input-field col s6">
              <input id="title" type="text" className="validate"/>
              <label htmlFor="title">Title</label>
            </div>
            <div className="input-field col s6">
              <input id="points" type="text" className="validate"/>
              <label htmlFor="points">Points</label>
            </div>
            <div className="input-field col s12">
              <input id="userStory" type="text" className="validate"/>
              <label htmlFor="userStory">User Story</label>
            </div>
            <div className="input-field col s12">
              <input id="actionCriteria" type="text" className="validate"/>
              <label htmlFor="actionCriteria">Acceptance Criteria</label>
            </div>
            <input type="submit" value="Submit"/>
          </div>
        </form>
      </div>
    </div>
  );
}

const formSubmit = (event) =>{
  event.preventDefault();
  let postdata = "title="+document.getElementById("title").value+"&userStory="+document.getElementById("userStory").value+"&actionCriteria="+document.getElementById("actionCriteria").value+ "&points="+ document.getElementById("points").value;
  fetch("/api/story/backlog", {method:"POST",
   headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json'},
   body: postdata
  }).then(function(response) {
   
  }).then(location.reload());
}

class BacklogList extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props;
  }

  render() {
    return (<div>{listItems(filterBy(this.state.backlog, "BACKLOG"))}</div>);
  }
}

class SprintList extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props;
  }

  render() {
    return (<div>{listItems(filterBy(this.state.sprint, "SPRINT"))}</div>);
  }
}

class TestingList extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props;
  }

  render() {
    return (<div>{listItems(filterBy(this.state.testing, "TESTING"))}</div>);
  }
}

class CompleteList extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props;
  }

  render() {
    return (<div>{listItems(filterBy(this.state.complete, "COMPLETE"))}</div>);
  }
}

const filterBy = (props, filter) => {
  return props.filter(item => item.sprintIteration === filter);
}

const changeIteration = (event) => {
  event.preventDefault();
  fetch("/api/story/"+ event.target.parentElement.parentElement.id + "/" + event.target.id, {method:"PUT",
   headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json'},
   body: ""
  }).then(function(response) {
   console.log(response);
  }).then(location.reload());
}

const dropdown = (event) => {
  let drop = event.target.nextSibling;
  console.log(drop);
  drop.classList.toggle("hide");
}

const listItems = (props) => {
  return(
    props.map((item, index) =>
      <div key={item.id}>
        <div className="card blue-grey darken-1">
          <div className="card-content white-text">
            <span className="card-title">{item.title}</span>
            <p>{item.userStory}</p>
            <p>{item.actionCriteria}</p>
            <p className="col s3">{item.points}</p>
            <div className="col s9 row">
              <button className="waves-effect waves-light btn col s12" onClick={dropdown}>Send To:</button>
              <ul id={item.id} className="drop hide">
                <li>
                  <button id="backlog" className="waves-effect waves-light btn col s12" onClick={changeIteration}>Backlog</button>
                </li>
                <li>
                  <button id="sprint" className="waves-effect waves-light btn col s12" onClick={changeIteration}>Sprint</button>
                </li>
                <li>
                  <button id="testing" className="waves-effect waves-light btn col s12" onClick={changeIteration}>Testing</button>
                </li>
                <li>
                  <button id="complete" className="waves-effect waves-light btn col s12" onClick={changeIteration}>Complete</button>
                </li>
                <li>
                  <button id="delete" className="waves-effect waves-light btn col s12" onClick={changeIteration}>Delete</button>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    )
  );
}

fetch("/api/story")
.then(function(response) {
  return response.json();
})
.then(function(myJson) {
  let user = myJson;
  let stories = myJson;
  ReactDOM.render(<Main stories={stories}/>, document.getElementById('root'));
});
