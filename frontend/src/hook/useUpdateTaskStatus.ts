const API_URL = 'http://localhost:8080/task';

export const updateTaskStatus = async (id: number) => {
    const response = await fetch(`${API_URL}/${id}/status`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if(!response.ok) throw new Error("Failed to update task status!");
}