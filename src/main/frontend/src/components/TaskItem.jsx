import React, { useState, useEffect } from 'react';

const TaskItem = (props) => {

    const {deleteTaskItem} = props;
    const [taskItem, setTaskItem] = useState(props.data);
    const [isModified, setModified] = useState(false)

    useEffect(() => {
        if(isModified){
            fetch(
                `http://localhost:8080/api/v1/tasks/update/${taskItem.id}`, {
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
        fetch(
            `http://localhost:8080/api/v1/tasks/delete/${taskItem.id}`, {
                method : "DELETE"
            }
        ).then((response) => response.json)
        .then((data) => {
            deleteTaskItem(taskItem);
        });
    }

    return (
        <div>
            <input type="checkbox" checked={taskItem.completed} onChange={updateTask}></input>
            <span>{taskItem.text}</span>
            <button onClick={deleteTask}>Delete</button>
        </div>
    );
};

export default TaskItem;