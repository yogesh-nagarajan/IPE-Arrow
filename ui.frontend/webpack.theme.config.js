const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const brands = require('./config/brands');

const THEME_DIST = path.join(__dirname, 'dist', 'themes');

const entry = brands.reduce((acc, brand) => {
  acc[brand.id] = path.join(
    __dirname,
    'src',
    'styles',
    'brands',
    brand.id,
    'entry.scss'
  );
  return acc;
}, {});

module.exports = {
  mode: 'production',
  entry,
  output: {
    path: THEME_DIST,
    filename: '[name]/[name].js'
  },
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: [
          MiniCssExtractPlugin.loader,
          {
            loader: 'css-loader',
            options: { url: false }
          },
          {
            loader: 'sass-loader',
            options: {
              sassOptions: {
                includePaths: [path.join(__dirname, 'src', 'styles')]
              }
            }
          }
        ]
      }
    ]
  },
  plugins: [
    new CleanWebpackPlugin(['dist/themes'], { root: __dirname }),
    new MiniCssExtractPlugin({
      filename: '[name]/[name].css'
    })
  ]
};
