var Helpers = Helpers || {};

Helpers.validPhone = function (phone) {
    return /^[\+]?(?:[(][0-9]{1,3}[)]|(?:84|0))[0-9]{9,10}$/im.test(phone);
}

Helpers.uploadFile = function (url, data, callback) {
    $.ajax({
        url: url,
        cache: false,
        contentType: false,
        processData: false,
        data: data,
        type: 'POST',
        success: function (result) {
            callback(result);
        }
    });
}

Helpers.post = function (url, data, callback) {
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        success: function (result) {
            callback(result);
        }
    });
}

Helpers.uploadAvatar = function (input) {
    if (input.files &&
        input.files.length > 0 &&
        input.files[0].size > 0 &&
        (input.files[0].type == 'image/jpeg' ||
            input.files[0].type == 'image/png' ||
            input.files[0].type == 'image/jpg'))
    {
        let file_data = input.files[0];
        let form_data = new FormData();
        form_data.append('file', file_data);
        Helpers.uploadFile('/uploadAvatar', form_data, function (result) {
            if (result.status == 1){
                $('#avatar_header').attr('src', result.url + '?' + new Date().getTime())
            }
        });
    }else
        ToastPush.push('', 'Avatar', 'Tập tin không hợp lệ.!!');
}

