
import './App.css';
import Gig from './Gig';
import Header from './Header';
import Sidebar from './Sidebar';

function App() {
  return (
    <div className="app">

      {/* Header */}
      <Header />
      {/* App Body */}
      <div className="app__body">
        <Sidebar />
        <Gig />
      </div>
        {/* Side Bar */}
        {/* Feeds */}
        {/* Widgets */}


    </div>
  );
}

export default App;
