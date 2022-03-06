const fs = require("fs");
const { faker } = require("@faker-js/faker");
const path = require("path");

const generateEmail = faker.internet.email;
const generateFirstName = faker.name.firstName;
const generateLastName = faker.name.lastName;
const generateDOB = () => faker.date.between("1975-01-01", "2000-01-01");

const user = ({ cwd, output = "output.sql", limit = "10", seed = 0 }) => {
	faker.seed(seed);

	let data = "insert all\n";
	for (let i = 0; i < limit; i++) {
		const email = generateEmail();
		const firstName = generateFirstName();
		const lastName = generateLastName();
		const dob = generateDOB();

		const sqlRow = `into "user" (email, first_name, last_name, dob) values('${email}', q'[${firstName}]', q'[${lastName}]', TO_DATE('${dob.getFullYear()}-${
			dob.getMonth() + 1
		}-${dob.getDate()}', 'yyyy-mm-dd'))\n`;
		data += sqlRow;
	}
	data += "select * from dual;";

	fs.writeFileSync(path.resolve(cwd, output), data);
};

module.exports = user;