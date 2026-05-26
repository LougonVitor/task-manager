import { useNavigate } from 'react-router-dom';
import './Header.css';

export function Header() {
    const navigate = useNavigate();

    const handleLogoff = () => {
        localStorage.removeItem('token');
        navigate('/');
    }

    return (
    <>
    <header className="main-header">
        <div className="header-container">
        {/* Application Title */}
        <div className="app-title">My Tasks</div>

        {/* Navigation Links */}
        <nav className="header-nav">
            <a href="#tasks" className="nav-link active">Tasks</a>
            <a className="nav-link logoff" onClick={(e) => {e.preventDefault(); handleLogoff();}}>Logoff</a>
        </nav>
        </div>
    </header>
    </>
    );
};