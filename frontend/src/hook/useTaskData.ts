import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import type { Task } from '../interface/task';

const API_URL = 'http://localhost:8080/task';

const fetchTasks = async (): Promise<Task[]> => {
    const response = await axios.get<Task[]>(API_URL);
    return response.data;
}

export const useTaskData = () => {
    return useQuery({
        queryFn: fetchTasks,
        queryKey: ['task-data'],
        retry: 2
    });
}