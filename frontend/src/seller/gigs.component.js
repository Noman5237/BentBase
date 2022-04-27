import React from 'react';
import logo from './images/logo.png';
import styles from './gigStyles.css';

const Gigs = () => {
return (
    <>
        <ul>
            <li>
            <div className="logo-image">
                <img src={logo}  className="img-thumbnail" class="center"/>
            </div>
            </li>
            <li><a className="active" href="#home">Home</a></li>
            <li><a href="./">Profile</a></li>
            <li><a href="#">Earning</a></li>
            <li><a href="#">Orders</a></li>
            <li><a href='#'>Reviews</a></li>
        </ul>
    <div class='center'>
        <h1> <bold>Gigs of Abdullah Al Noman </bold></h1>
    </div>    
        <div class="card">
            <div class="card-image"></div>
            <div class="card-text">
                <h2>Web Development</h2>
                <p>I will design responsive websites for you!</p>
            </div>
            <div class="card-stats">
                <div class="stat">
                <div class="value">36</div>
                <div class="type">Reviews</div>
                </div>
                <div class="stat border">
                <div class="value">45</div>
                <div class="type">Orders</div>
                </div>
                <div class="stat">
                <div class="value">4.50/5.00</div>
                <div class="type">Rating</div>
                </div>
            </div>
        </div>


    </>
)

}

export default Gigs;
