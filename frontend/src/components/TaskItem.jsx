import React, { useState, useEffect } from 'react';
import Config from "../config.json";

const TaskItem = (props) => {

    const {deleteTaskItem} = props;
    const [taskItem, setTaskItem] = useState(props.data);
    const [isModified, setModified] = useState(false)
    const baseUrl = Config.BASE_URL;

    useEffect(() => {
        if(isModified){
            fetch(`http://${baseUrl}:8080/api/v1/tasks/update/${taskItem.id}`,
            {
                method : "PUT",
                body: JSON.stringify(taskItem),
                headers: {
                    "content-type" : "application/json"
                }
            })
        }        
    }, [taskItem, isModified])

    function updateTask () {
        setModified(true);
        setTaskItem({...taskItem, completed : !taskItem.completed});
    }

    function deleteTask () {
        fetch(`http://${baseUrl}:8080/api/v1/tasks/delete/${taskItem.id}`,
        {
            method : "DELETE"
        }).then((response) => response.json)
        .then((data) => {
            deleteTaskItem(taskItem);
        });
    }

    return (
        <div>
            <input type="checkbox" style={{marginLeft: "1rem", width: "25px", height: "25px"}} checked={taskItem.completed} onChange={updateTask}></input>
            <span className={taskItem.completed ? 'completed' : ''} style={{marginLeft: "1rem"}}>{taskItem.text}</span>
            <button id="deleteButton" style={{marginLeft: "1rem"}} onClick={deleteTask}>🗑️</button>
        </div>
    );
};

export default TaskItem;
