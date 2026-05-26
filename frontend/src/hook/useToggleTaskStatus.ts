import axios from "axios";

const API_URL = 'http://localhost:8080/task';

export const toggleTaskStatus = async (id: number) => {
    try {
        await axios.put(`${API_URL}/${id}/status`,);
    } catch (error) {
        throw new Error("Failed to update task status!");
    }
}