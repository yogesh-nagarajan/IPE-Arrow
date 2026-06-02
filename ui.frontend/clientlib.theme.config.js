const path = require('path');
const brands = require('./config/brands');

const THEME_DIST = path.join(__dirname, 'dist', 'themes');
const CLIENTLIB_DIR = path.join(
  __dirname,
  '..',
  'ui.apps',
  'src',
  'main',
  'content',
  'jcr_root',
  'apps',
  'ipe-arrow',
  'clientlibs'
);

module.exports = {
  context: THEME_DIST,
  clientLibRoot: CLIENTLIB_DIR,
  libs: brands.map(brand => ({
    name: brand.clientlibName,
    allowProxy: true,
    categories: [brand.category],
    serializationFormat: 'xml',
    cssProcessor: ['default:none', 'min:none'],
    jsProcessor: ['default:none', 'min:none'],
    assets: {
      css: [`${brand.id}/${brand.id}.css`]
    }
  }))
};
