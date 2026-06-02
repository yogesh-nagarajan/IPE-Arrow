/**
 * Single source of truth for brand theme builds and AEM clientlibs.
 *
 * id: folder name under src/styles/brands/
 * clientlibName: folder under ui.apps/.../clientlibs/
 * category: AEM clientlib category (used in customheaderlibs.html)
 * pageTheme: value of page properties.theme in HTL
 */
module.exports = [
  {
    id: 'arrow',
    clientlibName: 'clientlib-arrow',
    category: 'ipe-arrow.arrow',
    pageTheme: 'arrow'
  },
  {
    id: 'ipe-arrow',
    clientlibName: 'clientlib-ipe-arrow',
    category: 'ipe-arrow.ipe',
    pageTheme: 'ipe'
  },
  {
    id: 'abc-arrow',
    clientlibName: 'clientlib-abc-arrow',
    category: 'ipe-arrow.abc',
    pageTheme: 'abc'
  }
];
