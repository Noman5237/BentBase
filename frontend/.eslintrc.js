module.exports = {
	env: {
		browser: true,
		es2021: true,
	},
	extends: [
		// 'eslint:recommended',
		// 'plugin:react/recommended',
		// 'plugin:react-hooks/recommended',
		// 'plugin:prettier/recommended',
	],
	parserOptions: {
		ecmaFeatures: {
			jsx: true,
		},
		ecmaVersion: 'latest',
		sourceType: 'module',
	},
	plugins: ['react'],
	rules: {
		// 'prettier/prettier': 'error',
		// 'react/function-component-definition': [
		// 	2,
		// 	{ namedComponents: 'arrow-function' },
		//	],
	},
};
