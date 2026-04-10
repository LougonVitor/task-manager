import './style.css';
import { Check, Edit2, Trash2, Bookmark } from 'lucide-react';

export interface TaskProps {
  id: number;
  title: string;
  date: string;
  description: string;
  status: boolean;
  onStatusChange: () => void;
  onEdit: () => void;
  onDelete: () => void;
}

export function TaskCard({ id, title, date, description, status, onStatusChange, onEdit, onDelete }: TaskProps) {

  return (
      <>
        <div key={id} className="task-card">
          {status 
              ? <div className="status-icon-check"><Check size={24} color="#00a896" /></div> 
              : <div className="status-icon-bookmark"><Bookmark size={24} fill="#d4d782" color="#d4d782" /></div>
          }
          <div className="task-content">
              {status
                  ? <h3>{title} <span className="date-label">Completed at: {date}</span></h3>
                  : <h3>{title} <span className="date-label">Deadline: {date}</span></h3>
              }
            <p>{description}</p>
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