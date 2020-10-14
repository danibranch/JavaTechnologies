let http = require('http');
let querystring = require('querystring');

let logs = [];

const parameters = querystring.stringify({letters: ['a', 'a', 'j', 'v']});
const options = {
    hostname: 'localhost',
    port: 8081,
    path: '/demo1-1.0/hello?' + parameters,
    method: 'GET',
    headers: {
        'User-Agent': "Desktop app"
    }
};

function sendRequests(totalNrOfRequests) {
    for (let i = 0; i < totalNrOfRequests; i++)
    {
        http.get(options, res => {
            const { statusCode } = res;
            let time = (new Date()).getTime();

            res.setEncoding('utf8');
            let rawData = '';
            res.on('data', (chunk) => { rawData += chunk; });
            res.on('end', () => {
                try {
                    const parsedData = rawData;
                    logs.push({
                        data: parsedData,
                        statusCode: statusCode,
                        responseTime: (new Date()).getTime() - time,
                        time: new Date()
                    });
                } catch (e) {
                    console.error(e.message);
            }
            });
        }).on('error', (e) => {
            console.error(`Got error: ${e.message}`);
        });
    }

    setTimeout(() => console.log(logs), 2500);
}

sendRequests(100);