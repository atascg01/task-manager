import React, {useState, useEffect} from 'react';
import './App.css';
import TaskItem from './components/TaskItem';
import TaskForm from './components/TaskForm';

function App() {

  const [tasks, setTasks] = useState(null);

  useEffect(() => {
    if(!tasks){
      fetch("http://localhost:8080/api/v1/tasks/all")
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
    <div>
      <TaskForm addTaskItem={handleAddTaskItem}/>
      {tasks 
        ? tasks.map((task) => {
          return <TaskItem key={task.id} data={task} deleteTaskItem={handleDeleteTaskItem}/>}) 
        : "Loading data..."}
    </div>
    )
}

export default App;

