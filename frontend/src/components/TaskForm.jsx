import React, {useState} from 'react';
import Config from "../config.json";
const TaskForm = (props) => {

    const {addTaskItem} = props;
    const [text, setText] = useState('')
  
    const createTask = () => {
      const baseUrl = Config.BASE_URL;
      fetch(`http://${baseUrl}:8080/api/v1/tasks/create/${text}`,
        {
            method : "POST",
            body: JSON.stringify(text),
            headers: {
                "Content-Type": "application/json"
            }
        }
      ).then(response => response.json())
      .then((data) => addTaskItem(data))
    }
  
    return (
      <>
        <input id="addTaskText" style={{marginBottom: "1rem", fontSize:"25px", width: "70%", height: "50px"}} onChange={event => setText(event.target.value)} type="text" name="text" placeholder='Task name'/>
        <button id="addTaskButton" style={{marginLeft: "1rem", fontSize:"25px", width: "10%", height: "60px"}} onClick={createTask}>Add</button>
      </>
    );
};

export default TaskForm;
