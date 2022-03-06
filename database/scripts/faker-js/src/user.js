const fs = require('fs');
const { faker } = require('@faker-js/faker');

const generateEmail = faker.internet.email;
const generateFirstName = faker.name.firstName;
const generateLastName = faker.name.lastName;
const generateDOB = () => faker.date.between('1975-01-01', '2000-01-01');

fs.writeFileSync()
for (let i = 0; i < 10; i++) {
	const email = generateEmail();
	const firstName = generateFirstName();
	const lastName = generateLastName();
	const dob = generateDOB();

	const sqlRow = `into "users" (email, first_name, last_name, dob) values(${email}, ${firstName}, ${lastName}, TO_DATE())`
}