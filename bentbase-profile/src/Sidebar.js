import React from 'react';
import "./Sidebar.css";
import { Avatar } from '@mui/material';

function Sidebar() {

  const recentItem = (topic) => (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">#</span>
      <p>{topic}</p>
    </div>
  );



  return (
    <div className="sidebar">
         <div className="sidebar__top">
           <img src="https://images.unsplash.com/photo-1553095066-5014bc7b7f2d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8d2FsbCUyMGJhY2tncm91bmR8ZW58MHx8MHx8&w=1000&q=80 " alt="" />
           <Avatar className="sidebar__avatar" />
           <h2>Md.Sakibul Islam</h2>
           <h4>isakibul15@gmail.com</h4>
         </div>

         <div className="sidebar__stats">
           <div className="sidebar__stat">
              <p>Who viewed you</p>
              <p className="sidebar__statNumber">2,543</p>
           </div>
           <div className="sidebar__stat">
              <p>Views on post</p>
              <p className="sidebar__statNumber">2,448</p>
           </div>
         </div>

        <div className="sidebar_bottom">
          <p>Recent</p>
          
        {recentItem("react-js")}
        {recentItem("programming")}
        {recentItem("software engineering")}
        {recentItem("design")}
        {recentItem("developer")}
        </div>
    </div>

   
  );
}

export default Sidebar