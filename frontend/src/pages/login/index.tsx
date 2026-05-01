import { useState } from 'react';
import loginImage from '../../assets/login_imgs/login_image.png';
import { LoginModal } from '../../component/login-modal';
import './style.css'

export function Login() {
    const [isCreateView, setIsCreateView] = useState(false);

    return (
        <>
        <section className='login-page'>
            <div className="login-box">
                <div className='info-login-box'>
                    <div className='centrilizing-login-box'>

                        <LoginModal isCreateView={isCreateView} />
                        
                         <div className='line-separator'>
                            <span></span>
                            <p>or</p>
                            <span></span>
                        </div>

                        <button className='login-button' onClick={() => setIsCreateView(!isCreateView)}>
                            {isCreateView ? 'Login' : 'Create Account'}
                        </button>
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