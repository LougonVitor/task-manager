import { useMutation, useQueryClient } from "@tanstack/react-query";
import axios from "axios";

const API_URL = 'http://localhost:8080/task';

const deleteData = async (id?: number) => {
    const response = await axios.delete(API_URL + `/${id}`);
    return response;
}

export const useDeleteTask = () => {
    const queryClient = useQueryClient();

    const mutateDelete = useMutation({
        mutationFn: deleteData,
        retry: 1,
        onSuccess: () => {
            console.log('Task deleted successfully');
            queryClient.invalidateQueries({queryKey: ['task-data']});
        },
        onError: (error) => {
            console.error(`Failed to delete task: ${error}`)
        }
    });

    return mutateDelete;
}