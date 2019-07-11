// document.addEventListener('DOMContentLoaded', function() {
//   var elems = document.querySelectorAll('.modal');
//   var instances = M.Modal.init(elems, options);
// });

class Main extends React.Component {
  constructor(props){
    super(props);
    this.state = this.props
  }
  render(){
    return (
      <div>
        <div className="row">
          <div className="col m3 s6"><h5>Backlog</h5><button id="backlog" className="waves-effect waves-light btn" onClick={handleClick}>+</button></div>
          <div className="col m3 s6"><h5>Sprint</h5><button id="sprint" className="waves-effect waves-light btn" onClick={handleClick}>+</button></div>
          <div className="col m3 s6"><h5>Testing</h5><button id="testing" className="waves-effect waves-light btn" onClick={handleClick}>+</button></div>
          <div className="col m3 s6"><h5>Complete</h5><button id="complete" className="waves-effect waves-light btn" onClick={handleClick}>+</button></div>
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
//<a class="waves-effect waves-light btn modal-trigger" href="#modal1">+</a>
// <button className="waves-effect waves-light btn" onClick={handleClick}>+</button>

const handleClick = (event) =>{
  console.log(event.target.id);
  // return form(event.target.id);
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
  // const data = new URLSearchParams();
  // for (const pair of new FormData(event.target)) {
  //     data.append(pair[0], pair[1]);
  // }
  // let data = new FormData();
  // // data.append("title", document.getElementById("title").value);
  // // data.append("userStory", document.getElementById("userStory").value);
  // // data.append("points", document.getElementById("points").value);
  // // data.append("actionCriteria", document.getElementById("actionCriteria").value);
  // data.append("title", "FUCKYOU");
  // data.append("userStory", "FUCKYOU");
  // data.append("points", "FUCKYOU");
  // data.append("actionCriteria", "FUCKYOU");
  // "title" document.getElementById("title").value
  // console.log(data);
  let postdata = "title="+document.getElementById("title").value+"&userStory="+document.getElementById("userStory").value+"&actionCriteria="+document.getElementById("actionCriteria").value+ "&points="+ document.getElementById("points").value;
  fetch("/api/story/backlog", {method:"POST",
   headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json'},
   body: postdata
  }).then(function(response) {
    console.log(response);
});

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
  console.log(props);
  console.log(filter);
  return props.filter(item => item.sprintIteration === filter);
}

const listItems = (props) => {
  return(
    props.map((item, index) =>
      <div key={index}>
        <div className="card blue-grey darken-1">
          <div className="card-content white-text">
            <span className="card-title">{item.title}</span>
            <p>{item.userStory}</p>
            <p>{item.actionCriteria}</p>
            <p>{item.points}</p>
            <p>{item.iteration}</p>
          </div>
        </div>
      </div>
    )
  );
}



//fetch("/api/login", {method:"PUT",
//  headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json'},
//  body: email=ishmaru@123abc.com&password=123abc
//})
fetch("/api/story")
.then(function(response) {
  return response.json();
})
.then(function(myJson) {
  let user = myJson;
  let stories = myJson;
  ReactDOM.render(<Main stories={stories}/>, document.getElementById('root'));
});
