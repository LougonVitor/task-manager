import './style.css';
import { Plus, Search} from 'lucide-react';
import { useState } from 'react';
import { Header } from '../../component/header';
import { TaskCard } from '../../component/task-card';
import { TaskModal } from '../../component/task-modal';

export function Home() {
  const [tasks, setTasks] = useState([
    { id: 1, title: 'Integrate User Authentication', date: '22/11/2025', desc: 'Finalize the JWT token generation on the back-end...', completed: true },
    { id: 2, title: 'Integrate User Authentication', date: '22/11/2025', desc: 'Finalize the JWT token generation on the back-end...', completed: false },
    { id: 3, title: 'Buy Groceries for Dinner', date: '22/11/2025', desc: 'Milk, eggs, bread, and check the expiration date...', completed: false },
  ]);

  const [searchQuery, setSearchQuery] = useState('');

  const [isModalOpen, setIsModalOpen] = useState(false);

  const [isCreateModal, setIsCreateModal] = useState(true);

  const [isDeleteModal, setIsDeleteModal] = useState(false);

  const filteredTasks = tasks.filter(task =>
    task.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
    task.desc.toLowerCase().includes(searchQuery.toLowerCase()) ||
    (task.completed ? 'completed' : 'pending').includes(searchQuery.toLowerCase())
  );

  const filters = ['completed', 'pending'];

  const handleFilterButtonClick = (filter: typeof filters[number]) => {
    if(searchQuery === filter) {
      setSearchQuery('');
      return;
    } else {
      setSearchQuery(filter);
    }
  };

  return (
    <>
    <Header />

    <div className="container">
      <div className="header-actions">
        <div className="input-wrapper">
          {/* 3. Bind input to state and handle onChange */}
          <input 
            type="text" 
            placeholder="Search tasks..." 
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <button className="add-btn">
            <Search size={20} /> {/* Icon changed to Search */}
          </button>
        </div>
        <button className="add-task-button" onClick={() => { setIsDeleteModal(false); setIsCreateModal(true); setIsModalOpen(true); }}>
          <Plus size={20} />
          <span>Add New Task</span>
        </button>
        <div className="filter-group">
          {filters.map((filter) => (
          <button
            key={filter}
            // Check if this specific button is the one currently active
            className={`filter-btn ${
              searchQuery === filter 
                ? 'active' 
                : ''
            }`}
            onClick={() => handleFilterButtonClick(filter)}
          >
            {/* Capitalize first letter */}
            {filter.charAt(0).toUpperCase() + filter.slice(1)}
          </button>
          ))}
        </div>
      </div>
      {/* Completed Section */}
      {filteredTasks.some(t => t.completed) ?
      <section className="task-section">
        <div className="section-header">
          <h2>Completed</h2>
          <span className="order-by">Order by: date</span>
        </div>
        {filteredTasks.filter(t => t.completed).map(task => (
          <TaskCard 
            id={task.id}
            title={task.title}
            date={task.date}
            description={task.desc}
            status={task.completed}
            onStatusChange={() => {setTasks(tasks.map(t => t.id == task.id ? { ...t, completed: !t.completed } : t))}}
            onEdit={() => { setIsDeleteModal(false); setIsCreateModal(false); setIsModalOpen(true); }}
            onDelete={() => { setIsDeleteModal(true); setIsModalOpen(true); }}
          />
        ))}
      </section>: <></>
      }
      {/* To Do Section */}
      {filteredTasks.some(task => !task.completed) ?
      <section className="task-section">
        <div className="section-header">
          <h2>To do</h2>
          <span className="order-by">Order by: date</span>
        </div>
        {filteredTasks.filter(t => !t.completed).map(task => (
            <TaskCard 
              id={task.id}
              title={task.title}
              date={task.date}
              description={task.desc}
              status={task.completed}
              onStatusChange={() => {setTasks(tasks.map(t => t.id == task.id ? { ...t, completed: !t.completed } : t))}}
              onEdit={() => { setIsDeleteModal(false); setIsCreateModal(false); setIsModalOpen(true); }}
              onDelete={() => {setIsDeleteModal(true); setIsModalOpen(true); }}
            />
        ))}
      </section> : <></>
      }
    </div>

    {/* --- MODAL UI --- */}
    {isModalOpen && (<TaskModal 
      onClose={() => setIsModalOpen(false)}
      isCreateModal={isCreateModal}
      isDeleteModal={isDeleteModal}/>)
    }
    </>
  );
};