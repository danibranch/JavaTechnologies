let http = require('http');
let querystring = require('querystring');

const parameters = {
    letters: ['a', 'a', 'j', 'v']
};

const get_request_args = querystring.stringify(parameters);

const options = {
    hostname: 'localhost',
    port: 8081,
    path: '/demo1-1.0/hello?' + get_request_args,
    method: 'GET',
    headers: {
        'User-Agent': "Desktop app"
    }
};


http.get(options, res => {
    res.setEncoding('utf8');
    let rawData = '';
    res.on('data', (chunk) => {
        rawData += chunk;
        // console.log(chunk);
    });
    res.on('end', () => {
        try {
            console.log(rawData);
        } catch (e) {
            console.error(e.message);
        }
    });
}).on('error', (e) => {
    console.error(`Got error: ${e.message}`);
});