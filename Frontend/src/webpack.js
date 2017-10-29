module.exports = {
    entry: './js/nevsuite.app.root.js',
    output: {
        filename: 'nevsuite.bundle.js',
        path: './js'
    },
    module: {
        loaders: [
            {
                loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
                    presets: ['es2015', 'react']
                }
            }
        ]
    },
};