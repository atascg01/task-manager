import React, {useState} from 'react';

const TaskForm = (props) => {

    const {addTaskItem} = props;
    const [text, setText] = useState('')
  
    const createTask = () => {
 
      fetch(`http://localhost:8080/api/v1/tasks/create/${text}`,
        {
            method : "POST",
            body: JSON.stringify(text),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        }
      ).then(response => response.json)
      .then(() => {
          addTaskItem()
      })
    }
  
    return (
      <form>
        <label>
          Task name:
          <input onChange={event => setText(event.target.value)} type="text" name="text" />
        </label>
        <input type="submit" value="Add" onClick={createTask}/>
      </form>
    );
};

export default TaskForm;
