import React, {useState, useEffect} from 'react';
import './App.css';
import axios from 'axios';

const Tasks = () => {

  const [tasks, setTasks] = useState([]);

  const fetchTasks = () => {
    axios.get("http://localhost:8080/api/v1/tasks/all").then(res =>{
      setTasks(res.data);
    })
  }

  useEffect(() => {
    fetchTasks();
  }, []);

  return tasks.map((task, index) => {
    return (
      <div key={index}>
        <h1>{task.id}</h1>
        <p>{task.text}</p>
      </div>
    )
  })
};

function App() {
  return (
    <div className="App">
      <Tasks/>
    </div>
  );
}

export default App;
