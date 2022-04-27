import React from 'react';
import styles from './profileStyles.css'; 
import logo from './images/logo.png'

const profile = () => {
return (
    <>
        <div className='container emp-profile'>
        <ul>
            <li>
            <div class="logo-image">
                <img src={logo}  class="img-fluid"/>
            </div>
            </li>
            <li><a class="active" href="#home">Home</a></li>
            <li><a href="#">Gigs</a></li>
            <li><a href="#">Earning</a></li>
            <li><a href="#">Orders</a></li>
        </ul>

            <form method="">
                <div className='row'>
                    <div className='col-md-4'>
                        <img src= "https://indiahikes.com/wp-content/uploads/2020/11/Nik-Passport-Size-Photo.jpg"  alt="Noman" />
                    </div>

                    <div className="col-md-6">
                        <div className="profile-head">
                            <h5>Abdullah Al Noman</h5>
                            <h6>Web Developer</h6>
                            <p className='profile-rating mt-3 mb-5'>Rating <span> 1/10 </span></p>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </>
)

}

export default profile;
