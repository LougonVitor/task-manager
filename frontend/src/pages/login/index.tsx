import './style.css'

export function Login() {
    return (
        <>
            <section className='login-page'>
                <div className="login-box">
                    <div className='info-login-box'>

                        <div className='centrilizing-login-box'>
                            <h1>Create Account</h1>

                            <div className='input-box'>
                                <p>Let's get started with your tasks</p>
                                <form action="" method="post">
                                    <input type="text" className='login-field' placeholder='User'/>
                                    <input type="text" className='login-field' placeholder='Email'/>
                                    <input type="text" className='login-field' placeholder='Password'/>
                                    <button type="submit" className='create-account-button login-field'>CREATE ACCOUNT</button>
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

                    </aside>
                </div>
            </section>
        </>
    )
}