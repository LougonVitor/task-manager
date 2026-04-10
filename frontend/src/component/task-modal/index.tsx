import { Plus } from 'lucide-react';
import './style.css';

interface TaskModalProps {
  onClose: () => void;
  isCreateModal: boolean;
  isDeleteModal: boolean;
}

export function TaskModal({onClose, isCreateModal, isDeleteModal}: TaskModalProps) {
  return (
    <>
    <div className="modal-overlay">
      <div className="modal-content">
        <div className="modal-header">
          <h3 className="modal-title">
            {isDeleteModal ? 'Confirm Deletion' : (isCreateModal ? 'Add New Task' : 'Edit Task')}
          </h3>
        </div>

        <form className="task-form" onSubmit={(e) => e.preventDefault()}>
          <div className="input-group">
            <label>Title</label>
            <input type="text" placeholder="Enter task title..." className="modal-input" {...isDeleteModal ? { disabled: true } : {}}
            value={''}/>
          </div>
          {isDeleteModal ? 
          <></> 
          : 
          <>
          <div className="input-group">
            <label>Deadline</label>
            <input type="date" className="modal-input" value={''}/>
          </div>

          <div className="input-group">
            <label>Description</label>
            <textarea placeholder="Description of the new task..." className="modal-input modal-textarea" value={''} />
          </div>
          </>
          }

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
              {isDeleteModal ? 'Delete Task' : (isCreateModal ? 'Add Task' : 'Edit Task')}
            </button>
          </div>
        </form>
      </div>
    </div>
    </>
  );
}