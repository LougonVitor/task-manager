import { useState } from 'react';
import './style.css'
import { useLoginAuth } from '../../hook/useLoginAuth';
import { useNavigate } from 'react-router-dom';

interface LoginModalProps {
    isCreateView: boolean;
}

export function LoginModal({ isCreateView }: LoginModalProps) {
    const {mutate, isPending, isError, error} = useLoginAuth();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const userNavigate = useNavigate();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        if(!isCreateView) {
            mutate({username, password}, {
                onSuccess: (data) => {
                    console.log('Login successful:', data.token);
                    // Handle successful login, e.g., store token, redirect, etc.
                    localStorage.setItem('token', data.token);
                    userNavigate('/home');
                },
                onError: (error) => {
                    console.error('Login failed:', error.message);
                    // Handle login failure, e.g., show error message to user
                }
            })
        }
    }

    return (
        <>
        <div className='input-box'>
        <h1>{isCreateView ? 'Create Account' : 'Login'}</h1>
        <p>Let's get started with your tasks</p>
        <form action="" method="post" onSubmit={handleSubmit}>
            <input
                type="text"
                className='login-field' 
                placeholder='User'
                onChange={e => setUsername(e.target.value)}
                value={username}
                required
            />
            {isCreateView ?
            <input
                type="email"
                className='login-field' 
                placeholder='Email'
                onChange={e => setEmail(e.target.value)}
                value={email}
                required
            />
            :
            <></>
            }
            <input
                type="password"
                className='login-field'
                placeholder='Password'
                onChange={e => setPassword(e.target.value)}
                value={password}
                required
            />
            {isCreateView ?
            <input
                type="password"
                className='login-field'
                placeholder='Confirm Password'
                onChange={e => setPassword(e.target.value)}
                value={password}
                required
            />
            :
            <></>
            }
            <button type="submit" className='create-account-button login-field'>
                {isCreateView ? 'Create Account' : (isPending ? 'Logging in...' : 'Login')}
            </button>
            {isError && <p style={{ color: 'red' }}>{error.message}</p>}
        </form>
        </div>
        </>
    )
}