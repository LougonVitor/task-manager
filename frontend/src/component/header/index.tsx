import './style.css';

export function Header() {
  return (
    <>
    <header className="main-header">
      <div className="header-container">
        {/* Application Title */}
        <div className="app-title">My Tasks</div>

        {/* Navigation Links */}
        <nav className="header-nav">
          <a href="#tasks" className="nav-link active">Tasks</a>
        </nav>
      </div>
    </header>
    </>
  );
};