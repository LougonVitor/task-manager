import { Plus, X } from 'lucide-react';
import './style.css';

interface CreateModalProps {
  onClose: () => void;
}

export function CreateModal({onClose}: CreateModalProps) {
  return (
    <>
    <div className="modal-overlay">
        <div className="modal-content">
          <div className="modal-header">
            <h3 className="modal-title">Add New Task</h3>
          </div>

          <form className="task-form" onSubmit={(e) => e.preventDefault()}>
            <div className="input-group">
              <label>Title</label>
              <input type="text" placeholder="Enter task title..." className="modal-input" />
            </div>

            <div className="input-group">
              <label>Deadline</label>
              <input type="date" className="modal-input" />
            </div>

            <div className="input-group">
              <label>Description</label>
              <textarea placeholder="Description of the new task..." className="modal-input modal-textarea" />
            </div>

            <div className="modal-footer">
              <button 
                type="button" 
                className="btn-cancel-link" 
                onClick={onClose}
              >
                Cancel
              </button>
              <button type="submit" className="btn-primary-teal">
                <Plus size={18} style={{ marginRight: '4px' }} />
                Add Task
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}