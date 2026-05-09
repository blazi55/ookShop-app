import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './component/App.css';
import { BuyPage } from './component/BuyPage.tsx';
import { FirstPage } from './component/FirstPage.tsx';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<FirstPage/>} />
          <Route path="/buy" element={<BuyPage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
