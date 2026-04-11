import './style.css';
import { Plus, Search } from 'lucide-react';
import { useState, useMemo } from 'react';
import { Header } from '../../component/header';
import { TaskCard } from '../../component/task-card';
import { TaskModal } from '../../component/task-modal';

// Define a type for our Task for better TS support
interface Task {
  id: number;
  title: string;
  date: string;
  desc: string;
  completed: boolean;
}

export function Home() {
  const [tasks, setTasks] = useState<Task[]>([
    { id: 1, title: 'Integrate User Authentication', date: '22/11/2025', desc: 'Finalize the JWT token generation on the back-end...', completed: true },
    { id: 2, title: 'Integrate User Authentication', date: '22/11/2025', desc: 'Finalize the JWT token generation on the back-end...', completed: false },
    { id: 3, title: 'Buy Groceries for Dinner', date: '22/11/2025', desc: 'Milk, eggs, bread, and check the expiration date...', completed: false },
  ]);

  const [searchQuery, setSearchQuery] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isCreateModal, setIsCreateModal] = useState(true);
  const [isDeleteModal, setIsDeleteModal] = useState(false);

  const filters = ['completed', 'pending'] as const;

  // Memoize filtered results for performance
  const filteredTasks = useMemo(() => {
    return tasks.filter(task => {
      const search = searchQuery.toLowerCase();
      const statusString = task.completed ? 'completed' : 'pending';
      
      return (
        task.title.toLowerCase().includes(search) ||
        task.desc.toLowerCase().includes(search) ||
        statusString.includes(search)
      );
    });
  }, [tasks, searchQuery]);

  // Split tasks into categories once per render
  const pendingTasks = filteredTasks.filter(t => !t.completed);
  const completedTasks = filteredTasks.filter(t => t.completed);

  const handleFilterButtonClick = (filter: typeof filters[number]) => {
    setSearchQuery(prev => (prev === filter ? '' : filter));
  };

  const handleStatusToggle = (id: number) => {
    setTasks(prevTasks =>
      prevTasks.map(t => (t.id === id ? { ...t, completed: !t.completed } : t))
    );
  };

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
            key={task.id} // Essential for React reconciliation
            id={task.id}
            title={task.title}
            date={task.date}
            description={task.desc}
            status={task.completed}
            onStatusChange={() => handleStatusToggle(task.id)}
            onEdit={() => {
              setIsDeleteModal(false);
              setIsCreateModal(false);
              setIsModalOpen(true);
            }}
            onDelete={() => {
              setIsDeleteModal(true);
              setIsModalOpen(true);
            }}
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
            <input
              type="text"
              placeholder="Search tasks..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
            <button className="add-btn" aria-label="Search">
              <Search size={20} />
            </button>
          </div>

          <button 
            className="add-task-button" 
            onClick={() => { 
              setIsDeleteModal(false); 
              setIsCreateModal(true); 
              setIsModalOpen(true); 
            }}
          >
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
          onClose={() => setIsModalOpen(false)}
          isCreateModal={isCreateModal}
          isDeleteModal={isDeleteModal}
        />
      )}
    </>
  );
}