import React from 'react';
import { Route, Routes } from 'react-router-dom';

import { Home as SellerHome } from './seller';

const App = () => (
	<div className="App">
		<Routes>
			<Route path="/" element={<SellerHome />} />
		</Routes>
	</div>
);

export default App;
