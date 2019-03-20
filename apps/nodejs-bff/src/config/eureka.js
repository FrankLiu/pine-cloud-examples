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
  requestMiddleware: (requestOpts, done) => {
      requestOpts.auth = {
        user: 'admin',
        password: '123456',
      };
      done(requestOpts);
  },
  eureka: {
    fetchRegistry: true,
    host: '10.200.151.6',
    port: 3000,
    servicePath: '/eureka/apps/',
    ssl: false,
    useDns: false,
    fetchMetadata: false,
    preferIpAddress: true,
  },
};

