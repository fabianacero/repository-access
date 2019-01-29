## MONGODB
# Create a MongoDB User
use admin
db.createUser({user:'admin',pwd:'mypass',roles:[{role: 'userAdminAnyDatabase', db:'admin'}]});

# Enable autentication
auth = true
setParameter = enableLocalhostAuthBypass=0

use sample
db.createUser({ user: "admin", pwd: "mypass", roles: ["readWrite"] })


# Creatins collection an documents
db.customers.insert([{id: 1, name: "Gonzalo"},{id:2, name: "Laura"}]);