window.natnot = function(str, callback) {
        cordova.exec(callback, function(err) {
            callback('Nothing to echo.');
        }, "Natnot", "echo", [str]);
    };