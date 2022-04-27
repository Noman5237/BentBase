import React from 'react';
import "./Gig.css";
import InfoIcon from '@mui/icons-material/Info';
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';

function Gig() {

  const newArticle = (heading  , subtitle) => (
    <div className="gigs__article">
      <div className="gigs__articleLeft">
        <FiberManualRecordIcon />
      </div>

      <div className="gigs__articleRight">
        <h4>{heading}</h4>
        <p>{subtitle}</p>
      </div>

    </div>
  );

  return (
    <div className="gigs">
      <div className="gigs__header">
      <h2>Gigs</h2>
      <InfoIcon />
      </div>
      {newArticle("Logo Designer" , 'I can design any sort of logo for your major brand')}
      {newArticle("Software Engineer" , 'I can manage any sort of ERP software for your major workspace')}
      {newArticle("Algorithm Expert" , 'I am fully professional in any sort of Algorithmic Issues')}
      
    </div>
  );
}

export default Gig;