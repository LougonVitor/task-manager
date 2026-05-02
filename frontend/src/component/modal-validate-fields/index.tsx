import './style.css';

interface ModalValidateProps {
    errors: string[];
    onCloseModal: () => void
}

export function ModalValidate({ errors, onCloseModal }: ModalValidateProps) {
    return (
        <>
        <div className="error-container">
            <div className="error-header">
                <div style={{ display: "flex", alignItems: "center", gap: "8px" }}>
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#b91c1c" strokeWidth="2">
                        <circle cx="12" cy="12" r="10"></circle>
                        <line x1="12" y1="8" x2="12" y2="12"></line>
                        <line x1="12" y1="16" x2="12.01" y2="16"></line>
                    </svg>
                    Please correct the following errors:
                </div>
                
                {/* Close Button */}
                <button 
                    className="error-close-btn" 
                    onClick={() => onCloseModal()}
                    aria-label="Close error banner"
                >
                    ✕
                </button>
            </div>
            
            <ul className="error-list">
                {errors
                    .filter(error => error && error.trim() !== "")
                    .map((error, index) => (
                        <li key={index}>{error}</li>
                    ))
                }
            </ul>
        </div>
        </>
    )
}