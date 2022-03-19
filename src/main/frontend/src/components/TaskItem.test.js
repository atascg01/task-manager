import React from "react";
import '@testing-library/jest-dom/extend-expect'
import { render } from "@testing-library/react";
import TaskItem from "./TaskItem";

test('renders one task', () => {

    const task = {
        id : 1,
        text : "Example text",
        completed : false
    }
    const component = render(<TaskItem key="1" data={task} />)

    component.getByText('Example text')
    component.getByText('🗑️')
})

test('renders two tasks', () => {

    const firstTaskJson = {
            id : 1,
            text : "New task 1",
            completed : false
    }

    const secondTaskJson = {
        id : 2,
        text : "New task 2",
        completed : false
    }
    
    const firstTaskComponent = render(<TaskItem key="1" data={firstTaskJson} />)
    const secondTaskComponent = render(<TaskItem key="2" data={secondTaskJson} />)

    firstTaskComponent.getByText('New task 1')
    secondTaskComponent.getByText('New task 2')
})