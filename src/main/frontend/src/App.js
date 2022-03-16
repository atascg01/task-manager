import React, {useState, useEffect} from 'react';
import './App.css';
import axios from 'axios';
import TaskItem from './components/TaskItem';

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

  const [tasks, setTasks] = useState(null);

  useEffect(() => {
    if(!tasks){
      axios.get("http://localhost:8080/api/v1/tasks/all").then(res =>{
        setTasks(res.data);
      })
    }
    
  }, [tasks]);

  function handleDeleteTaskItem (item){
    const taskItems = tasks.filter(task => task.id !== item.id)
    setTasks([...taskItems])
  }

  return (
    <div>
      <CreateTask/>
      {tasks 
        ? tasks.map((task) => {
          return <TaskItem key={task.id} data={task} deleteTaskItem={handleDeleteTaskItem}/>}) 
        : "Loading data..."}
    </div>
    )
}

export default App;

