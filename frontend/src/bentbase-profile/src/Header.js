import React from 'react'
import './Header.css'
import SearchIcon from '@mui/icons-material/Search'; 
import HeaderOption from './HeaderOption';
import HomeIcon from '@mui/icons-material/Home';
import SupervisorAccountIcon from '@mui/icons-material/SupervisorAccount';
import BusinessCenterIcon from '@mui/icons-material/BusinessCenter';
import ChatIcon from '@mui/icons-material/Chat';
import NotificationsIcon from '@mui/icons-material/Notifications';


function Header() {
  return (
    <div className="header">

        <div className="header__left">
            <img src="./images/output-onlinepngtools.png" alt="Bentbase" />

            <div className="header__search">
              <SearchIcon />
              <input type="text" placeholder="Search" />
            </div>

        </div>

        <div className="header__right">
            <HeaderOption Icon={HomeIcon} title="Home"/>
            <HeaderOption Icon={SupervisorAccountIcon} title="My Network"/>
            <HeaderOption Icon={BusinessCenterIcon} title="Jobs"/>
            <HeaderOption Icon={ChatIcon} title="Messaging"/>
            <HeaderOption Icon={NotificationsIcon} title="Notifications"/>
            <HeaderOption avatar="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlOpvj5vZZjUsUN8oQ3SbJSan-4W3o6cqUMOFTFj1ToPwDoPEzjJ2rH1WQhY_YJN_6_7s&usqp=CAU" title="me"/>
        </div>
    </div>
  )
}

export default Header