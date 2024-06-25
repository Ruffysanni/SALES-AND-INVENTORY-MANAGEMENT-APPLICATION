import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/scss/bootstrap.scss';
import '../App.css';
import Navbar2 from './Navbar2';

const ForgotPasswordForm: React.FC = () => {
    return (
        <><div>
            <Navbar2 />
        </div><div className="auth-page">
            <div className="card card-container">
                <div className="card-body">
                    <h4 className="card-title text-center">Forgot Password</h4>
                    <p className="text-center">Enter email address</p>
                    <div className="form-group">
                        <input type="text" className="form-style" placeholder="Your Email" />
                        <i className="input-icon uil uil-at"></i>
                    </div>
                    <a href="#" className="btn btn-primary mt-4 w-100">Submit</a>
                </div>
            </div>
        </div></>
    );
};

export default ForgotPasswordForm;
