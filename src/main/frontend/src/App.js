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

  const deleteTask = (id) => {
    const formData = new FormData();
    formData.append("id", id);
    axios.delete(
      `http://localhost:8080/api/v1/tasks/delete/${id}`,
      {
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
        }
      },
      formData
      )
  }

  useEffect(() => {
    fetchTasks();
  }, []);

  return tasks.map((task, index) => {
    return (
      <div key={index}>
        <h1>{task.id}</h1>
        <p>{task.text}</p>
        <button>Delete</button>
      </div>
    )
  })
};

const CreateTask = () => {

  const [text, setText] = useState('')

  const createTask = () => {
    const formData = new FormData();
    formData.append("text", text);

    axios.post(
      `http://localhost:8080/api/v1/tasks/create/${text}`,
      formData,
      {
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
        }
      }
    )
  }

  return (
    <form>
      <label>
        Task name:
        <input onChange={event => setText(event.target.value)} type="text" name="text" />
      </label>
      <input type="submit" value="Submit" onClick={createTask}/>
    </form>
  );
}

function App() {
  return (
    <div className="App">
      <CreateTask/>
      <Tasks/>
    </div>
  );
}

export default App;

