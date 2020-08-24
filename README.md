# Heartbeat-API

### Introduction


### Instruction to run
```shell script
mvn spring-boot:run
```

### Flyway
(1) clean from start
```shell script
mvn flyway:clean
```
 
 
### Deploy instructions
(1) sudo systemctl stop heartbeat-api.service

(2) git pull

(3) mvn clean install

(4) sudo systemctl start heartbeat-api.service


### Read logs
(1) journalctl -f -u heartbeat-api


### Fitbit OAuth2 Configuration

callback_uri = https://app.getpostman.com/oauth2/callback

auth_uri = https://www.fitbit.com/oauth2/authorize

access_token_uri = https://api.fitbit.com/oauth2/token


### License
Copyright 2019 Jun Huh 

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

```shell script
http://www.apache.org/licenses/LICENSE-2.0
```

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

