import React, {useState, useEffect} from 'react';
import './App.css';
import TaskItem from './components/TaskItem';
import TaskForm from './components/TaskForm';
import Config from "./config.json";

function App() {

  const [tasks, setTasks] = useState(null);
  const baseUrl = Config.BASE_URL;
  useEffect(() => {
    if(!tasks){
      fetch(`http://${baseUrl}:8080/api/v1/tasks/all`)
      .then(response => response.json())
      .then(data => setTasks(data))
    }
    
  }, [tasks]);

  function handleAddTaskItem (taskItem){
    setTasks([...tasks, taskItem])
  }

  function handleDeleteTaskItem (taskItem){
    const taskItems = tasks.filter(task => task.id !== taskItem.id)
    setTasks([...taskItems])
  }

  return (
    <div id="appDiv">
      <div class="header">
        <h1>Task Manager</h1>
      </div>
      <div id="app">
        <TaskForm addTaskItem={handleAddTaskItem}/>
        {tasks 
          ? tasks.map((task) => {
            return <TaskItem key={task.id} data={task} deleteTaskItem={handleDeleteTaskItem}/>}) 
          : "Loading data..."}
      </div>
      
    </div>
    )
}

export default App;

