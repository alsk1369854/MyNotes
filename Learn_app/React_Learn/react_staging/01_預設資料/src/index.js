import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
// reportWebVitals 是一個React 性能測試工具
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  // 包在React.StrictMode標籤中, 會幫我們檢查內部程式碼的合理性, 例如:會提醒使用的方法即將放棄等等
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// reportWebVitals 是一個React 性能測試工具
reportWebVitals();
