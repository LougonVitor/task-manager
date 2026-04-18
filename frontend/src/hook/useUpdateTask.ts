import type { Task } from "../interface/task";
import type { TaskRequest } from "../interface/taskRequest";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import axios from "axios";

const API_URL = 'http://localhost:8080/task';

const updateTask = async (task: Task) => {
    const taskRequest : TaskRequest = {
        title: task.title,
        description: task.description,
        deadline: task.deadline.toISOString().slice(0, 10)
    };

    const response = await axios.put(API_URL + `/${task.id}`, taskRequest);

    return response;
};

export const  useUpdateTask = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: updateTask,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['task-data']});
        }
    });
}