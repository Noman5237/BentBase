import React from 'react';
import styles from './profileStyles.css'; 
import logo from './images/logo.png'

const profile = () => {
return (
    <>
        <ul>
            <li>
            <div className="logo-image">
                <img src={logo}  className="img-thumbnail" class="center"/>
            </div>
            </li>
            <li><a className="active" href="#home">Home</a></li>
            <li><a href="/Gigs">Gigs</a></li>
            <li><a href="#">Earning</a></li>
            <li><a href="#">Orders</a></li>
            <li><a href='#'>Reviews</a></li>
        </ul>
        <div className='container emp-profile' class='center'>
            <form method="">
                <div className='row'>
                    <div className='col-md-4'>
                        <img src= "https://indiahikes.com/wp-content/uploads/2020/11/Nik-Passport-Size-Photo.jpg"  alt="Noman" />
                    </div>

                    <div className="col-md-6">
                        <div className="profile-head">
                            <h2>Abdullah Al Noman</h2>
                            <h3>Web Developer</h3>
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
