import React from "react";
import '@testing-library/jest-dom/extend-expect'
import { render } from "@testing-library/react";
import TaskForm from "./TaskForm";

test('renders content', () => {

    const component = render(<TaskForm/>)

    component.getByPlaceholderText('Task name')
    component.getByText('Add')
})
