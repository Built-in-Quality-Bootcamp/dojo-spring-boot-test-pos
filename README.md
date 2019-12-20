# Getting Started

### Start selenium for e2e test on local
```
docker run --rm -d -p 4444:4444  -v $(PWD):/app selenium/standalone-chrome
```

### Start jenkins
```
mkdir -p .jenkins_home
docker run \
        --restart=always \
        --name jenkins \
        --user root \
        --publish 8090:8080 \
        --publish 8443:443 \
        --publish 50000:50000 \
        --volume $(pwd)/.jenkins_home:/var/jenkins_home \
        --volume /var/run/docker.sock:/var/run/docker.sock \
        -d \
        jenkinsci/blueocean:1.19.0
```
### Start sonarqube
```
 docker run \
  --restart=always \
  -d \
  --name sonarqube \
  -p 9000:9000 sonarqube
```
