import { useState } from 'react'
import { Login } from './pages/login'
import { Home } from './pages/home'
import { ProtectedRoute } from './component/ProtectedRoute'
import './App.css'
import { HashRouter, Routes, Route } from 'react-router-dom'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <HashRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={
          <ProtectedRoute>
            <Home />
          </ProtectedRoute>}
        />
      </Routes>
    </HashRouter>
    </>
  )
}

export default App