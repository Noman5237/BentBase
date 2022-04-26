import React from 'react';
import { Route, Routes } from 'react-router-dom';

import { Home as SellerHome } from './seller';
import Profile from './seller/profile.component';

const App = () => (
	<div className="App">
		<Profile/>
	</div>
);

export default App;
