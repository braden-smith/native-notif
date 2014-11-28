var natnot =  {
    sendBasic: function(strContentTitle, strContentText, strContentTitleBig, strContentQR, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Natnot', // mapped to our native Java class
            'createBasic', // with this action name
            [{
                "contentTitle": strContentTitle,
                "contentText": strContentText,
                "contentTitleBig": strContentTitleBig,
                "contentQR": strContentQR
            }]
        );
    }
}
module.exports = natnot;
