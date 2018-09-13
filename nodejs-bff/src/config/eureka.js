'use strict';

module.exports = {
  instance: {
    instanceId: 'node-bff-service-01',
    app: 'node-bff-service',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    // preferIpAddress: true, // default is false and host will be used.
    // homePageUrl: 'http://localhost:4001/info',
    statusPageUrl: 'http://localhost:4001/info',
    // healthCheckUrl: 'http://localhost:4001/info',
    port: {
      '$': 4001,
      '@enabled': 'true',
    },
    vipAddress: 'node-bff-service', // Important, otherwise spring-apigateway cannot find instance of node-bff-service
    // secureVipAddress: 'node-bff-service',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    fetchRegistry: true,
    host: 'localhost',
    port: 1111,
    servicePath: '/eureka/apps/',
    ssl: false,
    useDns: false,
    fetchMetadata: false,
    preferIpAddress: true,
  },
};

