import { useQuery } from '@tanstack/react-query';
import axios, { type AxiosPromise } from 'axios';
import type { Task } from '../interface/task';

const API_URL = 'http://localhost:8080/task';

const fetchTasks = async(): AxiosPromise<Task[]> => {
    const response = axios.get(API_URL);
    return response;
}

export const useTaskData = () => {
    const query = useQuery({
        queryFn: fetchTasks,
        queryKey: ['task-data'],
        retry: 2
    });

    return {
        ...query,
        data: query.data?.data
    }
}