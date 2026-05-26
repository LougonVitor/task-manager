import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';

interface TaskRequest {
    title?: string;
    description?: string;
    status?: string;
    deadline?: string;
}

interface TaskResponse {
    id: number;
    title: string;
    createdAt: string;
}

const API_URL = 'http://localhost:8080/task/create';

const createTask = async (task: TaskRequest) =>  {
    try {
        const response = await axios.post(API_URL, task);
        return response.data;
    } catch(error) {
        throw new Error("Failed to create task!");
    }
}

export const useCreateTask = () => {
    const queryClient = useQueryClient();

    return useMutation<TaskResponse, Error, TaskRequest>({
        mutationFn: createTask,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['task-data']});
        }
    });
}