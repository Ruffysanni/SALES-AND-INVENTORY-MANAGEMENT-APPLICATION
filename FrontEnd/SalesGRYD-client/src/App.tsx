import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import SignupForm from './components/SignupForm';
import ForgotPasswordForm from './components/ForgotPasswordForm';
import LandingPage from './components/LandingPage';

const App: React.FC = () => {
    return (
        <Router>
            <Routes>
                <Route path="/"  Component={LandingPage} />
                <Route path="/login" Component={LoginForm} />
                <Route path="/signup" Component={SignupForm} />
                <Route path="/forgot-password" Component={ForgotPasswordForm} />
            </Routes>
        </Router>
    );
};

export default App;
