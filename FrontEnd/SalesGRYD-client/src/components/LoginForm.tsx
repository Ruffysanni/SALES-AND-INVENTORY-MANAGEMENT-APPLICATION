import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/scss/bootstrap.scss';
import '../App.css';
import Navbar2 from './Navbar2';

const LoginForm: React.FC = () => {
    return (
        <><div>
            <Navbar2 />
        </div><div className="auth-page">
            <div className="card card-container">
                <div className="card-body">
                    <h4 className="card-title text-center">Login</h4>
                    <div className="form-group">
                        <input type="text" className="form-style" placeholder="Your Email" />
                        <i className="input-icon uil uil-at"></i>
                    </div>
                    <div className="form-group mt-2">
                        <input type="password" className="form-style" placeholder="Your Password" />
                        <i className="input-icon uil uil-lock-alt"></i>
                    </div>
                    <a href="#" className="btn btn-primary mt-4 w-100">Submit</a>
                    <a href="/forgot-password" className="forgot-password-link mt-2 d-block text-center">Forgot Password?</a>
                </div>
            </div>
        </div></>
    );
};

export default LoginForm;