import { Plus } from 'lucide-react';
import './style.css';
import type { Task } from '../../interface/task';
import type { TaskRequest } from '../../interface/taskRequest';
import { useCreateTask } from '../../hook/useCreateTask';
import { useDeleteTask } from '../../hook/useDeleteTask';
import { useUpdateTask } from '../../hook/useUpdateTask';
import React, { useState } from 'react';

interface TaskModalProps {
  task?: Task | null;
  onClose: () => void;
  isCreateModal: boolean;
  isDeleteModal: boolean;
}

export function TaskModal({ task, onClose, isCreateModal, isDeleteModal}: TaskModalProps) {
  const { mutate, isPending } = useCreateTask();
  const { mutate: deleteTask, isPending: isDeleting } = useDeleteTask();
  const { mutate: updateTask, isPending: isEditing } = useUpdateTask();

  const [formData, setFormData] = useState<TaskRequest>({
    title: task?.title,
    description: task?.description,
    status: task?.isCompleted ? 'completed' : 'in_progress',
    deadline: task?.deadline.toString(),
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement| HTMLSelectElement>) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (isCreateModal) {
      mutate(formData, {
        onSuccess: (data) => {
          console.log(data.createdAt.toString());
          onClose();
        },
        onError: (error) => console.error(error.message)
      });
    }

    else if (isDeleteModal && task?.id) {
      deleteTask(task.id, {
        onSuccess: () => {onClose(); console.log("Modal closing");},
        onError: (error) => console.error(error.message)
      });
    }

    else if (task) {
      console.log("Está chegando aqui")

      const taskRequeste : Task = {
        id: task.id,
        title: formData.title || 'Untitled Task',
        description: formData.description || '',
        isCompleted: false,
        deadline: formData.deadline ? new Date(formData.deadline) : new Date()
      }

      updateTask(taskRequeste, {
        onSuccess: () => {onClose(); console.log("Sucesso")},
        onError: (error) => console.error(error.message)
      });
    }
  };

  return (
    <>
    <div className="modal-overlay">
      <div className="modal-content">
        <div className="modal-header">
          <h3 className="modal-title">
            {isDeleteModal ? 'Confirm Deletion' : (isCreateModal ? 'Add New Task' : 'Edit Task')}
          </h3>
        </div>

        <form className="task-form" onSubmit={handleSubmit}>
          <div className="input-group">
            <label>Title</label>
            <input
              name='title'
              type="text"
              placeholder="Enter task title..."
              className="modal-input"
              {...isDeleteModal ? { disabled: true } : {}}
              value={formData.title}
              onChange={handleChange}
            />
          </div>
          {isDeleteModal ? 
          <></> 
          : 
          <>
          <div className="input-group">
            <label>Deadline</label>
            <input
              name='deadline'
              type="date"
              className="modal-input"
              value= {formData.deadline ? new Date(formData.deadline).toISOString().slice(0, 10) : ''}
              onChange={handleChange}
            />
          </div>

          <div className="input-group">
            <label>Description</label>
            <textarea
              name='description'
              placeholder="Description of the new task..."
              className="modal-input modal-textarea"
              value={formData.description}
              onChange={handleChange}
            />
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
              {isCreateModal && isPending
              ? 'Saving...'
              : isDeleteModal && isDeleting
              ? 'Deleting...'
              : !isCreateModal && !isDeleteModal && isEditing
              ? 'Editing...'
              : isDeleteModal
              ? 'Delete Task'
              : isCreateModal
              ? 'Add Task'
              : 'Edit Task'}
            </button>
          </div>
        </form>
      </div>
    </div>
    </>
  );
}