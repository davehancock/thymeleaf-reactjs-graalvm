const path = require('path');

const CleanWebpackPlugin = require('clean-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const webpack = require('webpack');

const SERVER_OUTPUT_PATH = 'src/main/resources/public/generated/server';
const CLIENT_OUTPUT_PATH = 'src/main/resources/public/generated/client';


function serverComponentEntry(componentName) {

    return {
        mode: 'production',
        entry: `./components-src/${componentName}/${componentName}-server.js`,
        output: {
            filename: `${componentName}.js`,
            path: path.resolve(__dirname, SERVER_OUTPUT_PATH),
        },
        plugins: [
            new CleanWebpackPlugin([SERVER_OUTPUT_PATH]),
        ],
        module: {
            rules: [
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: 'babel-loader',
                },
                // TODO Do we really need these?? - These are required by the Comparison components only
                {
                    test: /\.svg$/,
                    loader: 'null-loader',
                },
                {
                    test: /\.(ttf|eot|woff2?)$/,
                    loader: 'null-loader',
                },
            ],
        },
    };
}

function clientComponentEntry(componentName) {

    return {
        mode: 'production',
        entry: `./components-src/${componentName}/${componentName}-client.js`,

        output: {
            filename: `${componentName}.js`,
            path: path.resolve(__dirname, CLIENT_OUTPUT_PATH),
        },

        plugins: [
            new CleanWebpackPlugin([CLIENT_OUTPUT_PATH]),
            new MiniCssExtractPlugin({filename: `${componentName}.css`})
        ],

        module: {
            rules: [
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: 'babel-loader',
                },
                {
                    test: /\.css$/,
                    use: [
                        MiniCssExtractPlugin.loader,
                        'css-loader'
                    ],
                },
                // TODO Do we really need these?? - These are required by the Comparison components only
                {
                    test: /\.svg$/,
                    loader: 'null-loader',
                },
                {
                    test: /\.(ttf|eot|woff2?)$/,
                    loader: 'null-loader',
                },
            ],
        }
    };
}

module.exports = [

    // SSR Entries
    serverComponentEntry("postcode"),
    serverComponentEntry("nav"),
    serverComponentEntry("comparison-table"),
    serverComponentEntry("comparison-widget"),

    // Client Entries
    clientComponentEntry('postcode'),
    clientComponentEntry('nav'),
    clientComponentEntry('comparison-table'),
    clientComponentEntry("comparison-widget"),

];
