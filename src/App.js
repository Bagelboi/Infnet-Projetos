import './App.css';
import GunList from './components/Market';
import SiteHeader from './components/Header';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <SiteHeader />
        <GunList />
      </header>
    </div>
  );
}

export default App;
