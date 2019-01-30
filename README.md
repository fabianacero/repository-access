# REPOSITORY CONNECTION EXAMPLES

## Create a MongoDB User
```mongodb
use admin
db.createUser({user:'admin',pwd:'mypass',roles:[{role: 'userAdminAnyDatabase', db:'admin'}]});
```
## Enable autentication
```mongodb
auth = true
setParameter = enableLocalhostAuthBypass=0

use sample
db.createUser({ user: "admin", pwd: "mypass", roles: ["readWrite"] })
```

## Creating collection an documents
```mongodb
db.customers.insert([{id: 1, name: "Gonzalo"},{id:2, name: "Laura"}]);
```

## Redis Connection

Access to redis.conf and enabled password

```redis
requirepass Ch4ng3m3
```