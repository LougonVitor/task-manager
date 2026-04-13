import './style.css';
import { Plus, Search } from 'lucide-react';
import { useState, useMemo } from 'react';
import { Header } from '../../component/header';
import { TaskCard } from '../../component/task-card';
import { TaskModal } from '../../component/task-modal';
import type { Task } from '../../interface/task';

export function Home() {
  const [tasks, setTasks] = useState<Task[]>([
    { id: 1, title: 'Integrate User Authentication', deadline: new Date('2025-11-22'), description: 'Finalize the JWT token generation on the back-end...', isCompleted: true },
    { id: 2, title: 'Integrate User Authentication', deadline: new Date('2025-11-22'), description: 'Finalize the JWT token generation on the back-end...', isCompleted: false },
    { id: 3, title: 'Buy Groceries for Dinner', deadline: new Date('2025-11-22'), description: 'Milk, eggs, bread, and check the expiration date...', isCompleted: false },
  ]);

  const [searchQuery, setSearchQuery] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isCreateModal, setIsCreateModal] = useState(true);
  const [isDeleteModal, setIsDeleteModal] = useState(false);
  const [selectedTask, setSelectedTask] = useState<Task | null>(null);

  const filters = ['completed', 'pending'] as const;

  // Memoize filtered results for performance
  const filteredTasks = useMemo(() => {
    return tasks.filter(task => {
      const search = searchQuery.toLowerCase();
      const statusString = task.isCompleted ? 'completed' : 'pending';
      
      return (
        task.title.toLowerCase().includes(search) ||
        task.description.toLowerCase().includes(search) ||
        statusString.includes(search)
      );
    });
  }, [tasks, searchQuery]);

  // Split tasks into categories once per render
  const pendingTasks = filteredTasks.filter(t => !t.isCompleted);
  const completedTasks = filteredTasks.filter(t => t.isCompleted);

  const handleFilterButtonClick = (filter: typeof filters[number]) => {
    setSearchQuery(prev => (prev === filter ? '' : filter));
  };

  const handleStatusToggle = (id: number) => {
    setTasks(prevTasks =>
      prevTasks.map(t => (t.id === id ? { ...t, isCompleted: !t.isCompleted } : t))
    );
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
  const renderTaskSection = (title: string, list: Task[]) => {
    if (list.length === 0) return null;

    return (
      <section className="task-section">
        <div className="section-header">
          <h2>{title}</h2>
          <span className="order-by">Order by: date</span>
        </div>
        {list.map(task => (
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

        {renderTaskSection("Completed", completedTasks)}
        {renderTaskSection("To do", pendingTasks)}
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