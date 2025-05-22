import React, { useState } from 'react';
import Reader from './Reader';
import Admin from './Admin';

export default function App() {
  const [isAdmin, setIsAdmin] = useState(false);
  return (
    <div>
      <button onClick={() => setIsAdmin(!isAdmin)}>{isAdmin ? 'Switch to Public View' : 'Switch to Admin View'}</button>
      {isAdmin ? <Admin /> : <Reader />}
    </div>
  );
}
