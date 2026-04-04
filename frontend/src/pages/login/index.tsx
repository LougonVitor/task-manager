import { useState } from 'react';
import loginImage from '../../assets/login_imgs/login_image.png'; // Add extension
import './style.css'
import { useLoginAuth } from '../../hook/useLoginAuth';

export function Login() {
    const {mutate, isPending, isError, error} = useLoginAuth();
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        mutate({ username, password },  {
            onSuccess: (data) => {
                console.log('Login successful:', data.token);
                // Handle successful login, e.g., store token, redirect, etc.
            },
            onError: (error) => {
                console.error('Login failed:', error.message);
                // Handle login failure, e.g., show error message to user
            }
        });
    }

    return (
        <>
            <section className='login-page'>
                <div className="login-box">
                    <div className='info-login-box'>

                        <div className='centrilizing-login-box'>
                            <h1>Create Account</h1>

                            <div className='input-box'>
                                <p>Let's get started with your tasks</p>
                                <form action="" method="post" onSubmit={handleSubmit}>
                                    <input
                                        type="text"
                                        className='login-field' 
                                        placeholder='User'
                                        onChange={(e) => setUsername(e.target.value)}
                                        value={username}
                                        required
                                    />
                                    <input
                                        type="text"
                                        className='login-field'
                                        placeholder='Email'
                                        required
                                    />
                                    <input
                                        type="text"
                                        className='login-field'
                                        placeholder='Password'
                                        onChange={(e) => setPassword(e.target.value)}
                                        value={password}
                                        required
                                    />
                                    <button type="submit" className='create-account-button login-field'>
                                        {isPending ? 'Creating...' : 'Create Account'}
                                    </button>

                                    {isError && <p style={{ color: 'red' }}>{error.message}</p>}
                                </form>

                                <div className='line-separator'>
                                    <span></span>
                                    <p>OR</p>
                                    <span></span>
                                </div>

                                <button className='login-button'>Login</button>
                            </div>
                        </div>

                    </div>
                    <aside>
                        <img src={loginImage} alt="Test" />
                    </aside>
                </div>
            </section>
        </>
    )

}