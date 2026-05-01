import { useMutation } from "@tanstack/react-query";

interface RegisterCredentials {
    username: string;
    email: string;
    password: string;
    role: string;
}

const API_URL = 'http://localhost:8080/auth/register';

export const useRegisterAuth = () => {
    return useMutation<String, Error, RegisterCredentials>({
        mutationFn: async(register: RegisterCredentials) => {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(register)
            })

            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.message || "User creation failed!");
            }

            return response.text();
        }
    });
}