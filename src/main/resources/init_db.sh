#!/bin/sh
psql postgres -c "CREATE USER trancheck_user WITH PASSWORD 'trancheck_pass' SUPERUSER"
psql postgres -c "CREATE DATABASE trancheck OWNER trancheck_user"
psql trancheck -c "CREATE TABLE IF NOT EXISTS transactions (
	id INT PRIMARY KEY NOT NULL,
	amount NUMERIC(19,2) NOT NULL,
	data json
)"
psql trancheck -c "insert into transactions (id, amount, data) values 
(123, 100.05, '{\"a\":1,\"b\":2}'::json),
(124, 150.75, '{\"a\":10,\"b\":20}'::json),
(125, 1010.00, '{\"a\":20,\"b\":30}'::json),
(126, 15.5, '{\"a\":30,\"b\":40}'::json);"