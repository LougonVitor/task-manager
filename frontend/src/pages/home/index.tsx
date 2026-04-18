import './style.css';
import { Plus, Search } from 'lucide-react';
import { useState, useMemo } from 'react';
import { Header } from '../../component/header';
import { TaskCard } from '../../component/task-card';
import { TaskModal } from '../../component/task-modal';
import type { Task } from '../../interface/task';
import { useTaskData } from '../../hook/useTaskData';
import { updateTaskStatus } from '../../hook/useUpdateTaskStatus';

export function Home() {
  const { data, refetch } = useTaskData();

  const [searchQuery, setSearchQuery] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isCreateModal, setIsCreateModal] = useState(true);
  const [isDeleteModal, setIsDeleteModal] = useState(false);
  const [selectedTask, setSelectedTask] = useState<Task | null>(null);

  const filters = ['completed', 'pending'] as const;

  // Memoize filtered results for performance
  const filteredTasks = useMemo(() => {
    return data?.filter(task => {
      const search = searchQuery.toLowerCase();
      const statusString = task.isCompleted ? 'completed' : 'pending';
      
      return (
        task.title.toLowerCase().includes(search) ||
        task.description.toLowerCase().includes(search) ||
        statusString.includes(search)
      );
    });
  }, [data, searchQuery]);

  // Split tasks into categories once per render
  const pendingTasks = filteredTasks?.filter(t => !t.isCompleted);
  const completedTasks = filteredTasks?.filter(t => t.isCompleted);

  const handleFilterButtonClick = (filter: typeof filters[number]) => {
    setSearchQuery(prev => (prev === filter ? '' : filter));
  };

  const handleStatusToggle = async (id: number) => {
    try {
      await updateTaskStatus(id);
      refetch(); // Refresh data after status update
    } catch (error) {
      console.error("Error updating task status:", error);
    }
  };

  const handleAddTask = () => {
    setIsDeleteModal(false); 
    setIsCreateModal(true); 
    setIsModalOpen(true);
    setSelectedTask(null);
  }

  const handleEditTask = (task: Task) => {
    setIsDeleteModal(false);
    setIsCreateModal(false);
    setIsModalOpen(true);
    setSelectedTask(task);
  }

  const handleDeleteTask = (task: Task) => {
    setIsDeleteModal(true);
    setIsModalOpen(true);
    setSelectedTask(task);
  }

  // Helper to render sections (Keeps JSX clean)
  const renderTaskSection = (title: string, list: Task[] | null) => {
    if (list?.length === 0) return null;

    return (
      <section className="task-section">
        <div className="section-header">
          <h2>{title}</h2>
          <span className="order-by">Order by: date</span>
        </div>
        {list?.map(task => (
          <TaskCard
            task={task}
            onStatusChange={() => handleStatusToggle(task.id)}
            onEdit={() => handleEditTask(task)}
            onDelete={() => handleDeleteTask(task)}
          />
        ))}
      </section>
    );
  };

  return (
    <>
      <Header />

      <div className="container">
        <div className="header-actions">
          <div className="input-wrapper">
            <input type="text" placeholder="Search tasks..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)} />
            <button className="add-btn" aria-label="Search">
              <Search size={20} />
            </button>
          </div>

          <button className="add-task-button" onClick={() => handleAddTask()} >
            <Plus size={20} />
            <span>Add New Task</span>
          </button>

          <div className="filter-group">
            {filters.map((filter) => (
              <button
                key={filter}
                className={`filter-btn ${searchQuery === filter ? 'active' : ''}`}
                onClick={() => handleFilterButtonClick(filter)}
              >
                {filter.charAt(0).toUpperCase() + filter.slice(1)}
              </button>
            ))}
          </div>
        </div>

        {renderTaskSection("Completed", completedTasks ? completedTasks : null)}
        {renderTaskSection("To do", pendingTasks ? pendingTasks : null)}


      </div>

      {isModalOpen && (
        <TaskModal
          task={selectedTask}
          onClose={() => setIsModalOpen(false)}
          isCreateModal={isCreateModal}
          isDeleteModal={isDeleteModal}
        />
      )}
    </>
  );
}