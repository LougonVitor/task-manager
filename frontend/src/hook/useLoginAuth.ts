import { useMutation } from '@tanstack/react-query';

interface LoginCredentials {
    username: string;
    password: string;
}

interface LoginResponse {
    token: string;
}

const API_URL = 'http://localhost:8080/auth/login';

export const useLoginAuth = () => {
    return useMutation<LoginResponse, Error, LoginCredentials>({
        mutationFn: async (credentials: LoginCredentials) => {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(credentials),
            });
            if (!response.ok) {
                throw new Error('Login failed');
            }
            return response.json();
        },
    });
};