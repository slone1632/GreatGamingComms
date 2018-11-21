# GreatGamingComms
# Getting started
- Download maven https://maven.apache.org/download.cgi and install it https://maven.apache.org/install.html
- Clone the repository from git in some directory $SOME_DIRECTORY

# Packaging
```
cd $SOME_DIRECTORY\greatgaming-server
mvn package
```

# Letting server and client use this package as a dependency on your dev box
```
cd $SOME_DIRECTORY\greatgaming-server
mvn clean install
```