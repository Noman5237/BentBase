import React from 'react';
import { Route, Routes } from 'react-router-dom';

import { Home as SellerHome } from './seller';
import Gigs from './seller/gigs.component';
import Profile from './seller/profile.component';

const App = () => (
	<div className="App">
		<Routes>
			<Route path="" element={<Profile/>} />
			<Route path="/Gigs" element={<Gigs/>} />
		</Routes>
	</div>
);

export default App;
