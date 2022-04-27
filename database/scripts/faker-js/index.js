#!/usr/bin/env node
const fs = require("fs");
const { Command } = require("commander");
const { user, seller } = require("./src");
const path = require("path");

const packageOptions = JSON.parse(fs.readFileSync(path.join(__dirname, "package.json"), "utf-8"));

const program = new Command();

program.version(packageOptions.version);

program
	.command("user")
	.option("--output [output]", "output file name")
	.option("--limit [limit]", "limit of data")
	.option("--seed [seed]", "seed of randomness")
	.action(({ output, limit, seed }) => {
		user({ cwd: process.cwd(), output, limit, seed: parseInt(seed) });
	});

program
	.command("seller")
	.option("--output [output]", "output file name")
	.option("--limit [limit]", "limit of data")
	.option("--seed [seed]", "seed of randomness")
	.action(({ output, limit, seed }) => {
		seller({ cwd: process.cwd(), output, limit, seed: parseInt(seed) });
	});
program.parse();
