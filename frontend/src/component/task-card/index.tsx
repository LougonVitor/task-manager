import type { Task } from '../../interface/task';
import './style.css';
import { Check, Edit2, Trash2, Bookmark } from 'lucide-react';

export interface TaskProps {
  task: Task;
  onStatusChange: () => void;
  onEdit: () => void;
  onDelete: () => void;
}

export function TaskCard({ task, onStatusChange, onEdit, onDelete }: TaskProps) {

  return (
      <>
        <div key={task.id} className="task-card">
          {task.isCompleted 
              ? <div className="status-icon-check"><Check size={24} color="#00a896" /></div> 
              : <div className="status-icon-bookmark"><Bookmark size={24} fill="#d4d782" color="#d4d782" /></div>
          }
          <div className="task-content">
              {task.isCompleted
                  ? <h3>{task.title} <span className="date-label">Completed at: {task.completedAt?.toLocaleDateString()}</span></h3>
                  : <h3>{task.title} <span className="date-label">Deadline: {task.deadline.toLocaleDateString()}</span></h3>
              }
            <p>{task.description}</p>
          </div>
          <div className="action-icons">
            <button className="icon-circle" onClick={onStatusChange}>
              <Check size={16} />
              </button>
            <button className="icon-circle" onClick={onEdit}>
              <Edit2 size={16} />
            </button>
            <button className="icon-circle" onClick={onDelete}>
              <Trash2 size={16} />
            </button>
          </div>
        </div>
      </>
  );
}