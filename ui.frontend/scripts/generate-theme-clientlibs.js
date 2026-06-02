const clientlib = require('aem-clientlib-generator');
const config = require('../clientlib.theme.config.js');

clientlib(
  config.libs,
  {
    context: config.context,
    clientLibRoot: config.clientLibRoot,
    verbose: true
  },
  () => {
    console.log('Brand theme clientlibs generated.');
  }
);
