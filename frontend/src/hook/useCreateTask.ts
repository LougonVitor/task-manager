import { useMutation, useQueryClient } from '@tanstack/react-query';

interface TaskRequest {
    title: string;
    description: string;
    status: string;
    deadline: string;
}

interface TaskResponse {
    id: number;
    title: string;
    createdAt: string;
}

const API_URL = 'http://localhost:8080/task/create';

export const useCreateTask = () => {
    const queryClient = useQueryClient();

    return useMutation<TaskResponse, Error, TaskRequest>({
        mutationFn: async(task: TaskRequest) => {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(task)
            });

            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.message || "Task creation failed!");
            }

            return response.json();
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['task-data']});
        }
    });
}