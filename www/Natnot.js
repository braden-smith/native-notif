var natnot =  {
    sendBasic: function(str, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Natnot', // mapped to our native Java class called "Calendar"
            'createBasic', // with this action name
            [str]
        );
    }
}
module.exports = natnot;

//window.natnot = function(str, callback) {
//        cordova.exec(callback, function(err) {
//            callback('Nothing to echo.');
//        }, "Natnot", "natnot", [str]);
//    };
