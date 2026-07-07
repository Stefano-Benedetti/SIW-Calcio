import './App.css'
import TorneiPage from './pages/TorneiPage'
import SquadrePage from './pages/SquadrePage'

function App() {
  const path = window.location.pathname

  if (path.startsWith('/react/squadre')) {
    return <SquadrePage />
  }

  return <TorneiPage />
}

export default App