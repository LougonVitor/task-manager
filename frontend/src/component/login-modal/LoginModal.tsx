import { useState } from 'react';
import './LoginModal.css'
import { useLoginAuth } from '../../hook/useLogin';
import { useNavigate } from 'react-router-dom';
import { useRegisterAuth } from '../../hook/useRegister';
import { ModalValidate } from '../validation-error-banner/ValidationErrorBanner';

interface LoginModalProps {
    isCreateView: boolean;
}

export function LoginModal({ isCreateView }: LoginModalProps) {
    const {mutate: mutateCreation, isPending, isError, error} = useLoginAuth();
    const {mutate: mutateRegister} = useRegisterAuth();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [validationErrors, setValidationErrors] = useState<string[]>([]);
    const userNavigate = useNavigate();

    const onCloseModal = () => {
        setValidationErrors([]);
    }

    const validateSubmit = (e: React.SubmitEvent) => {
        e.preventDefault();

        const currentErros = [];

        if(!username || username.trim() === "") currentErros.push("Username is required!")
        if(isCreateView && ( !email || email.trim() === "")) currentErros.push("Email is required!")
        if(!password || password.trim() === "") currentErros.push("Password is required!")
        if(isCreateView && ( !confirmPassword || confirmPassword.trim() === "")) currentErros.push("Confirm password is required!")
        if(isCreateView && ( password != confirmPassword)) currentErros.push('Passwords do not match.')

        if(currentErros.length > 0) {
            setValidationErrors(currentErros);
        } else {
            handleSubmit();
        };
        
    }

    const handleSubmit = () => {
        console.log('Arrives here')
        if(!isCreateView) {
            console.log('Arrives here 1')
            mutateCreation({username, password}, {
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
        } else {
            console.log('Arrives here 2')
            mutateRegister({username, email, password, role: 'COMMON'}, {
                onSuccess: (data) => {
                    console.log('User created successufully :', data);
                    userNavigate(0);
                },
                onError: (error) => {
                    console.error('Creation failed:', error.message);
                }
            })
        }
    }

    return (
        <>
        <div className='input-box'>
        <h1>{isCreateView ? 'Create Account' : 'Login'}</h1>
        <p>Let's get started with your tasks</p>
        <form action="" method="post" onSubmit={validateSubmit}>
            <input
                type="text"
                className='login-field' 
                placeholder='User'
                onChange={e => setUsername(e.target.value)}
                value={username}
            />
            {isCreateView && (
                <input
                    type="email"
                    className='login-field' 
                    placeholder='Email'
                    onChange={e => setEmail(e.target.value)}
                    value={email}
                />
            )}
            <input
                type="password"
                className='login-field'
                placeholder='Password'
                onChange={e => setPassword(e.target.value)}
                value={password}
            />
            {isCreateView && (
                <input
                    type="password"
                    className='login-field'
                    placeholder='Confirm Password'
                    onChange={e => setConfirmPassword(e.target.value)}
                    value={confirmPassword}
                />
            )}

            <button type="submit" className='create-account-button login-field'>
                {isCreateView ? 'Create Account' : (isPending ? 'Logging in...' : 'Login')}
            </button>
            
            {isError && <p style={{ color: 'red' }}>{error.message}</p>}
            {validationErrors.length > 0 && <ModalValidate errors={validationErrors} onCloseModal={onCloseModal}/>}
        </form>
        </div>
        </>
    )
}