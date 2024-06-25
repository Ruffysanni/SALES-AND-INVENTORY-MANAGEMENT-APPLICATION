import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/scss/bootstrap.scss';
import '../App.css';
import React from 'react';
import Navbar from './Navbar';

const LandingPage: React.FC = () => {
    return (
        <div className="landing-page">
            <Navbar />
            <header className="hero-section">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6">
                            <h1 className="hero-title">Welcome to SalesGRYD</h1>
                            <p className="hero-subtitle">
                                Manage your sales efficiently and effectively
                            </p>
                            <a className="btn btn-primary" href="#">
                                Get Started
                            </a>
                        </div>
                        <div className="col-md-6">
                            <img src="https://via.placeholder.com/500" alt="Sales Management" className="img-fluid" />
                        </div>
                    </div>
                </div>
            </header>
            <section className="features-section text-center">
                <div className="container">
                    <h2>Key Features of SalesGRYD</h2>
                    <div className="row">
                        <div className="col-md-6">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Comprehensive Sales Dashboard</h3>
                                    <p className="card-text">
                                        Get a bird’s-eye view of your sales performance with our intuitive dashboard. Track key metrics such as sales targets, team performance, and pipeline health in real-time.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Lead and Contact Management</h3>
                                    <p className="card-text">
                                        Never miss an opportunity with our robust lead and contact management system. Easily import leads, track interactions, and nurture relationships through every stage of the sales cycle.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Automated Workflow</h3>
                                    <p className="card-text">
                                        Save time and eliminate manual tasks with our automated workflows. From lead assignment to follow-up reminders, SalesGRYD ensures nothing falls through the cracks.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Advanced Analytics and Reporting</h3>
                                    <p className="card-text">
                                        Make informed decisions with our powerful analytics and reporting tools. Generate detailed reports on sales activities, forecast future sales, and identify trends to optimize your strategy.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Mobile Access</h3>
                                    <p className="card-text">
                                        Stay connected on the go with SalesGRYD’s mobile app. Access your sales data, update records, and communicate with your team from anywhere, at any time.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Integration Capabilities</h3>
                                    <p className="card-text">
                                        Seamlessly integrate SalesGRYD with your existing CRM, email marketing tools, and other business applications. Enjoy a unified system that works for you.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6 offset-md-3">
                            <div className="card feature-card">
                                <div className="card-body">
                                    <h3 className="card-title">Customizable Interface</h3>
                                    <p className="card-text">
                                        Tailor SalesGRYD to fit your unique business needs. Customize fields, workflows, and reports to create a personalized experience that enhances your efficiency.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="content-section text-center">
                <div className="container">
                    <h2>Why Choose SalesGRYD?</h2>
                    <p>User-Friendly Design: Our intuitive interface ensures that your team can hit the ground running with minimal training.</p>
                    <p>Scalable Solutions: SalesGRYD grows with your business, offering scalable options to meet the needs of both small and large enterprises.</p>
                    <p>Dedicated Support: Our customer support team is here to assist you every step of the way, providing expert help whenever you need it.</p>
                </div>
            </section>
            <section className="blog-section text-center">
                <div className="container">
                    <h2>Customer Success Stories</h2>
                    <div className="testimonial">
                        <blockquote>
                            "SalesGRYD has transformed our sales process. The comprehensive dashboard and automated workflows have significantly boosted our team’s productivity and efficiency."
                        </blockquote>
                        <cite>— Sarah Mitchell, Sales Manager at Tech Innovators</cite>
                    </div>
                    <div className="testimonial">
                        <blockquote>
                            "With SalesGRYD, we've gained invaluable insights into our sales pipeline, allowing us to make strategic decisions that drive growth. It's an indispensable tool for our business."
                        </blockquote>
                        <cite>— John Rivera, CEO of GreenWave Solutions</cite>
                    </div>
                </div>
            </section>
            <section className="stats-section text-center">
                <div className="container">
                    <h2>Get Started with SalesGRYD Today!</h2>
                    <p>
                        Don’t let outdated sales processes hold you back. Take your sales management to the next level with SalesGRYD. Sign up for a free trial today and see the difference it can make for your business.
                    </p>
                </div>
            </section>
            <footer className="footer-section text-center">
                <div className="container">
                    <h2>Contact Us</h2>
                    <p>Website: <a href="http://www.salesgryd.com">www.salesgryd.com</a></p>
                    <p>Email: <a href="mailto:support@salesgryd.com">support@salesgryd.com</a></p>
                    <p>Phone: 1-800-SALES-GRYD</p>
                    <p>
                        Follow Us on Social Media: <br />
                        LinkedIn: <a href="http://linkedin.com">SalesGRYD on LinkedIn</a><br />
                        Twitter: <a href="http://twitter.com">@SalesGRYD</a><br />
                        Facebook: <a href="http://facebook.com">SalesGRYD on Facebook</a>
                    </p>
                    <p>
                        Empower your sales team, drive results, and achieve your business goals with SalesGRYD. Let's grow together!
                    </p>
                </div>
            </footer>
        </div>
    );
};

export default LandingPage;
