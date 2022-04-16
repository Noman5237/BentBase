const fs = require("fs");
const path = require("path");
const { faker } = require("@faker-js/faker");
const SHA256 = require("crypto-js/sha256");
const Base64 = require("crypto-js/enc-base64");

const generateFirstName = faker.name.firstName;
const generateLastName = faker.name.lastName;
const generateDOB = () => faker.date.between("1975-01-01", "2000-01-01");

const seller = ({ cwd, output = "output.sql", limit = "10", seed }) => {
	if (!!!seed) {
		seed = 13;
	}
	faker.seed(seed);

	let data = "";
	for (let i = 0; i < limit; i++) {
		const firstName = generateFirstName();
		const lastName = generateLastName();
		const dob = generateDOB();
		const email = `${firstName}.${Base64.stringify(SHA256(`${lastName}.${i}`))
			.substring(0, 8)
			.replace(/\W/g, "a")}@gmail.com`;

		const sqlRow = `insert into seller (user_email) values('${email}');\n`;

		data += sqlRow;
	}

	fs.writeFileSync(path.resolve(cwd, output), data);
};

module.exports = seller;
