#!
# important to put the & at the end of the command
# timing off otherwise and not all commands will execute
curl localhost:8082/fake/path/Greg/Addr1/Mazenko &

curl localhost:8082/fake/path/Gregory/Addr1/Mazenko &

curl -X POST localhost:8082/fake/path/send -d '{"firstName":"Greg", "address":"Addr1", "lastName":"Mazenko"}'&

curl -X POST localhost:8082/fake/path/publish -d '{"firstName":"Greg", "address":"Addr1", "lastName":"Mazenko"}'&

curl -X POST localhost:8082/fake/path/publish -d '{"firstName":"Greg", "address":"Addr2", "lastName":"Mazenko"}'&
