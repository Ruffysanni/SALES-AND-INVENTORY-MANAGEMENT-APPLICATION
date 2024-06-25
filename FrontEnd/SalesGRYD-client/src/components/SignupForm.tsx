import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/scss/bootstrap.scss';
import '../App.css';
import Navbar2 from './Navbar2';

const SignupForm: React.FC = () => {
    return (
        <><div>
            <Navbar2 />
        </div><div className="auth-page">
            <div className="card card-container">
                <div className="card-body">
                    <h4 className="card-title text-center">Sign Up</h4>
                    <div className="form-group">
                        <input type="text" className="form-style" placeholder="Full Name" />
                        <i className="input-icon uil uil-user"></i>
                    </div>
                    <div className="form-group mt-2">
                        <input type="text" className="form-style" placeholder="Email" />
                        <i className="input-icon uil uil-at"></i>
                    </div>
                    <div className="form-group mt-2">
                        <input type="password" className="form-style" placeholder="Password" />
                        <i className="input-icon uil uil-lock-alt"></i>
                    </div>
                    <a href="#" className="btn btn-primary mt-4 w-100">Submit</a>
                </div>
            </div>
        </div></>
    );
};

export default SignupForm;
