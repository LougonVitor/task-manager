import './style.css';
import { Plus, Search } from 'lucide-react';
import { TaskComponent } from '../../component/task';
import { Header } from '../../component/header';
import { useState } from 'react';

export function Home() {
  const tasks = [
    { id: 1, title: 'Integrate User Authentication', date: '22/11/2025', desc: 'Finalize the JWT token generation on the back-end...', completed: true },
    { id: 2, title: 'Integrate User Authentication', date: '22/11/2025', desc: 'Finalize the JWT token generation on the back-end...', completed: false },
    { id: 3, title: 'Buy Groceries for Dinner', date: '22/11/2025', desc: 'Milk, eggs, bread, and check the expiration date...', completed: false },
  ];

  const [searchQuery, setSearchQuery] = useState('');

  const filteredTasks = tasks.filter(task =>
    task.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
    task.desc.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <>
    <Header />

    <div className="container">
      {/* Header / Search Area */}
      {/* 
      <div className="header-actions">
        <div className="input-wrapper">
          <input type="text" placeholder="..." />
          <button className="add-btn"><Plus size={20} /></button>
        </div>
        <div className="filter-group">
          <span>All</span>
          <button className="filter-btn active">Completed</button>
          <span>Pending</span>
        </div>
      </div>
      */}
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
        
        <div className="filter-group">
          <span>All</span>
          <button className="filter-btn active">Completed</button>
          <span>Pending</span>
        </div>
      </div>

      {/* Completed Section */}
      <section className="task-section">
        <div className="section-header">
          <h2>Completed</h2>
          <span className="order-by">Order by: date</span>
        </div>
        {filteredTasks.filter(t => t.completed).map(task => (
          <TaskComponent id={task.id} title={task.title} date={task.date} description={task.desc} status={task.completed}/>
        ))}
      </section>

      {/* To Do Section */}
      <section className="task-section">
        <div className="section-header">
          <h2>To do</h2>
          <span className="order-by">Order by: date</span>
        </div>
        {filteredTasks.filter(t => !t.completed).map(task => (
            <TaskComponent id={task.id} title={task.title} date={task.date} description={task.desc} status={task.completed}/>
        ))}
      </section>
    </div>
    </>
  );
};