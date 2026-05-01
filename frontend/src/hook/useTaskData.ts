import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import type { Task } from '../interface/task';

const API_URL = 'http://localhost:8080/task';

const fetchTasks = async (userId: Number): Promise<Task[]> => {
    const response = await axios.get<Task[]>(API_URL + `/${userId}`);
    return response.data;
}

export const useTaskData = (userId: Number) => {
    return useQuery({
        queryFn: () => fetchTasks(userId),
        queryKey: ['task-data', userId],
        retry: 2
    });
}